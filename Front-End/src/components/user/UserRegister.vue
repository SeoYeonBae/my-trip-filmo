<template>
  <b-container class="mt-5 mb-5">
    <b-row>
      <b-col cols="7" class="text-center">
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col cols="7">
        <b-card class="mt-3" style="max-width: 40rem">
          <b-form class="mt-3">
            <label for="userid">아이디</label>
            <b-form-input
              id="userid"
              v-model="userInfo.userid"
              required
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form>
          <b-form class="mt-3">
            <label for="password">비밀번호</label>
            <b-form-input
              id="password"
              v-model="userInfo.password"
              required
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form>
          <b-form class="mt-3">
            <label for="passconfirm">비밀번호 확인</label>
            <b-form-input
              id="passconfirm"
              required
              placeholder="비밀번호 재입력"
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form>
          <b-form class="mt-3">
            <label for="username">이름</label>
            <b-form-input
              id="username"
              v-model="userInfo.username"
              required
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form>
          <b-form class="mt-3">
            <label for="phone">휴대전화</label>
            <b-form-input
              id="phone"
              v-model="userInfo.phone"
              required
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form>
          <b-form class="mt-3">
            <label for="email">이메일</label>
            <b-form-input
              id="email"
              v-model="userInfo.email"
              required
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form>
          <b-row>
            <b-form-checkbox class="mt-5" v-model="checked"> Are You Manager? </b-form-checkbox>
          </b-row>
          <b-row>
            <b-form class="mt-3 mb-5" v-if="checked">
              <label for="managernum">Enter the manager number : &nbsp;</label>
              <b-form-input
                id="managernum"
                v-model="managernum"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form>
          </b-row>
          <b-row class="mt-3">
            <b-button type="button" class="m-1">회원가입</b-button>
            <b-button type="button" class="m-1" @click="$router.push({ name: 'appMain' })"
              >취소</b-button
            >
          </b-row>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "UserRegister",
  data() {
    return {
      checked: false,
      userInfo: {
        username: null,
        userid: null,
        phone: null,
        email: null,
        joindate: null,
        password: null,
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
