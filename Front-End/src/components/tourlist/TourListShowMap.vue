<template>
  <b-container>
    <tour-list-option-bar></tour-list-option-bar>
    <div class="tab-content mt-2" id="mapcontent">
      <div class="tab-pane fade show active" id="tabpane" role="tabpanel" aria-labelledby="tabpane">
        <div class="map_wrap">
          <div id="map" style="width: 100%; height: 700px"></div>
          <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
          <div class="custom_zoomcontrol radius_border">
            <span onclick="zoomIn()"
              ><img
                src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
                alt="확대"
            /></span>
            <span onclick="zoomOut()"
              ><img
                src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png"
                alt="축소"
            /></span>
          </div>
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
import TourListOptionBar from "@/components/tourlist/TourListOptionBar";

export default {
  name: "TourListShowMap",
  components: {
    TourListOptionBar,
  },
  data() {
    return {
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0,
    };
  },
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
.map_wrap {
  position: relative;
  overflow: hidden;
  height: 500px;
  margin-bottom: 5%;
}
.radius_border {
  border: 1px solid #919191;
  border-radius: 5px;
}
.custom_zoomcontrol {
  position: absolute;
  top: 10px;
  right: 10px;
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

/* map overlay */
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  min-height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 288px;
  min-height: 120px;
  border-radius: 10px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  min-height: 30px;
  background: rgb(208, 222, 240);
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
  width: 288px;
  white-space: normal;
  word-break: break-all;
  word-wrap: break-word;
}
.info .body {
  width: 288px;
  margin-top: 7px;
  margin-bottom: 10px;
  display: flex;
}
.info .img {
  margin-left: 7px;
  margin-right: 5px;
}

.info .ellipsis {
  width: 200px;
  word-break: break-all;
  word-wrap: break-word;
  white-space: normal;
}
</style>
