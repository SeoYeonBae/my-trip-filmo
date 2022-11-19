<template>
  <b-col md="8">
    <b-jumbotron text-variant="black">
      <b-card-group deck v-for="(article, index) in this.articles" :key="index">
        <b-card class="mb-4">
          <b-card-text>{{ article.subject }}</b-card-text>
        </b-card>
      </b-card-group>
    </b-jumbotron>
  </b-col>
</template>

<script>
import { apiInstance } from "@/api/index.js";

const api = apiInstance();

export default {
  name: "MyPageArticleList",
  data() {
    return {
      articles: [],
    };
  },
  mounted() {
    api.get(`/board/list`).then(({ data }) => (this.articles = data.list));
  },
};
</script>

<style scoped>
.row {
  margin: 0px;
  padding: 0px;
  justify-content: center;
}
.container {
  padding: 0px;
}
.col-md-8 {
  padding-top: 3%;
}
.jumbotron {
  border: 2px solid #ffd5e5;
  border-radius: 15px;
  background-color: transparent;
}
</style>
