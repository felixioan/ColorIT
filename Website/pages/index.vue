<template>
    <main class="homePage">
        <div class="firstBlock" :style="{'background-image': `url(${require('../static/code-banner.jpg')})`}">
            <div class="firstBlockContent">
                <img class="logo" src="../static/icon.png" alt="ColourIT Logo">
                <h1 class="colourIT">ColourIT</h1>
                <div class="scrollDown" @click="scrollDown()">
                    <i class="fal fa-mouse-alt"></i>
                    <span>Scroll down</span>
                </div>
            </div>
        </div>
        <div class="container" ref="scrollToBlock">
            <h2 class="homeHeadline iconBarHeadline">What we do</h2>
            <div class="icon-bar">
                <div class="iconItem">
                    <i class="fas fa-code"></i>
                    <span>We can develop a website or an app for you</span>
                </div>
                <div class="iconItem">
                    <i class="fas fa-dice-d6"></i>
                    <span>We work with Java, JavaScript, HTML and CSS</span>
                </div>
                <div class="iconItem">
                    <i class="fas fa-paint-brush"></i>
                    <span>We can create a new design, including copied logo and simple stuff</span>
                </div>
                <div class="iconItem">
                    <i class="fas fa-stethoscope"></i>
                    <span>For the right price we offer ongoing support to your app or website</span>
                </div>
            </div>

            <div class="news">
                <h2 class="homeHeadline">Latest news</h2>
                <ul>
                    <li>
                        <a href="#">
                            <div class="headline">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</div>
                            <div class="date">Nov 12</div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div class="headline">Aliquam consequat varius consequat.</div>
                            <div class="date">Nov 7</div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div class="headline">Fusce dapibus turpis vel nisi malesuada sollicitudin.</div>
                            <div class="date">Nov 2</div>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <div class="headline">Pellentesque auctor molestie orci ut blandit.</div>
                            <div class="date">Oct 28</div>
                        </a>
                    </li>
                </ul>
            </div>

        </div>
    </main>
</template>

<script>
export default {
    head() {
        return {
            title: 'Home'
        }
    },
    methods: {
        scrollDown: function() {
            this.$scrollTo(
                this.$refs.scrollToBlock,
                500,
                {
                    x:false,
                    y:true,
                    cancelable: true,
                    offset: -79,
                }
            );
        }
    },
}
</script>