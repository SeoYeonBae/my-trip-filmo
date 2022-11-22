<template>
  <b-container>
    <tour-list-option-bar></tour-list-option-bar>
    <b-card-group columns class="wrap">
      <b-card
        v-for="(tour, index) in this.tourList"
        :key="index"
        :img-src="tour.image"
        img-alt="Image"
        img-top
      >
        <b-card-title>{{ tour.title }}</b-card-title>
        <b-card-text>{{ tour.addr1 }}</b-card-text>
        <b-card-text>{{ tour.tel }}</b-card-text>
      </b-card>
    </b-card-group>
  </b-container>
</template>

<script>
import TourListOptionBar from "@/components/tourlist/TourListOptionBar";
import { mapState } from "vuex";

const tourListStore = "tourListStore";

export default {
  name: "TourListShowImage",
  components: {
    TourListOptionBar,
  },
  computed: {
    ...mapState(tourListStore, ["tourList"]),
  },
};
</script>

<style scoped>
.card {
  border: 1px solid lightgray;
}
.wrap {
  margin-top: 5%;
  height: 100%;
}
</style>
