<template>
  <b-container>
    <b-card no-body class="overflow-hidden mb-5" style="max-width: 100%">
      <b-row no-gutters>
        <b-col md="6">
          <b-card-img
            :src="require('@/assets/img/airplane.png')"
            alt="Image"
            class="rounded-0"
          ></b-card-img>
        </b-col>
        <b-col md="6">
          <b-card-body title="전국 관광지 안내">
            <b-card-text>목적지만 선택하면 주변 정보가 한 눈에</b-card-text>
            <b-button @click="moveTourList">구경하기</b-button>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>
    <b-card no-body class="overflow-hidden mb-5" style="max-width: 100%">
      <b-row no-gutters>
        <b-col md="6">
          <b-card-body title="여행 계획하기">
            <b-card-text>여행의 주인공은 바로 나</b-card-text>
            <b-card-text>나만의 여행 일정을 만들어보세요!</b-card-text>
            <b-button @click="movePlan">두근두근 일정 짜기</b-button>
          </b-card-body>
        </b-col>
        <b-col md="6">
          <b-card-img
            :src="require('@/assets/img/airplane.png')"
            alt="Image"
            class="rounded-0"
          ></b-card-img>
        </b-col>
      </b-row>
    </b-card>
    <b-card no-body class="overflow-hidden mb-5" style="max-width: 100%">
      <b-row no-gutters>
        <b-col md="6">
          <b-card-img
            :src="require('@/assets/img/sun.jpg')"
            alt="Image"
            class="rounded-0"
          ></b-card-img>
        </b-col>
        <b-col md="6">
          <b-card-body title="일출 일몰 정보">
            <b-card-text>인생샷을 남기고 싶다면?</b-card-text>
            <b-card-text>해가 뜨고 해가 지는 아름다운 여행지</b-card-text>
            <b-button @click="moveSunRiseSunSet">그 날의 해는?</b-button>
          </b-card-body>
        </b-col>
      </b-row>
    </b-card>
    <hr class="m-5" />
  </b-container>
</template>

<script>
export default {
  name: "MainContentsPanel",
  methods: {
    moveTourList() {
      this.$router.push({ name: "tour" });
    },
    moveSunRiseSunSet() {
      this.$router.push({ name: "sun" });
    },
    movePlan() {
      this.$router.push({ name: "plan" });
    },
  },
};
</script>

<style scoped>
button {
  margin-top: 16px;
  background-color: #d4fcee;
  border: 0;
  color: black;
  font-weight: bold;
  padding: 16px;
}

button:hover {
  background-color: #dfe4ff;
  color: white;
}
div.card-body {
  text-align: center;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.card {
  border: 0px;
  background-color: #fffbea;
}

.card-text {
  margin: 5px;
}
</style>
