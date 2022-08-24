import {defineStore} from 'pinia';
import {MetaDataControllerConnection, UserControllerConnection} from 'src/controller';

const metaDataController = new MetaDataControllerConnection();
const userController = new UserControllerConnection();

export const useInstallationStore = defineStore('installation', {
  state: () => ({
    step: 0,
    setupData: {
      account: {
        username: 'admin',
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        passwordRepeat: ''
      }
    }
  }),

  getters: {},

  actions: {
    async nextStep() {
      this.step++;
      if (this.step === 1) {
        await this.executeInstall();
      }
    },
    async executeInstall() {
      const user =  await userController.create({
        username: this.setupData.account.username,
        email: this.setupData.account.email,
        firstName: this.setupData.account.firstName,
        lastName: this.setupData.account.lastName,
      });
      await userController.createAuthentication({
        userId: user.id,
        type: 'USERNAME_PASSWORD',
        data: {
          password: this.setupData.account.password
        }
      });

      await metaDataController.lockInstallation();
    }
  }
});
