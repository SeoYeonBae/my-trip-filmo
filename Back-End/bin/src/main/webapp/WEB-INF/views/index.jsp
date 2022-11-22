<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/headerNoLogin.jsp"%>

<!-- cover start -->
<header class="masthead">
	<div
		class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
		<div class="d-flex justify-content-center">
			<div class="text-center">
				<h1 class="mx-auto text-uppercase">
					Enjoy<br />Trip
				</h1>
				<h2 class="text-black-50 mx-auto mt-3 mb-5 custom-title">내가 그리는
					나만의 여행</h2>
				<a class="btn btn-primary" href="#recommend">let's trip</a>
			</div>
		</div>
	</div>
</header>
<!-- cover end -->
<!-- 여행 추천 start -->
<section class="text-center justify-content-center mb-5" id="recommend">
	<div class="container pt-5">
		<h4 class="fw-bold mb-5 custom-title">목적지가 없다면? 취향 저격 추천 명소</h4>
	</div>
	<div class="align-content-center justify-content-center d-flex">
		<div class="image me-5">
			<a href="${root}/tourlist/today/12">  <img class="image__img"
				src="./assets/img/tourSpot.png" />
				<div class="image__overlay mouse-pointer">
					<p class="image__description">오늘의 관광지</p>
				</div>
			</a>
		</div>
		<div class="image me-5">
			 <a href="${root}/tourlist/today/15"> <img class="image__img"
				src="./assets/img/festival.jpg" />
				<div class="image__overlay mouse-pointer">
					<p class="image__description">오늘의 행사/공연/축제</p>
				</div>
			</a>
		</div>
		<div class="image">
			<a href="${root}/tourlist/today/39"> <img class="image__img"
				src="./assets/img/restaurant.jpg" />
				<div class="image__overlay mouse-pointer">
					<p class="image__description">오늘의 맛집</p>
				</div>
			</a>
		</div>
	</div>
	<hr class="m-5">
</section>
<!-- 여행 추천 end -->

<!-- 목적지 근처 정보 start -->
<section class="text-center mt-5" id="#">
	<div class="container px-4 px-lg-5">
		<!-- Featured Project Row-->
		<div class="row gx-0 mb-4 mb-lg-5 align-items-center menu-box">
			<div class="col-xl-8 col-lg-7">
				<img class="img-fluid mb-3 mb-lg-0 w-100"
					src="assets/img/airplane.png" alt="..." />
			</div>
			<div class="col-xl-4 col-lg-5">
				<div class="featured-text text-center text-lg-left">
					<h4 class="fw-bold">나만의 여행 계획하기</h4>
					<p class="text-black-50 mb-0">목적지만 선택하면 주변 정보가 한 눈에</p>
					<a class="btn btn-primary mt-3" href="${root}/map">어디로 떠나시나요?</a>
				</div>
			</div>
		</div>
</section>
<!-- 목적지 근처 정보 end -->

<!-- 일출일몰 정보 start -->
<section class="text-center mt-5" id="#">
	<div class="container px-4 px-lg-5">
		<!-- Featured Project Row-->
		<div class="row gx-0 mb-4 mb-lg-5 align-items-center menu-box">
			<div class="col-xl-4 col-lg-5">
				<div class="featured-text text-center text-lg-left">
					<h4 class="fw-bold">일출 일몰</h4>
					<p class="text-black-50 mb-0">
						인생샷을 남기고 싶다면?<br> 해가 뜨고 해가 지는 아름다운 여행지
					</p>
					<a class="btn btn-primary mt-3" href="${root}/sun">그 날의 해는?</a>
				</div>
			</div>
			<div class="col-xl-8 col-lg-7">
				<img class="img-fluid mb-3 mb-lg-0 w-100" src="assets/img/sun.jpg"
					alt="..." />
			</div>
		</div>
</section>
<!-- 일출일몰 정보 end -->

<!-- 날씨 정보 start -->
<section class="text-center mt-5" id="#">
	<div class="container px-4 px-lg-5">
		<!-- Featured Project Row-->
		<div class="row gx-0 mb-4 mb-lg-5 align-items-center menu-box">
			<div class="col-xl-8 col-lg-7">
				<img class="img-fluid mb-3 mb-lg-0 w-100"
					src="assets/img/weather.jpg" alt="..." />
			</div>
			<div class="col-xl-4 col-lg-5">
				<div class="featured-text text-center text-lg-left">
					<h4 class="fw-bold">날씨 정보</h4>
					<p class="text-black-50 mb-0">
						전국 주요 관광지의<br> 주간 날씨를 확인해보세요
					</p>
					<a class="btn btn-primary mt-3" href="${root}/weather">이번주 날씨는?</a>
				</div>
			</div>
		</div>
</section>

  <!-- 날씨 start -->
  <section class="text-center justify-content-center mb-5" id="#">
	<div class="container pt-5">
		<h4 class="fw-bold mb-5 custom-title">전국 팔도 주간 날씨</h4>
	</div>
	<div id="weather" class="container px-4 px-lg-5 align-content-center justify-content-center d-flex">
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
  </section>
<!-- 날씨 정보 end -->

<!-- 국내 여행 추천 start -->
<section class="text-center" id="#">
	<hr class="m-5">
	<div class="container mb-5">
		<div class="row justify-content-center">
			<h4 class="fw-bold mt-5 mb-5">내 나라 구석구석</h4>
			<ul class="d-flex justify-content-center mb-5">
				<li class="nook-item mouse-pointer"><a href="${root }/gooseok/sooncheon" class="gooseok" data-value="sooncheon"> <img
						class="rounded-circle my-nook" src="assets/img/sooncheon.jpg"
						alt="">
						<div class="m-3" val>
							<h5 class="fw-bold">순천시</h5>
							<p>
								전통과 역사가<br>살아 숨쉬는 도시
							</p>
						</div>
				</a></li>
				<li class="nook-item mouse-pointer"><a
					href="${root }/gooseok/seogwipo"  class="gooseok" data-value="seogwipo"> <img
						class="rounded-circle my-nook" src="assets/img/jeju.jpg" alt="">
						<div class="m-3">
							<h5 class="fw-bold">서귀포시</h5>
							<p>
								천혜의 자연경관이 수려한<br>세계적 휴양 관광지
							</p>
						</div></a></li>
				<li class="nook-item mouse-pointer"><a
					href="${root }/gooseok/gangreung"  class="gooseok" data-value="gangreung"><img
						class="rounded-circle my-nook" src="assets/img/gangreung.jpg"
						alt="">
						<div class="m-3">
							<h5 class="fw-bold">강릉시</h5>
							<p>
								전국 서퍼와<br>커피가 가득한 이 곳
							</p>
						</div></a></li>
				<li class="nook-item mouse-pointer"><a
					href="${root }/gooseok/haewoondae"  class="gooseok" data-value="haewoondae"><img
						class="rounded-circle my-nook" src="assets/img/haewoondae.jpg"
						alt="">
						<div class="m-3">
							<h5 class="fw-bold">해운대구</h5>
							<p>
								파란 하늘과<br>바다가 숨 쉬는 곳
							</p>
						</div></a></li>
				<li class="nook-item mouse-pointer"><a
					href="${root }/gooseok/ganghwa"  class="gooseok" data-value="ganghwa"><img
						class="rounded-circle my-nook" src="assets/img/ganghwa.jpg" alt="">
						<div class="m-3">
							<h5 class="fw-bold">강화군</h5>
							<p>
								크고 작은 15개 섬이<br>한 곳에
							</p>
						</div></a></li>
			</ul>
		</div>
	</div>
</section>

<!-- 게시판 공지사항 section start -->
<div class="container w-75">
	<div class="row mt-3 mb-5">
		<div class="col-md-6">
			<div class="d-flex">
				<h3 class="fw-bold mt-5">
					게시판
					<h6 class="mt-5" style="line-height: 40px">
						<a href="${root}/blist">&nbsp;&nbsp;바로가기</a>
					</h6>
				</h3>
			</div>
			<table class="table table-hover">
				<thead>
					<tr class="text-center">

						<th scope="col">제목</th>
						<th scope="col">작성자</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody id="boardlist">
				</tbody>
			</table>
		</div>
		<div class="col-md-6">
			<div class="d-flex">
				<h3 class="fw-bold mt-5">
					공지사항
					<h6 class="mt-5" style="line-height: 40px">
						<a href="${root}/nlist">&nbsp;&nbsp;바로가기</a>
					</h6>
				</h3>
			</div>

			<table class="table table-hover">
				<thead>
					<tr class="text-center">
						<th scope="col">제목</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody id="noticelist">
				</tbody>
			</table>
		</div>
	</div>
</div>
<!-- 게시판 공지사항 section end -->

<script>

// 게시판, 공지사항 3개 미리 얻어오기
window.onload = function () {
	fetch("${root}/board/preview")
	.then(response => response.json())
	.then(data => preview(data, "#boardlist"));
	
	fetch("${root}/notice/preview")
	.then(response => response.json())
	.then(data => preview(data, "#noticelist"));
}

function preview(list, id) {
	let bodycontent = ``;
	list.forEach(function(member) {
		bodycontent += `
			<tr class="text-center">
      <td class="text-start">\${member.subject}</td>`;
    if (id == "#boardlist") {
    	bodycontent += `<td>\${member.userId}</td>`;
    }
    bodycontent += `
      <td>\${member.hit}</td>
    </tr>
		`;
	})
	document.querySelector(id).innerHTML = bodycontent;
}

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

<%@include file="/WEB-INF/views/common/footer.jsp"%>