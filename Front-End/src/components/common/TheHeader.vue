<template>
  <div>
    <b-navbar
      toggleable="lg"
      :class="{
        original_color: scrollPosition < 100,
        change_color: scrollPosition > 100,
      }"
    >
      <b-container>
        <img
          src="@/assets/logo.png"
          @click="$router.push({ name: 'appMain' })"
          alt="logo"
        />
        <b-button id="btn-main" @click="$router.push({ name: 'appMain' })"
          >여행의 주연</b-button
        >

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <b-collapse id="nav-collapse" is-nav>
          <!-- 로그인 했을 때 -->
          <b-navbar-nav class="ml-auto" v-if="userInfo">
            <b-nav-item-dropdown text="여행하기" right>
              <b-dropdown-item @click="$router.push({ name: 'tour' })"
                >관광지 구경</b-dropdown-item
              >
              <b-dropdown-item @click="$router.push({ name: 'plan' })"
                >여행 계획</b-dropdown-item
              >
              <b-dropdown-item @click="$router.push({ name: 'sun' })"
                >일출 일몰</b-dropdown-item
              >
            </b-nav-item-dropdown>

            <b-nav-item-dropdown text="게시판" right>
              <b-dropdown-item @click="$router.push({ name: 'board' })"
                >핫플 자랑</b-dropdown-item
              >
              <b-dropdown-item @click="$router.push({ name: 'notice' })"
                >공지사항</b-dropdown-item
              >
              <b-dropdown-item @click="$router.push({ name: 'appQnA' })"
                >Q&A</b-dropdown-item
              >
            </b-nav-item-dropdown>

            <b-nav-item-dropdown text="User" right>
              <b-dropdown-item @click="$router.push({ name: 'mypage' })"
                >My page</b-dropdown-item
              >
              <b-dropdown-item @click.prevent="onClickLogout"
                >Sign Out</b-dropdown-item
              >
            </b-nav-item-dropdown>
          </b-navbar-nav>
          <!-- 로그인 안 했을 때 -->
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item @click="$router.push({ name: 'login' })"
              >로그인</b-nav-item
            >
            <b-nav-item @click="$router.push({ name: 'join' })"
              >회원가입</b-nav-item
            >
          </b-navbar-nav>
        </b-collapse>
      </b-container>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import ChatbotService from "@/store/modules/chatbotService";

const memberStore = "memberStore";

export default {
  name: "TheHeader",
  data() {
    return {
      scrollPosition: null,
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.id);
      this.callShutDown();
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "appMain" });
    },
    updateScroll() {
      this.scrollPosition = window.scrollY;
    },
    callShutDown() {
      ChatbotService.shutdown();
    },
  },
  mounted() {
    window.addEventListener("scroll", this.updateScroll);
  },
};
</script>

<style scoped>
nav {
  /* background-color: rgb(255, 219, 219); */
  z-index: 1000;
}
.navbar-light .navbar-nav .nav-link .navbar-brand {
  color: black;
  font-weight: bold;
}
img {
  width: 25px;
  margin-right: 7px;
}
#btn-main {
  background-color: transparent;
  color: black;
  border-style: none;
  border: none;
  box-shadow: none;
  height: auto;
}
#nav-collapse {
  height: 50px;
}
.container {
  margin: auto;
}

.original_color {
  position: fixed;
  background-color: rgba(0, 0, 0, 0.1);
  width: 100%;
  height: 75px;
  top: 0;
  transition: 0.5s;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
}

.change_color {
  position: fixed;
  background-color: aliceblue;
  width: 100%;
  height: 75px;
  top: 0;
  border-bottom: 1px solid #ebebeb;
  transition: 0.5s;
}
</style>
