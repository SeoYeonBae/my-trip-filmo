<template>
  <div>
    <b-container>
      댓글창
      <div :v-model="articleno">{{ articleno }}</div>
      <!-- <div class="container mt-5"> -->
      <!-- <c:forEach var="replys" items="${total_reply}">
            <b-container class="pt-3 ps-4 pe-4">
                <div class="row my-auto mb-3 ms-2">
                    <form method="post" class="col-10 form-reply">
                        <input type="hidden" value="${replys.idx}" class="modified-idx"
                            name="reply-idx" />
                        <p class="row font-weight-bold text-black">${replys.user_id}
                            님의 댓글</p>
                        <b-row>
                        <h6
                            class="origin-reply font-weight-bold text-primary heading mb-4">"
                            ${replys.content} "</h6>
                        </b-row>
                        <b-row class="row modify-div" style="display: none">
                            <textarea class="form-control mb-4" name="modify-content"
                                rows="3">${replys.content}</textarea>
                        </b-row>
                    </form>
                    <div class="col-2 mb-3 text-center">
                        <div v-show="this.writerid == this.userInfo.id">
                            <input type="button" value="수정"
                                class="modify-btn btn-sm btn-primary" />
                            <input type="button" value="삭제"
                                class="delete-btn btn-sm btn-warning" />
                        </div>
                        <div class="mt-3 mb-3 justify-content-center  float-right">${replys.regist_time}</div>
                    </div>
                </div>
            </b-container>
            <hr> -->
      <!-- </c:forEach> -->
      <!-- </div> -->
    </b-container>
  </div>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";

const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "BoardReplyItem",
  props: {
    articleno: { articleno: String },
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      replys: [],
    };
  },
  created() {
    // this.getReplys();
  },
  methods: {
    getReplys() {
      api.get(`/board/reply`).then(({ data }) => console.log(data));
    },
  },
};
</script>

<style scoped></style>
