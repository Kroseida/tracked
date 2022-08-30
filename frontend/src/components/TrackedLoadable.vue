<template>
  <div>
    <div v-if="loading" style="min-height: 200px">
      <q-inner-loading
        :label="$t('loading')"
        showing
      />
    </div>
    <div v-else>
      <slot/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TrackedLoadable',
  props: {
    loading: {
      type: Boolean,
      required: true
    }
  }
}
</script>

<style scoped>

</style>
