import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/do`).then(success).catch(fail);
}

function gugunList(areaCode, success, fail) {
  api.get(`/sigungu/${areaCode}`).then(success).catch(fail);
}

function tourListData(sidoCode, gugunCode, contentTypeId, success, fail) {
  api.get(`/tourlist/${sidoCode}/${gugunCode}/${contentTypeId}`).then(success).catch(fail);
}

export { sidoList, gugunList, tourListData };
