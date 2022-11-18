<template>
  <b-container>
    <tour-list-option-bar></tour-list-option-bar>
    <b-card-group columns>
      <b-card title="Title" img-src="https://placekitten.com/500/350" img-alt="Image" img-top>
        <b-card-text>
          This card has supporting text below as a natural lead-in to additional content.
        </b-card-text>
        <b-card-text class="small text-muted">Last updated 3 mins ago</b-card-text>
      </b-card>
      <b-card class="no-image-card">
        <b-card-title>Title</b-card-title>
        <b-card-text>
          This card has supporting text below as a natural lead-in to additional content.
        </b-card-text>
        <b-card-text class="small text-muted">Last updated 3 mins ago</b-card-text>
      </b-card>
    </b-card-group>
  </b-container>
</template>

<script>
import TourListOptionBar from "@/components/tourlist/TourListOptionBar";

export default {
  name: "TourListShowImage",
  components: {
    TourListOptionBar,
  },
};
</script>

<style scoped>
.card-columns {
  margin-bottom: 5%;
}
.no-image-card {
  background-color: #f8c5b4;
}

.card {
  border: 1px solid lightgray;
}
</style>
