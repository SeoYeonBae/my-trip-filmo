<template>
  <footer>
    <div class="footertop">
      <b-row>
        <b-col md="3">
          <b-row>
            <b-col md="4">
              <p class="toptitle">이용안내 |</p>
            </b-col>
            <b-col>
              <p class="clicktext" @click="openAbout">회사소개</p>
            </b-col>
          </b-row>
          <b-row>
            <b-col md="4">
              <p class="toptitle">고객지원 |</p>
            </b-col>
            <b-col md="3">
              <p class="clicktext" @click="$router.push({ name: 'notice' })">공지사항</p>
            </b-col>
            <b-col>
              <p class="clicktext" @click="$router.push({ name: 'appFaq' })">자주 묻는 질문</p>
            </b-col>
          </b-row>
        </b-col>
        <b-col md="6"></b-col>
        <b-col>
          <p class="toptitle mb-2">고객 상담</p>
          <p class="textlight mb-0">페이지 하단 챗봇을 이용해주세요.</p>
          <p class="textlight mb-0">운영시간 평일 09:00-18:00 (토∙일, 공휴일 휴무)</p>
          <p class="textlight mb-0">점심시간 평일 13:00 - 14:00</p>
        </b-col>
      </b-row>
    </div>
    <b-row class="footermiddle mt-4">
      <b-col md="10">
        <div>
          <p class="textlight mb-0">(주)여행의 주연</p>
          <p class="textlight mb-0">대표: 강윤주 배서연</p>
          <p class="textlight mb-0">주소: 서울특별시 강남구 역삼동 테헤란로 212</p>
          <p class="textlight mb-0">mail: tjdus2033@naver.com</p>
          <p class="textlight mb-0">tel: 010-1234-5678</p>
        </div>
      </b-col>
      <b-col>
        <b-row>
          <b-col md="7"></b-col>
          <b-col class="p-0 mx-2 text-right">
            <font-awesome-icon icon="fa-brands fa-instagram" />
          </b-col>
          <b-col class="p-0">
            <font-awesome-icon icon="fa-solid fa-comment" />
          </b-col>
        </b-row>
      </b-col>
    </b-row>
    <div class="footerbottom mt-4">
      <p class="textlight mb-0">Copyright &copy; (주)여행의 주연</p>
    </div>
  </footer>
</template>

<script>
export default {
  name: "TheFooter",
  methods: {
    openAbout() {
      let routeUrl = this.$router.resolve({
        path: "/about",
      });
      window.open(routeUrl.href, "_blank");
    },
  },
};
</script>

<style scoped>
* {
  font-size: 15px;
}
footer {
  margin-top: 10%;
  background-color: aliceblue;
  color: black;
  position: relative;
  padding: 5%;
  padding-bottom: 3%;
}
.toptitle {
  font-weight: bold;
}
.footertop.row.col {
  text-align: left;
}
.textlight {
  color: gray;
  font-size: 13px;
}
.footerbottom {
  text-align: right;
}
.clicktext:hover {
  cursor: pointer;
}
</style>
