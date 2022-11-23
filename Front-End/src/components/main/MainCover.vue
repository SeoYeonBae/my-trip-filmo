<template>
  <b-container>
    <div class="cropimage">
      <b-img :src="require('@/assets/img/cover.gif')"></b-img>
      <div class="covertext">여행의 주연이 다 준비했주연</div>
      <div class="coverbutton">
        <b-button
          >About 여행의 주연 &nbsp;
          <font-awesome-icon icon="fa-solid fa-arrow-right" />
        </b-button>
      </div>
    </div>
    <div class="mt-5">
      <b-row>
        <b-col
          @click="$router.push({ name: 'recommend', params: { typeid: 12 } })"
        >
          <b-img :src="require('@/assets/img/todayTour.png')"></b-img>
          <p>오늘의 관광지</p>
        </b-col>
        <b-col
          @click="$router.push({ name: 'recommend', params: { typeid: 39 } })"
        >
          <b-img :src="require('@/assets/img/todayRestaurant.png')"></b-img>
          <p>오늘의 맛집</p>
        </b-col>
        <b-col
          @click="$router.push({ name: 'recommend', params: { typeid: 15 } })"
        >
          <b-img :src="require('@/assets/img/todayFestival.png')"></b-img>
          <p>오늘의 축제</p>
        </b-col>
        <b-col @click="$router.push({ name: 'tour' })">
          <b-img :src="require('@/assets/img/tourList.png')"></b-img>
          <p>전국 관광지</p>
        </b-col>
        <b-col @click="$router.push({ name: 'plan' })">
          <b-img :src="require('@/assets/img/makePlan.png')"></b-img>
          <p>여행 계획하기</p>
        </b-col>
        <b-col @click="$router.push({ name: 'sun' })">
          <b-img :src="require('@/assets/img/sunset.png')"></b-img>
          <p>일출일몰</p>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
export default {};
</script>

<style scoped>
.container {
  margin-top: 130px;
}
.cropimage {
  width: 100%;
  max-height: 700px;
  overflow: hidden;
  position: relative;
}
.cropimage img {
  width: 100%;
  margin-top: -15%;
  max-height: initial;
}
.covertext {
  text-align: center;
  position: absolute;
  top: 50%;
  left: 10%;
  font-size: 20px;
  color: white;
}
.coverbutton {
  position: absolute;
  top: 75%;
  left: 70%;
}
button {
  background-color: rgba(0, 0, 0, 0.5);
  height: 50px;
  padding-left: 30px;
  padding-right: 30px;
  border-radius: 50px;
  border: 0;
}
button:hover {
  border: 0;
  background-color: rgba(0, 0, 0, 0);
}
.col {
  text-align: center;
}
.col > img {
  width: 50%;
}
.col > p {
  margin-top: 8px;
}
.col:hover {
  cursor: pointer;
}
</style>
