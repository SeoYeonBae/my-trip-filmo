<template>
  <div id="main">
    <b-row id="mainrow">
      <b-col md="2" class="shadow p-3 bg-body rounded text-center">
        <div class="choice">
          <h3 id="region">{{ place }}</h3>
          <h3 id="period">3 DAY</h3>
          <p>2022.12.20 ~ 2022.12.21</p>
        </div>
        <hr />
        <div id="choicediv">
          <draggable v-model="mychoices">
            <transition-group>
              <b-col v-for="(choice, index) in mychoices" v-bind:key="index" class="choiced-places">
                <b-card
                  img-src="https://cdn.travie.com/news/photo/first/201710/img_19975_1.jpg"
                  img-alt="Image"
                  img-top
                  tag="article"
                  style="max-width: 10rem"
                  class="mb-2 cards"
                >
                  <button class="btn-delete">
                    <font-awesome-icon icon="fa-solid fa-circle-xmark" style="color: red" />
                  </button>
                  <b-card-text>
                    <font-awesome-icon icon="fa-solid fa-bed" />
                    {{ choice.name }}
                  </b-card-text>
                </b-card>
              </b-col>
            </transition-group>
          </draggable>
        </div>
      </b-col>
      <b-col md="8">
        <b-row class="optionbar"><plan-option-bar></plan-option-bar></b-row>
        <b-row>
          <b-container>
            <tour-list-option-bar @makeMarker="makeMapMarkers"></tour-list-option-bar>
            <div class="tab-content mt-2" id="mapcontent">
              <div class="tab-pane fade show active" id="tabpane" role="tabpanel" aria-labelledby="tabpane">
                <div class="map_wrap">
                  <div id="map" style="width: 100%; height: 700px"></div>
                  <!-- 지도 확대, 축소 컨트롤 div 입니다 -->
                  <div class="custom_zoomcontrol radius_border">
                    <span @click="zoomIn"
                      ><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png" alt="확대"
                    /></span>
                    <span @click="zoomOut"
                      ><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png" alt="축소"
                    /></span>
                  </div>
                </div>
              </div>
            </div>
          </b-container>
        </b-row>
      </b-col>
      <b-col md="2" id="list" class="shadow bg-body rounded row justify-content-center scrollcol">
        <b-container class="scrolldiv">
          <b-card
            no-body
            class="overflow-hidden cards"
            style="max-width: 300px"
            v-for="(tour, index) in this.tourList"
            :key="index"
          >
            <b-row no-gutters>
              <b-col md="3">
                <b-card-img :img-src="`${tour.image}`" img-alt="Image" img-top></b-card-img>
              </b-col>
              <b-col>
                <b-card-body id="cardfont">
                  <b-card-title>{{ tour.title }}</b-card-title>
                  <!-- <b-card-text>{{ tour.addr1 }}</b-card-text>
                  <b-card-text>{{ tour.tel }}</b-card-text> -->
                </b-card-body>
              </b-col>
            </b-row>
          </b-card>
        </b-container>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState } from "vuex";
import draggable from "vuedraggable";
import PlanOptionBar from "@/components/plan/PlanOptionBar";

const tourListStore = "tourListStore";

export default {
  name: "PlanView",
  components: {
    draggable,
    PlanOptionBar,
  },
  computed: {
    ...mapState(tourListStore, ["sidoCode", "gugunCode", "contentTypeId", "tourList"]),
  },
  watch: {
    // 디버깅용
    mychoices: function () {
      console.log(this.mychoices[0].name);
    },
    sidoCode: function () {
      let doname;
      switch (this.sidoCode) {
        case 1:
          doname = "서울";
          break;
        case 2:
      }
      this.place = this.sidoCode;
    },
  },
  data() {
    return {
      doname: [
        " ",
        "서울",
        "인천",
        "대전",
        "대구",
        "광주",
        "부산",
        "울산",
        "세종특별자치시",
        "경기도",
        "강원도",
        "충청북도",
        "충청남도",
        "경상북도",
        "전라북도",
        "전라남도",
        "제주도",
      ],
      place: "제주도",
      period: 0,
      start_date: "2022-12-20",
      end_date: "2022-12-20",
      mychoices: [
        { name: "멋진숙소1", type: "숙박" },
        { name: "아름다운숙소2", type: "숙박" },
      ],
      places: [],
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0,
      overlay: null,
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
      script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=84438603ef15ec1f521f260675951d5f";
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
        let image;
        if (tour.image) image = tour.image;
        else image = "/defaultImage.jpg";
        let mapx = tour.mapx;
        let mapy = tour.mapy;
        let tel = tour.tel;
        let title = tour.title;
        let zipcode = tour.zipcode;
        positions.push({
          content: `<div class="wrap" style="border-radius: 5px; margin-top: 3%;">
        <div class="info" style="border-radius: 5px; background-color: white;">
          <div class="title" style=" border-radius: 5px; background-color: #ffdbdb; padding-left:5px; padding-right:5px;">${title}
         </div>
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
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap");

#main {
  margin: 50px;
}
#mainrow {
  max-height: 950px;
  min-height: 950px;
}
#cardfont > * {
  font-size: 15px;
}
#choicediv {
  overflow-y: scroll;
  min-height: 700px;
  max-height: 700px;
}
.scrollcol {
  max-height: 950px;
}
.scrolldiv {
  max-height: 950px;
  overflow-y: scroll;
}
.cards {
  margin-top: 20px;
  margin-bottom: 20px;
}
.choice > * {
  /* font-family: "Noto Sans KR", sans-serif; */
  font-weight: bold;
  color: #13151f;
  text-align: center;
  padding: 10px 0;
}

.btn-delete {
  float: right;
  background-color: transparent;
  border-style: none;
  border: none;
  box-shadow: none;
  height: auto;
}

.card {
  border: 1px solid lightgray;
}
.wrap {
  margin-top: 5%;
  height: 100%;
}
.map_wrap {
  position: relative;
  overflow: hidden;
  /* min-height: 300px; */
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
#map >>> .wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  min-height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 18px;
  line-height: 1.5;
  text-align: center;
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
.info >>> .title {
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
