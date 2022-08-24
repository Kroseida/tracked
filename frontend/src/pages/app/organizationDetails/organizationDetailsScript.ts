import {defineComponent} from 'vue';
import TrackedDialogTitle from '../../../components/TrackedDialogTitle.vue';
import {useOrganizationStore} from "stores/organization";

export default defineComponent({
  name: 'OrganizationDetailsView',
  components: {
    TrackedDialogTitle
  },
  methods: {},
  data() {
    return {
      tab: 'base',
      organizationStore: useOrganizationStore(),
    }
  }
});
