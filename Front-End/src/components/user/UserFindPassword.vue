<template>
  <b-container class="mt-5 mb-5">
    <b-row>
      <b-col cols="7" class="text-center">
        <b-alert variant="secondary" show><h3>비밀번호 찾기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="7">
        <b-card class="text-center mt-3" style="max-width: 40rem">
          <b-form class="text-left">
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.id"
                required
                placeholder="아이디를 입력해주세요"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="username">
              <b-form-input
                id="username"
                v-model="user.name"
                required
                placeholder="이름을 입력해주세요"
              ></b-form-input>
            </b-form-group>
            <b-row>
              <b-button type="button" class="m-1" @click="$router.push({ name: 'login' })"
                >로그인</b-button
              >
              <b-button type="button" class="m-1" @click="checkValue">비밀번호 찾기</b-button>
            </b-row>
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";

const api = apiInstance();

export default {
  name: "UserLogin",
  data() {
    return {
      user: {
        id: "",
        name: "",
      },
    };
  },
  methods: {
    checkValue() {
      if (this.user.id === "" || this.user.name === "") {
        alert("모든 내용을 입력해주세요");
        return;
      } else {
        this.findPass();
      }
    },
    findPass() {
      api.post("/user/findPass", this.user).then(({ data }) => {
        if (!data) {
          alert("존재하지 않는 회원 정보입니다.");
        } else {
          if (data) alert("가입하신 메일 " + data.user_email + "로 비밀번호를 전송하였습니다.");
          this.$router.push({ name: "login" });
        }
      });
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
</style>
