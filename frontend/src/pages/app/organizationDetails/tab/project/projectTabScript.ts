import {defineComponent} from 'vue';
import {useOrganizationDetailsStore} from 'stores/organizationDetails';
import ProjectDto from 'src/controller/project/dto/in/ProjectDto';
import {Notify} from 'quasar';
import TrackedDialogTitle from 'components/TrackedDialogTitle.vue';

export default defineComponent({
  name: 'ProjectTabView',
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
      projectOriginals: {} as any
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

      this.projectOriginals = {};
      this.organizationDetailsStore.projects.content.forEach((project: ProjectDto) => {
        this.projectOriginals[project.id] = JSON.parse(JSON.stringify(project));
      });

      this.loading = false;
    },
    applyFilter() {
      this.filter = this.rawFilter;
    },
    hasBaseDataChanges(project: ProjectDto) {
      return JSON.stringify(this.projectOriginals[project.id]) !== JSON.stringify(project);
    },
    startProjectDeletion(id: string) {
      this.organizationDetailsStore.projectDeletion.id = id;
      this.modal.delete = true;
    },
    async deleteProject() {
      this.modal.delete = false;
      await this.organizationDetailsStore.deleteProject();
      this.organizationDetailsStore.projectDeletion.id = '';

      Notify.create({
        type: 'positive',
        message: this.$t('notification.project.deletion.success'),
      });

      await this.onRequest({
        pagination: this.pagination,
        filter: this.filter
      });
    },
    async updateProject(project: ProjectDto) {
      await this.organizationDetailsStore.updateProject(project);
      Notify.create({
        type: 'positive',
        message: this.$t('notification.project.update.success'),
      });
      this.projectOriginals = {};

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
    cancelProjectCreation() {
      this.organizationDetailsStore.resetOrganizationCreation();
      this.modal.create = false;
    },
    async createProject() {
      await this.organizationDetailsStore.createProject();
      this.organizationDetailsStore.resetOrganizationCreation();
      Notify.create({
        type: 'positive',
        message: this.$t('notification.project.creation.success'),
      });
      await this.onRequest({
        pagination: this.pagination,
        filter: this.filter
      });
      this.modal.create = false;
    },
  }
});
