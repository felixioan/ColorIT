<template>
  <div class="defaultLayout">
    <header-component />
    <transition name="fade" mode="out-in">
      <nuxt class="route" />
    </transition>
    <footer-component/>
  </div>
</template>

<script>
import headerComponent from '@/blocks/Header.vue'
import footerComponent from '@/blocks/Footer.vue'
export default {
  components: {
    headerComponent,
    footerComponent
  },
  watch: {
    '$route': {
      immediate: true,
      handler(val, oldVal) {
        if(!oldVal){
          return this.$nextTick(()=>{
            document.documentElement.scrollTop=0;
          });
        }
        if(val.path!==oldVal.path) {
          this.$nextTick(()=>{
            let lastScroll = this.$store.getters['scroll/getLastScroll'];
            document.documentElement.scrollTop=lastScroll!==false?lastScroll.y:0;
          });
        }
      },
    }
  },
  computed: {
    screenLoader: function () {
      return this.$store.getters.getScreenLoader
    },
  },
  created () {
    if (process.client) {
      window.history.scrollRestoration = 'manual';
    }
    this.$store.dispatch('projects/initReceiveInterval');
  },
}
</script>
