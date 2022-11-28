import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";

import store from "@/store";

Vue.use(VueRouter);

const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo != null && token) {
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "appMain",
    component: AppMain,
  },
  {
    path: "/recommend/:typeid",
    name: "recommend",
    beforeEnter: onlyAuthUser,
    component: () => import("@/components/recommend/RecommendView"),
  },
  {
    path: "/plan",
    name: "plan",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/AppPlan"),
    children: [
      {
        path: "view",
        name: "planview",
        component: () => import("@/components/plan/PlanView.vue"),
      },
      {
        path: "info",
        name: "planinfo",
        component: () => import("@/components/plan/PlanInputItem.vue"),
      },
    ],
  },
  {
    path: "/sun",
    name: "sun",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/AppSunRiseSunSet"),
  },
  {
    path: "/about",
    name: "about",
    component: () => import("@/views/AppAbout"),
  },
  {
    path: "/faq",
    name: "appFaq",
    component: () => import("@/views/AppFaq"),
  },
  {
    path: "/tour",
    name: "tour",
    component: () => import("@/views/AppTourList"),
    redirect: "/tour/choice",
    children: [
      {
        path: "choice",
        name: "tourchoice",
        component: () => import("@/components/tourlist/TourListChoicePanel"),
      },
      {
        path: "map",
        name: "tourmap",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/tourlist/TourListShowMap"),
      },
      {
        path: "image",
        name: "tourimage",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/tourlist/TourListShowImage"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/views/AppUser"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/user/UserLogin"),
      },
      {
        path: "join",
        name: "join",
        component: () => import("@/components/user/UserRegister"),
      },
      {
        path: "password",
        name: "findpassword",
        component: () => import("@/components/user/UserFindPassword"),
      },
    ],
  },
  {
    path: "/mypage",
    name: "mypage",
    beforeEnter: onlyAuthUser,
    component: () => import("@/views/AppMyPage"),
    redirect: "/mypage/profile",
    children: [
      {
        path: "profile",
        name: "userinfo",
        component: () => import("@/components/user/mypage/MyPageUserInfo"),
      },
      {
        path: "planlist",
        name: "planlist",
        component: () => import("@/components/user/mypage/MyPagePlanList"),
      },
      {
        path: "plandetail",
        name: "plandetail",
        component: () => import("@/components/user/mypage/MyPagePlanDetail"),
      },
      {
        path: "articlelist",
        name: "myarticlelist",
        component: () => import("@/components/user/mypage/MyPageArticleList"),
      },
      {
        path: "replylist",
        name: "myreplylist",
        component: () => import("@/components/user/mypage/MyPageReplyList"),
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: () => import("@/views/AppBoard"),
    redirect: "/board/list",
    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "write",
        name: "boardwrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno",
        name: "boardview",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "modify",
        name: "boardmodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/board/BoardDelete"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: () => import("@/views/AppNotice"),
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticelist",
        component: () => import("@/components/notice/NoticeList"),
      },
      {
        path: "write",
        name: "noticewrite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/notice/NoticeWrite"),
      },
      {
        path: "view/:articleno",
        name: "noticeview",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/notice/NoticeView"),
      },
      {
        path: "modify",
        name: "noticemodify",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/notice/NoticeModify"),
      },
      {
        path: "delete/:articleno",
        name: "noticedelete",
        beforeEnter: onlyAuthUser,
        component: () => import("@/components/notice/NoticeDelete"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
  scrollBehavior() {
    return { x: 0, y: 0 };
  },
});

export default router;
