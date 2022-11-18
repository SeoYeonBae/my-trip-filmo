<template>
  <b-container class="mt-5 mb-5">
    <b-row>
      <b-col cols="7" class="text-center">
        <b-alert variant="secondary" show><h3>로그인</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="7">
        <b-card class="text-center mt-3" style="max-width: 40rem">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.id"
                required
                placeholder="아이디를 입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.password"
                required
                placeholder="비밀번호를 입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-row>
              <b-button type="button" class="m-1" @click="confirm">로그인</b-button>
              <b-button type="button" class="m-1" @click="$router.push({ name: 'findpassword' })"
                >비밀번호 찾기</b-button
              >
              <b-button type="button" class="m-1" @click="$router.push({ name: 'join' })"
                >회원가입</b-button
              >
            </b-row>
          </b-form>
        </b-card>
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
      // isLoginError: false,
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
button {
  background-color: #dfe4ff;
  border: 0;
  color: black;
  font-weight: bold;
  padding: 16px;
  border-style: none;
  border: none;
  box-shadow: none;
}
button:hover {
  background-color: #d4fcee;
  color: black;
}
.container {
  height: 100%;
}
</style>
