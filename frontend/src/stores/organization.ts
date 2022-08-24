import {defineStore} from 'pinia';
import {OrganizationControllerConnection} from 'src/controller';
import OrganizationDto from 'src/controller/organization/dto/in/OrganizationDto';

const organizationController = new OrganizationControllerConnection();

export const useOrganizationStore = defineStore('organization', {
  state: () => ({
    modal: {
      create: false,
      delete: false
    },
    organizationCreation: {
      name: '',
      description: '',
    },
    organizationDeletion: {
      id: ''
    },
    organizations: [] as OrganizationDto[]
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
      this.modal.create = false;
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
