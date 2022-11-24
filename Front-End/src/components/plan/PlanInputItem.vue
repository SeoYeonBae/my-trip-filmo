<template>
  <b-container fill id="main" class="justify-content-center">
    <div>{{ userId }} / {{ planidx }}번 계획을 생성했음</div>
    <div>이번 여행 이름을 입력하세요 : <input type="text" v-model="planTitle" /> {{ planTitle }}</div>
    <!-- <div>this.tourIdx</div> -->

    <div>
      <div v-for="(item, idx) in myPlaces" :key="idx" class="text-align-center">
        <b-row>
          <b-col>
            <b-card
              :img-src="`${item.image}`"
              img-top
              img-height="130px"
              class="mt-4 mb-4 me-2"
              style="max-width: 13rem; min-width: 15rem"
            >
              <b-card-text> {{ item.addr1 }}</b-card-text>
            </b-card>
          </b-col>
          <b-col
            ><h4 class="my-auto">
              {{ item.title }}
            </h4>
          </b-col>
        </b-row>
      </div>
    </div>
    <b-row style="display: flex; justify-content: center"
      ><b-button size="lg" @click="registPlanInfo()" style="background-color: #dfe4ff; color: black; border: none"
        >완성하기</b-button
      ></b-row
    >
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
const api = apiInstance();

export default {
  name: "PlanInputItem",
  data() {
    return {
      userId: "",
      myPlaces: [],
      planidx: 0,
      tourIdx: {},
      planTitle: "",
      planInfo: [],
    };
  },
  watch: {
    planTitle: function () {
      console.log(this.planTitle);
    },
  },
  created() {
    this.userId = this.$route.params.id;
    this.planidx = this.$route.params.planidx;
    this.getMyPlan();
  },
  methods: {
    getMyPlan() {
      console.log("방문할 여행지의 idx들을 받아옵니다");
      api.get(`/plan/myplan/${this.planidx}`).then(({ data }) => {
        this.tourIdx = data;
        console.log(" => 잘 담았음 : " + this.tourIdx);

        if (this.tourIdx != null) {
          console.log("방문지들의 info를 받아옵니다.");
          // idx 배열을 넘겨줌
          api.post(`/plan/myplan/details`, this.tourIdx).then(({ data }) => {
            this.myPlaces = data;
            console.log(data);
            console.log(this.myPlaces);
          });
        }
      });
    },
    registPlanInfo() {},
  },
};
</script>

<style scoped>
#main {
  padding: 100px 20px 100px 200px;
}
.row > * {
  display: flex;
  justify-content: center;
  text-align: center;
}
</style>
