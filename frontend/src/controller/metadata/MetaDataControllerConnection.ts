import {ControllerConnection} from 'src/controller/ControllerConnection';

export class MetaDataControllerConnection extends ControllerConnection {

  public async list(): Promise<Map<string, string>> {
    return new Map<string, string>(Object.entries(await this.get('/metadata/')));
  }

  public async lockInstallation() {
    await this.post('/metadata/install/')
  }

}
