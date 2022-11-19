<template>
  <b-container class="mt-5 mb-5">
    <b-row>
      <b-col cols="7" class="text-center">
        <h3>여행의 주연</h3>
        <h5>어떤 여행을 꿈꾸시나요?</h5>
      </b-col>
    </b-row>
    <b-row class="mt-5">
      <b-col cols="7">
        <div class="input-box">
          <h5 class="input-title">이름</h5>
          <input v-model="user.name" class="input-item" type="text" />
        </div>
        <div class="input-box mt-4">
          <h5 class="input-title" :class="{ 'title-danger': idHasError }">아이디</h5>
          <input
            v-model="user.id"
            class="input-item"
            type="text"
            placeholder="영어 또는 영어 숫자 조합 (5-15자)"
            :class="{ 'input-danger': idHasError }"
          />
          <p v-show="valid.id" class="input-error">
            사용 불가능한 ID입니다. 영어 또는 영어 숫자 조합 (5-15자)
          </p>
        </div>
        <div class="input-box mt-4">
          <h5 class="input-title" :class="{ 'title-danger': passwordHasError }">비밀번호</h5>
          <input
            v-model="user.password"
            class="input-item"
            type="password"
            placeholder="영문, 숫자, 특수문자 조합 8-16자"
            :class="{ 'input-danger': passwordHasError }"
          />
          <p v-show="valid.password" class="input-error">
            영문, 숫자, 특수문자를 조합하여 입력해주세요 (8-16자)
          </p>
        </div>
        <div class="input-box mt-4">
          <h5 class="input-title" :class="{ 'title-danger': telHasError }">핸드폰</h5>
          <input
            v-model="user.tel"
            class="input-item"
            type="text"
            placeholder="예) 010-1234-5678"
            :class="{ 'input-danger': telHasError }"
          />
          <p v-show="valid.tel" class="input-error">핸드폰 번호를 정확히 입력해주세요.</p>
        </div>
        <div class="input-box mt-4">
          <h5 class="input-title" :class="{ 'title-danger': emailHasError }">이메일 주소</h5>
          <input
            v-model="user.email"
            class="input-item"
            type="text"
            placeholder="예) juyeon@juyeon.co.kr"
            :class="{ 'input-danger': emailHasError }"
          />
          <p v-show="valid.email" class="input-error">이메일 주소를 정확히 입력해주세요.</p>
        </div>
        <b-row>
          <b-form-checkbox class="mt-5" v-model="checked"> Are You Manager? </b-form-checkbox>
        </b-row>
        <b-row>
          <b-form class="mt-3 mb-5 text-center" v-if="checked">
            <label for="managernum">Enter the manager number</label>
            <b-form-input
              id="managernum"
              v-model="user.isManager"
              required
              @keyup.enter="confirm"
            ></b-form-input>
          </b-form>
        </b-row>
        <b-row class="mt-5">
          <b-button type="button" class="m-1" @click="checkValue">회원가입</b-button>
          <b-button type="button" class="m-1" @click="$router.push({ name: 'appMain' })"
            >취소</b-button
          >
        </b-row>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";

const api = apiInstance();

export default {
  name: "UserRegister",
  data() {
    return {
      checked: false,
      valid: {
        email: false,
        password: false,
        id: false,
        tel: false,
      },
      user: {
        name: "",
        id: "",
        email: "",
        tel: "",
        password: "",
        isManager: "",
      },
      emailHasError: false,
      passwordHasError: false,
      idHasError: false,
      telHasError: false,
    };
  },
  methods: {
    checkValue() {
      if (
        this.user.name === "" ||
        this.user.id === "" ||
        this.user.password === "" ||
        this.user.tel === "" ||
        this.user.email === ""
      ) {
        alert("모든 내용을 입력해주세요");
        return;
      }
      if (this.idHasError) {
        alert("아이디를 확인해주세요");
        return;
      }
      if (this.passwordHasError) {
        alert("비밀번호를 확인해주세요");
        return;
      }
      if (this.telHasError) {
        alert("핸드폰 번호를 확인해주세요");
        return;
      }
      if (this.emailHasError) {
        alert("이메일 주소를 확인해주세요");
        return;
      }
      if (this.checked) {
        if (this.user.isManager === "") {
          alert("매니저 번호를 입력하세요");
          return;
        } else if (this.user.isManager != 1111) {
          alert("매니저 암호가 바르지 않습니다");
          return;
        } else {
          this.user.isManager = 1;
        }
      }
      this.registuser();
    },
    registuser() {
      api.post("/user/join", this.user).then(({ data }) => {
        console.log(this.user);
        let msg = "회원가입 중 문제 발생!!!";
        if (data) msg = "회원가입 성공!!!";
        alert(msg);
        this.$router.push({ name: "appMain" });
      });
    },
    checkEmail() {
      // 이메일 형식 검사
      const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;

      if (!validateEmail.test(this.user.email)) {
        this.valid.email = true;
        this.emailHasError = true;
        return;
      }
      this.valid.email = false;
      this.emailHasError = false;
    },
    checkPassword() {
      // 비밀번호 형식 검사(영문, 숫자, 특수문자)
      const validatePassword = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

      if (!validatePassword.test(this.user.password)) {
        this.valid.password = true;
        this.passwordHasError = true;
        return;
      }
      this.valid.password = false;
      this.passwordHasError = false;
    },
    checkTel() {
      const validateTel = /^\d{3}-\d{3,4}-\d{4}$/;

      if (!validateTel.test(this.user.tel)) {
        this.valid.tel = true;
        this.telHasError = true;
        return;
      }
      this.valid.tel = false;
      this.telHasError = false;
    },
    checkId() {
      // 아이디 유효성 검사
      const validateId = /^[a-zA-Z]+[a-zA-Z0-9]{4,15}$/;
      if (!validateId.test(this.user.id)) {
        this.valid.id = true;
        this.idHasError = true;
        return;
      }
      // 아이디 중복 검사
      api.get(`/user/${this.user.id}`).then(({ data }) => {
        if (data == "1") {
          this.valid.id = true;
          this.idHasError = true;
          return;
        }
      });

      this.valid.id = false;
      this.idHasError = false;
    },
  },
  watch: {
    "user.email": function () {
      this.checkEmail();
    },
    "user.password": function () {
      this.checkPassword();
    },
    "user.tel": function () {
      this.checkTel();
    },
    "user.id": function () {
      this.checkId();
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&family=Song+Myung&display=swap");
* {
  font-family: "Noto Sans KR", sans-serif;
  color: #171d3a;
}
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
  width: 100%;
}
button:hover {
  background-color: #d4fcee;
  color: black;
}
.input-error {
  line-height: 16px;
  font-size: 11px;
  color: red;
}
.title-danger {
  color: red;
}
.input-danger {
  border-bottom: 1px solid red !important;
}
input.input-item {
  border-left-width: 0;
  border-right-width: 0;
  border-top-width: 0;
  border-bottom-width: 1;
  width: 100%;
}
input:focus {
  outline: none;
}
</style>
