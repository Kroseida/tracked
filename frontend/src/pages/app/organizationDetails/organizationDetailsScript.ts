import {defineComponent} from 'vue';
import baseTab from './tab/base/baseTabView.vue';
import projectTab from './tab/project/projectTabView.vue';
import activityTab from './tab/activity/activityTabView.vue';
import {useOrganizationDetailsStore} from 'stores/organizationDetails';

export default defineComponent({
  name: 'OrganizationDetailsView',
  data() {
    return {
      organizationDetailsStore: useOrganizationDetailsStore(),
      tab: 'base',
    }
  },
  components: {
    baseTab,
    projectTab,
    activityTab
  }
});
