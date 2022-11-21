<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show variant="warning"><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button size="sm" @click="moveModifyArticle" class="mr-2">글수정</b-button>
        <b-button size="sm" @click="deleteArticle">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleNo}.
          ${article.subject} [${article.hit}]</h3><div><h6>${article.userId}</div><div>${article.registerTime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";
const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "NoticeDetail",
  data() {
    return {
      article: {},
    };
  },
  created() {
    api.get(`/notice/${this.$route.params.articleno}`).then(({ data }) => {
      this.article = data;
    });
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "noticemodify",
        params: { articleno: this.article.articleNo },
      });
    },
    deleteArticle() {
      api.delete(`/notice/${this.$route.params.articleno}`).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "noticelist" });
      });
    },
    moveList() {
      this.$router.push({ name: "noticelist" });
    },
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
}
button {
  margin-left: 30px;
  background-color: #f8c5b4;
  border: 0;
  color: black;
  padding-left: 16px;
  padding-right: 16px;
  width: 100px;
}
button:hover {
  background-color: #f8c5b4;
  color: white;
}
</style>
