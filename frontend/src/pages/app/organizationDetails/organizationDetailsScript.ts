import {defineComponent} from 'vue';
import baseTab from './tab/base/baseTabView.vue';
import projectTab from './tab/project/projectTabView.vue';

export default defineComponent({
  name: 'OrganizationDetailsView',
  data() {
    return {
      tab: 'base',
    }
  },
  components: {
    baseTab,
    projectTab
  }
});
