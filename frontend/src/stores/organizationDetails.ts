import {defineStore} from 'pinia';
import {OrganizationControllerConnection} from 'src/controller';
import OrganizationDto from 'src/controller/organization/dto/in/OrganizationDto';

const organizationController = new OrganizationControllerConnection();

export const useOrganizationDetailsStore = defineStore('organizationDetails', {
  state: () => ({
    organization: undefined as OrganizationDto | undefined,
  }),
  actions: {
    async fetchOrganization(id: string) {
      const organization = await organizationController.get(id);
      this.organization = organization;
    },
    async updateOrganization() {
      await organizationController.patch(this.organization!.id, {
        name: this.organization!.name,
        description: this.organization!.description,
        active: this.organization!.active,
      });
    },
  }
});
