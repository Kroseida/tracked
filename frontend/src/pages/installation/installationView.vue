<template>
  <q-page class="row items-center justify-evenly">
    <account-installation v-if="installationStore.step === 0"/>
  </q-page>
</template>
<style src="./installationStyle.css"/>
<script src="./installationScript.ts" lang="ts"/>
