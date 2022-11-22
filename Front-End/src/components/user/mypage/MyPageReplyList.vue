<template>
  <b-col md="8">
    <div class="text-center my-5">
      <h5 class="mb-0">내가 쓴 댓글</h5>
    </div>
    <b-table hover :items="replies" :fields="fields" @row-clicked="viewArticle">
      <template #cell(content)="data">
        <router-link
          :to="{
            name: 'boardview',
            params: { articleno: data.item.article_no },
          }"
        >
          {{ data.item.content }}
        </router-link>
      </template>
    </b-table>
  </b-col>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";

const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "MyPageRepleyList",
  data() {
    return {
      replies: [],
      fields: [
        { key: "content", label: "내용", tdClass: "tdSubject" },
        { key: "regist_time", label: "작성일", tdClass: "tdClass" },
      ],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  mounted() {
    api.get(`/reply/my/${this.userInfo.id}`).then(({ data }) => {
      console.log(data);
      this.replies = data;
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
</style>
