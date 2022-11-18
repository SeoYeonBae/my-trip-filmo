<template>
  <div>
    <b-button id="btn-back" class="mt-2" @click="moveChoice()">이전으로</b-button>
    <b-container>
      <b-row class="mb-4 text-center justify-content-md-center">
        <h5>나는</h5>
        <b-col md="3">
          <b-form-select v-model="sidoCode"></b-form-select>
        </b-col>
        <b-col md="3">
          <b-form-select v-model="gugunCode"></b-form-select>
        </b-col>
        <h5>로 떠나고 싶어요</h5>
      </b-row>
      <div>
        <b-tabs content-class="mt-3" fill>
          <b-tab title="전체" active></b-tab>
          <b-tab title="관광지"></b-tab>
          <b-tab title="문화시설"></b-tab>
          <b-tab title="행사 / 공연 / 축제"></b-tab>
          <b-tab title="여행코스"></b-tab>
          <b-tab title="레포츠"></b-tab>
          <b-tab title="숙박"></b-tab>
          <b-tab title="쇼핑"></b-tab>
          <b-tab title="음식점"></b-tab>
        </b-tabs>
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "TourListOptinBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
    };
  },
  methods: {
    moveChoice() {
      this.$router.push({ name: "tour" });
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
