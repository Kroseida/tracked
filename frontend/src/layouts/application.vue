<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="toggleLeftDrawer"
        />

        <q-toolbar-title>
          Tracked
        </q-toolbar-title>

        <div>Tracked v{{ globalStore.metaData.get('version') }}</div>
      </q-toolbar>
    </q-header>

    <div v-if="globalStore.localUser">
      <q-page-container>
        <router-view/>
      </q-page-container>
      <q-drawer
        v-model="leftDrawerOpen"
        show-if-above
        bordered
      >
        <q-list>
          <q-item-label
            header
          >

          </q-item-label>
          <EssentialLink
            v-for="link in essentialLinks"
            :key="link.title"
            v-bind="link"
          />
        </q-list>
      </q-drawer>

      <q-page-container>
        <router-view/>
      </q-page-container>
    </div>
    <div v-else>
      <q-page class="row items-center justify-evenly">
        <q-card dark bordered class="bg-white text-dark col-11 col-sm-6 col-md-3">
          <q-card-section>
            <div class="text-h6">{{ $t('title.login') }}</div>
          </q-card-section>
          <q-separator inset/>
          <q-card-section>
            <q-input
              v-model="globalStore.loginUsername"
              type="text"
              tabindex="1"
              :label="$t('username')"
              dense/>
            <q-input
              v-model="globalStore.loginPassword"
              type="password"
              tabindex="2"
              :label="$t('password')"
              class="q-mt-md q-mb-md"
              dense/>
            <q-btn color="primary"
                   :label="$t('login')"
                   tabindex="5"
                   @click="globalStore.createSession(globalStore.loginUsername, globalStore.loginPassword)"
                   class="q-mb-md float-right"/>
          </q-card-section>
        </q-card>
      </q-page>
    </div>
  </q-layout>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import EssentialLink from 'components/EssentialLink.vue';
import {useGlobalStore} from 'stores/global';
import {LocalStorage} from 'quasar';

const linksList = [
  {
    title: 'Docs',
    caption: 'quasar.dev',
    icon: 'school',
    link: 'https://quasar.dev'
  },
  {
    title: 'Github',
    caption: 'github.com/quasarframework',
    icon: 'code',
    link: 'https://github.com/quasarframework'
  },
  {
    title: 'Discord Chat Channel',
    caption: 'chat.quasar.dev',
    icon: 'chat',
    link: 'https://chat.quasar.dev'
  },
  {
    title: 'Forum',
    caption: 'forum.quasar.dev',
    icon: 'record_voice_over',
    link: 'https://forum.quasar.dev'
  },
  {
    title: 'Twitter',
    caption: '@quasarframework',
    icon: 'rss_feed',
    link: 'https://twitter.quasar.dev'
  },
  {
    title: 'Facebook',
    caption: '@QuasarFramework',
    icon: 'public',
    link: 'https://facebook.quasar.dev'
  },
  {
    title: 'Abmelden',
    icon: 'logout',
    action: () => {
      LocalStorage.remove('session');
      useGlobalStore().localUser = undefined;
    }
  }
];

export default defineComponent({
  name: 'ApplicationLayout',

  components: {
    EssentialLink
  },

  setup() {
    const leftDrawerOpen = ref(false)

    return {
      globalStore: useGlobalStore(),
      essentialLinks: linksList,
      leftDrawerOpen,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  },

  async mounted() {
    // Check if the application is installed
    await this.globalStore.fetchMetaData();
    if (this.globalStore.metaData.get('status') === 'UNINSTALLED') {
      return this.$router.push('/installation/'); // Redirect to installation if not installed
    }
    // Keep default startup
    await this.globalStore.fetchLocalUser();
  }

});
</script>
