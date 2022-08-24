import {ControllerConnection} from 'src/controller/ControllerConnection';
import OrganizationDto from 'src/controller/organization/dto/in/OrganizationDto';

export class MetaDataControllerConnection extends ControllerConnection {

  public async list(): Promise<Map<string, string>> {
    const metaData = await this.request({
      method: 'GET',
      url: '/metadata/'
    });
    return new Map<string, string>(Object.entries(metaData));
  }

  public async lockInstallation() {
    await this.request({
      method: 'POST',
      url: '/metadata/install/'
    });
  }

}
