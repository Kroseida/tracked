<template>
  <div>
    <q-table
      :title="$t('projects')"
      :rows="organizationDetailsStore.projects.content"
      :columns="columns"
      row-key="id"
      v-model:pagination="pagination"
      :loading="loading"
      :filter="filter"
      @request="onRequest"
      :rows-per-page-label="$t('recordsPerPage')"
      :pagination-label="(firstItem, lastItem, total) => $t('displayedRecords', { firstItem, lastItem, total })"
      :rows-per-page-options="[5, 10, 20, 50, 75, 100]"
      :no-results-label="$t('project.noResults')"
      :no-data-label="$t('project.noData')"
      binary-state-sort
    >
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
    </q-table>
  </div>
</template>
<style src="./projectTabStyle.css"/>
<script src="./projectTabScript.ts" lang="ts"/>
