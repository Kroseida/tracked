import {defineComponent} from 'vue';
import TrackedDialogTitle from 'components/TrackedDialogTitle.vue';
import {useOrganizationStore} from 'stores/organization';
import {Notify} from 'quasar'
import TrackedLoadable from 'components/TrackedLoadable.vue';

export default defineComponent({
  name: 'OrganizationView',
  components: {
    TrackedDialogTitle,
    TrackedLoadable
  },
  methods: {
    async createOrganization() {
      await this.organizationStore.createOrganization();
      this.organizationStore.resetOrganizationCreation();
      Notify.create({
        type: 'positive',
        message: this.$t('notification.organization.creation.success'),
      });
      await this.organizationStore.fetchOrganizations();
      this.modal.create = false;
    },
    cancelCreateOrganization() {
      this.organizationStore.resetOrganizationCreation();
    },
    async deleteOrganization() {
      this.modal.delete = false;
      await this.organizationStore.deleteOrganization();
      this.organizationStore.resetOrganizationCreation();
      Notify.create({
        type: 'positive',
        message: this.$t('notification.organization.deletion.success'),
      });
      await this.organizationStore.fetchOrganizations();
    },
    startOrganizationDeletion(id: string) {
      this.modal.delete = true;
      this.organizationStore.organizationDeletion.id = id;
    },
    openOrganizationDetails(id: string) {
      this.$router.push({name: 'organizationDetails', params: {id}});
    }
  },
  async mounted() {
    this.loading = true;
    this.organizationStore.$reset();
    await this.organizationStore.fetchOrganizations();
    this.loading = false;
  },
  data() {
    return {
      loading: false,
      organizationStore: useOrganizationStore(),
      modal: {
        create: false,
        delete: false
      },
    }
  }
});
