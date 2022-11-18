<template>
  <div id="app">
    <the-header></the-header>
    <div id="maincontentview">
      <router-view></router-view>
    </div>
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

<style scoped>
* {
  font-family: "Gowun Dodum", sans-serif;
}
#maincontentview {
  height: auto;
  min-height: 800px;
}
</style>
