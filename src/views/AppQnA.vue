<template>
  <b-container>
    <b-alert show variant="warning"><h3>자주 묻는 Q&A</h3></b-alert>
    <h4>회원 정보 관련 QnA</h4>
    <hr />
    <div class="mb-3">
      <a v-b-toggle href="#collapse1-1" @click.prevent>▶ 회원 정보 수정은 어떻게 하나요?</a>
    </div>
    <b-collapse id="collapse1-1">
      <b-card title="안녕하세요.">불가능!</b-card>
    </b-collapse>
    <div class="mb-3">
      <a v-b-toggle href="#collapse1-2" @click.prevent>▶ 회원 탈퇴는 어떻게 하나요?</a>
    </div>
    <b-collapse id="collapse1-2">
      <b-card title="안녕하세요.">불가능!</b-card>
    </b-collapse>

    <h4>여행 계획 관련 QnA</h4>
    <hr />
    <div class="mb-3">
      <a v-b-toggle href="#collapse2-1" @click.prevent>▶ 회원 정보 수정은 어떻게 하나요?</a>
    </div>
    <b-collapse id="collapse2-1">
      <b-card title="안녕하세요.">불가능!</b-card>
    </b-collapse>
    <div class="mb-3">
      <a v-b-toggle href="#collapse2-2" @click.prevent>▶ 회원 탈퇴는 어떻게 하나요?</a>
    </div>
    <b-collapse id="collapse2-2">
      <b-card title="안녕하세요.">불가능!</b-card>
    </b-collapse>
  </b-container>
</template>

<script>
export default {};
</script>

<style>
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
