<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3>글수정</h3>
      </b-col>
    </b-row>
    <notice-input-item type="modify" />
  </b-container>
</template>

<script scoped>
import NoticeInputItem from "@/components/notice/item/NoticeInputItem";

export default {
  name: "NoticeModify",
  components: {
    NoticeInputItem,
  },
};
</script>

<style scoped>
h3 {
  text-align: center;
  font-weight: bold;
  margin-top: 5%;
  margin-bottom: 5%;
}
</style>
