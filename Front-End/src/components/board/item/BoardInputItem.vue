<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="userid-group" label="작성자" label-for="userid">
          <b-form-input
            id="userid"
            :disabled="true"
            v-model="article.userId"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="subject-group" label="제목" label-for="subject">
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            ref="content"
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <div class="my-4" align="left">
          <!-- <label for="upfile"></label> -->
          <input
            v-if="this.type === 'register'"
            @change="fileChange()"
            type="file"
            ref="uploadimage"
            multiple
            accept="image/*"
          />
        </div>
        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" class="m-1" v-else>글수정</b-button>
        <b-button type="reset" class="m-1">글목록</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";
const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "BoardInputItem",
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
        file: [],
      },
      // isUserid: false,
    };
  },
  mounted() {
    if (this.type == "register") this.setUserId();
  },
  created() {
    if (this.type === "modify") {
      api.get(`/board/${this.$route.params.articleno}`).then(({ data }) => {
        // this.article.articleno = data.article.articleno;
        // this.article.userid = data.article.userid;
        // this.article.subject = data.article.subject;
        // this.article.content = data.article.content;
        this.article = data;
      });
      this.isUserid = true;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleNo = 0;
      this.article.subject = "";
      this.article.content = "";
      this.moveList();
    },
    fileChange() {
      this.file = this.$refs.uploadimage.files;
      console.log(this.file);
    },
    registArticle() {
      // 파일 없이 글 등록
      if (
        typeof this.file == "undefined" ||
        this.file == null ||
        this.file == ""
      ) {
        api
          .post(`/board/register`, {
            userId: this.article.userId,
            subject: this.article.subject,
            content: this.article.content,
          })
          .then(({ data }) => {
            let msg =
              "DB에 잘 들어가는데 왜 등록 처리시 문제가 발생했습니다.라는거야";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            this.moveList();
          });
      } else {
        // 파일 넣고 글 등록
        const form = new FormData();
        form.append(
          "boardDto",
          new Blob([JSON.stringify(this.article)], { type: "application/json" })
        );
        for (let i = 0; i < this.file.length; i++) {
          form.append("fileInfos", this.file[i]);
        }
        api
          .post(`/board/register/img`, form, {
            headers: { "Content-Type": "multipart/form-data" },
          })
          .then(({ data }) => {
            let msg =
              "DB에 잘 들어가는데 왜 등록 처리시 문제가 발생했습니다.라는거야";
            if (data === "success") {
              msg = "등록이 완료되었습니다.";
            }
            alert(msg);
            this.moveList();
          });
      }
    },
    modifyArticle() {
      api
        .put(`/board`, {
          articleNo: this.article.articleNo,
          userId: this.article.userId,
          subject: this.article.subject,
          content: this.article.content,
        })
        .then(({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
    setUserId() {
      this.article.userId = this.userInfo.id;
    },
  },
};
</script>

<style scoped>
#userid,
#subject,
#content {
  border-left-width: 0;
  border-right-width: 0;
  border-top-width: 0;
  border-bottom-width: 1;
  width: 100%;
  border-radius: 0;
  border-bottom-color: lightgray;
}
#subject:focus,
#content:focus {
  outline: none;
  border-bottom-width: 2px;
  border-bottom-color: black;
}
button {
  background-color: #dfe4ff;
  border: 0;
  color: black;
  /* font-weight: bold; */
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
