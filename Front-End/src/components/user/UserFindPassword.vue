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
          <div class="mt-4">
            <p class="input-title">아이디</p>
            <input
              id="userid"
              v-model="user.id"
              class="input-item"
              required
              placeholder="비밀번호를 찾으실 아이디를 입력해주세요"
            />
          </div>
          <div class="mt-4 mb-5">
            <p class="input-title">이름</p>
            <input
              id="username"
              v-model="user.name"
              class="input-item"
              required
              placeholder="본인 확인을 위한 이름을 입력해주세요"
            />
          </div>
          <b-row>
            <b-button type="button" class="m-1" @click="$router.push({ name: 'login' })"
              >로그인</b-button
            >
            <b-button type="button" class="m-1 mb-3" @click="checkValue">비밀번호 찾기</b-button>
          </b-row>
        </b-form>
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
  width: 80%;
  height: 50px;
}
button:hover {
  background-color: #d4fcee;
  color: black;
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
