<template>
  <q-item
    clickable
    @click="action()"
    :style="isOpen ? {'background-color': 'rgb(235, 235, 235)'} : {}"
  >
    <q-item-section
      v-if="icon"
      avatar
    >
      <q-icon :name="icon"/>
    </q-item-section>

    <q-item-section>
      <q-item-label>{{ $t(title) }}</q-item-label>
    </q-item-section>
  </q-item>
</template>

<script lang="ts">
import {defineComponent} from 'vue';

export default defineComponent({
  name: 'MenuLink',
  props: {
    title: {
      type: String,
      required: true
    },

    caption: {
      type: String,
      default: ''
    },

    action: {
      type: Function,
      default: () => {
        // Do nothing
      }
    },

    icon: {
      type: String,
      default: ''
    },
    isOpen: {
      type: Boolean,
      default: false
    }
  }
});
</script>
