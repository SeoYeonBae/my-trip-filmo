<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3>공지사항</h3>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <div v-show="this.userInfo.id == 'admin'">
          <b-button @click="moveWrite()">글쓰기</b-button>
        </div>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle">
          <template #cell(subject)="data">
            <router-link :to="{ name: 'noticeview', params: { articleno: data.item.articleNo } }">
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
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
  name: "NoticeList",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleNo", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userId", label: "작성자", tdClass: "tdClass" },
        { key: "registerTime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    this.fetchBoard();
  },
  methods: {
    fetchBoard() {
      api.get(`/notice/list`).then(({ data }) => (this.articles = data.list));
    },
    viewArticle(article) {
      this.$router.push({
        name: "noticeview",
        params: { articleno: article.articleNo },
      });
    },
    moveWrite() {
      this.$router.push({ name: "noticewrite" });
    },
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}
h3 {
  font-family: "Noto Sans KR", sans-serif;
  text-align: center;
  font-weight: bold;
  margin-top: 5%;
  margin-bottom: 5%;
}
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
button {
  background-color: #dfe4ff;
  border: 0;
  color: black;
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
