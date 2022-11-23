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

import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "App",
  components: {
    TheHeader,
    TheFooter,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped>
* {
  font-family: "Noto Sans KR", sans-serif;
}
</style>
