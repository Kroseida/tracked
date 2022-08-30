import {defineComponent} from 'vue';
import {useOrganizationDetailsStore} from 'stores/organizationDetails';
import {Notify} from 'quasar';
import TrackedLoadable from 'components/TrackedLoadable.vue';

export default defineComponent({
  name: 'BaseTabView',
  data() {
    return {
      loading: false,
      activeOptions: [
        {label: this.$t('active'), value: true},
        {label: this.$t('inactive'), value: false},
      ],
      organizationDetailsStore: useOrganizationDetailsStore(),
      organizationOriginal: '' as string,
    }
  },
  components: {
    TrackedLoadable
  },
  props: {
    organizationId: {
      type: String,
      required: true,
    }
  },
  async mounted() {
    this.loading = true;
    this.organizationDetailsStore.$reset();
    await this.organizationDetailsStore.fetchOrganization(this.organizationId);
    this.organizationOriginal = JSON.stringify(this.organizationDetailsStore.organization);
    this.loading = false;
  },
  methods: {
    hasBaseDataChanges() {
      return this.organizationOriginal !== JSON.stringify(this.organizationDetailsStore.organization);
    },
    async updateOrganization() {
      await this.organizationDetailsStore.updateOrganization();
      Notify.create({
        type: 'positive',
        message: this.$t('notification.organization.update.success'),
      });
      await this.organizationDetailsStore.fetchOrganization(this.organizationId);
      this.organizationOriginal = JSON.stringify(this.organizationDetailsStore.organization);
    }
  }
});
