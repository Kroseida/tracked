import {defineStore} from 'pinia';
import {OrganizationControllerConnection, ProjectControllerConnection} from 'src/controller';
import OrganizationDto from 'src/controller/organization/dto/in/OrganizationDto';
import {Page, Pageable} from 'src/controller/Page';
import ProjectDto from 'src/controller/project/dto/ProjectDto';
import {TrackedError} from 'src/controller/error/TrackedError';

const organizationController = new OrganizationControllerConnection();
const projectController = new ProjectControllerConnection();

export const useOrganizationDetailsStore = defineStore('organizationDetails', {
  state: () => ({
    organization: undefined as OrganizationDto | undefined,
    projects: {content: [] as ProjectDto[]} as Page<ProjectDto>,
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
    }
  }
});
