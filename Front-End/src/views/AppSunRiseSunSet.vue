<template>
  <b-container style="margin-top: 120px">
    <h3 class="font-weight-bold">그 날의 일출 일몰</h3>
    <h5 class="mt-4 mb-4">{{ subtitle }}</h5>
    <b-row class="justify-content-md-center mb-4">
      <b-col col md="4">
        <b-form-datepicker
          id="datepicker"
          placeholder="날짜를 선택해주세요"
          v-model="locdate"
          menu-class="w-100"
          calendar-width="100%"
          class="mb-2"
          @input="changeLocdate()"
        ></b-form-datepicker>
      </b-col>
      <b-col col md="4">
        <b-card no-body class="overflow-hidden" style="max-width: 540px">
          <b-row no-gutters>
            <b-col md="3">
              <b-card-header header="일출"></b-card-header>
            </b-col>
            <b-col md="6">
              <b-card-text>{{ sunrise }}</b-card-text>
            </b-col>
          </b-row>
        </b-card>
      </b-col>
      <b-col col md="4">
        <b-card no-body class="overflow-hidden" style="max-width: 540px">
          <b-row no-gutters>
            <b-col md="3">
              <b-card-header header="일몰"></b-card-header>
            </b-col>
            <b-col md="6">
              <b-card-text>{{ sunset }}</b-card-text>
            </b-col>
          </b-row>
        </b-card>
      </b-col>
    </b-row>
    <div id="map" style="width: 100%; height: 500px"></div>
  </b-container>
</template>

<script>
export default {
  name: "AppSunRiseSunSer",
  data() {
    return {
      map: null,
      markers: [],
      latitude: 0,
      longitude: 0,
      dateSelect: "",
      locdate: "",
      locdateAfterReplace: "",
      subtitle: "여행 날짜와 위치를 선택해주세요!",
      sunrise: "",
      sunset: "",
    };
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
  },
  methods: {
    addKakaoMapScript() {
      var script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=84438603ef15ec1f521f260675951d5f";
      document.head.appendChild(script);
    },
    initMap() {
      // 지도 표시
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.5012743, 127.039585),
        level: 12,
      };
      this.longitude = 12704;
      this.latitude = 3750;
      this.map = new kakao.maps.Map(container, options);
      // 마커 등록
      var marker = new kakao.maps.Marker({
        position: this.map.getCenter(),
      });
      marker.setMap(this.map);
      // 마커 위치를 클릭한 위치로 옮기고 위도 경도 정보 받아옴
      kakao.maps.event.addListener(this.map, "click", (mouseEvent) => {
        var latlng = mouseEvent.latLng;

        marker.setPosition(latlng);

        let longitudeDo = parseInt(latlng.getLng());
        let longitudeBun = parseInt((latlng.getLng() - longitudeDo) * 60);
        this.longitude = longitudeDo * 100 + longitudeBun;

        let latitudeDo = parseInt(latlng.getLat());
        let latitudeBun = parseInt((latlng.getLat() - latitudeDo) * 60);
        this.latitude = latitudeDo * 100 + latitudeBun;

        if (this.locdateAfterReplace) {
          this.areaBasedListRequest();
        }
      });
    },
    async areaBasedListRequest() {
      const url =
        "http://apis.data.go.kr/B090041/openapi/service/RiseSetInfoService/getLCRiseSetInfo";
      const serviceKey =
        "xm8WNz89IdYlIC%2BkUC1JXCml8iXXAZBFyO7I1wb7MsvVwbyEJxWISJH3iXOGnwUZqT7ARxQvSenoe9CI03l0lg%3D%3D";
      let params =
        "longitude=" +
        this.longitude +
        "&latitude=" +
        this.latitude +
        "&locdate=" +
        this.locdateAfterReplace +
        "&dnYn=N&ServiceKey=" +
        serviceKey;

      await fetch(`${url}?${params}`)
        .then((response) => response.text())
        .then((data) => {
          this.showSunInfo(data);
        });
    },
    changeLocdate() {
      this.locdateAfterReplace = this.locdate.replaceAll("-", "");
      this.areaBasedListRequest();
    },
    showSunInfo(data) {
      let parser = new DOMParser();
      const xml = parser.parseFromString(data, "application/xml");
      let item = xml.querySelector("item");
      if (item) {
        // console.log(item);
        let sunriseTime = item.querySelector("sunrise").textContent;
        let sunriseHour = sunriseTime.substring(0, 2);
        let sunriseMinutes = sunriseTime.substring(2, 4);
        let sunsetTime = item.querySelector("sunset").textContent;
        let sunsetHour = sunsetTime.substring(0, 2);
        let sunsetMinutes = sunsetTime.substring(2, 4);
        this.subtitle =
          item.querySelector("location").textContent + "의 " + this.locdate + " 일출 일몰 시간";
        this.sunrise = sunriseHour + "시 " + sunriseMinutes + "분";
        this.sunset = sunsetHour + "시 " + sunsetMinutes + "분";
      }
    },
  },
};
</script>

<style scoped>
h3,
h4,
h5 {
  text-align: center;
}
#suninfo {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
#datepicker__outer_ {
  height: 50px;
  margin-bottom: 5%;
  align-items: center;
  justify-content: center;
}
.card {
  height: 50px;
}
.card-text {
  line-height: 50px;
}
.col-md-4 {
  text-align: center;
  align-items: center;
  justify-content: center;
}
</style>
