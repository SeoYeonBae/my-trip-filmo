<template>
  <div>
    <b-carousel
      id="carousel-fade"
      style="text-shadow: 0px 0px 2px #000"
      fade
      indicators
      img-width="1024"
      img-height="480"
    >
      <b-carousel-slide
        caption="- 영화같은 여행의 주연은 당신"
        text="쏼라쏼라"
        img-src="https://picsum.photos/1024/480/?image=10"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="- 내가 꿈꾸는 여행"
        text="주쭝"
        img-src="https://picsum.photos/1024/480/?image=12"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="- 내가 만드는 여행"
        text="우하하하"
        img-src="https://picsum.photos/1024/480/?image=52"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="- 여행의 주연과 함께하세요"
        text="고고"
        img-src="https://picsum.photos/1024/480/?image=58"
      ></b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
export default {
  name: "MainCoverSlider",
};
</script>

<style></style>
