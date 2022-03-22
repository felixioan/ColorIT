<template>
    <main class="projectsPage">
        <div class="container">
            <h1>Our Projects</h1>
            <div class="projects" v-if="projectsList.length>0">
                <nuxt-link :to="`/projects/${item.id}`" class="project" v-for="(item,index) in projectsList" :key="index">
                    <span class="projectName">{{item.name}}</span>
                    <p class="description">{{item.description}}</p>
                </nuxt-link>
                <!-- <nuxt-link to="/projects/1" class="project">
                    <span class="projectName">Avocado on toast</span>
                    <span class="forCompany">
                        for <span class="company">Google</span>
                    </span>
                    <p class="description">Turn a storecupboard staple into a twist on this popular brunch dish, livened up with sesame seeds and spring onions. Cooking time: 15 min.</p>
                    <div class="lastActive">Active Recently</div>
                </nuxt-link> -->
            </div>
            <div class="noProjects" v-else>
                <span>No projects yet</span>
            </div>
        </div>
    </main>
</template>

<script>


export default {
    head() {
        return {
            title: 'Our Projects'
        }
    },
    async asyncData({store, params, redirect}) {
        if(!window.projectsData){
            await store.dispatch('projects/getProjectsData');
        }
    },
    computed: {
        projectsList: function() {
            return this.$store.getters['projects/get'];
        }
    },
}
</script>