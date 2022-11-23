<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <div><h3>핫플 자랑하기</h3></div>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table hover :items="articles" :fields="fields" @row-clicked="viewArticle">
          <template #cell(subject)="data">
            <router-link :to="{ name: 'boardview', params: { articleno: data.item.articleNo } }">
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
const api = apiInstance();

export default {
  name: "BoardList",
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
      api.get(`/board/list`).then(({ data }) => (this.articles = data.list));
    },
    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleNo },
      });
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap");

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
