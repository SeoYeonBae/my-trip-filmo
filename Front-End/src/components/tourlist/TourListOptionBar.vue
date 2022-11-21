<template>
  <div>
    <b-button id="btn-back" class="mt-2" @click="moveChoice()"
      >이전으로</b-button
    >
    <b-container>
      <b-row class="mb-4 text-center justify-content-md-center">
        <h5>나는</h5>
        <b-col md="3">
          <b-form-select
            v-model="selectSidoCode"
            :options="sidos"
            @change="this.makeGugun"
          ></b-form-select>
        </b-col>
        <b-col md="3">
          <b-form-select
            v-model="selectGugunCode"
            :options="guguns"
            @change="this.makeList"
          ></b-form-select>
        </b-col>
        <h5>로 떠나고 싶어요</h5>
      </b-row>
      <div>
        <b-tabs content-class="mt-3" v-model="tabInit" fill>
          <b-tab title="전체" @click="setContentTypeId(0)"></b-tab>
          <b-tab title="관광지" @click="setContentTypeId(12)"></b-tab>
          <b-tab title="문화시설" @click="setContentTypeId(14)"></b-tab>
          <b-tab
            title="행사 / 공연 / 축제"
            @click="setContentTypeId(15)"
          ></b-tab>
          <b-tab title="여행코스" @click="setContentTypeId(25)"></b-tab>
          <b-tab title="레포츠" @click="setContentTypeId(28)"></b-tab>
          <b-tab title="숙박" @click="setContentTypeId(32)"></b-tab>
          <b-tab title="쇼핑" @click="setContentTypeId(38)"></b-tab>
          <b-tab title="음식점" @click="setContentTypeId(39)"></b-tab>
        </b-tabs>
      </div>
    </b-container>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const tourListStore = "tourListStore";

export default {
  name: "TourListOptinBar",
  data() {
    return {
      selectSidoCode: "",
      selectGugunCode: "",
      tabInit: 0,
    };
  },
  computed: {
    ...mapState(tourListStore, [
      "sidoCode",
      "gugunCode",
      "contentTypeId",
      "sidos",
      "guguns",
      "mapShow",
      "imageShow",
      "tourList",
    ]),
  },
  created() {
    this.CLEAR_SIDO_CODE();
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_CODE();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_CONTENT_TYPE_ID();
    this.CLEAR_TOUR_LIST();
    this.getSido();
  },
  watch: {
    tourList: function () {
      this.makeContents();
    },
  },
  methods: {
    ...mapActions(tourListStore, ["getSido", "getGugun", "getTourList"]),
    ...mapMutations(tourListStore, [
      "CLEAR_SIDO_CODE",
      "CLEAR_GUGUN_CODE",
      "CLEAR_CONTENT_TYPE_ID",
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_TOUR_LIST",
      "SET_SIDO_CODE",
      "SET_GUGUN_CODE",
      "SET_CONTENT_TYPE_ID",
      "SET_MAP_SHOW",
      "SET_IMAGE_SHOW",
    ]),
    setContentTypeId(inputId) {
      // this.contentTypeId = inputId;
      this.SET_CONTENT_TYPE_ID(inputId);
      this.getTourList();
    },
    moveChoice() {
      this.SET_MAP_SHOW(false);
      this.SET_IMAGE_SHOW(false);
      this.$router.push({ name: "tour" });
    },
    makeContents() {
      if (this.mapShow) this.$emit("makeMarker");
      else if (this.imageShow) this.$emit("makeCard");
    },
    makeGugun() {
      this.SET_SIDO_CODE(this.selectSidoCode);
      this.CLEAR_GUGUN_LIST();
      this.getGugun(this.selectSidoCode);
    },
    async makeList() {
      this.CLEAR_CONTENT_TYPE_ID();
      this.tabInit = 0;
      this.SET_GUGUN_CODE(this.selectGugunCode);
      this.CLEAR_TOUR_LIST();
      this.getTourList();
    },
  },
};
</script>

<style scoped>
.container {
  margin-top: 5%;
}
button {
  margin-left: 30px;
  background-color: #fffbea;
  border: 0;
  color: black;
  padding-left: 16px;
  padding-right: 16px;
  width: 100px;
}
button:hover {
  background-color: #dfe4ff;
  color: white;
}
#btn-back {
  float: right;
}
h5 {
  font-weight: bold;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 0 0 0 0;
}
.row {
  line-height: 50px;
}
.custom-select {
  height: 50px;
  width: 100%;
  border-style: none;
}
</style>
