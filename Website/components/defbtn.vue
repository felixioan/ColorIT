<template>
    <button class="defbtn" v-if="!to"><slot /></button>
    <nuxt-link class="defbtn" v-else :to="to"><slot /></nuxt-link>
</template>

<script>
export default {
    props: {
        to: {
            type: String,
            default: '',
            required: false
        }
    }
}
</script>