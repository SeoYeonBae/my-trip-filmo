<template>
  <b-container>
    <tour-list-option-bar @makeMarker="makeMapMarkers"></tour-list-option-bar>
    <div class="tab-content mt-2" id="mapcontent">
      <div class="tab-pane fade show active" id="tabpane" role="tabpanel" aria-labelledby="tabpane">
        <div class="map_wrap">
          <div id="map" style="width: 100%; height: 700px"></div>
          <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
          <div class="custom_zoomcontrol radius_border">
            <span @click="zoomIn"
              ><img
                src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png"
                alt="확대"
            /></span>
            <span @click="zoomOut"
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
import { mapState } from "vuex";

const tourListStore = "tourListStore";

export default {
  name: "TourListShowMap",
  components: {
    TourListOptionBar,
  },
  computed: {
    ...mapState(tourListStore, ["sidoCode", "gugunCode", "contentTypeId", "tourList"]),
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
    zoomIn() {
      this.map.setLevel(this.map.getLevel() - 1);
    },
    zoomOut() {
      this.map.setLevel(this.map.getLevel() + 1);
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
    makeMapMarkers() {
      console.log("마커 만들어용!!");
      // ****** 여러개 마커에 이벤트 등록하기1 ******
      this.markers.forEach(function (mark) {
        mark.setMap(null);
      });
      this.markers = [];

      var positions = [];
      this.tourList.forEach((tour) => {
        let addr = tour.addr1;
        let image = tour.image;
        let mapx = tour.mapx;
        let mapy = tour.mapy;
        let tel = tour.tel;
        let title = tour.title;
        let zipcode = tour.zipcode;
        positions.push({
          content: `<div class="wrap">
        <div class="info bg-light">
          <div class="title">${title}</div>
          <div class="body">
            <div class="img">
              <img src="${image}" width="73" height="70">
            </div>
            <div class="desc">
              <div class="ellipsis">${addr}</div>
              <div class="ellipsis">(우) ${zipcode}</div>
              <div class="ellipsis">(전화번호) ${tel}</div>
            </div>
          </div>
        </div>
      </div>`,
          latlng: new kakao.maps.LatLng(mapy, mapx),
        });
      });

      for (var i = 0; i < positions.length; i++) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커의 위치
        });

        this.map.setLevel(7);
        this.map.setCenter(positions[i].latlng);

        this.markers.push(marker);

        // 마커 위에 커스텀오버레이를 표시합니다
        // 마커를 중심으로 커스텀 오버레이를 표시하기위해 CSS를 이용해 위치를 설정했습니다
        let overlay = new kakao.maps.CustomOverlay({
          content: positions[i].content,
          map: this.map,
          position: marker.getPosition(),
        });
        overlay.setMap(null);

        // 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
        kakao.maps.event.addListener(marker, "mouseover", () => {
          overlay.setMap(this.map);
        });

        kakao.maps.event.addListener(marker, "mouseout", () => {
          overlay.setMap(null);
        });
      }
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
