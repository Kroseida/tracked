<template>
  <q-page class="q-pa-md">
    <q-dialog v-model="organizationStore.modal.create" persistent>
      <q-card>
        <q-card-section class="row items-center">
          <tracked-dialog-title :title="$t('action.create.organization')"/>
          <div style="width: 100%; margin-top: 15px">
            <q-input
              type="text"
              tabindex="1"
              v-model="organizationStore.organizationCreation.name"
              :label="$t('organization.name')"
              style="width: 100%"
              dense/>
            <q-input
              :label="$t('organization.description')"
              v-model="organizationStore.organizationCreation.description"
              type="textarea"
              dense
            />
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
    <q-table
      wrap-cells
      :title="$t('title.organization')"
      :rows="organizationStore.organizations"
      :columns="columns"
      :rows-per-page-label="$t('recordsPerPage')"
      :no-data-label="$t('organization.noOrganization')"
      :pagination-label="(firstItem, lastItem, totalItem) => $t('displayedRecords', {firstItem, lastItem, totalItem})"
    >
      <template v-slot:top>
        <div class="text-h6">{{ $t('title.organization') }}</div>
        <q-space/>
        <q-btn class="q-ml-sm"
               color="primary"
               :label="$t('action.create.organization')"
               @click="organizationStore.modal.create = true"/>
      </template>
      <template v-slot:body-cell-action="props">
        <q-td :props="props">
          <div class="tracked-actions" style="min-width: 70px">
            <q-dialog v-model="organizationStore.modal.delete" persistent>
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
            <q-btn dense round flat color="grey" icon="edit"></q-btn>
            <q-btn dense round flat color="grey" icon="delete" @click="startOrganizationDeletion(props.row.id)"></q-btn>
          </div>
        </q-td>
      </template>
      <q-separator/>
    </q-table>
  </q-page>
</template>
<style src="./organizationStyle.css"/>
<script src="./organizationScript.ts" lang="ts"/>
