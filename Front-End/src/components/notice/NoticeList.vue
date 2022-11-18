<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show variant="warning"><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button @click="moveWrite()">글쓰기</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table striped hover :items="articles" :fields="fields" @row-clicked="viewArticle">
          <template #cell(subject)="data">
            <router-link :to="{ name: 'noticeview', params: { articleno: data.item.articleno } }">
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "NoticeList",
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
    };
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
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
  background-color: #f8c5b4;
  border: 0;
  color: black;
  padding-left: 16px;
  padding-right: 16px;
  width: 100px;
  margin-bottom: 10px;
}
button:hover {
  background-color: #f8c5b4;
  color: white;
}
</style>
