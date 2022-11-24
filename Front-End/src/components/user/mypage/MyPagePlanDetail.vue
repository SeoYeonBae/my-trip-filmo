<template>
  <b-col md="8">
    <b-jumbotron class="mt-5">
      <div>
        <font-awesome-icon icon="fa-plane-departure" class="fa-2x mb-3" />
        <!-- <b-button @click="$router.push({ name: 'planlist' })">이전으로</b-button> -->
        <h3 class="mb-3" style="font-weight: bold">" {{ planDetails.title }} "</h3>
        <h5 class="mb-4 font-italic">{{ planDetails.start_date }} - {{ planDetails.end_date }}</h5>
        <h6 v-if="planDetails.invited_user == ''"></h6>
        <h6 v-else style="color: grey">
          <font-awesome-icon icon="fa-regular fa-address-card" />
          {{ planDetails.invited_user }} 님과 함께합니다
        </h6>
        <!-- <h5>여행 관련 체크 리스트 들어갈 공간</h5> -->
        <hr class="my-4" />
        <div
          class="overflow-hidden shadow bg-body rounded justify-content-center"
          style="min-height: 500px"
        >
          <div v-for="(item, idx) in myPlaces" :key="idx" class="text-align-center">
            <b-row style="min-width: 400px; padding: 30px">
              <b-col>
                <b-card
                  :img-src="`${item.image}`"
                  img-top
                  img-height="130px"
                  class="mt-4 mb-4 me-2"
                  style="max-width: 13rem; min-width: 15rem"
                >
                  <b-card-text style="color: grey"> {{ item.addr1 }}</b-card-text>
                </b-card>
              </b-col>
              <b-col
                ><b-img
                  :src="require('@/assets/img/MarkerIcon.png')"
                  style="width: 40px; height: 40px"
                  class="my-auto"
                ></b-img>
              </b-col>
              <b-col
                ><h5 class="my-auto" style="font-weight: bold">
                  {{ item.title }}
                </h5>
              </b-col>
            </b-row>
          </div>
        </div>
      </div>
      <b-row class="mt-5" style="display: flex; justify-content: center"
        ><b-button size="lg" @click="deletePlanInfo()">삭제</b-button></b-row
      >
    </b-jumbotron>
  </b-col>
</template>

<script>
import { mapState } from "vuex";
import { apiInstance } from "@/api/index.js";
const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "MyPagePlanDetail",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      planDetails: {
        title: "",
        start_date: "",
        end_date: "",
        invited_user: "",
      },
      myPlaces: [],
      fields: [
        { key: "period", label: "기간", tdClass: "tdClass" },
        { key: "invited_user", label: "일행", tdClass: "tdSubject" },
        { key: "checklist", label: "체크리스트", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    console.log(this.$route.params.planidx + "번 계획의 상세정보입니다");
    this.getMyPlanInfo();
    this.getMyPlaces();
  },
  methods: {
    getMyPlaces() {
      api.get(`/plan/myplan/${this.$route.params.planidx}`).then(({ data }) => {
        console.log("=> 상세내용을 불러옵니다.");
        console.log(data);
        this.myPlaces = data;
      });
    },
    getMyPlanInfo() {
      api.get(`/plan/myplan/info/${this.$route.params.planidx}`).then(({ data }) => {
        this.planDetails = data;
        console.log(data);
        console.log(this.planDetails);
      });
    },
    deletePlanInfo() {
      api.delete(`/plan/delete/${this.$route.params.planidx}`).then(({ data }) => {
        console.log(data);
        this.$router.push({ name: "planlist" });
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
.row > * {
  display: flex;
  justify-content: center;
  text-align: center;
}
button {
  background-color: #dfe4ff;
  border: 0;
  color: black;
  font-weight: bold;
  padding: 16px;
  float: right;
}
button:hover {
  background-color: #d4fcee;
  color: black;
}
.jumbotron {
  border: none;
  background-color: transparent;
  width: 80%;
  padding-top: 0%;
}
</style>
