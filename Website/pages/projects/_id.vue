<template>
    <main class="projectPage">
        <div class="container" v-if="project">
            <div class="projectHeader">
                <nuxt-link to="/projects" class="returnBtn">
                    <i class="fal fa-angle-left"></i>
                    <span>Projects</span>
                </nuxt-link>
                <h1>{{project.name}}</h1>
                <!-- <span class="forCompany">
                    for <span class="company">Google</span>
                </span> -->
            </div>
            <div class="tileContainer">
                <h3 class="tileHeadline">
                    Description
                    <span v-if="!project.description">No project description</span>
                </h3>
                <p class="description" v-if="project.description">{{project.description}}</p>
                
                <h3 class="tileHeadline">
                    Requirements 
                    <span v-if="project.requirements.length>0">{{openedTasks}} of {{project.requirements.length}} open</span>
                    <span v-else>No requirements yet</span>
                </h3>
                <div class="requirementsList">
                    <div class="requirement" v-for="(item) in project.requirements" :key="item.id" :data-status="item.status">
                        <div class="status">{{item.status}}</div>
                        <div class="requirementMain">
                            #{{item.id}}: {{item.name}} <span class="date" :class="{'late': dateNow>item.deadline}"> <i class="fal fa-horizontal-rule"></i> {{getFormatedTime(item.deadline)}}</span> <span class="functional" v-if="item.description"> <i class="far fa-dot-circle" v-if="item.description.length>2"></i><i v-else class="far fa-circle"></i> {{item.description.length>2?'Functional':'Non-Functional'}}</span>
                        </div>
                    </div>
                    <!-- <div class="requirement notStarted">
                        <div class="status">Not Started</div>
                        <div class="requirementMain">
                            #6: Serve with lemon wedges to squeeze ove <span class="date"> <i class="fal fa-horizontal-rule"></i> Dec 32</span>
                        </div>
                    </div>
                    <div class="requirement notStarted">
                        <div class="status">Not Started</div>
                        <div class="requirementMain">
                            #5: Fold the avocado through the chickpeas, then spread over the bread <span class="date"> <i class="fal fa-horizontal-rule"></i> Dec 4</span>
                        </div>
                    </div>
                    <div class="requirement started">
                        <div class="status">Started</div>
                        <div class="requirementMain">
                            #4: Fold the avocado through the chickpeas, then spread over the bread <span class="date"> <i class="fal fa-horizontal-rule"></i> Nov 23</span>
                        </div>
                    </div>
                    <div class="requirement started">
                        <div class="status">Started</div>
                        <div class="requirementMain">
                            #3: Spoon the avocado into another bowl and break up with a fork until roughly crushed <span class="date late"> <i class="fal fa-horizontal-rule"></i> Nov 14</span>
                        </div>
                    </div>
                    <div class="requirement rejected">
                        <div class="status">Rejected</div>
                        <div class="requirementMain">
                            #2: Use a potato masher to crush everything together until you have a rough paste <span class="date"> <i class="fal fa-horizontal-rule"></i> Nov 1</span>
                        </div>
                    </div>
                    <div class="requirement done">
                        <div class="status">Done</div>
                        <div class="requirementMain">
                            #1: Put the chickpeas, miso, sesame oil, lemon juice and some seasoning in a bowl <span class="date"> <i class="fal fa-horizontal-rule"></i> Oct 21</span>
                        </div>
                    </div> -->
                </div>
            </div>
        </div>
    </main>
</template>

<script>
import moment from 'moment';
export default {
    head() {
        return {
            title: this.project.name
        }
    },
    async asyncData({store, params, redirect}) {
        if(!window.projectsData){
            await store.dispatch('projects/getProjectsData');
        }
        if(isNaN(parseInt(params.id))) {
            return redirect('/projects');
        }
        const project = store.getters['projects/getById'](parseInt(params.id));
        if(!project) {
            return redirect('/projects');
        }
    },
    watch: {
        project(val) {
            if(!val) {
                this.$router.push('/projects');
            }
        }
    },
    computed: {
        project: function() {
            return this.$store.getters['projects/getById'](parseInt(this.$route.params.id));
        },
        openedTasks: function() {
            let count = 0;
            for(const item of this.project.requirements) {
                if(item.status==='Started' || item.status==='Not started') {
                    count++;
                }
            }
            return count;
        },
        dateNow: function() {
            return new Date().getTime();
        }
    },
    methods: {
        getFormatedTime: function(time) {
            return moment(time).format("MMM DD YYYY")
        },
    },
}
</script>