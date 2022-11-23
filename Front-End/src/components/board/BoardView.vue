<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button size="sm" @click="moveList">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <div v-show="this.article.userId == this.userInfo.id">
          <b-button size="sm" @click="moveModifyArticle" class="mr-2"
            >글수정</b-button
          >
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
            <div
              v-for="file in fileInfos"
              :src="file.url"
              v-bind:key="file.originalFile"
            >
              <!-- <img
                :src="
                  require('C:/mytripfilmo/board/fileUpload/' +
                    file.saveFolder +
                    '/' +
                    file.saveFile)
                "
                width="200px"
              /> -->
            </div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row>
      <board-reply-item :articleno="`${article.articleNo}`"></board-reply-item>
    </b-row>
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";
import BoardReplyItem from "@/components/board/item/BoardReplyItem.vue";

const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      fileInfos: [],
    };
  },
  created() {
    api.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
      console.log(data);
      this.fileInfos = data.fileInfos;
      this.article = data;
      console.log(this.fileInfos);
    });
  },
  components: {
    BoardReplyItem,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
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
    deleteArticle() {
      api.delete(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "boardlist" });
      });
    },
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
  background-color: #dfe4ff;
  border: 0;
  color: black;
  padding-left: 15px;
  padding-right: 15px;
  margin-right: 30px;
  width: 100px;
}
button:hover {
  background-color: #dfe4ff;
  color: white;
}
</style>
