<template>
  <div class="container">
    <!-- title -->
    <div class="container mt-3 p-4 title"></div>

    <b-row class="px-5">
      <h1 id="title" class="fx-4">{{ placeInfo.title }}</h1></b-row
    >
    <b-row class="px-5">
      <b-col md="6" class="today-content mt-3">
        <h4 id="addr" class="fx-3">{{ placeInfo.addr1 }}</h4>
        <h5 id="tel" class="fx-3">{{ placeInfo.tel }}</h5>
        <div v-if="placeInfo.image != ''">
          <b-img id="image" class="mt-4" :src="placeInfo.image" alt="이미지" width="100%" />
        </div>
        <div v-else>
          <b-img
            id="image"
            class="mt-4"
            :src="require('@/assets/img/defaultImage.jpg')"
            alt="이미지"
            width="100%"
          />
        </div>
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
          <b-col class="recommend-col">
            <b-button id="btn recommend" class="fs-6" @click="newRecommend"
              >새로운 추천받기</b-button
            >
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const api = apiInstance();

export default {
  name: "RecommendView",
  data() {
    return {
      typeId: 0,
      placeInfo: {},
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0,
    };
  },
  created() {
    this.typeId = this.$route.params.typeid;
    // this.setInfo();
    api.get(`/tourlist/recommend/${this.typeId}`).then(({ data }) => (this.placeInfo = data));
    // {
    // this.placeInfo = data;
    // console.log(data);
    // this.latitude = data.mapy;
    // this.longitude = data.mapx;
    // console.log("1.5 setInfo 정보세팅" + this.latitude + "," + this.longitude);
    // });
    this.setmap();
  },
  mounted() {
    this.setmap();
  },
  methods: {
    setInfo() {},
    setmap() {
      window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
    },
    newRecommend() {
      this.setInfo();
    },
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
        // center: new kakao.maps.LatLng(33.450701, 126.570667),
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };
      console.log("플레이스인포" + this.placeInfo.title);
      this.map = new kakao.maps.Map(container, options);
    },
    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map((position) => new kakao.maps.LatLng(...position));

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
#title {
  font-family: "Do Hyeon", sans-serif;
  color: #171d3a;
}
#map {
  /* width: 100%; */
  /* height: 100%; */
  width: 500px;
  height: 450px;
}
#content {
  position: relative;
}
#image {
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
  right: 50px;
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
}
button:hover {
  background-color: #dfe4ff;
  color: white;
}
.recommend-col {
  justify-content: center;
  text-align: center;
}
</style>
