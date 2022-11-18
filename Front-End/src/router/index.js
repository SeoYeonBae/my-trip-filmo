import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";
import RecommendView from "@/components/recommend/RecommendView";
import PlanView from "@/components/plan/PlanView";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "appMain",
    component: AppMain,
  },
  {
    path: "/recommend/:typeid",
    name: "recommend",
    component: RecommendView,
  },
  {
    path: "/plan",
    name: "plan",
    component: PlanView,
  },
  {
    path: "/qna",
    name: "appQnA",
    component: () => import("@/views/AppQnA"),
  },
  {
    path: "/sun",
    name: "sun",
    component: () => import("@/views/AppSunRiseSunSet"),
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
        component: () => import("@/components/tourlist/TourListShowMap"),
      },
      {
        path: "image",
        name: "tourimage",
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
        component: () => import("@/components/board/BoardWrite"),
      },
      {
        path: "view/:articleno",
        name: "boardview",
        component: () => import("@/components/board/BoardView"),
      },
      {
        path: "modify",
        name: "boardmodify",
        component: () => import("@/components/board/BoardModify"),
      },
      {
        path: "delete/:articleno",
        name: "boarddelete",
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
        component: () => import("@/components/notice/NoticeWrite"),
      },
      {
        path: "view/:articleno",
        name: "noticeview",
        component: () => import("@/components/notice/NoticeView"),
      },
      {
        path: "modify",
        name: "noticemodify",
        component: () => import("@/components/notice/NoticeModify"),
      },
      {
        path: "delete/:articleno",
        name: "noticedelete",
        component: () => import("@/components/notice/NoticeDelete"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
