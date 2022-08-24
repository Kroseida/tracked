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
      <q-drawer
        v-model="leftDrawerOpen"
        show-if-above
        bordered
      >
        <q-list>
          <q-item-label header/>
          <MenuLink
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
                   @click="authenticate()"
                   class="q-mb-md float-right"/>
          </q-card-section>
        </q-card>
      </q-page>
    </div>
  </q-layout>
</template>

<script lang="ts">
import {defineComponent, ref} from 'vue';
import MenuLink from 'components/MenuLink.vue';
import {useGlobalStore} from 'stores/global';
import {LocalStorage, Notify} from 'quasar';
import {TrackedError} from 'src/controller/error/TrackedError';

export default defineComponent({
  name: 'ApplicationLayout',

  components: {
    MenuLink
  },

  data() {
    const leftDrawerOpen = ref(false)

    return {
      globalStore: useGlobalStore(),
      essentialLinks: [
        {
          title: 'menu.createReport.title',
          icon: 'access_time'
        },
        {
          title: 'menu.activity.title',
          icon: 'local_activity'
        },
        {
          title: 'menu.project.title',
          icon: 'folder'
        },
        {
          title: 'menu.organization.title',
          icon: 'view_compact',
          action: () => {
            this.$router.push({name: 'organizationList'});
          }
        },
        {
          title: 'menu.settings.title',
          icon: 'settings'
        },
        {
          title: 'menu.users.title',
          icon: 'group'
        },
        {
          title: 'menu.logout.title',
          icon: 'logout',
          action: () => {
            LocalStorage.remove('session');
            useGlobalStore().localUser = undefined;
          }
        }
      ],
      leftDrawerOpen,
      toggleLeftDrawer() {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  },
  methods: {
    async authenticate() {
      try {
        await this.globalStore.createSession(
          this.globalStore.loginUsername,
          this.globalStore.loginPassword
        );
        this.globalStore.loginUsername = '';
        this.globalStore.loginPassword = '';
        await this.globalStore.fetchLocalUser();
        Notify.create({
          type: 'positive',
          message: this.$t('notification.authentication.authenticate.success')
        });
      } catch (error) {
        Notify.create({
          type: 'negative',
          message: this.$t('notification.authentication.authenticate.' + (<TrackedError>error).message),
        });
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
