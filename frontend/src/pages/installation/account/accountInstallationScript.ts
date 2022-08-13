import {defineComponent} from 'vue';
import {noSpaceInputRule, emailInputRule} from 'components/input/rules';
import {useInstallationStore} from 'stores/installation';

export default defineComponent({
  name: 'CreateAccount',
  components: {},
  setup() {
    return {
      installationStore: useInstallationStore(),
      noSpaceInputRule,
      emailInputRule
    };
  },
  methods: {
    isPasswordEqual() {
      return this.installationStore.setupData.account.password ===
        this.installationStore.setupData.account.passwordRepeat;
    }
  }
});
