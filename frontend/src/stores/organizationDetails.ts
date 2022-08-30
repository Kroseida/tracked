import {defineStore} from 'pinia';
import {OrganizationControllerConnection, ProjectControllerConnection} from 'src/controller';
import OrganizationDto from 'src/controller/organization/dto/in/OrganizationDto';
import {Page, Pageable} from 'src/controller/Page';
import ProjectDto from 'src/controller/project/dto/in/ProjectDto';
import {TrackedError} from 'src/controller/error/TrackedError';
import ProjectCreationDto from 'src/controller/project/dto/out/ProjectCreationDto';

const organizationController = new OrganizationControllerConnection();
const projectController = new ProjectControllerConnection();

export const useOrganizationDetailsStore = defineStore('organizationDetails', {
  state: () => ({
    projectCreation: {
      name: '',
      description: '',
      active: true,
      startDate: '',
      endDate: '',
      organizationId: '',
    } as ProjectCreationDto,
    organization: undefined as OrganizationDto | undefined,
    projects: {content: [] as ProjectDto[]} as Page<ProjectDto>,
    projectDeletion: {
      id: '' as string
    }
  }),
  actions: {
    async fetchOrganization(id: string) {
      const organization = await organizationController.get(id);
      this.organization = organization;
    },
    async updateOrganization() {
      if (!this.organization) {
        throw new TrackedError('Organization not found');
      }
      await organizationController.patch(this.organization.id, {
        name: this.organization.name,
        description: this.organization.description,
        active: this.organization.active,
      });
    },
    async fetchProjects(pageable: Pageable) {
      if (!this.organization) {
        throw new TrackedError('Organization not found');
      }
      this.projects = await projectController.list(this.organization.id, pageable);
    },
    async deleteProject() {
      await projectController.delete(this.projectDeletion.id);
    },
    async updateProject(project: ProjectDto) {
      await projectController.patch(project.id, {
        name: project.name,
        description: project.description,
        active: project.active,
        startDate: project.startDate,
        endDate: project.endDate,
      });
    },
    resetOrganizationCreation() {
      this.projectCreation.name = '';
      this.projectCreation.description = '';
      this.projectCreation.startDate = '';
      this.projectCreation.endDate = '';
    },
    async createProject() {
      await projectController.create({
        name: this.projectCreation.name,
        description: this.projectCreation.description,
        startDate: this.projectCreation.startDate,
        endDate: this.projectCreation.endDate,
        active: true,
        organizationId: this.organization?.id || '',
      });
    },
  }
});
