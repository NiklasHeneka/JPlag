<!-- 
  Base Component for Buttons 
-->
<template>
  <Interactable
    class="px-2 py-1 flex items-center text-center justify-center"
    @click="(e) => $emit('click', e)"
  >
    <slot></slot>
  </Interactable>
</template>

<script setup lang="ts">
import Interactable from './InteractableComponent.vue'

defineEmits(['click'])
</script>
