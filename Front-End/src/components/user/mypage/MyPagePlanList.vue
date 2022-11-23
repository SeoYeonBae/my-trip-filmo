<template>
  <b-col md="8">
    <b-jumbotron class="mt-5">
      <font-awesome-icon icon="fa-plane-departure" class="fa-2x mb-3" />
      <h4 class="mb-3">내가 만든 여행 계획</h4>
      <h5>카드를 눌러 여행 계획의 상세 정보를 확인해 보세요</h5>

      <hr class="my-4" />

      <b-card no-body class="overflow-hidden" style="max-width: 540px">
        <b-row no-gutters @click="$router.push({ name: 'plandetail' })">
          <b-col md="6">
            <b-card-img
              :src="require('@/assets/img/cover.gif')"
              alt="Image"
              class="rounded-0"
            ></b-card-img>
          </b-col>
          <b-col md="6">
            <b-card-body title="제주도 3박 4일">
              <b-card-text> 2022-11-17 - 2022-11-19 </b-card-text>
              <b-card-text> 인원: 총 4 명 </b-card-text>
            </b-card-body>
          </b-col>
        </b-row>
      </b-card>
    </b-jumbotron>
  </b-col>
</template>

<script>
export default {
  name: "MyPagePlanList",
  components: {},
};
</script>

<style scoped>
.row {
  margin: 0px;
  padding: 0px;
  justify-content: center;
}
.jumbotron {
  border: none;
  background-color: transparent;
  width: 80%;
  padding-top: 0%;
}
</style>
