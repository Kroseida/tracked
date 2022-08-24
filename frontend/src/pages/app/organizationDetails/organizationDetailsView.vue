<template>
  <q-page class="q-pa-md">
    <q-card bordered>
      <q-card-section>
        <div class="text-h6">{{ $t('title.organization') }}</div>
      </q-card-section>
      <q-card-section class="q-pt-none" v-if="organizationDetailsStore.organization">
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
            <div class="row">
              <div class="col-4">
                <q-input
                  class="tracked-input"
                  type="text"
                  :label="$t('organization.id')"
                  v-model="organizationDetailsStore.organization.id"
                  readonly
                  filled
                  dense/>
              </div>
              <div class="col-4">
                <q-input
                  class="tracked-input"
                  type="text"
                  :label="$t('organization.name')"
                  v-model="organizationDetailsStore.organization.name"
                  filled
                  dense/>
              </div>
              <div class="col-4">
                <q-select
                  class="tracked-input"
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
                   :disable="!hasBaseDataChanges()"
                   outline
                   color="primary"
                   :label="$t('action.update.organization')"
                   @click="updateOrganization()"/>
          </q-tab-panel>
          <q-tab-panel name="projects">
            <q-list>
              <div v-for="item in ['test', 'test']" :key="item">
                <q-item>
                  <q-item-section avatar top>
                    <q-icon name="folder" color="black" size="34px"/>
                  </q-item-section>

                  <q-item-section top class="col-2 gt-sm">
                    <q-item-label class="q-mt-sm">PlayLegend</q-item-label>
                  </q-item-section>

                  <q-item-section top>
                    <q-item-label lines="1">
                      <span class="text-grey-8">GitHub repository</span>
                    </q-item-label>
                    <q-item-label caption lines="1">
                      @rstoenescu in #3: > Generic type parameter for props
                    </q-item-label>
                  </q-item-section>

                  <q-item-section top side>
                    <div class="text-grey-8 q-gutter-xs">
                      <q-btn dense round flat color="grey" icon="edit" @click="openOrganizationDetails(props.row.id)"/>
                      <q-btn dense round flat color="grey" icon="delete"
                             @click="startOrganizationDeletion(props.row.id)"/>
                    </div>
                  </q-item-section>
                </q-item>
                <q-separator spaced/>
              </div>
            </q-list>
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
