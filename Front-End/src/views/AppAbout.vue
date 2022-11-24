<template>
  <div id="app">
    <main class="sticky-container mt-3" ref="sticky-container">
      <div class="sticky">
        <div class="slide-container">
          <div class="slide" ref="scdown">
            <b-row>
              <b-col>
                <div class="slide-big-text">
                  <p style="font-size: 50px">여 행의</p>
                  <p style="font-size: 50px">행 복이 시작되는 곳</p>
                  <p style="font-size: 50px">의</p>
                  <p style="font-size: 50px">주 인공이 되어</p>
                  <p style="font-size: 50px">연 휴를 즐겨보자</p>
                </div>
                <p class="mb-0 mt-5">
                  여행 계획의 훌륭한 <em><b>길라잡이</b></em>
                </p>
                <p>오늘도 내일도 내가 주인공</p>
                <p>매일이 오늘만 같아라</p>
              </b-col>
              <b-col class="mt-5 pt-5 fade-in-box">
                <b-img :src="require('@/assets/aboutImg/main.png')"></b-img>
              </b-col>
            </b-row>
          </div>
          <div class="scdown blink text-center mb-5">
            go
            <font-awesome-icon icon="fa-solid fa-arrow-down" />
          </div>
          <div class="slide" ref="sl1">
            <div class="slide-big-text text-center">
              <p class="mb-5">내가 찾는 바로 그거</p>
              <p>여행의 주연에 "다" 있다</p>
            </div>
          </div>
          <div class="slide" ref="sl2">
            <b-row>
              <b-col>
                <div class="slide-big-text">
                  <p>01.</p>
                  <p class="mb-3">지금 당장 떠나자!</p>
                  계획은 없지만 바로 떠나고 싶다면 <br />
                  오늘의 핫플 추천 받기를 이용해보세요<br />
                  맛집, 축제, 관광지를 추천해드려요<br /><br />

                  <h5><b>고민보단 go!</b></h5>
                </div>
              </b-col>
              <b-col class="mt-5 up-img">
                <b-img :src="require('@/assets/aboutImg/recommend.png')"></b-img>
              </b-col>
            </b-row>
          </div>
          <div class="slide sl3" ref="sl3">
            <b-row>
              <b-col>
                <div class="slide-big-text">
                  <p>02.</p>
                  <p>검색부터 계획까지</p>
                  <p class="mb-3">한 번에</p>
                  <h5 class="font-weight-bold">지역별 관광지? OK!</h5>
                  <h5 class="font-weight-bold">테마별 관광지? OK!</h5>
                  <br />
                  여러 지역의 관광지를 한번에 한 눈에<br />
                  마음에 드는 곳은 나의 여행 계획에 추가해보세요
                </div>
              </b-col>
              <b-col>
                <b-img :src="require('@/assets/aboutImg/search_plan.png')"></b-img>
              </b-col>
            </b-row>
          </div>
          <div class="slide slide-left sl4" ref="sl4">
            <div class="sl4-content">
              <b-row>
                <b-col>
                  <div class="slide-big-text" ref="sl4-big">
                    <p>03.</p>
                    <p>해가 뜰 때 소원을</p>
                    <p class="mb-3">해가 질 때 인생샷을</p>

                    <h5 class="font-weight-bold">인생샷 공식의 삼요소</h5>
                    <h5 class="font-weight-bold">온도, 습도 그리고 조명</h5>
                    <br />

                    <h5 class="font-weight-bold">자연이 선물하는 최고의 조명 일출 일몰</h5>
                    <br />
                    여행날짜와 위치만 입력하면 <br />
                    일출 일몰 시간을 확인할 수 있어요.
                  </div>
                </b-col>
                <b-col>
                  <b-img :src="require('@/assets/aboutImg/sun.png')" style="width: 500px"></b-img>
                </b-col>
              </b-row>
            </div>
          </div>
          <div class="slide slide-left sl5" ref="sl5">
            <b-row>
              <b-col>
                <div class="slide-big-text">
                  <p>04.</p>
                  <p class="mb-3">사람들은 어딜 가지?</p>
                  서로 공유하는 나만의 핫플 <br />
                  우리나라 여행지의 아름다운 곳곳을 보여주세요
                </div>
              </b-col>
              <b-col>
                <b-img :src="require('@/assets/aboutImg/board.png')"></b-img>
              </b-col>
            </b-row>
          </div>
          <div class="slide slide-left sl6" ref="sl6">
            <b-row>
              <b-col>
                <div class="slide-big-text">
                  <p>05.</p>
                  <p>친구야 같이 가자!</p>
                  <p>계획도 공유도</p>
                  <p class="mb-3">여행의 주연에서</p>

                  내가 만든 여행 계획에 함께 갈 친구를 초대할 수 있어요<br />
                  완성된 계획을 카톡으로 공유해 여행내내 간편하게 관리하세요
                </div>
              </b-col>
              <b-col>
                <b-img :src="require('@/assets/aboutImg/friend.png')"></b-img>
              </b-col>
            </b-row>
          </div>
          <div class="wave" ref="wave"></div>
          <div class="slide slide-left sl7" ref="sl7">
            <div class="slide-big-text">
              <p>여행의 주연</p>
              <p>지금 만나 보세요</p>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import bezierEasing from "bezier-easing";

const ease = bezierEasing(0.25, 0.1, 0.25, 1.0);
const easeIn = bezierEasing(0.38, 0.01, 0.78, 0.13);
const midSlow = bezierEasing(0, 0.7, 1, 0.3);

const def = {
  height: 10300,
  elements: {
    scdown: {
      top: 0,
      bottom: 1000,
      topStyle: {
        opacity: 1,
      },
      bottomStyle: {
        opacity: 0,
      },
    },
    sl1: {
      top: 500,
      bottom: 1900,
      topStyle: {
        opacity: 0,
        translateY: -60,
      },
      bottomStyle: {
        opacity: 0,
        translateY: 60,
      },
    },
    sl2: {
      top: 1900,
      bottom: 3300,
      topStyle: {
        opacity: 0,
        translateY: -60,
      },
      bottomStyle: {
        opacity: 0,
        translateY: 60,
      },
    },
    sl3: {
      top: 3300,
      bottom: 4700,
      topStyle: {
        opacity: 0,
      },
      bottomStyle: {
        opacity: 0,
      },
    },
    sl4: {
      top: 4700,
      bottom: 6100,
      topStyle: {
        opacity: 0,
      },
      bottomStyle: {
        opacity: 0,
      },
    },
    sl5: {
      top: 6100,
      bottom: 7500,
      topStyle: {
        opacity: 0,
      },
      bottomStyle: {
        opacity: 0,
      },
    },
    sl6: {
      top: 7500,
      bottom: 8900,
      topStyle: {
        opacity: 0,
      },
      bottomStyle: {
        opacity: 0,
      },
    },
    sl7: {
      top: 8900,
      bottom: 10300,
      topStyle: {
        opacity: 0,
      },
      bottomStyle: {
        opacity: 0,
      },
    },
  },
  animations: {
    scdown: [
      {
        top: 600,
        bottom: 1000,
        easing: easeIn,
        styles: {
          opacity: {
            topValue: 1,
            bottomValue: 0,
          },
        },
      },
    ],
    sl1: [
      {
        top: 500,
        bottom: 1900,
        easing: midSlow,
        styles: {
          translateY: {
            topValue: 60,
            bottomValue: -60,
          },
        },
      },
      {
        top: 500,
        bottom: 1200,
        easing: ease,
        styles: {
          opacity: {
            topValue: 0,
            bottomValue: 1,
          },
        },
      },
      {
        top: 1200,
        bottom: 1900,
        easing: easeIn,
        styles: {
          opacity: {
            topValue: 1,
            bottomValue: 0,
          },
        },
      },
    ],
    sl2: [
      {
        top: 1900,
        bottom: 3300,
        easing: midSlow,
        styles: {
          translateY: {
            topValue: 60,
            bottomValue: -60,
          },
        },
      },
      {
        top: 1900,
        bottom: 2600,
        easing: ease,
        styles: {
          opacity: {
            topValue: 0,
            bottomValue: 1,
          },
        },
      },
      {
        top: 2600,
        bottom: 3300,
        easing: easeIn,
        styles: {
          opacity: {
            topValue: 1,
            bottomValue: 0,
          },
        },
      },
    ],
    sl3: [
      {
        top: 3300,
        bottom: 4700,
        easing: midSlow,
        styles: {
          translateY: {
            topValue: 60,
            bottomValue: -60,
          },
        },
      },
      {
        top: 3300,
        bottom: 4000,
        easing: ease,
        styles: {
          opacity: {
            topValue: 0,
            bottomValue: 1,
          },
        },
      },
      {
        top: 4000,
        bottom: 4700,
        easing: easeIn,
        styles: {
          opacity: {
            topValue: 1,
            bottomValue: 0,
          },
        },
      },
    ],
    sl4: [
      {
        top: 4700,
        bottom: 6100,
        easing: midSlow,
        styles: {
          translateY: {
            topValue: 60,
            bottomValue: -60,
          },
        },
      },
      {
        top: 4700,
        bottom: 5400,
        easing: ease,
        styles: {
          opacity: {
            topValue: 0,
            bottomValue: 1,
          },
        },
      },
      {
        top: 5400,
        bottom: 6100,
        easing: easeIn,
        styles: {
          opacity: {
            topValue: 1,
            bottomValue: 0,
          },
        },
      },
    ],
    sl5: [
      {
        top: 6100,
        bottom: 7500,
        easing: midSlow,
        styles: {
          translateY: {
            topValue: 60,
            bottomValue: -60,
          },
        },
      },
      {
        top: 6100,
        bottom: 6800,
        easing: ease,
        styles: {
          opacity: {
            topValue: 0,
            bottomValue: 1,
          },
        },
      },
      {
        top: 6800,
        bottom: 7500,
        easing: easeIn,
        styles: {
          opacity: {
            topValue: 1,
            bottomValue: 0,
          },
        },
      },
    ],
    sl6: [
      {
        top: 7500,
        bottom: 8900,
        easing: midSlow,
        styles: {
          translateY: {
            topValue: 60,
            bottomValue: -60,
          },
        },
      },
      {
        top: 7500,
        bottom: 8200,
        easing: ease,
        styles: {
          opacity: {
            topValue: 0,
            bottomValue: 1,
          },
        },
      },
      {
        top: 8200,
        bottom: 8900,
        easing: easeIn,
        styles: {
          opacity: {
            topValue: 1,
            bottomValue: 0,
          },
        },
      },
    ],
    sl7: [
      {
        top: 8900,
        bottom: 10300,
        easing: midSlow,
        styles: {
          translateY: {
            topValue: 60,
            bottomValue: -60,
          },
        },
      },
      {
        top: 8900,
        bottom: 9600,
        easing: ease,
        styles: {
          opacity: {
            topValue: 0,
            bottomValue: 1,
          },
        },
      },
    ],
  },
};

let enabled = new Map();
let disabled = new Map();

const isAmong = (num, top, bottom) => num >= top && num <= bottom;

const applyStyle = (element, styleName, value, unit = "px") => {
  if (styleName === "translateY") {
    // eslint-disable-next-line no-param-reassign
    element.style.transform = `translateY(${value}${unit})`;
    return;
  }
  if (styleName === "translateX") {
    // eslint-disable-next-line no-param-reassign
    element.style.transform = `translateX(${value}${unit})`;
    return;
  }
  // eslint-disable-next-line no-param-reassign
  element.style[styleName] = value;
};
export default {
  name: "AppAbout",
  data() {
    return {
      message: "Welcome to Vue!",
    };
  },
  mounted() {
    this.init();
    window.addEventListener("scroll", this.onScroll);
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.onScroll);
  },

  methods: {
    closePage() {
      window.close();
    },
    init() {
      this.initAnimation();
    },
    // 애니메이션 초기화
    initAnimation() {
      // Sticky Conainer 의 높이를 설정함.
      this.$refs["sticky-container"].style.height = `${def.height}px`;

      // disabled, enabled 를 비움.
      disabled.clear();
      enabled.clear();

      // 모든 요소를 disabled 에 넣음.
      for (const refname of Object.keys(def.elements)) {
        disabled.set(refname, def.elements[refname]);
      }

      // 각 애니메이션을 enabled == false 로 만듬.
      for (const refname of Object.keys(def.animations)) {
        for (const animation of def.animations[refname]) {
          animation.enabled = false;
        }
      }

      // 초기 스타일 적용
      disabled.forEach((obj, refname) => {
        Object.keys(obj.topStyle).forEach((styleName) => {
          const pushValue = obj.topStyle[styleName];
          this.$refs[refname].style[styleName] = pushValue;
        });
      });

      // 이미 요소의 범위 및 애니메이션의 범위에 있는 것들을 렌더링하기 위해
      // 임의로 스크롤 이벤트 핸들러를 한 번 실행시킴.
      this.onScroll();
    },
    applyStyles(currentPos, refname, styles, r, unit = "px") {
      for (const style of Object.keys(styles)) {
        const { topValue, bottomValue } = styles[style];
        const calc = (bottomValue - topValue) * r + topValue;
        applyStyle(this.$refs[refname], style, calc, unit);
      }
    },
    applyAllAnimation(currentPos, refname) {
      const animations = def.animations[refname];
      if (!animations) return;
      for (const animation of animations) {
        const { top: a_top, bottom: a_bottom, easing, styles } = animation;
        const isIn = isAmong(currentPos, a_top, a_bottom);
        // 만약 애니메이션이 새롭게 들어갈 때 혹은 나갈때 enabled 설정
        if (isIn) {
          if (!animation.enabled) animation.enabled = true;
        } else if (!isIn && animation.enabled) {
          if (currentPos <= a_top) {
            this.applyStyles(currentPos, refname, styles, 0);
          } else if (currentPos >= a_bottom) {
            this.applyStyles(currentPos, refname, styles, 1);
          }
          // eslint-disable-next-line no-param-reassign
          animation.enabled = false;
        }

        // 애니메이션이 enabled 라면, 애니메이션 적용.
        if (animation.enabled) {
          const r = easing((currentPos - a_top) / (a_bottom - a_top));
          // eslint-disable-next-line no-param-reassign
          this.applyStyles(currentPos, refname, styles, r);
        }
      }
    },
    onScroll() {
      // 현재 스크롤 위치 파악
      const scrollTop = window.scrollY || window.pageYOffset;
      const currentPos = scrollTop + window.innerHeight / 2;

      // disabled 순회하며 활성화할 요소 찾기.
      disabled.forEach((obj, refname) => {
        // 만약 칸에 있다면 해당 요소 활성화
        if (isAmong(currentPos, obj.top, obj.bottom)) {
          enabled.set(refname, obj);
          this.$refs[refname].classList.remove("disabled");
          this.$refs[refname].classList.add("enabled");
          disabled.delete(refname);
        }
      });

      // enabled 순회하면서 헤제할 요소를 체크
      enabled.forEach((obj, refname) => {
        const { top, bottom, topStyle, bottomStyle } = obj;
        // console.log(`${top}, ${bottom}, ${topStyle}, ${bottomStyle}`);
        // 범위 밖에 있다면
        if (!isAmong(currentPos, top, bottom)) {
          // 위로 나갔다면 시작하는 스타일 적용
          if (currentPos <= top) {
            Object.keys(topStyle).forEach((styleName) => {
              applyStyle(this.$refs[refname], styleName, topStyle[styleName]);
            });
          }
          // 아래로 나갔다면 끝나는 스타일적용
          else if (currentPos >= bottom) {
            Object.keys(bottomStyle).forEach((styleName) => {
              applyStyle(this.$refs[refname], styleName, bottomStyle[styleName]);
              // this.$refs[refname].style[styleName] = bottomStyle[styleName];
            });
          }

          // 리스트에서 삭제하고 disabled로 옮김.
          disabled.set(refname, obj);
          this.$refs[refname].classList.remove("enabled");
          this.$refs[refname].classList.add("disabled");
          enabled.delete(refname);
        }

        // enable 순회중, 범위 내부에 제대로 있다면 각 애니메이션 적용시키기.
        else {
          this.applyAllAnimation(currentPos, refname);
        }
      });
    },
  },
};
</script>

<style scoped>
nav {
  background-color: rgba(0, 0, 0, 0);
}

body {
  padding: 0;
  margin: 0;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  margin: 0;
  padding: 0;
}
.c2 {
  height: 400px;
}

.sticky {
  position: sticky;
  top: 0;
  width: 100%;
  height: 100vh;
}

.slide-container {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
}
.slide {
  position: absolute;
  display: none;
  z-index: 0;
  width: 70%;
}
.slide.enabled {
  display: block;
}

.slide-big-text p {
  margin: 0;
  font-size: 45px;
  font-weight: bold;
  line-height: 1.35;
  letter-spacing: -1.5px;
  word-spacing: 1.5px;
}

.slide-small-text {
  margin-top: 20px;
  margin-left: 5px;
}

.slide-small-text p {
  font-size: 18px;
  margin: 0;
}

.scdown {
  position: absolute;
  padding: 30px 0;
  width: 100%;
  height: 50px;
  bottom: 0px;
}
@keyframes blink-effect {
  50% {
    opacity: 0.3;
  }
}
.blink {
  animation: blink-effect 1s step-end infinite;
  color: gray;
}
.mainimg {
  padding: 0;
  width: 100%;
}
button {
  margin-top: 30px;
}
.sl7 {
  text-align: center;
}
.fade-in-box {
  display: inline-block;
  padding: 10px;
  animation: fadein 3s;
  -moz-animation: fadein 3s; /* Firefox */
  -webkit-animation: fadein 3s; /* Safari and Chrome */
  -o-animation: fadein 3s; /* Opera */
}
@keyframes fadein {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-moz-keyframes fadein {
  /* Firefox */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-webkit-keyframes fadein {
  /* Safari and Chrome */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
@-o-keyframes fadein {
  /* Opera */
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
button {
  height: 50px;
  width: 300px;
  color: black;
  border: none;
}
button:hover {
  background-color: #d4fcee;
}
</style>
