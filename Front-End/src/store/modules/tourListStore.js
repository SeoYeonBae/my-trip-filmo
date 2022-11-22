import { sidoList, gugunList, tourListData } from "@/api/tourList.js";

const houseStore = {
  namespaced: true,
  state: {
    sidoCode: 0,
    gugunCode: 0,
    contentTypeId: 0,
    sidos: [{ value: null, text: "우리나라의" }],
    guguns: [{ value: null, text: "이곳으로" }],
    tourList: [],
    mapShow: false,
    imageShow: false,
  },
  getters: {},
  mutations: {
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "우리나라의" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "이곳으로" }];
    },
    CLEAR_CONTENT_TYPE_ID(state) {
      state.contentTypeId = 0;
    },
    CLEAR_SIDO_CODE(state) {
      state.sidoCode = 0;
    },
    CLEAR_GUGUN_CODE(state) {
      state.gugunCode = 0;
    },
    CLEAR_TOUR_LIST(state) {
      state.tourList = [];
    },
    CLEAR_MAP_SHOW(state) {
      state.mapShow = false;
    },
    CLEAR_IMAGE_SHOW(state) {
      state.imageShow = false;
    },
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.areaCode, text: sido.name });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.sigunguCode, text: gugun.name });
      });
    },
    SET_CONTENT_TYPE_ID(state, inputId) {
      state.contentTypeId = inputId;
    },
    SET_SIDO_CODE(state, inputCode) {
      state.sidoCode = inputCode;
    },
    SET_GUGUN_CODE(state, inputCode) {
      state.gugunCode = inputCode;
    },
    SET_TOUR_LIST(state, tourList) {
      state.tourList = tourList;
    },
    SET_MAP_SHOW(state, isMap) {
      state.mapShow = isMap;
    },
    SET_IMAGE_SHOW(state, isImage) {
      state.imageShow = isImage;
    },
  },
  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      gugunList(
        sidoCode,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getTourList({ commit, state }) {
      console.log(state.sidoCode, state.gugunCode, state.contentTypeId);
      tourListData(
        state.sidoCode,
        state.gugunCode,
        state.contentTypeId,
        ({ data }) => {
          console.log(data);
          commit("CLEAR_TOUR_LIST");
          commit("SET_TOUR_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
