<template>
  <b-container fluid>
    <b-row class="justify-content-md-center">
      <b-col cols="3">
        <h5>지도로 확인하기</h5>
        <div>
          <b-button @click="moveMap()">
            <b-img
              fluid
              :src="require('@/assets/img/TourlistChoiceMapImg.jpg')"
              alt="지도로 보기"
            ></b-img>
          </b-button>
        </div>
      </b-col>
      <b-col cols="3">
        <h5>사진으로 확인하기</h5>
        <div>
          <b-button @click="moveImage()">
            <b-img
              fluid
              :src="require('@/assets/img/TourlistChoiceImg.jpg')"
              alt="사진으로 보기"
            ></b-img>
          </b-button>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";

const tourListStore = "tourListStore";

export default {
  name: "TourListChoicePanel",
  computed: {
    ...mapState(tourListStore, ["mapShow", "imageShow"]),
  },
  methods: {
    ...mapMutations(tourListStore, [
      "CLEAR_MAP_SHOW",
      "CLEAR_IMAGE_SHOW",
      "SET_MAP_SHOW",
      "SET_IMAGE_SHOW",
    ]),
    moveMap() {
      this.SET_MAP_SHOW(true);
      this.SET_IMAGE_SHOW(false);
      this.$router.push({ name: "tourmap" });
    },
    moveImage() {
      this.SET_MAP_SHOW(false);
      this.SET_IMAGE_SHOW(true);
      this.$router.push({ name: "tourimage" });
    },
  },
};
</script>

<style scoped>
.container-fluid {
  margin-top: 10%;
  margin-bottom: 10%;
}
.col-3 {
  text-align: center;
}
div {
  border-radius: 10px;
  overflow: hidden;
}
img {
  overflow: hidden;
  border-radius: 10px;
  width: 300px;
  background-size: cover;
  transition: all 0.4s linear;
}
img:hover {
  transform: scale(1.5);
  border-radius: 10px;
}
h5 {
  font-weight: bold;
  margin-bottom: 16px;
}
button {
  background-color: white;
  border: 0;
}
</style>
