<template>
  <b-container fill id="main" class="justify-content-center">
    <!-- <div>/ {{ this.$route.params.planidx }}번 계획을 생성했음</div> -->
    <b-row class="mb-5">
      <b-col md="6">
        <div>
          <p>
            제목
            <b-form-input type="text" v-model="planTitle" placeholder="여행 이름을 입력해주세요..." />
          </p>
          <div>
            <p>
              초대자
              <b-form-select v-model="invitedUser" :options="allUsers"></b-form-select>
            </p>
          </div>
        </div>
      </b-col>
      <b-col md="6">
        <b-col>
          <div>
            투두리스트
            <button class="todobtn" @click="addForm()">
              <font-awesome-icon icon="fa-solid fa-circle-plus fa-lg" style="color: #dfe4ff" />
            </button>
          </div>
          <div>
            <b-form-group id="userid-group" label-for="userid">
              <b-form-input
                id="userid"
                v-model="checkList"
                type="text"
                required
                placeholder="체크리스트 입력..."
              ></b-form-input>
            </b-form-group>
          </div>
        </b-col>
      </b-col>
    </b-row>
    <!-- <div>this.tourIdx</div> -->
    <hr />
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
    <b-row class="mt-5" style="display: flex; justify-content: center"
      ><b-button
        size="lg"
        id="btnregist"
        @click="registPlanInfo()"
        style="background-color: #dfe4ff; color: black; border: none"
        >완성하기</b-button
      ></b-row
    >
  </b-container>
</template>

<script>
import { apiInstance } from "@/api/index.js";
import { mapState } from "vuex";
const api = apiInstance();
const memberStore = "memberStore";

export default {
  name: "PlanInputItem",
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  data() {
    return {
      myPlaces: [],
      tourIdx: {},
      planTitle: "",
      planInfo: {
        plan_idx: 0,
        title: "",
        user_id: "",
        invited_user: "",
      },
      invitedUser: "",
      checkList: "",
      allUsers: {},
    };
  },
  watch: {
    // planTitle: function () {
    //   console.log(this.planTitle);
    //   this.planInfo.plan_idx = this.planidx;
    //   this.planInfo.title = this.planTitle;
    //   this.planInfo.user_id = this.userInfo.id;
    //   this.planInfo.invited_user = this.invitedUser;
    // },
  },
  created() {
    console.log(this.$route.params.planidx + "번 계획 상세정보 등록 시작");
    this.getMyPlan();
    this.getAllUsers();
  },
  methods: {
    getMyPlan() {
      console.log("방문할 여행지의 idx들을 받아옵니다");
      api.get(`/plan/myplan/${this.$route.params.planidx}`).then(({ data }) => {
        this.myPlaces = data;
        console.log(data);
        // this.tourIdx = data;
        // console.log(" => 잘 담았음 : " + this.tourIdx);

        // if (this.tourIdx != null) {
        //   console.log("방문지들의 info를 받아옵니다.");
        //   // idx 배열을 넘겨줌
        //   api.post(`/plan/myplan/details`, this.tourIdx).then(({ data }) => {
        //     this.myPlaces = data;
        //     console.log(data);
        //     console.log(this.myPlaces);
        //   });
        // }
      });
    },
    registPlanInfo() {
      api
        .post(`/plan/regist/info`, {
          title: this.planTitle,
          plan_idx: this.$route.params.planidx,
          invited_user: this.invitedUser,
        })
        .then(({ data }) => {
          console.log(data);
        });
      this.$router.push({ name: "planlist" });
    },
    getAllUsers() {
      // 존재하는 유저인지 확인
      // api.get(`/user/getall`).then(({ data }) => {
      //   console.log(data);
      //   // this.allUsers = data;
      // });
    },
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
.todobtn {
  float: right;
  background-color: transparent;
  border-style: none;
  border: none;
  box-shadow: none;
  height: auto;
}
#btnregist:hover {
  background-color: rgb(203, 209, 255);
  color: white;
  border: none;
}
</style>
