<template>
    <main class="teamPage">
        <div class="container">
            <h1>Team</h1>
            <div class="teamList">
                <div class="teamMember" v-for="item in team" :key="item.email">
                    <img :src="`data:image/svg+xml;utf8,${generateAvatar(item.email)}`" :alt="item.name">
                    <div class="name">{{item.name}}</div>
                    <a :href="`mailto:${item.email}`" class="email">{{item.email}}</a>
                </div>
            </div>
        </div>
    </main>
</template>

<script>
import { generateFromString } from 'generate-avatar';
export default {
    computed: {
        team: function() {
            return this.$store.getters['projects/getTeam'];
        },
    },
    methods: {
        generateAvatar: function(str) {
            if(!str){return null}
            return generateFromString(str);
        }
    },
}
</script>