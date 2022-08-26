import {defineComponent} from 'vue';
import {useOrganizationDetailsStore} from 'stores/organizationDetails';

export default defineComponent({
  name: 'ProjectTabView',
  data() {
    return {
      loading: false,
      filter: '',
      rawFilter: '',
      organizationDetailsStore: useOrganizationDetailsStore(),
      columns: [
        {
          name: 'name',
          required: true,
          label: this.$t('project.name'),
          field: 'name',
          align: 'left',
          sortable: true
        },
        {
          name: 'id',
          required: true,
          label: this.$t('project.id'),
          field: 'id',
          align: 'left',
          sortable: true
        },
        {
          name: 'active',
          required: true,
          label: this.$t('project.status'),
          field: 'active',
          align: 'left',
          sortable: true
        },
      ],
      pagination: {
        sortBy: 'name',
        descending: false,
        page: 0,
        rowsPerPage: 5,
        rowsNumber: 0
      }
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
  methods: {
    async onRequest(params: any) {
      this.loading = true;
      this.pagination = params.pagination;
      this.filter = params.filter;
      await this.organizationDetailsStore.fetchProjects(
        {
          sortBy: this.pagination.sortBy,
          descending: this.pagination.descending,
          page: this.pagination.page,
          rowsPerPage: this.pagination.rowsPerPage,
          filter: this.filter,
        }
      )
      this.pagination.rowsNumber = this.organizationDetailsStore.projects!.totalElements;
      this.loading = false;
    },
    applyFilter() {
      this.filter = this.rawFilter;
    }
  }
});
