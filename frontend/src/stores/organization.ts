import {defineStore} from 'pinia';
import {OrganizationControllerConnection} from 'src/controller';
import OrganizationDto from 'src/controller/organization/dto/in/OrganizationDto';
import {Page} from 'src/controller/Page';
import OrganizationCreationDto from 'src/controller/organization/dto/out/OrganizationCreationDto';

const organizationController = new OrganizationControllerConnection();

export const useOrganizationStore = defineStore('organization', {
  state: () => ({
    organizationCreation: {
      name: '',
      description: '',
      active: true
    } as OrganizationCreationDto,
    organizationDeletion: {
      id: ''
    },
    organizations: undefined as Page<OrganizationDto> | undefined
  }),
  actions: {
    async createOrganization() {
      await organizationController.create({
        name: this.organizationCreation.name,
        description: this.organizationCreation.description,
        active: true,
      });
    },
    resetOrganizationCreation() {
      this.organizationCreation.name = '';
      this.organizationCreation.description = '';
    },
    async fetchOrganizations() {
      const organizations = await organizationController.list();
      this.organizations = organizations;
    },
    async deleteOrganization() {
      await organizationController.delete(this.organizationDeletion.id);
    }
  }
});
