<template>
  <div id="app">
    <the-header></the-header>
    <router-view></router-view>
    <the-footer></the-footer>
  </div>
</template>

<script>
import TheHeader from "@/components/common/TheHeader";
import TheFooter from "./components/common/TheFooter";

export default {
  name: "App",
  components: {
    TheHeader,
    TheFooter,
  },
};
</script>

<style>
* {
  font-family: "Gowun Dodum", sans-serif;
}
</style>
