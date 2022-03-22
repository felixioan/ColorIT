export default {
  ssr: false,
  srcDir: __dirname,
  /* srcDir: __dirname, */

  /*
  ** Headers of the page
  */
  head: {
    title: 'ColourIT',
    titleTemplate: '%s - ' + 'ColourIT',
    meta: [
      { charset: 'utf-8' },
      { name: 'viewport', content: 'width=device-width, initial-scale=1' },
      {
        hid: 'description',
        name: 'description',
        content: process.env.npm_package_description || '',
      },
      {
        hid: 'msapplication-TileImage',
        name: 'msapplication-TileImage',
        content: './icon.png',
      },
      { hid: 'theme-color', name: 'theme-color', content: '#e33d4f' },
      {
        hid: 'msapplication-TileColor',
        property: 'msapplication-TileColor',
        content: '#4e529a',
      },
    ],
  },
  /*
  ** Customize the progress-bar color
  */
  loading: { color: '#4e529a' },
  /*
  ** Global CSS
  */
  css: [
    '@/assets/style/main.scss',
  ],
  /*
  ** Plugins to load before mounting the App
  */
  plugins: [
    '@/plugins/main.js',
  ],

  router: {
    mode: 'hash',
    /* base: './' */
  },
  /*
  ** Nuxt.js dev-modules
  */
  buildModules: [],
  

  /*
  ** Nuxt.js modules
  */
  modules: [
    // Doc: https://axios.nuxtjs.org/usage
    '@nuxtjs/pwa',
  ],
  pwa: {
    manifest: {
      name: 'ColourIT',
      short_name: 'ColourIT',
      description: 'Colour IT Website',
      start_url: '/',
      scope: '/',
      display: 'standalone',
      background_color: '#ffffff',
      theme_color: '#e33d4f',
    },
  },

  /*
  ** Build configuration
  */
  generate: {
    dir: '../WebsiteBuild/'
  },
  /* buildDir: '../WebsiteBuild/', */
  build: {
    extend(config, { isDev, isClient }) {
      config.node = {
        fs: 'empty',
        dns: 'empty',
        net: 'empty',
      }
      if (!isDev) {
        config.module.rules.push({
					enforce: 'pre',
					test: /\.(js|vue)$/,
					loader: 'eslint-loader',
					exclude: /(node_modules)/
				});
        config.output.publicPath = './_nuxt/';
      }
      return config;
    }
  }
}
