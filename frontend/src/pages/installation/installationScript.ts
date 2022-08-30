import {defineComponent} from 'vue';
import accountInstallation from 'pages/installation/account/accountInstallationView.vue';
import {useInstallationStore} from 'stores/installation';

export default defineComponent({
  name: 'InstallationView',
  components: {
    accountInstallation
  },
  mounted() {
    this.installationStore.$reset();
  },
  data() {
    return {
      installationStore: useInstallationStore(),
    };
  }
});
