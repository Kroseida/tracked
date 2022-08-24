import {defineComponent} from 'vue';
import {useOrganizationDetailsStore} from 'stores/organizationDetails';
import {Notify} from 'quasar';
import {TrackedError} from 'src/controller/error/TrackedError';

export default defineComponent({
  name: 'OrganizationDetailsView',
  data() {
    return {
      activeOptions: [
        {label: this.$t('active'), value: true},
        {label: this.$t('inactive'), value: false},
      ],
      organizationId: this.$route.params.id as string,
      tab: 'base',
      organizationDetailsStore: useOrganizationDetailsStore(),
      organizationOriginal: '' as string,
    }
  },
  async mounted() {
    await this.organizationDetailsStore.fetchOrganization(this.organizationId);
    this.organizationOriginal = JSON.stringify(this.organizationDetailsStore.organization);
  },
  methods: {
    hasBaseDataChanges() {
      return this.organizationOriginal !== JSON.stringify(this.organizationDetailsStore.organization);
    },
    async updateOrganization() {
      try {
        await this.organizationDetailsStore.updateOrganization();
        Notify.create({
          type: 'positive',
          message: this.$t('notification.organization.update.success'),
        });
        await this.organizationDetailsStore.fetchOrganization(this.organizationId);
        this.organizationOriginal = JSON.stringify(this.organizationDetailsStore.organization);
      } catch (error) {
        Notify.create({
          type: 'negative',
          message: this.$t('notification.' + (<TrackedError>error).message),
        });
      }
    }
  }
});
