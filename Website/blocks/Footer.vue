<template>
    <footer>
        <div class="container">
            <div class="footerRow">
                <nuxt-link to="/" class="logoImgContainer">
                    <img class="logo" src="../static/icon.png" alt="ColourIT Logo">
                </nuxt-link>
                <div class="col-12 col-sm-auto">
                    <div class="iconsBlock mx-auto mt-3 mx-sm-0 mt-sm-0">
                        <a href="https://facebook.com/" target="_blank" class="icon">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a href="https://twitter.com/" target="_blank" class="icon">
                            <i class="fab fa-twitter"></i>
                        </a>
                        <a href="https://instagram.com/" target="_blank" class="icon">
                            <i class="fab fa-instagram"></i>
                        </a>
                    </div>
                </div>
            </div>
            <div class="footerRow">
                <ul class="linksContainer">
                    <li>
                        <nuxt-link class="link" to="/">Home</nuxt-link>
                    </li>
                    <li>
                        <nuxt-link class="link" to="/team">Team</nuxt-link>
                    </li>
                    <li>
                        <nuxt-link class="link" to="/about">About</nuxt-link>
                    </li>
                    <li>
                        <nuxt-link class="link" to="/projects">Projects</nuxt-link>
                    </li>
                </ul>
                <div class="copyright">
                    © ColourIT<br>
                    All rights reserved
                </div>
            </div>
        </div>
    </footer>
</template>

<script>
export default {

}
</script>