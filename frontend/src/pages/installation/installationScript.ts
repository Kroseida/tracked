import {defineComponent, ref} from 'vue';
import accountInstallation from 'pages/installation/account/accountInstallationView.vue';
import {useInstallationStore} from 'stores/installation';

export default defineComponent({
  name: 'CreateAccount',
  components: {
    accountInstallation
  },
  setup() {
    return {
      installationStore: useInstallationStore(),
    };
  }
});
