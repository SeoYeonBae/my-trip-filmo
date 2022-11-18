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
            <!-- <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 이름을 확인하세요.</b-alert
            > -->
            <b-form-group label="아이디" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.userid"
                required
                placeholder="아이디를 입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름" label-for="username">
              <b-form-input
                id="username"
                v-model="user.username"
                required
                placeholder="이름을 입력해주세요"
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-row>
              <b-button type="button" class="m-1" @click="$router.push({ name: 'login' })"
                >로그인</b-button
              >
              <b-button type="button" class="m-1">비밀번호 찾기</b-button>
            </b-row>
          </b-form>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userid: null,
        username: null,
      },
    };
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
