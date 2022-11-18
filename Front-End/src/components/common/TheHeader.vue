<template>
  <div>
    <b-navbar toggleable="lg">
      <img src="@/assets/logo.png" @click="$router.push({ name: 'appMain' })" alt="logo" />
      <b-button id="btn-main" @click="$router.push({ name: 'appMain' })">여행의 주연</b-button>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav class="ml-auto">
          <!-- 로그인 안 했을 때 -->
          <b-navbar-nav>
            <b-nav-item @click="$router.push({ name: 'login' })">로그인</b-nav-item>
            <b-nav-item @click="$router.push({ name: 'join' })">회원가입</b-nav-item>
          </b-navbar-nav>
          <!-- 로그인 했을 때 -->
          <b-nav-item-dropdown text="여행하기" right>
            <b-dropdown-item @click="$router.push({ name: 'tour' })">관광지 구경</b-dropdown-item>
            <b-dropdown-item @click="$router.push({ name: 'plan' })">여행 계획</b-dropdown-item>
            <b-dropdown-item @click="$router.push({ name: 'sun' })">일출 일몰</b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown text="게시판" right>
            <b-dropdown-item @click="$router.push({ name: 'board' })">핫플 자랑</b-dropdown-item>
            <b-dropdown-item @click="$router.push({ name: 'notice' })">공지사항</b-dropdown-item>
            <b-dropdown-item @click="$router.push({ name: 'appQnA' })">Q&A</b-dropdown-item>
          </b-nav-item-dropdown>

          <b-nav-item-dropdown text="User" right>
            <b-dropdown-item @click="$router.push({ name: 'mypage' })">My page</b-dropdown-item>
            <b-dropdown-item href="#">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
export default {
  name: "TheHeader",
};
</script>

<style scoped>
nav {
  background-color: rgb(255, 219, 219);
  height: 75px;
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
</style>
