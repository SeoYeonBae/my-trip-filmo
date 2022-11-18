<template>
  <b-container>
    <b-img :src="require('@/assets/img/mypage.jpg')" alt="Fluid image"></b-img>
    <p>Welcome us!</p>
    <!-- <p>여행의 주연이 되어보세요</p> -->
  </b-container>
</template>

<script>
export default {
  name: "MyPageHeader",
};
</script>

<style scoped>
.container {
  width: 100%;
  margin: 0;
  padding: 0;
}
img {
  width: 100%;
  height: auto;
  background-size: cover;
  filter: brightness(50%);
  position: relative;
}
p {
  position: absolute;
  font-size: 50px;
  font-weight: bold;
  color: white;
  text-shadow: 1px 2px 0 #000;
  text-align: center;
  top: 23%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
