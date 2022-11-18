<template>
  <div class="container text-center mb-5">
    <h4 class="mb-5 custom-title">전국 팔도 주간 날씨</h4>
    <div
      id="weather"
      class="container px-4 px-lg-5 align-content-center justify-content-center d-flex"
    >
      <!-- 서울 -->
      <a
        class="weatherwidget-io me-2"
        href="https://forecast7.com/en/37d57126d98/seoul/"
        data-label_1="SEOUL"
        data-label_2="WEATHER"
        data-font="Fira Sans"
        data-icons="Climacons Animated"
        data-mode="Current"
        data-days="3"
        data-theme="weather_one"
        >SEOUL WEATHER</a
      >
      <!-- 대전 충청도 -->
      <a
        class="weatherwidget-io me-2"
        href="https://forecast7.com/en/36d35127d38/daejeon/"
        data-label_1="DAEJEON"
        data-label_2="WEATHER"
        data-font="Fira Sans"
        data-icons="Climacons Animated"
        data-mode="Current"
        data-days="3"
        data-theme="weather_one"
        >DAEJEON WEATHER</a
      >
      <!-- 강릉 강원도 -->
      <a
        class="weatherwidget-io me-2"
        href="https://forecast7.com/en/37d75128d88/gangneung-si/"
        data-label_1="GANGNEUNG"
        data-label_2="WEATHER"
        data-font="Fira Sans"
        data-icons="Climacons Animated"
        data-mode="Current"
        data-days="3"
        data-theme="weather_one"
        >GANGNEUNG WEATHER</a
      >
      <!-- 전주 전라북도 -->
      <a
        class="weatherwidget-io me-2"
        href="https://forecast7.com/en/35d82127d15/jeonju-si/"
        data-label_1="JEONJU"
        data-label_2="WEATHER"
        data-font="Fira Sans"
        data-icons="Climacons Animated"
        data-mode="Current"
        data-days="3"
        data-theme="weather_one"
        >JEONJU WEATHER</a
      >
      <!-- 광주 전라남도 -->
      <a
        class="weatherwidget-io me-2"
        href="https://forecast7.com/en/35d16126d85/gwangju/"
        data-label_1="GWANGJU"
        data-label_2="WEATHER"
        data-font="Fira Sans"
        data-icons="Climacons Animated"
        data-mode="Current"
        data-days="3"
        data-theme="weather_one"
        >GWANGJU WEATHER</a
      >
      <!-- 대구 경상북도 -->
      <a
        class="weatherwidget-io me-2"
        href="https://forecast7.com/en/35d87128d60/daegu/"
        data-label_1="DAEGU"
        data-label_2="WEATHER"
        data-font="Fira Sans"
        data-icons="Climacons Animated"
        data-mode="Current"
        data-days="3"
        data-theme="weather_one"
        >DAEGU WEATHER</a
      >
      <!-- 부산 경상남도-->
      <a
        class="weatherwidget-io me-2"
        href="https://forecast7.com/en/35d18129d08/busan/"
        data-label_1="BUSAN"
        data-label_2="WEATHER"
        data-font="Fira Sans"
        data-icons="Climacons Animated"
        data-mode="Current"
        data-days="3"
        data-theme="weather_one"
        >BUSAN WEATHER</a
      >
      <!-- 제주도 -->
      <a
        class="weatherwidget-io me-2"
        href="https://forecast7.com/en/33d50126d53/jeju-si/"
        data-label_1="JEJU"
        data-label_2="WEATHER"
        data-font="Fira Sans"
        data-icons="Climacons Animated"
        data-mode="Current"
        data-days="3"
        data-theme="weather_one"
        >JEJU WEATHER</a
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "MainWeather",
  methods: {
    mounted() {
      let recaptchaScript = document.createElement("script");
      recaptchaScript.setAttribute("src", "https://weatherwidget.io/js/widget.min.js");
      document.head.appendChild(recaptchaScript);

      // !(function (d, s, id) {
      //   var js,
      //     fjs = d.getElementsByTagName(s)[0];
      //   if (!d.getElementById(id)) {
      //     js = d.createElement(s);
      //     js.id = id;
      //     js.src = "https://weatherwidget.io/js/widget.min.js";
      //     fjs.parentNode.insertBefore(js, fjs);
      //   }
      // })(document, "script", "weatherwidget-io-js");
    },
  },
};
</script>

<style scoped>
h4 {
  font-weight: bold;
}
</style>
