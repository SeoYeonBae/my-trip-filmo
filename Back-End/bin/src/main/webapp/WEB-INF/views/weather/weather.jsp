<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include
file="/WEB-INF/views/common/headerNoLogin.jsp"%>

<link href="${root}/assets/css/notice.css" rel="stylesheet" />
<style></style>
<!-- 날씨 start -->
<div class="container mt-5 pt-5 mb-5">
  <div class="col-md-12 col-12">
    <h2 class="my-3 py-3 shadow p-3 mb-5 bg-white rounded text-center">
      <p class="fw-bold">전국 주간 날씨</p>
    </h2>
  </div>
  <div id="weather" class="row">
    <!-- 서울 -->
    <a
      class="weatherwidget-io col"
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
      class="weatherwidget-io col"
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
      class="weatherwidget-io col"
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
      class="weatherwidget-io col"
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
      class="weatherwidget-io col"
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
      class="weatherwidget-io col"
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
      class="weatherwidget-io col"
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
      class="weatherwidget-io"
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
<script>
  !(function (d, s, id) {
    var js,
      fjs = d.getElementsByTagName(s)[0];
    if (!d.getElementById(id)) {
      js = d.createElement(s);
      js.id = id;
      js.src = "https://weatherwidget.io/js/widget.min.js";
      fjs.parentNode.insertBefore(js, fjs);
    }
  })(document, "script", "weatherwidget-io-js");
</script>
<!-- 날씨 end -->
<%@include file="/WEB-INF/views/common/footer.jsp"%>
