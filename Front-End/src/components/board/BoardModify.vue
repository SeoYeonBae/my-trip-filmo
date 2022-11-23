<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3>글수정</h3>
      </b-col>
    </b-row>
    <board-input-item type="modify" />
  </b-container>
</template>

<script scoped>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardModify",
  components: {
    BoardInputItem,
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
