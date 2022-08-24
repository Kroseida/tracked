import {ControllerConnection} from 'src/controller/ControllerConnection';
import OrganizationCreationDto from 'src/controller/organization/dto/out/OrganizationCreationDto';
import OrganizationDto from 'src/controller/organization/dto/in/OrganizationDto';

export class OrganizationControllerConnection extends ControllerConnection {

  public async create(data: OrganizationCreationDto): Promise<OrganizationDto> {
    return <OrganizationDto>await this.request({
      method: 'POST',
      url: '/organization/',
      data
    });
  }

  public async delete(id: string): Promise<OrganizationDto> {
    return await this.request({
      method: 'DELETE',
      url: '/organization/' + id + '/'
    });
  }

  public async list(): Promise<OrganizationDto[]> {
    return <OrganizationDto[]>await this.request({
      method: 'GET',
      url: '/organization/'
    });
  }

}
