import {ControllerConnection} from 'src/controller/ControllerConnection';
import OrganizationCreationDto from 'src/controller/organization/dto/out/OrganizationCreationDto';
import OrganizationDto from 'src/controller/organization/dto/in/OrganizationDto';
import {Page} from 'src/controller/Page';

export class OrganizationControllerConnection extends ControllerConnection {

  public async create(data: OrganizationCreationDto): Promise<OrganizationDto> {
    return <OrganizationDto>await this.request({
      method: 'POST',
      url: '/organization/',
      data
    });
  }

  public async delete(id: string) {
    await this.request({
      method: 'DELETE',
      url: '/organization/' + id + '/'
    });
  }

  public async list(): Promise<Page<OrganizationDto>> {
    return <Page<OrganizationDto>>await this.request({
      method: 'GET',
      url: '/organization/',
      params: {
        size: 2000 // TODO: pagination
      }
    });
  }

  async get(id: string): Promise<OrganizationDto> {
    return <OrganizationDto>await this.request({
      method: 'GET',
      url: '/organization/' + id + '/'
    });
  }

  async patch(id: string, data: OrganizationCreationDto) {
    return <OrganizationDto>await this.request({
      method: 'PATCH',
      url: '/organization/' + id + '/',
      data
    });
  }

}
