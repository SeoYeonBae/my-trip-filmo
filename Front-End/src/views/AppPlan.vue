<template>
  <div>
    <plan-view></plan-view>
    <!-- <tour-list-map></tour-list-map> -->
  </div>
</template>

<script>
// import TourListMap from "@/components/tourlist/TourListShowMap.vue";
import PlanView from "@/components/plan/PlanView";

export default {
  name: "AppPlan",
  components: {
    PlanView,
    // TourListMap,
  },
};
</script>

<style scoped>
div {
  margin-top: 100px;
}
</style>
