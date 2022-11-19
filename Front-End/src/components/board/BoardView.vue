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
        <div v-show="this.article.userId == this.userInfo.id">
          <b-button size="sm" @click="moveModifyArticle" class="mr-2">글수정</b-button>
          <b-button size="sm" @click="deleteArticle">글삭제</b-button>
        </div>
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
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  created() {
    api.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
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
        name: "boardmodify",
        params: { articleno: this.article.articleNo },
      });
    },
    deleteArticle() {},
    moveList() {
      this.$router.push({ name: "boardlist" });
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
