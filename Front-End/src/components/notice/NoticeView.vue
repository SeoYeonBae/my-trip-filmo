<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-jumbotron>
        <h3 class="text-center">{{ article.articleNo }}.{{ article.subject }}</h3>
        <p class="m-1">{{ article.userId }}</p>
        <b-row>
          <b-col>
            <p>{{ article.registerTime }}</p>
          </b-col>
          <b-col>
            <p class="text-right">읽음 | {{ article.hit }}</p>
          </b-col>
        </b-row>
        <hr class="my-1 mb-5" />
        <div class="mb-5">
          <p>
            {{ article.content }}
          </p>
          <b-card-group columns class="wrap"> </b-card-group>
        </div>
        <b-row class="mt-1">
          <b-col class="text-left">
            <b-button class="m-0" @click="moveList">목록</b-button>
          </b-col>
          <b-col class="text-right">
            <div v-show="this.userInfo.isManager == 1">
              <b-button class="m-0 mr-3" @click="moveModifyArticle">글수정</b-button>
              <b-button class="m-0" @click="deleteArticle">글삭제</b-button>
            </div>
          </b-col>
        </b-row>
      </b-jumbotron>
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
      // 공지사항 수정으로 이동
      this.$router.replace({
        name: "noticemodify",
        params: { articleno: this.article.articleNo },
      });
    },
    deleteArticle() {
      // 공지사항 삭제
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
.jumbotron {
  border: 1px solid lightgray;
  background-color: transparent;
  width: 100%;
  padding: 7%;
  padding-bottom: 4%;
}
h3 {
  text-align: center;
  font-weight: bold;
  margin-bottom: 40px;
}
button {
  background-color: #dfe4ff;
  border: 0;
  color: black;
  font-weight: bold;
  padding-left: 16px;
  padding-right: 16px;
  width: 200px;
  margin-bottom: 10px;
}
button:hover {
  background-color: rgb(203, 209, 255);
  color: black;
  border: none;
}
</style>
