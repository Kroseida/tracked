import {defineStore} from 'pinia';
import {MetaDataControllerConnection, UserControllerConnection} from 'src/controller/';
import {LocalStorage} from 'quasar';
import UserDto from 'src/controller/user/dto/in/UserDto';

const metaDataController = new MetaDataControllerConnection();
const userController = new UserControllerConnection();

export const useGlobalStore = defineStore('global', {
  state: () => ({
    metaData: new Map<string, string>() as Map<string, string>,
    localUser: undefined as UserDto | undefined,
    loginUsername: '' as string,
    loginPassword: '' as string,
  }),
  getters: {
    getMetaData(state) {
      return state.metaData;
    }
  },
  actions: {
    async fetchMetaData() {
      this.metaData = await metaDataController.list();
    },
    async createSession(username: string, password: string) {
      const authenticationResponse = await userController.createSession({username, password});
      LocalStorage.set('session', authenticationResponse.session);
    },
    async fetchLocalUser() {
      this.localUser = await userController.getSession();
    }
  }
});
