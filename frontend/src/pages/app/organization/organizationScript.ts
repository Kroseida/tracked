import {defineComponent} from 'vue';
import TrackedDialogTitle from '../../../components/TrackedDialogTitle.vue';
import {useOrganizationStore} from "stores/organization";
import {Notify} from 'quasar'
import {TrackedError} from "src/controller/error/TrackedError";

export default defineComponent({
  name: 'OrganizationView',
  components: {
    TrackedDialogTitle
  },
  methods: {
    async createOrganization() {
      try {
        await this.organizationStore.createOrganization();
        this.organizationStore.resetOrganizationCreation();
        Notify.create({
          type: 'positive',
          message: this.$t('notification.organization.creation.success'),
        });
        await this.organizationStore.fetchOrganizations();
      } catch (error) {
        Notify.create({
          type: 'negative',
          message: this.$t('notification.organization.creation.' + (<TrackedError>error).message),
        });
      }
    },
    cancelCreateOrganization() {
      this.organizationStore.resetOrganizationCreation();
    },
    async deleteOrganization() {
      this.organizationStore.modal.delete = false;
      try {
        await this.organizationStore.deleteOrganization();
        this.organizationStore.resetOrganizationCreation();
        Notify.create({
          type: 'positive',
          message: this.$t('notification.organization.deletion.success'),
        });
        await this.organizationStore.fetchOrganizations();
      } catch (error) {
        Notify.create({
          type: 'negative',
          message: this.$t('notification.organization.deletion.' + (<TrackedError>error).message),
        });
      }
    },
    startOrganizationDeletion(id: string) {
      this.organizationStore.modal.delete = true;
      this.organizationStore.organizationDeletion.id = id;
    }
  },
  async mounted() {
    await this.organizationStore.fetchOrganizations();
  },
  data() {
    return {
      organizationStore: useOrganizationStore(),
      columns: [
        {
          name: 'name',
          required: true,
          label: this.$t('organization.name'),
          align: 'left',
          field: 'name',
          sortable: true
        },
        {
          name: 'isActive',
          required: true,
          label: this.$t('isActive'),
          align: 'left',
          field: 'active',
          boolean: true,
          sortable: true
        },
        {
          name: 'action',
          required: false,
          label: '',
          align: 'right',
          sortable: false
        },
      ]
    }
  }
});
