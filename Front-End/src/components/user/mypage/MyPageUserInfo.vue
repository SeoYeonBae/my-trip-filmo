<template>
  <!-- <my-page-header></my-page-header> -->
  <b-col md="8">
    <b-jumbotron text-variant="black">
      <b-row id="title">
        <b-col md="2" id="titleitem">
          <h5 class="mb-0">기본 정보</h5>
        </b-col>
        <b-col md="1">
          <b-button id="infohelp"
            ><font-awesome-icon icon="fa-regular fa-circle-question"
          /></b-button>
          <b-tooltip id="infotooltip" target="infohelp" placement="bottom">
            <p>
              <strong>사용자 이름</strong><br />
              서비스를 이용하려면 사용자의 이름 확인이 필요합니다. 이름 외에 연락처, 이메일 등의
              정보가 변경되었다면 수정도 할 수 있습니다.
            </p>
            <p>
              <strong>연락처 이메일</strong><br />
              문의 사항 안내에 필요한 경우 사용합니다.
            </p>
            <p>
              <strong>본인확인 이메일</strong><br />
              비밀번호 찾기, 로그인이 안되는 경우 등 본인확인이 필요한 경우 사용합니다.
            </p>
          </b-tooltip>
        </b-col>
      </b-row>
      <b-row>
        <b-col md="1" class="p-0">
          <font-awesome-icon icon="fa-regular fa-face-smile" class="fa-4x" />
        </b-col>
        <b-col>
          <h4 class="font-weight-bold">{{ userInfo.username }}</h4>
          {{ userInfo.userid }}
        </b-col>
      </b-row>
      <div fluid>
        <hr class="my-4" />
        <h5 class="mb-4">상세 정보</h5>
        <b-form inline>
          <label for="username">이름: &nbsp;</label>
          <b-form-input
            id="username"
            v-model="userInfo.username"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form>
        <b-form inline class="mt-3">
          <label for="userid">아이디: &nbsp;</label>
          <b-form-input
            id="userid"
            v-model="userInfo.userid"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form>
        <b-form inline class="mt-3">
          <label for="phone">휴대전화: &nbsp;</label>
          <b-form-input
            id="phone"
            v-model="userInfo.phone"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form>
        <b-form inline class="mt-3">
          <label for="email">이메일: &nbsp;</label>
          <b-form-input
            id="email"
            v-model="userInfo.email"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form>
        <b-form inline class="mt-3">
          <label for="joindate">가입일: &nbsp;</label>
          <b-form-input
            id="joindate"
            v-model="userInfo.joindate"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form>
        <b-form inline class="mt-3">
          <label for="password">비밀번호: &nbsp;</label>
          <b-form-input
            id="password"
            v-model="userInfo.password"
            required
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form>
        <b-form inline class="mt-3">
          <label for="passconfirm">비밀번호 확인: &nbsp;</label>
          <b-form-input
            id="passconfirm"
            required
            placeholder="회원정보 수정 시 기입"
            @keyup.enter="confirm"
          ></b-form-input>
        </b-form>
        <hr class="my-4" />
        <b-row>
          <b-button class="btn mr-3 float-left">정보수정</b-button>
          <b-button class="btn float-left">회원탈퇴</b-button>
        </b-row>
      </div>
    </b-jumbotron>
  </b-col>
</template>

<script>
// import MyPageHeader from "@/components/user/mypage/MyPageHeader";
export default {
  name: "MyPageUserInfo",
  components: {
    // MyPageHeader,
  },
  data() {
    return {
      userInfo: {
        username: "융쭈",
        userid: "jjooody",
        phone: "010-1234-5678",
        email: "jjooody@naver.com",
        joindate: "yy-mm-dd",
        password: "",
      },
    };
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
  border: 2px solid #ffd5e5;
  border-radius: 15px;
  background-color: transparent;
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
</style>
