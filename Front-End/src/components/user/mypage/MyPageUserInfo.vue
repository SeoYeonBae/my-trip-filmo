<template>
  <b-col md="8">
    <b-jumbotron>
      <b-row id="title">
        <b-col md="2" id="titleitem">
          <h5 class="mb-0">기본 정보</h5>
        </b-col>
        <b-col md="1">
          <b-button class="mb-0" id="infohelp"
            ><font-awesome-icon icon="fa-regular fa-circle-question"
          /></b-button>
          <b-tooltip id="infotooltip" target="infohelp" placement="bottom">
            <p>
              <strong>사용자 이름</strong><br />
              서비스를 이용하려면 사용자의 이름 확인이 필요합니다. 이름 외에
              연락처, 이메일 등의 정보가 변경되었다면 수정도 할 수 있습니다.
            </p>
            <p>
              <strong>연락처 이메일</strong><br />
              문의 사항 안내에 필요한 경우 사용합니다.
            </p>
            <p>
              <strong>본인확인 이메일</strong><br />
              비밀번호 찾기, 로그인이 안되는 경우 등 본인확인이 필요한 경우
              사용합니다.
            </p>
          </b-tooltip>
        </b-col>
      </b-row>
      <b-row>
        <b-col md="1" class="p-0">
          <b-img id="profilesm" rounded="circle" :src="profileimg"></b-img>
        </b-col>
        <b-col class="px-5">
          <h5 class="font-weight-bold">{{ userInfo.name }}</h5>
          {{ userInfo.id }}
        </b-col>
        <b-col>
          <label for="inputfile">&nbsp;</label>
          <input
            id="inputfile"
            @change="fileChange()"
            type="file"
            ref="uploadimage"
            accept="image/*"
        /></b-col>
      </b-row>
      <div fluid>
        <hr class="my-4" />
        <h5 class="mb-4">상세 정보</h5>
        <div class="input-box">
          <h5 class="input-title">이름</h5>
          <input v-model="user.name" class="input-item" type="text" />
        </div>
        <div class="input-box mt-4">
          <h5 class="input-title">아이디</h5>
          <input v-model="user.id" class="input-item" type="text" />
        </div>
        <div class="input-box mt-4">
          <h5 class="input-title" :class="{ 'title-danger': passwordHasError }">
            비밀번호
          </h5>
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
          <h5 class="input-title" :class="{ 'title-danger': telHasError }">
            핸드폰
          </h5>
          <input
            v-model="user.tel"
            class="input-item"
            type="text"
            placeholder="예) 010-1234-5678"
            :class="{ 'input-danger': telHasError }"
          />
          <p v-show="valid.tel" class="input-error">
            핸드폰 번호를 정확히 입력해주세요.
          </p>
        </div>
        <div class="input-box mt-4 mb-5">
          <h5 class="input-title" :class="{ 'title-danger': emailHasError }">
            이메일 주소
          </h5>
          <input
            v-model="user.email"
            class="input-item"
            type="text"
            placeholder="예) juyeon@juyeon.co.kr"
            :class="{ 'input-danger': emailHasError }"
          />
          <p v-show="valid.email" class="input-error">
            이메일 주소를 정확히 입력해주세요.
          </p>
        </div>
        <b-row>
          <b-button class="btn mr-3 float-left" @click="checkValue"
            >정보수정</b-button
          >
          <b-button class="btn float-left" @click="userDelete"
            >회원탈퇴</b-button
          >
        </b-row>
      </div>
    </b-jumbotron>
  </b-col>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
import { apiInstance } from "@/api/index.js";

const memberStore = "memberStore";
const api = apiInstance();

export default {
  name: "MyPageUserInfo",
  data() {
    return {
      user: {
        name: "",
        id: "",
        email: "",
        tel: "",
        password: "",
        isManager: "",
        saveFile: "",
      },
      valid: {
        email: false,
        password: false,
        tel: false,
      },
      emailHasError: false,
      passwordHasError: false,
      telHasError: false,
      profileimg: require("@/assets/img/user.png"),
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    this.user = JSON.parse(JSON.stringify(this.userInfo));
    if (this.user.saveFile) {
      this.profileimg =
        "http://localhost:9999/mytripfilmo/upload/profile/" +
        this.user.saveFile;
    }
  },
  methods: {
    ...mapActions(memberStore, ["getUserInfo", "userLogout"]),
    ...mapMutations(memberStore, ["SET_USER_INFO"]),
    fileChange() {
      this.file = this.$refs["uploadimage"].files[0];
    },
    userDelete() {
      console.log(this.user);
      api.post("/user/delete", this.user).then((data) => {
        let msg = "회원 탈퇴 완료. 감사합니다.";
        if (data.data === "success") {
          msg = "회원 탈퇴 완료. 감사합니다.";
          this.userLogout(this.userInfo.userid);
          sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
          sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
        }
        alert(msg);
        this.$router.push({ name: "appMain" });
      });
    },
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
      this.modifyuser();
    },
    modifyuser() {
      if (
        typeof this.file == "undefined" ||
        this.file == null ||
        this.file == ""
      ) {
        api.put("/user/modify", this.user).then((data) => {
          let msg = "회원 정보 수정 중 문제 발생!!!";
          if (data) {
            msg = "회원 정보 수정 완료";
            this.user = JSON.parse(JSON.stringify(this.user));
            let token = sessionStorage.getItem("access-token");
            this.getUserInfo(token);
          }
          alert(msg);
        });
      } else {
        // 파일 넣고 글 등록
        const form = new FormData();
        form.append(
          "userDto",
          new Blob([JSON.stringify(this.user)], { type: "application/json" })
        );
        form.append("fileInfo", this.file);
        api
          .put("/user/modify/img", form, {
            headers: { "Content-Type": "multipart/form-data" },
          })
          .then(({ data }) => {
            let msg = "회원 정보 수정 중 문제 발생!!!";
            if (data) {
              msg = "회원 정보 수정 완료";
              // console.log(data.saveFile);
              this.user = JSON.parse(JSON.stringify(this.user));
              this.user.saveFile = data.saveFile;
              this.SET_USER_INFO(this.user);
              let token = sessionStorage.getItem("access-token");
              this.getUserInfo(token);
              this.$router.go();
            }
            alert(msg);
          });
      }
    },
    checkEmail() {
      // 이메일 형식 검사
      const validateEmail =
        /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;

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
      const validatePassword =
        /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/;

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
  },
};
</script>

<style scoped>
.row {
  margin: 0px;
  padding: 0px;
  justify-content: center;
}
.container {
  padding: 0px;
}
.col-md-8 {
  padding-top: 3%;
}
.jumbotron {
  border: none;
  background-color: transparent;
  width: 80%;
  padding-top: 0%;
}
.form-control {
  width: 400px;
}
#infohelp {
  background-color: transparent;
  color: black;
  border-style: none;
  border: none;
  box-shadow: none;
}
#title {
  margin-bottom: 3%;
}
h5.mb-4 {
  margin-bottom: 0;
}
#titleitem {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.tooltip .tooltip-inner {
  padding: 5%;
  text-align: left;
  max-width: 550px !important;
  width: 550px !important;
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
.input-error {
  line-height: 16px;
  font-size: 13px;
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
.input-title {
  font-weight: bold;
  font-size: 15px;
}
#profilesm {
  width: 60px;
}
#inputfile {
}
</style>
