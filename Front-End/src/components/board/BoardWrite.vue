<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h3>글작성</h3>
      </b-col>
    </b-row>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem";

export default {
  name: "BoardWrite",
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
button {
  margin-left: 30px;
  background-color: #d4fcee;
  border: 0;
  color: black;
  padding-left: 16px;
  padding-right: 16px;
  width: 100px;
}
button:hover {
  background-color: #d4fcee;
  color: white;
}
</style>
