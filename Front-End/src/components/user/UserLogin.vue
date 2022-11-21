<template>
  <b-container class="mt-5 mb-5">
    <b-row>
      <b-col cols="7" class="text-center">
        <h3><strong>여행의 주연</strong></h3>
        <p><em>- 내가 만드는 나만의 여행</em></p>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="7">
        <b-form class="text-left">
          <b-alert show variant="danger" v-if="isLoginError"
            >아이디 또는 비밀번호를 확인하세요.</b-alert
          >
          <div class="mt-4">
            <p class="input-title">아이디</p>
            <input
              id="userid"
              v-model="user.id"
              class="input-item"
              required
              placeholder="아이디를 입력해주세요"
              @keyup.enter="confirm"
            />
          </div>
          <div class="mt-4 mb-5">
            <p class="input-title">비밀번호</p>
            <input
              type="password"
              id="userpwd"
              class="input-item"
              v-model="user.password"
              required
              placeholder="비밀번호를 입력해주세요"
              @keyup.enter="confirm"
            />
          </div>
          <b-row>
            <b-button type="button" class="m-1" @click="confirm">로그인</b-button>
            <b-button type="button" class="m-1" @click="$router.push({ name: 'findpassword' })"
              >비밀번호 찾기</b-button
            >
            <b-button type="button" class="m-1 mb-3" @click="$router.push({ name: 'join' })"
              >회원가입</b-button
            >
          </b-row>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        id: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      // console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        // console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "appMain" });
      }
    },
  },
};
</script>

<style scoped>
.row {
  justify-content: center;
}
.input-label {
  color: black;
  font-weight: bold;
}
button {
  background-color: #dfe4ff;
  border: 0;
  color: black;
  font-weight: bold;
  padding: 16px;
  border-style: none;
  border: none;
  box-shadow: none;
  width: 80%;
  height: 50px;
}
button:hover {
  background-color: #d4fcee;
  color: black;
}
.container {
  height: 100%;
}
input.input-item {
  border-left-width: 0;
  border-right-width: 0;
  border-top-width: 0;
  border-bottom-width: 1;
  width: 100%;
  border-radius: 0;
  border-bottom-color: lightgray;
}
input:focus {
  outline: none;
  border-bottom-width: 2px;
  border-bottom-color: black;
}
</style>
