<template>
  <router-view />
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import {Notify} from 'quasar';
import { TrackedError } from './controller/error/TrackedError';
import {AxiosError} from 'axios';

export default defineComponent({
  name: 'App',
  mounted() {
    window.addEventListener('unhandledrejection', (event) => {
      if(event.reason instanceof TrackedError || event.reason instanceof AxiosError) {
        Notify.create({
          type: 'negative',
          message: this.$t('notification.' + event.reason.message.replace(' ', '_').toUpperCase())
        });
      } else {
        Notify.create({
          type: 'negative',
          message: event.reason.message
        });
      }
    });
  }
});
</script>
