<template>
  <div>
    <b-container fluid>
      댓글창
      <div :v-model="articleno">{{ articleno }} 번 글입니다.</div>
      <b-form @submit="onSubmit">
        <b-row>
          <b-col md="10" class="">
            <b-form-group id="replyform" label-cols-sm="1" label-align-sm="right">
              <b-form-input
                id="content"
                type="text"
                v-model="newReply.content"
                required
                placeholder="상대방을 존중하는 댓글을 남겨주세요"
              ></b-form-input>
            </b-form-group>
          </b-col>
          <b-col class="">
            <b-button type="button" variant="primary" class="m-1" @click="registReply">등록하기</b-button>
          </b-col>
        </b-row>
      </b-form>
    </b-container>
  </div>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";

const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "BoardReplyItem",
  props: {
    articleno: { articleno: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      newReply: {
        user_id: "",
        article_no: "",
        content: "",
        regist_time: "",
      },
    };
  },
  watch: {
    articleno: function () {
      this.setReplyInfo();
    },
  },
  methods: {
    registReply() {
      api
        .post(`/reply/register`, {
          user_id: this.newReply.user_id,
          article_no: this.newReply.article_no,
          content: this.newReply.content,
        })
        .then(({ data }) => {
          let msg = " 문제가 발생하였습니다. ";
          if (data === "success") msg = "댓글 등록이 완료되었습니다.";

          alert(msg);
          this.moveList();
        });
    },
    setReplyInfo() {
      this.newReply.user_id = this.userInfo.id;
      this.newReply.article_no = this.articleno;
      console.log("세팅완료" + this.newReply.user_id);
      console.log("세팅완료" + this.newReply.article_no);
    },
    onSubmit(event) {
      event.preventDefault();

      if (this.newReply.content == "") {
        alert("댓글 내용을 입력해주세요");
      } else this.registReply();
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
};
</script>

<style scoped>
.replyform {
  margin: 0 auto;
  width: 100%;
  display: flex;
  justify-content: center;
  text-align: center;
}
#replyrow {
  width: 100%;
}
</style>
