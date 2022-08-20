import {ControllerConnection} from 'src/controller/ControllerConnection';

export class MetaDataControllerConnection extends ControllerConnection {

  public async list(): Promise<Map<string, any>> {
    const response = await this.get('/metadata/')
    return new Map<string, any>(Object.entries(response));
  }

  public async lockInstallation() {
    await this.post('/metadata/install/')
  }

}
