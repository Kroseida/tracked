<template>
  <tracked-loadable :loading="loading">
    <div v-if="organizationDetailsStore.organization">
      <div class="row">
        <div class="col-md-4 col-sm-12 col-xs-12">
          <q-input
            class="q-mt-sm tracked-input"
            type="text"
            :label="$t('organization.id')"
            v-model="organizationDetailsStore.organization.id"
            readonly
            filled
            dense/>
        </div>
        <div class="col-md-4 col-sm-6 col-xs-12">
          <q-input
            class="q-mt-sm tracked-input"
            type="text"
            :label="$t('organization.name')"
            v-model="organizationDetailsStore.organization.name"
            filled
            dense/>
        </div>
        <div class="col-md-4 col-sm-6 col-xs-12">
          <q-select
            class="q-mt-sm tracked-input"
            :label="$t('organization.status')"
            v-model="organizationDetailsStore.organization.active"
            :display-value="organizationDetailsStore.organization.active ? $t('active') : $t('inactive')"
            :options="activeOptions"
            :emit-value="true"
            filled
            dense
            options-dense
          />
        </div>
        <div class="col-12">
          <q-input
            class="q-mt-sm tracked-input"
            :label="$t('organization.description')"
            v-model="organizationDetailsStore.organization.description"
            type="textarea"
            filled
            dense
          />
        </div>
      </div>
      <q-btn class="q-mt-sm"
             :disable="!hasBaseDataChanges() || organizationDetailsStore.organization.name === ''"
             outline
             color="positive"
             :label="$t('action.update.organization')"
             @click="updateOrganization()"/>
    </div>
  </tracked-loadable>
</template>
<style src="./baseTabStyle.css"/>
<script src="./baseTabScript.ts" lang="ts"/>
