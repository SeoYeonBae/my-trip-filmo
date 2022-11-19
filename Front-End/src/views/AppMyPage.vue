<template>
  <b-container fluid>
    <b-row>
      <b-col md="3" class="text-center">
        <div>
          <b-row id="logo">
            <font-awesome-icon icon="fa-regular fa-address-card" class="fa-2x" />
            <h4>&nbsp;my page</h4>
          </b-row>
        </div>
        <div id="usericon">
          <font-awesome-icon icon="fa-regular fa-face-smile" class="fa-8x" />
          <h4 class="mt-4 mb-3">{{ userInfo.name }}</h4>
          <h5 class="mb-4">id: {{ userInfo.id }}</h5>
        </div>
        <hr />
        <div>
          <b-row>
            <b-button id="btnprofile" class="mb-3" @click="$router.push({ name: 'userinfo' })"
              >내 프로필</b-button
            >
          </b-row>
          <b-row>
            <b-button id="btnplanlist" @click="$router.push({ name: 'planlist' })" class="mb-4"
              >여행 계획 관리</b-button
            >
          </b-row>
          <b-row>
            <b-button id="myarticlelist" @click="$router.push({ name: 'articlelist' })" class="mb-4"
              >내가 쓴 글</b-button
            >
          </b-row>
          <b-row>
            <b-button id="myreplylist" @click="$router.push({ name: 'myreplylist' })" class="mb-4"
              >내가 쓴 댓글</b-button
            >
          </b-row>
        </div>
      </b-col>
      <router-view></router-view>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "AppUser",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
h3 {
  font-family: "Do Hyeon", sans-serif;
}
h4 {
  font-weight: bold;
}
a {
  font-size: 20px;
  color: black;
}
a:hover {
  font-weight: bold;
  color: black;
  text-decoration: none;
  font-size: 25px;
}
.col-md-3 {
  margin-left: 16px;
  padding-bottom: 20%;
}
#usericon {
  padding-top: 15%;
}
hr {
  width: 100%;
}
h5 {
  color: gray;
}
.row {
  justify-content: center;
  width: 100%;
}
#btnprofile,
#btnplanlist,
#myarticlelist,
#myreplylist {
  background-color: transparent;
  color: black;
  border-style: none;
  border: none;
  box-shadow: none;
  font-weight: bold;
  font-size: 18px;
}
#logo {
  justify-content: left;
  margin-top: 10%;
}
</style>
