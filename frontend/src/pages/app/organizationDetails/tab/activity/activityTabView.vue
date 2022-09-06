<template>
  <div>
    <!-- CREATE - DIALOG -->
    <q-dialog v-model="modal.create" persistent>
      <q-card>
        <q-card-section class="row items-center">
          <tracked-dialog-title :title="$t('action.create.activity')"/>
          <div style="width: 100%;">
            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <q-input
                  class="q-mt-sm tracked-input"
                  type="text"
                  tabindex="1"
                  v-model="organizationDetailsStore.activityCreation.name"
                  :label="$t('activity.name')"
                  filled
                  dense/>
              </div>
              <div class="col-12">
                <q-input
                  class="q-mt-sm"
                  :label="$t('activity.description')"
                  v-model="organizationDetailsStore.activityCreation.description"
                  type="textarea"
                  filled
                  dense/>
              </div>
            </div>
          </div>
        </q-card-section>
        <q-card-actions align="right">
          <q-btn flat :label="$t('cancel')"
                 color="negative"
                 @click="cancelActivityCreation()"
                 v-close-popup/>
          <q-btn flat :label="$t('action.create.activity')"
                 color="primary"
                 :disabled="!organizationDetailsStore.activityCreation.name"
                 @click="createActivity()"/>
        </q-card-actions>
      </q-card>
    </q-dialog>

    <!-- DELETE - DIALOG -->
    <q-dialog v-model="modal.delete" persistent>
      <q-card>
        <q-card-section class="items-center">
          <tracked-dialog-title :title="$t('action.delete.activity')"
                                text-color="white"
                                color="negative"
                                icon="warning"/>
          <span v-html="$t('text.delete.activity')"></span>
        </q-card-section>

        <q-card-actions align="right">
          <q-btn flat
                 :label="$t('cancel')"
                 color="primary"
                 v-close-popup/>
          <q-btn flat
                 :label="$t('action.delete.activity')"
                 color="negative"
                 @click="deleteActivity"/>
        </q-card-actions>
      </q-card>
    </q-dialog>
    <q-table
      :title="$t('activities')"
      :rows="organizationDetailsStore.activities.content"
      :columns="columns"
      row-key="id"
      v-model:pagination="pagination"
      :loading="loading"
      :filter="filter"
      @request="onRequest"
      :rows-per-page-options="[5, 10, 20, 50, 75, 100]"
      :no-results-label="$t('activity.noResults')"
      binary-state-sort
    >
      <template v-slot:body="props">
        <q-tr :props="props"
              :style="props.expand ? 'background-color: rgba(25, 118, 210, 0.07);' : ''">
          <q-td
            v-for="col in props.cols"
            :key="col.name"
            :props="props"
          >
            <div v-if="col.name === 'active'">
              {{ props.row.active ? $t('active') : $t('inactive') }}
            </div>
            <div v-else-if="col.name === 'action'">
              <div class="text-grey-8 q-gutter-xs">
                <q-btn dense round flat
                       color="black"
                       icon="edit"
                       @click="props.expand = !props.expand"/>
                <q-btn dense round flat
                       color="black"
                       icon="delete"
                       @click="startActivityDeletion(props.row.id)"/>
              </div>
            </div>
            <div v-else>
              {{ col.value }}
            </div>
          </q-td>
        </q-tr>
        <q-tr v-show="props.expand" :props="props">
          <q-td colspan="100%">
            <div class="row">
              <div class="col-md-4 col-sm-12 col-xs-12">
                <q-input
                  class="q-mt-sm tracked-input"
                  type="text"
                  :label="$t('activity.id')"
                  v-model="props.row.id"
                  readonly
                  filled
                  dense/>
              </div>
              <div class="col-md-4 col-sm-6 col-xs-12">
                <q-input
                  class="q-mt-sm tracked-input"
                  type="text"
                  :label="$t('activity.name')"
                  v-model="props.row.name"
                  filled
                  dense/>
              </div>
              <div class="col-md-4 col-sm-6 col-xs-12">
                <q-select
                  class="q-mt-sm tracked-input"
                  :label="$t('activity.status')"
                  v-model="props.row.active"
                  :display-value="props.row.active ? $t('active') : $t('inactive')"
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
                  :label="$t('activity.description')"
                  v-model="props.row.description"
                  type="textarea"
                  filled
                  dense
                />
              </div>
            </div>
            <q-btn :disable="!hasBaseDataChanges(props.row)"
                   class="q-mt-sm"
                   outline
                   color="positive"
                   :label="$t('action.update.activity')"
                   @click="updateActivity(props.row)"/>
          </q-td>
        </q-tr>
      </template>

      <template v-slot:top-right>
        <q-form @submit="applyFilter">
          <q-input v-model="rawFilter" :placeholder="$t('search')" dense filled>
            <template v-slot:append>
              <q-icon name="search"/>
            </template>
          </q-input>
        </q-form>
      </template>
      <template v-slot:body-cell-active="{row}">
        <td>
          {{ row.active ? $t('active') : $t('inactive') }}
        </td>
      </template>
      <template v-slot:no-data>
        <q-item class="q-mt-md col-12">
          <q-item-section avatar top>
            <q-icon name="warning" color="black" size="34px"/>
          </q-item-section>
          <q-item-section top class="col-2 gt-sm"/>
          <q-item-section v-if="!filter" top>
            <q-item-label caption>
              <p class="text-black">{{ $t('activity.noData') }}</p>
              <q-btn
                class="q-mt-sm"
                outline
                color="primary"
                :label="$t('action.create.activity')"
                @click="modal.create = true"/>
            </q-item-label>
          </q-item-section>
          <q-item-section v-else top>
            <q-item-label caption>
              <p class="q-mt-sm text-black">{{ $t('activity.noResults') }}</p>
            </q-item-label>
          </q-item-section>
        </q-item>
      </template>
      <template v-slot:bottom>
        <q-btn class="q-mt-sm"
               outline
               color="primary"
               :label="$t('action.create.activity')"
               @click="modal.create = true"/>
        <q-space/>
        <q-pagination
          class="q-mt-sm"
          @update:model-value="onPageChange"
          v-model="rawPage"
          :max="Math.ceil(pagination.rowsNumber / pagination.rowsPerPage)"
          :disable="loading"
          icon-prev="chevron_left"
          icon-next="chevron_right"
          icon-first="fast_rewind"
          icon-last="fast_forward"
          direction-links
          boundary-links
          input
        />
      </template>
    </q-table>
  </div>
</template>
<style src="./activityTabStyle.css"/>
<script src="./activityTabScript.ts" lang="ts"/>
