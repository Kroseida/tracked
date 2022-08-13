<template>
  <q-card dark bordered class="bg-white text-dark col-11 col-sm-6 col-md-3">
    <q-card-section>
      <div class="text-h6">{{ $t('title.createAccount') }}</div>
    </q-card-section>
    <q-separator inset/>
    <q-card-section>
      <q-form @submit="installationStore.nextStep">
        <q-input
          v-model="installationStore.setupData.account.username"
          type="text"
          :rules="[(v) => noSpaceInputRule(v) || $t('rules.invalidUsername') ]"
          tabindex="1"
          :label="$t('username')"
          dense/>
        <q-input
          v-model="installationStore.setupData.account.password"
          type="password"
          :rules="[(v) => noSpaceInputRule(v) || $t('rules.invalidUsername') ]"
          tabindex="1"
          :label="$t('password')"
          dense/>
        <q-input
          v-model="installationStore.setupData.account.passwordRepeat"
          type="password"
          :rules="[(v) => isPasswordEqual() || $t('rules.invalidRepeatPassword') ]"
          tabindex="1"
          :label="$t('passwordRepeat')"
          dense/>
        <q-separator/>
        <q-input
          v-model="installationStore.setupData.account.firstName"
          :label="$t('firstName')"
          tabindex="2"
          type="text"
          class="q-mt-md"
          dense/>
        <q-input
          v-model="installationStore.setupData.account.lastName"
          :label="$t('lastName')"
          type="text"
          tabindex="3"
          class="q-mt-md"
          dense/>
        <q-input
          v-model="installationStore.setupData.account.email"
          :label="$t('email')"
          :rules="[(v) => emailInputRule(v) || $t('rules.invalidEmail') ]"
          tabindex="4"
          class="q-mt-md"
          dense/>
        <q-btn :disable="!installationStore.setupData.account.username ||
                       !installationStore.setupData.account.firstName ||
                       !installationStore.setupData.account.lastName ||
                       !installationStore.setupData.account.email"
               color="primary"
               :label="$t('createAccount')"
               tabindex="5"
               type="submit"
               class="q-mb-md float-right"/>
      </q-form>
    </q-card-section>
  </q-card>
</template>
<style src="./accountInstallationStyle.css"/>
<script src="./accountInstallationScript.ts" lang="ts"/>
