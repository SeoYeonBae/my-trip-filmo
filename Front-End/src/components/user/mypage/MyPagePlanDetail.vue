<template>
  <b-col md="8">
    <b-jumbotron class="mt-5">
      <font-awesome-icon icon="fa-plane-departure" class="fa-2x mb-3" />
      <b-button @click="$router.push({ name: 'planlist' })">이전으로</b-button>
      <h4 class="mb-3">제주도 3박 4일</h4>
      <h5>날짜: 2022-11-17 - 2022-11-19</h5>
      <h5>일행: 강윤주, 배서연, 한선희, 조서영</h5>
      <h5>여행 관련 체크 리스트 들어갈 공간</h5>
      <hr class="my-4" />
      <b-card no-body class="overflow-hidden" style="max-width: 540px">
        <b-row no-gutters>
          <b-col md="6">
            <b-card-img
              :src="require('@/assets/img/cover.gif')"
              alt="Image"
              class="rounded-0"
            ></b-card-img>
          </b-col>
          <b-col md="6">
            <b-card-body title="관광지 이름">
              <b-card-text> 주소 </b-card-text>
              <b-card-text> 전화번호 </b-card-text>
            </b-card-body>
          </b-col>
        </b-row>
      </b-card>
    </b-jumbotron>
  </b-col>
</template>

<script>
export default {
  name: "MyPagePlanDetail",
  components: {},
};
</script>

<style scoped>
.row {
  margin: 0px;
  padding: 0px;
  justify-content: center;
}
button {
  background-color: #dfe4ff;
  border: 0;
  color: black;
  font-weight: bold;
  padding: 16px;
  float: right;
}
button:hover {
  background-color: #d4fcee;
  color: black;
}
.jumbotron {
  border: none;
  background-color: transparent;
  width: 80%;
  padding-top: 0%;
}
</style>
