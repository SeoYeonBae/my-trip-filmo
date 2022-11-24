<template>
  <b-col md="8">
    <b-jumbotron class="mt-5">
      <font-awesome-icon icon="fa-plane-departure" class="fa-2x mb-3" />
      <h4 class="mb-3">내가 만든 여행 계획</h4>
      <h5>카드를 눌러 여행 계획의 상세 정보를 확인해 보세요</h5>

      <hr class="my-4" />

      <b-card
        v-for="(item, idx) in this.myplans"
        :key="idx"
        no-body
        class="overflow-hidden mb-5"
        style="max-width: 540px"
      >
        <b-row no-gutters @click="$router.push({ name: 'plandetail', params: { planidx: item.plan_idx } })">
          <div>{{ item.plan_idx }}</div>
          <b-col md="6">
            <b-card-img :src="require('@/assets/img/cover.gif')" alt="Image" class="rounded-0"></b-card-img>
          </b-col>
          <b-col md="6">
            <div>
              <b-card-body>
                <b-card-title>{{ item.title }}</b-card-title>
                <b-card-text> {{ item.start_date }} - {{ item.end_date }} </b-card-text>
                <b-card-text> 일행 : " {{ item.invited_user }} " 님</b-card-text>
              </b-card-body>
            </div>
          </b-col>
        </b-row>
      </b-card>
    </b-jumbotron>
  </b-col>
</template>

<script>
import { mapState } from "vuex";
import { apiInstance } from "@/api/index.js";
const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "MyPagePlanList",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      myplans: [],
    };
  },
  created() {
    this.getMyPlans();
  },
  methods: {
    getMyPlans() {
      console.log("나의 계획들을 가져옵니다");
      api.get(`/plan/total/planlist/${this.userInfo.id}`).then(({ data }) => {
        console.log(data);
        this.myplans = data;
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
.jumbotron {
  border: none;
  background-color: transparent;
  width: 80%;
  padding-top: 0%;
}
</style>
