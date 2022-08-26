<template>
  <q-page class="q-pa-md">
    <q-card bordered>
      <q-card-section>
        <div class="text-h6">{{ $t('title.organization') }}</div>
      </q-card-section>
      <q-card-section class="q-pt-none">
        <q-tabs
          v-model="tab"
          dense
          class="text-grey"
          active-color="primary"
          indicator-color="primary"
          align="justify"
          narrow-indicator
        >
          <q-tab name="base" :label="$t('baseData')"/>
          <q-tab name="projects" :label="$t('projects')"/>
          <q-tab name="activities" :label="$t('activities')"/>
          <q-tab name="user" :label="$t('users')"/>
        </q-tabs>
        <q-separator/>
        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="base">
            <base-tab :organization-id="$route.params.id"/>
          </q-tab-panel>
          <q-tab-panel name="projects">
            <project-tab :organization-id="$route.params.id"/>
          </q-tab-panel>
          <q-tab-panel name="movies">
            <div class="text-h6">Movies</div>
            Lorem ipsum dolor sit amet consectetur adipisicing elit.
          </q-tab-panel>
        </q-tab-panels>
      </q-card-section>
    </q-card>
  </q-page>
</template>
<style src="./organizationDetailsStyle.css"/>
<script src="./organizationDetailsScript.ts" lang="ts"/>
