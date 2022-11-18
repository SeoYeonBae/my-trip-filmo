<template>
  <b-container class="container text-center mb-5">
    <h4 class="fw-bold mb-5 mt-5">목적지가 없다면? 취향 저격 추천 명소</h4>
    <b-container fluid>
      <div class="recommend-cards row">
        <b-col class="card me-1" @click="$router.push({ name: 'recommend', params: { typeid: 12 } })">
          <b-img class="img" fluid :src="require('@/assets/img/TodayTourSpot.png')" alt="관광명소"></b-img>
          <p class="desc">오늘의 관광지</p>
        </b-col>
        <b-col class="card ms-1 me-1" @click="$router.push({ name: 'recommend', params: { typeid: 15 } })">
          <b-img class="img" fluid :src="require('@/assets/img/TodayFestival.jpg')" alt="축제"></b-img>
          <p class="desc">오늘의 축제</p>
        </b-col>
        <b-col class="card ms-1" @click="$router.push({ name: 'recommend', params: { typeid: 39 } })">
          <b-img class="img" fluid :src="require('@/assets/img/TodayRestaurant.jpg')" alt="맛집"></b-img>
          <p class="desc">오늘의 맛집</p>
        </b-col>
      </div>
    </b-container>
  </b-container>
</template>

<script>
export default {
  name: "MainTourRecommend",
  data() {
    return {
      theme: [{ "오늘의 관광지": 12 }, { "오늘의 축제": 15 }, { "오늘의 맛집": 39 }],
      active: false,
    };
  },
};
</script>

<style scoped>
img {
  width: 300px;
  border-radius: 10px;
  position: relative;
}
h4 {
  font-weight: bold;
}
.recommend-cards {
  position: relative;
  font-weight: bold;
  margin-top: 10px;
  top: 10%;
}
.main {
  text-align: center;
  font-size: 2em;
}
.card {
  margin: 0 auto;
  position: relative;
  border-color: transparent;
  width: 100%;
  height: 100%;
}
.img:hover {
  filter: brightness(50%);
  transform: scale(1.1);
  transition: transform 0.5s;
}
p {
  color: white;
  text-shadow: 1px 2px 0 #000;
  font-size: 25px;
  position: absolute;
  text-align: center;
  top: 90%;
  left: 45%;
  transform: translate(-50%, -50%);
}
</style>
