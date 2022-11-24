<template>
  <b-container id="main">
    <!-- <div>/ {{ this.$route.params.planidx }}번 계획을 생성했음</div> -->
    <b-row>
      <b-col md="3" class="my-auto"><h5>TITLE</h5> </b-col>
      <b-col>
        <b-form-input type="text" v-model="planTitle" placeholder="여행 제목을 입력해주세요..." />
      </b-col>
    </b-row>
    <b-row>
      <b-col md="3" class="my-auto">
        <h5>INVITED</h5>
      </b-col>
      <b-col>
        <b-form-input
          v-model="invitedUser"
          placeholder="동행자의 아이디를 입력해주세요..."
          :class="{ 'not-exist': isNotExist }"
          required
        ></b-form-input>
      </b-col>
    </b-row>
    <b-row>
      <b-col md="3">
        <h5>CHECK LIST</h5>
      </b-col>
      <b-col md="8">
        <b-form-group id="check-form" label-for="userid">
          <b-form-input
            v-model="checkInput"
            type="text"
            required
            placeholder="체크리스트 입력..."
          ></b-form-input>
        </b-form-group>
      </b-col>
      <b-col md="1" class="mt-1 flex text-align-center justify-content-center">
        <button class="todobtn" @click="addList(checkInput)">
          <font-awesome-icon icon="fa-solid fa-circle-plus" style="color: #dfe4ff" />
        </button>
      </b-col>
    </b-row>
    <div style="padding-left: 220px">
      <b-form-group v-slot="{ ariaDescribedby }">
        <b-form-checkbox-group
          v-model="selected"
          :options="checklist"
          :aria-describedby="ariaDescribedby"
          name="flavour-2a"
          stacked
        ></b-form-checkbox-group>
      </b-form-group>
    </div>
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
      ><b-button size="lg" id="btnregist" @click="registPlanInfo()">완성하기</b-button></b-row
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
      isNotExist: true,
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
      checkInput: "",
      checklist: [],
      selected: [],
    };
  },
  watch: {
    invitedUser: function () {
      this.isNotExist = false;
      this.checkUserId();
    },
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
  },
  methods: {
    getMyPlan() {
      console.log("방문할 여행지의 idx들을 받아옵니다");
      api.get(`/plan/myplan/${this.$route.params.planidx}`).then(({ data }) => {
        this.myPlaces = data;
        console.log(data);
      });
    },
    registPlanInfo() {
      if (this.isNotExist) alert("동행인의 아이디를 다시 확인해주세요");
      if (this.planTitle == "") alert("여행 제목을 입력해주세요");
      else {
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
      }
    },
    checkUserId() {
      // 존재하는 유저인지 확인
      api.get(`/user/${this.invitedUser}`).then(({ data }) => {
        // api.post(`/plan/getuser`, user_id).then(({ data }) => {
        if (data == 0) this.isNotExist = true;
        else this.isNotExist = false;
      });
    },
    addList(input) {
      if (input == "") alert("체크리스트를 입력해주세요");
      else this.checklist.push(input);
    },
  },
};
</script>

<style scoped>
#main {
  padding: 100px 200px 100px 200px;
}
.row {
  margin-bottom: 20px;
}
.row > * {
  text-align: center;
  width: 100%;
  margin: auto 0;
}
.todobtn {
  float: right;
  background-color: transparent;
  border-style: none;
  border: none;
  box-shadow: none;
  height: auto;
}
#btnregist {
  background-color: #dfe4ff;
  border: 0;
  color: black;
  font-weight: bold;
  border-style: none;
  border: none;
  box-shadow: none;
  width: 80%;
  height: 50px;
}
#btnregist:hover {
  background-color: #d4fcee;
  color: black;
}
.not-exist {
  color: red;
}
h5 {
  font-weight: bold;
}
input {
  border-left-width: 0;
  border-right-width: 0;
  border-top-width: 0;
  border-bottom-width: 1;
  width: 100%;
  border-radius: 0;
  border-bottom-color: lightgray;
}
input:focus {
  outline: none;
  border-bottom-width: 2px;
  border-bottom-color: black;
}
</style>
