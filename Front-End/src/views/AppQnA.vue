<template>
  <b-container style="margin-top: 140px">
    <h3>자주 묻는 Q&A</h3>
    <h5>회원 정보</h5>
    <hr />
    <div class="mb-3">
      <a v-b-toggle href="#collapse1-1" @click.prevent>▶ 회원 정보 수정은 어떻게 하나요?</a>
    </div>
    <b-collapse id="collapse1-1">
      <b-card title="안녕하세요.">마이페이지에서 정보를 수정하고 수정 버튼을 눌러주세요.</b-card>
    </b-collapse>
    <div class="mb-3">
      <a v-b-toggle href="#collapse1-2" @click.prevent>▶ 회원 탈퇴는 어떻게 하나요?</a>
    </div>
    <b-collapse id="collapse1-2">
      <b-card title="안녕하세요.">마이페이지에서 탈퇴 버튼을 눌러주세요.</b-card>
    </b-collapse>

    <h5>여행 계획</h5>
    <hr />
    <div class="mb-3">
      <a v-b-toggle href="#collapse2-1" @click.prevent>▶ 여행 계획은 어떻게 작성하나요?</a>
    </div>
    <b-collapse id="collapse2-1">
      <b-card title="안녕하세요."
        >우선 관광지와 날짜를 등록 후에 완성 버튼을 누르면 상세 항목을 입력하실 수 있습니다.</b-card
      >
    </b-collapse>
    <div class="mb-3">
      <a v-b-toggle href="#collapse2-2" @click.prevent>▶ 친구 추가는 어떻게 하나요?</a>
    </div>
    <b-collapse id="collapse2-2">
      <b-card title="안녕하세요.">여행 계획 상세 항목 입력시 친구의 아이디를 넣어주세요.</b-card>
    </b-collapse>
  </b-container>
</template>

<script>
export default {};
</script>

<style scoped>
a {
  color: black;
}
h3 {
  font-family: "Noto Sans KR", sans-serif;
  text-align: center;
  font-weight: bold;
  margin-top: 5%;
  margin-bottom: 5%;
}
h5 {
  margin-top: 5%;
  font-weight: bold;
}
.collapsed {
  color: black;
}
.collapsed:hover {
  font-size: 110%;
  color: black;
  font-weight: bold;
  text-decoration: none;
}
.card {
  margin-bottom: 3%;
  border: none;
}
.card-title {
  margin-top: 0px;
}
.container {
  margin-top: 5%;
  margin-bottom: 5%;
  text-align: center;
}
h3 {
  text-align: center;
}
h4 {
  margin-top: 3%;
}
</style>
