<template>
  <div>
    <router-view></router-view>
    <!-- <plan-view></plan-view> -->
  </div>
</template>

<script>
// import TourListMap from "@/components/tourlist/TourListShowMap.vue";
// import PlanView from "@/components/plan/PlanView";

export default {
  name: "AppPlan",
  components: {
    // PlanView,
    // TourListMap,
  },
};
</script>

<style scoped>
div {
  margin-top: 100px;
}
</style>
