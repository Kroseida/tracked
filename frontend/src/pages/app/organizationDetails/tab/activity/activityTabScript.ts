import {defineComponent} from 'vue';
import {useOrganizationDetailsStore} from 'stores/organizationDetails';
import ActivityDto from 'src/controller/activity/dto/in/ActivityDto';
import {Notify} from 'quasar';
import TrackedDialogTitle from 'components/TrackedDialogTitle.vue';

export default defineComponent({
  name: 'ActivityTabView',
  components: {
    TrackedDialogTitle
  },
  data() {
    return {
      modal: {
        delete: false,
        create: false,
      },
      loading: false,
      filter: '',
      rawFilter: '',
      rawPage: 1,
      organizationDetailsStore: useOrganizationDetailsStore(),
      activeOptions: [
        {label: this.$t('active'), value: true},
        {label: this.$t('inactive'), value: false},
      ],
      columns: [
        {
          name: 'name',
          required: true,
          label: this.$t('activity.name'),
          field: 'name',
          align: 'left',
          sortable: true
        },
        {
          name: 'id',
          required: true,
          label: this.$t('activity.id'),
          field: 'id',
          align: 'left',
          sortable: true
        },
        {
          name: 'active',
          required: true,
          label: this.$t('activity.status'),
          field: 'active',
          align: 'left',
          sortable: true
        },
        {
          name: 'action',
          required: false,
          label: '',
          field: 'action',
          align: 'right',
          sortable: false
        },
      ],
      pagination: {
        sortBy: 'name',
        descending: false,
        page: 1,
        rowsPerPage: 10,
        rowsNumber: 0
      },
      activityOriginals: {} as any
    }
  },
  props: {
    organizationId: {
      type: String,
      required: true,
    }
  },
  async mounted() {
    await this.onRequest({
      pagination: this.pagination,
      filter: this.filter
    });
  },
  watch: {
    filter(data) {
      console.log(data);
    }
  },
  methods: {
    async onRequest(params: any) {
      this.loading = true;
      this.pagination = params.pagination;
      this.filter = params.filter;

      await this.organizationDetailsStore.fetchActivities(
        {
          sortBy: this.pagination.sortBy,
          descending: this.pagination.descending,
          page: this.pagination.page,
          rowsPerPage: this.pagination.rowsPerPage,
          filter: this.filter,
        }
      )
      this.pagination.rowsNumber = this.organizationDetailsStore.activities!.totalElements;

      this.activityOriginals = {};
      this.organizationDetailsStore.activities.content.forEach((activity: ActivityDto) => {
        this.activityOriginals[activity.id] = JSON.parse(JSON.stringify(activity));
      });

      this.loading = false;
    },
    applyFilter() {
      this.filter = this.rawFilter;
    },
    hasBaseDataChanges(activity: ActivityDto) {
      if (!activity.name) {
        return false;
      }
      return JSON.stringify(this.activityOriginals[activity.id]) !== JSON.stringify(activity);
    },
    startActivityDeletion(id: string) {
      this.organizationDetailsStore.activityDeletion.id = id;
      this.modal.delete = true;
    },
    async deleteActivity() {
      this.modal.delete = false;
      await this.organizationDetailsStore.deleteActivity();
      this.organizationDetailsStore.activityDeletion.id = '';

      Notify.create({
        type: 'positive',
        message: this.$t('notification.activity.deletion.success'),
      });

      await this.onRequest({
        pagination: this.pagination,
        filter: this.filter
      });
    },
    async updateActivity(activity: ActivityDto) {
      await this.organizationDetailsStore.updateActivity(activity);
      Notify.create({
        type: 'positive',
        message: this.$t('notification.activity.update.success'),
      });
      this.activityOriginals = {};

      await this.onRequest({
        pagination: this.pagination,
        filter: this.filter
      });
    },
    async onPageChange(data: any) {
      this.pagination.page = data;
      await this.onRequest({
        pagination: this.pagination,
        filter: this.filter
      });
    },
    cancelActivityCreation() {
      this.organizationDetailsStore.resetOrganizationCreation();
      this.modal.create = false;
    },
    async createActivity() {
      await this.organizationDetailsStore.createActivity();
      this.organizationDetailsStore.resetOrganizationCreation();
      Notify.create({
        type: 'positive',
        message: this.$t('notification.activity.creation.success'),
      });
      await this.onRequest({
        pagination: this.pagination,
        filter: this.filter
      });
      this.modal.create = false;
    },
  }
});
