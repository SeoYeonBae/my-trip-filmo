<template>
  <b-row>
    <b-col md="2" id="choice" class="shadow p-3 bg-body rounded text-center">
      <h3 id="region">{{ place }}</h3>
      <h3 id="period">3 DAY</h3>
      <p>2022.12.20-2022.12.21</p>
      <div class="px-3">
        <draggable v-model="mychoices">
          <transition-group>
            <div v-for="(choice, index) in mychoices" v-bind:key="index" class="choiced-places">
              <b-card
                img-src="https://cdn.travie.com/news/photo/first/201710/img_19975_1.jpg"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 10rem"
                class="mb-2"
              >
                <b-card-text>
                  <font-awesome-icon icon="fa-solid fa-bed" />
                  {{ choice.name }}
                  <button class="btn-delete">
                    <font-awesome-icon icon="fa-solid fa-circle-xmark" style="color: red" />
                  </button>
                </b-card-text>
              </b-card>
            </div>
          </transition-group>
        </draggable>
      </div>
    </b-col>
    <b-col>
      <plan-option-bar></plan-option-bar>
    </b-col>
    <b-col md="10" class="myplan shadow p-3 mb-5 bg-body rounded"> </b-col>
    <b-row id="content">
      <b-col md="1" class="type">
        <button type="button">관광지</button>
        <button type="button">문화시설</button>
        <button type="button">레포츠</button>
        <button type="button">식당</button>
        <button type="button">쇼핑</button>
        <button type="button">숙박</button>
        <button type="button">축제</button>
      </b-col>

      <!-- <b-col md="8" id="map" class="mb-5"></b-col> -->
      <tour-list-map></tour-list-map>
      <b-col id="list" class="shadow p-3 mb-5 bg-body rounded row justify-content-center">
        <div class="col-lg-5 mb-4 mb-lg-0 places">
          <b-card
            overlay
            img-src="https://mdbcdn.b-cdn.net/img/Photos/Horizontal/Nature/4-col/img%20(73).webp"
            img-alt="Card Image"
            text-variant="white"
            title="명소1"
          ></b-card>
          <b-card
            overlay
            img-src="https://mdbcdn.b-cdn.net/img/Photos/Vertical/mountain1.webp"
            img-alt="Card Image"
            text-variant="white"
            title="명소3"
          ></b-card>
        </div>

        <div class="col-lg-5 mb-4 mb-lg-0 places">
          <b-card
            overlay
            img-src="https://mdbcdn.b-cdn.net/img/Photos/Vertical/mountain2.webp"
            img-alt="Card Image"
            text-variant="white"
            title="명소2"
          ></b-card>
          <b-card
            overlay
            img-src="https://mdbcdn.b-cdn.net/img/Photos/Horizontal/Nature/4-col/img%20(73).webp"
            img-alt="Card Image"
            text-variant="white"
            title="명소4"
          ></b-card>
        </div>
      </b-col>
    </b-row>
  </b-row>
</template>

<script>
import draggable from "vuedraggable";
import PlanOptionBar from "@/components/plan/PlanOptionBar";

export default {
  name: "PlanView",
  components: {
    draggable,
    PlanOptionBar,
  },
  data() {
    return {
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
    };
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
  },
  computed() {},
  methods: {
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
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap");

#choice > * {
  font-family: "Noto Sans KR", sans-serif;
  color: #13151f;
  text-align: center;
  padding: 10px 0;
}
#map {
  border-radius: 20px;
  margin: 0 auto;
  width: 90%;
  height: 700px;
}
.myplan {
  max-height: 200px;
}
.choiced-places {
  margin-right: 20px;
}
.choiced-places:hover {
  filter: brightness(90%);
}
.places > * {
  margin-bottom: 10px;
}
.places > * :hover {
  filter: brightness(80%);
}
.type > * {
  padding: 30px;
  margin: 20px;
  width: 80%;
  background-color: #fffbea;
  border: 0;
  color: black;
  font-weight: bold;
  padding: 16px;
}
button:hover,
button:active {
  background-color: transparent;
  color: #dfe4ff;
}
.btn-delete {
  float: right;
  background-color: transparent;
  border-style: none;
  border: none;
  box-shadow: none;
  height: auto;
}
img {
  max-width: 150px;
}
</style>
