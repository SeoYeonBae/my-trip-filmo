<template>
  <b-col md="8">
    <b-jumbotron>
      <div class="text-center my-5">
        <h5 class="mb-0">내가 쓴 글</h5>
      </div>
      <b-table
        hover
        :items="articles"
        :fields="fields"
        @row-clicked="viewArticle"
      >
        <template #cell(subject)="data">
          <router-link
            :to="{
              name: 'boardview',
              params: { articleno: data.item.articleNo },
            }"
          >
            {{ data.item.subject }}
          </router-link>
        </template>
      </b-table>
    </b-jumbotron>
  </b-col>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";

const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "MyPageArticleList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "registerTime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  mounted() {
    api.get(`/board/list/${this.userInfo.id}`).then(({ data }) => {
      this.articles = data.list;
      console.log(this.articles);
    });
  },
  methods: {
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
  border: none;
  background-color: transparent;
  width: 80%;
  padding-top: 0%;
}
</style>
