import axios, {AxiosRequestConfig} from 'axios';
import {HttpError} from 'src/controller/error/HttpError';
import {TrackedError} from 'src/controller/error/TrackedError';
import {LocalStorage} from 'quasar';

export class ControllerConnection {

  protected async request(config: AxiosRequestConfig): Promise<object> {
    if (!config.headers) {
      config.headers = {};
    }
    if (LocalStorage.has('session')) {
      const session = LocalStorage.getItem('session') as string;
      config.headers['Authorization'] = session;
    }
    const response = await axios(config);
    if (!(response.status >= 200 && response.status < 300)) {
      throw new HttpError(response.statusText);
    }
    if (response.data.result !== 'SUCCESS') {
      throw new TrackedError(response.data.result);
    }
    return response.data.data;
  }

  protected async get(url: string): Promise<object> {
    return this.request({
      method: 'GET',
      url: url
    });
  }

  protected async post(url: string): Promise<object> {
    return this.request({
      method: 'POST',
      url: url
    });
  }

}
