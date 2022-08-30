<template>
  <q-page class="q-pa-md">
    <!-- CREATE - DIALOG -->
    <q-dialog v-model="modal.create" persistent>
      <q-card>
        <q-card-section class="row items-center">
          <tracked-dialog-title :title="$t('action.create.organization')"/>
          <div style="width: 100%; margin-top: 15px">
            <q-input
              type="text"
              tabindex="1"
              v-model="organizationStore.organizationCreation.name"
              :label="$t('organization.name')"
              filled
              dense/>
            <q-input
              class="q-mt-sm"
              :label="$t('organization.description')"
              v-model="organizationStore.organizationCreation.description"
              type="textarea"
              filled
              dense/>
          </div>
        </q-card-section>
        <q-card-actions align="right">
          <q-btn flat :label="$t('cancel')"
                 color="negative"
                 @click="cancelCreateOrganization"
                 v-close-popup/>
          <q-btn flat :label="$t('action.create.organization')"
                 color="primary"
                 :disabled="!organizationStore.organizationCreation.name"
                 @click="createOrganization"/>
        </q-card-actions>
      </q-card>
    </q-dialog>

    <!-- DELETE - DIALOG -->
    <q-dialog v-model="modal.delete" persistent>
      <q-card>
        <q-card-section class="items-center">
          <tracked-dialog-title :title="$t('action.delete.organization')"
                                text-color="white"
                                color="negative"
                                icon="warning"/>
          <span v-html="$t('text.delete.organization')"></span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat
                 :label="$t('cancel')"
                 color="primary"
                 v-close-popup/>
          <q-btn flat
                 :label="$t('action.delete.organization')"
                 color="negative"
                 @click="deleteOrganization"/>
        </q-card-actions>
      </q-card>
    </q-dialog>

    <q-card bordered>
      <q-card-section>
        <div class="text-h6">{{ $t('title.organization') }}</div>
      </q-card-section>
      <q-card-section class="q-pt-none">
        <tracked-loadable :loading="loading">
          <div v-if="organizationStore.organizations">
            <div v-if="organizationStore.organizations.totalElements === 0">
              <q-item>
                <q-item-section class="q-mt-sm" avatar top>
                  <q-icon name="warning" color="black" size="34px"/>
                </q-item-section>
                <q-item-section top class="col-2 gt-sm"/>
                <q-item-section top>
                  <q-item-label caption>
                    <p class="text-black">{{ $t('organization.noOrganization') }}</p>
                    <q-btn
                      class="q-mt-sm"
                      outline
                      color="primary"
                      :label="$t('action.create.organization')"
                      @click="modal.create = true"/>
                  </q-item-label>
                </q-item-section>
              </q-item>
            </div>
            <div v-else>
              <div v-for="organization in organizationStore.organizations.content" :key="organization.id">
                <q-item>
                  <q-item-section avatar top>
                    <q-icon name="view_compact" color="black" size="34px"/>
                  </q-item-section>
                  <q-item-section top class="col-2 gt-sm">
                    <q-item-label class="q-mt-sm">{{ organization.name }}</q-item-label>
                  </q-item-section>
                  <q-item-section top>
                    <q-item-label caption>
                      {{ organization.description }}
                    </q-item-label>
                  </q-item-section>
                  <q-item-section top side>
                    <div class="text-grey-8 q-gutter-xs">
                      <q-btn dense round flat
                             color="black"
                             icon="edit"
                             @click="openOrganizationDetails(organization.id)"/>
                      <q-btn dense round flat
                             color="black"
                             icon="delete"
                             @click="startOrganizationDeletion(organization.id)"/>
                    </div>
                  </q-item-section>
                </q-item>
                <q-separator spaced/>
              </div>
              <q-btn class="q-mt-sm"
                     outline
                     color="primary"
                     :label="$t('action.create.organization')"
                     @click="modal.create = true"/>
            </div>
          </div>
        </tracked-loadable>
      </q-card-section>
    </q-card>
  </q-page>
</template>
<style src="./organizationStyle.css"/>
<script src="./organizationScript.ts" lang="ts"/>
