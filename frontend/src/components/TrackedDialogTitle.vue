<template>
  <div class="row title-row">
    <q-avatar v-if="icon" class="text-h4" :icon="icon" :color="color" :text-color="textColor"/>
    <span v-if="icon" class="q-ml-sm text-h6 text-icon" v-html="title"/>
    <span v-else class="text-h6" v-html="title"/>
  </div>
</template>

<script>
export default {
  name: 'TrackedCardTitle',
  props: {
    title: {
      type: String,
      required: false
    },
    icon: {
      type: String,
      required: false
    },
    color: {
      type: String,
      required: false
    },
    textColor: {
      type: String,
      required: false
    }
  }
}
</script>

<style scoped>
.text-icon {
  margin-left: 3%;
  margin-top: 2px;
}
.title-row {
  width: 100%;
  margin-top: 7px;
}
</style>
