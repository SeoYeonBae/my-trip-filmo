<template>
  <div class="container">
    <!-- title -->
    <div class="container mt-3 p-4 title"></div>

    <b-row class="px-5"> <h1 id="today-title" class="fx-4">제목입니다</h1></b-row>
    <b-row class="px-5">
      <b-col md="6" class="today-content mt-3">
        <h4 id="today-addr" class="fx-3">주소입니다</h4>
        <h5 id="today-tel" class="fx-3">연락처입니다</h5>
        <b-img
          id="today-image"
          class="mt-4"
          :src="require('@/assets/img/sun.jpg')"
          alt="이미지"
          width="100%"
        />
      </b-col>
      <b-col md="6" class="map_wrap">
        <b-row id="map" class="radius_border"></b-row>
        <div class="custom_zoomcontrol radius_border">
          <span
            ><img
              src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
              alt="확대"
          /></span>
          <span
            ><img
              src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png"
              alt="축소"
          /></span>
        </div>
        <b-row class="m-4">
          <button id="btn-recommend float-right" class="fs-6" href="#recommend">
            새로운 추천받기 왜 우측에 안붙냐?
          </button>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>

<script>
// import axios from "axios";
export default {
  name: "RecommendView",
  data() {
    return {
      typeId: "",
      today: [],
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0,
    };
  },
  //   computed() {
  //     const url = `http://localhost:9999/today/recommend/${this.typeId}`;
  //     axios.get(url).then((response) => (this.today = response.data.response.body.items.item));
  //   },
  //   mounted() {},
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
  },
  methods: {
    ZoomIn() {
      alert("확대");
    },
    ZoomOut() {
      alert("축소");
    },
    addKakaoMapScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=84438603ef15ec1f521f260675951d5f";
      document.head.appendChild(script);
    },
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
#today-title {
  font-family: "Do Hyeon", sans-serif;
  color: #2a356d;
}
#map {
  width: 500px;
  height: 450px;
}
#today-content {
  position: relative;
}
#today-image {
  border-radius: 5px;
  position: absolute;
  bottom: 0;
  /* right: 10px; */
  min-width: 500px;
  max-height: 420px;
  z-index: -1;
}
.map_wrap {
  position: relative;
  overflow: hidden;
}
.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}
.custom_zoomcontrol {
  position: absolute;
  top: 10px;
  right: 30px;
  width: 36px;
  height: 80px;
  overflow: hidden;
  z-index: 1;
  background-color: #f5f5f5;
}
.custom_zoomcontrol span {
  display: block;
  width: 36px;
  height: 40px;
  text-align: center;
  cursor: pointer;
}
.custom_zoomcontrol span img {
  width: 15px;
  height: 15px;
  margin-top: 9px;
  margin-right: 1px;
  border: none;
}
.custom_zoomcontrol span:first-child {
  border-bottom: 1px solid #bfbfbf;
}
button {
  background-color: #fffbea;
  border: 0;
  color: black;
  font-weight: bold;
  padding: 16px;
  float: right;
}
button:hover {
  background-color: #dfe4ff;
  color: white;
}
</style>
