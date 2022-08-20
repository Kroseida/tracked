<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title>
          Tracked
        </q-toolbar-title>
        <div>Tracked v{{ globalStore.metaData.get('version') }}</div>
      </q-toolbar>
    </q-header>
    <q-page-container>
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import {useGlobalStore} from 'stores/global';

export default defineComponent({
  name: 'InstallationLayout',

  data() {
    return {
      globalStore: useGlobalStore(),
    }
  },

  async mounted() {
    // Check if the application is installed
    await this.globalStore.fetchMetaData();
    if (this.globalStore.metaData.get('status') !== 'UNINSTALLED') {
      this.$router.push('/'); // Redirect to home if already installed
    }
  }
});
</script>
