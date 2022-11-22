<template>
  <b-container fluid>
    <b-container fluid class="main">
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
          <b-col>
            <b-button type="button" class="btn" @click="registReply">등록하기</b-button>
          </b-col>
        </b-row>
      </b-form>
    </b-container>
    <b-container flex>
      <b-row class="replydiv" v-for="reply in replys" :key="reply.idx">
        <b-col id="profile"><b-img :src="require('@/assets/img/DefaultProfile.png')"></b-img></b-col>
        <b-col md="11">
          <b-row>
            <b-col md="2" id="userid">{{ reply.user_id }}</b-col>
            <b-col md="10" id="content">{{ reply.content }}</b-col>
          </b-row>
          <b-row id="replytime">{{ reply.regist_time }}</b-row>
        </b-col>
      </b-row>
    </b-container>
  </b-container>
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
      replys: [],
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
      console.log(this.userInfo.id);
      this.setReplyInfo();
      this.getReplys();
    },
  },
  methods: {
    getReplys() {
      api.get(`/reply/${this.articleno}`).then(({ data }) => {
        this.replys = data;
      });
    },
    registReply() {
      api
        .post(`/reply/register`, {
          user_id: this.newReply.user_id,
          article_no: this.newReply.article_no,
          content: this.newReply.content,
        })
        .then(({ data }) => {
          let msg = " 문제가 발생하였습니다.";
          if (data === "success") msg = "댓글 등록이 완료되었습니다.";

          alert(msg);
          this.getReplys();
        });
    },
    setReplyInfo() {
      this.newReply.user_id = this.userInfo.id;
      this.newReply.article_no = this.articleno;
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
.main {
  margin: 20px;
}
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
.btn {
  /* font-size: 10px; */
  /* background-color: #dfe4ff; */
  background-color: white;
  color: black;
  /* border-style: solid; */
}
.btn:hover {
  color: white;
}
.replydiv {
  margin: 20px;
}
.replydiv > * {
  min-height: 100px;
}
#userid {
  font-weight: bold;
}
#replytime {
  font-size: 15px;
  color: grey;
}
img {
  width: 40px;
  height: 40px;
}
</style>
