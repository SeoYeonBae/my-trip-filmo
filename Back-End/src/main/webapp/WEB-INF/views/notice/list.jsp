<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ include
file="/WEB-INF/views/common/header.jsp"%>

<link href="${root}/assets/css/notice.css" rel="stylesheet" />
<!-- 공지사항 start -->
<section class="container px-4 px-lg-5 mt-5 pt-5 justify-content-center">
  <!-- list start -->
  <div class="row justify-content-center" id="list-page">
    <div class="col-md-10 col-12">
      <h2 class="my-3 py-3 shadow p-3 mb-5 bg-white rounded text-center">
        <p class="fw-bold">공지사항</p>
      </h2>
    </div>
    <div class="col-md-10 col-sm-12">
      <div class="row align-self-center mb-2">
        <div class="col-md-5">
          <c:if test="${sessionStorage.getItem('isManager') eq 1}">
          <button
            type="button"
            id="btn-mv-register"
            class="btn btn-primary mb-3"
            style="height: 10px; line-height: 10px"
            onclick="viewRegister()"
          >
            글쓰기
          </button>
          </c:if>
        </div>
        <div class="col-md-7 d-flex">
          <input type="hidden" id="pgno" value="1" />
          <select
            class="form-select form-select-sm ms-5 me-1 w-50"
            id="skey"
            name="key"
            aria-label="검색조건"
          >
            <option value="" selected>검색조건</option>
            <option value="subject">제목</option>
            <option value="userid">작성자</option>
          </select>
          <div class="input-group input-group-sm">
            <input
              type="text"
              class="form-control"
              id="sword"
              name="word"
              placeholder="검색어..."
            />
            <button id="btn-search" class="btn btn-dark" type="button">
              검색
            </button>
          </div>
        </div>
      </div>
      <table class="table table-hover">
        <thead>
          <tr class="text-center">
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">작성자</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
          </tr>
        </thead>
        <tbody id="notice-tbody"></tbody>
      </table>
    </div>
    <div class="m-3 row" id="navigation"></div>
  </div>
  <!-- list end -->
  <!-- 글쓰기 start -->
  <div
    class="row justify-content-center"
    id="register-page"
    style="display: none"
  >
    <div class="col-md-10 col-12">
      <h2 class="my-3 py-3 shadow p-3 mb-5 bg-white rounded text-center">
        <p class="fw-bold">글쓰기</p>
      </h2>
    </div>
    <div class="col-md-10 col-12">
      <input type="hidden" id="act" name="act" value="write" />
      <div class="mb-3">
        <label for="subject" class="form-label">제목 : </label>
        <input
          type="text"
          class="form-control"
          id="subject"
          name="subject"
          placeholder="제목..."
        />
      </div>
      <div class="mb-3">
        <label for="content" class="form-label">내용 : </label>
        <textarea
          class="form-control"
          id="content"
          name="content"
          rows="7"
        ></textarea>
      </div>
      <div class="col-auto text-center">
        <button
          type="button"
          id="btn-register"
          class="btn btn-outline-primary mb-3"
        >
          글작성
        </button>
        <button
          type="button"
          id="btn-write-to-list"
          class="btn btn-outline-danger mb-3"
        >
          목록으로이동...
        </button>
      </div>
    </div>
  </div>
  <!-- 글쓰기 end -->
  <!-- 상세보기 start -->
  <div class="row justify-content-center" id="view-page" style="display: none">
    <div class="col-md-10 col-12">
      <h2 class="my-3 py-3 shadow p-3 mb-5 bg-white rounded text-center">
        <p class="fw-bold">상세 보기</p>
      </h2>
    </div>
    <div class="row my-4" style="float: left">
      <h2 class="text-center" id="varticleno"></h2>
      <h2 class="text-center" id="vsubject"></h2>
    </div>
    <div class="row">
      <div class="col-md-8">
        <div class="clearfix align-content-center">
          <img
            class="avatar me-2 float-md-start bg-light p-2"
            src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
          />
          <p>
            <span class="fw-bold" id="vuserid">작성자</span> <br />
            <span class="text-secondary fw-light" id="vregistertime">
              작성시간
            </span>
          </p>
        </div>
      </div>
      <div class="col-md-4 align-self-center text-end" id="vhit">조회수 :</div>
      <div class="divider mb-3"></div>
      <div class="text-secondary" id="vcontent">글내용</div>
      <div class="divider mt-3 mb-3"></div>
      <div class="d-flex justify-content-end">
        <button
          type="button"
          id="btn-view-to-list"
          class="btn btn-outline-primary mb-3"
        >
          글목록
        </button>
        <!--    <c:if test="${notice.userId eq userinfo.id}"></c:if>-->
        <button
          type="button"
          id="btn-mv-modify"
          class="btn btn-outline-success mb-3 ms-1"
          onclick="viewModify()"
        >
          글수정
        </button>
        <button
          type="button"
          id="btn-delete"
          class="btn btn-outline-danger mb-3 ms-1"
          onclick="noticeDelete()"
        >
          글삭제
        </button>
      </div>
    </div>
  </div>
  <!-- 상세보기 end -->
  <!-- 수정페이지 start -->
  <div class="row justify-content-center" id="modify-page" style="display: none">
    <div class="col-md-10 col-12">
      <h2 class="my-3 py-3 shadow p-3 mb-5 bg-white rounded text-center">
        <p class="fw-bold">글 수정</p>
      </h2>
    </div>
    <div class="col-lg-8 col-md-10 col-sm-12" id="marticleno">
        <class class="mb-3">
          <label for="subject" class="form-label">제목 : </label>
          <input type="text" class="form-control" id="msubject" name="subject"/>
        </div>
        <div class="mb-3">
          <label for="content" class="form-label">내용 : </label>
          <textarea class="form-control" id="mcontent" name="content" rows="7">
          </textarea>
        </div>
        <div class="col-auto text-center">
          <button type="button" id="btn-modify" class="btn btn-outline-primary mb-3"
          onclick="noticeModify()">
            글수정
          </button>
          <button type="button" id="btn-modify-to-list" class="btn btn-outline-danger mb-3">
            목록으로이동...
          </button>
        </div>
    </div>
  </div>
</div>
  <!-- 수정페이지 end -->
  <script>
    window.onload = function () {
      loadList();
    };
    function loadList() {
      let info = {
        pgno: document.getElementById("pgno").value,
        key: document.getElementById("skey").value,
        word: document.getElementById("sword").value,
      };
      let config = {
        method: "POST",
        cache: "no-cache",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(info),
      };
      fetch("${root}/notice/list", config)
        .then((response) => response.json())
        .then((data) => {
          makePagenation(data.pageNavigation);
          makeList(data.list);
        });
    }

    // 페이지네이션
    function makePagenation(data) {
      let startPage = parseInt((data.currentPage - 1) / data.naviSize) * data.naviSize + 1;
      let endPage = startPage + data.naviSize - 1;
      if (data.totalPageCount < endPage) endPage = data.totalPageCount;
      let body = `
    	    <ul class = "pagination justify-content-center">
            <li class="page-item" data-pg="1">
              <a href="#" class="page-link">최신</a>
            </li>
            <li class="page-item" data-pg= ` + (data.startRange ? 1 : startPage - 1) + `>
              <a href="#" class="page-link">이전</a>
            </li>
            `;

      for (var i = startPage; i <= endPage; i++) {
        body += `<li class= "` + (data.currentPage == i ? "page-item active" :  "page-item" ) + `" data-pg= \${i}>
                <a href="#" class = page-link>\${i}</a>
              </li>`;
      }

      body += `
            <li class="page-item" data-pg= ` + (data.endRange ? endPage : (endPage - 1)) +`>
              <a href="#" class="page-link">다음</a>
            </li>
            <li class="page-item" data-pg=\${data.totalPageCount}>
              <a href="#" class="page-link">마지막</a>
            </li>
          </ul>
    	`;
      document.querySelector("#navigation").innerHTML = body;
      addPageEvent();
    }


    function addPageEvent() {
      let pages = document.querySelectorAll(".page-link");
      pages.forEach(function (page) {
        page.addEventListener("click", function () {
          document.querySelector("#pgno").value =
            page.parentNode.getAttribute("data-pg");
          let info = {
            pgno: document.getElementById("pgno").value,
            key: document.getElementById("skey").value,
        word: document.getElementById("sword").value,
          };
          let config = {
          method: "POST",
          cache: "no-cache",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(info),
        };
          fetch("${root}/notice/list", config)
          .then((response) => response.json())
          .then((data) => {
            makePagenation(data.pageNavigation);
            makeList(data.list);
          });
        });
      });
    }

    // 글 목록 얻기
    function makeList(notices) {
      let tbody = ``;
      notices.forEach(function (notice) {
        tbody += `
    		<tr class="text-center" data-id="\${notice.articleNo}" ondblclick="noticeView(this);">
              <th scope="row">\${notice.articleNo}</th>
    			<td class="text-start">
                <a
                  href="#"
                  class="article-title link-dark"
                  data-no="\${notice.articleNo}"
                  style="text-decoration: none"
                >
                  \${notice.subject}
                </a>
              </td>
              <td>\${notice.userId}</td>
              <td>\${notice.hit}</td>
              <td>\${notice.registerTime}</td>
    		</tr>
    	`;
      });
      document.querySelector("#notice-tbody").innerHTML = tbody;
    }
    
    document.querySelector("#btn-search").addEventListener("click", function () {
      let config = {
        method: "POST",
        cache: "no-cache",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(info),
      };
      fetch("${root}/notice/list", config)
        .then((response) => response.json())
        .then((data) => {
          makePagenation(data.pageNavigation);
          makeList(data.list);
        });
      });

    // 글 쓰기 페이지 보기
    function viewRegister() {
      document
        .querySelector(`#register-page`)
        .setAttribute("style", "display: ;");
      document
        .querySelector(`#list-page`)
        .setAttribute("style", "display: none;");
    }
    // 글 다 썼는지 확인
    document
      .querySelector("#btn-register")
      .addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        } else {
          let registerinfo = {
            userId: "admin",
            subject: document.querySelector("#subject").value,
            content: document.querySelector("#content").value,
          };
          let config = {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify(registerinfo),
          };
          fetch(`${root}/notice/register`, config)
            .then((response) => response.json())
            .then((data) => makeList(data));

          removeRegisterInfo();

          document
            .querySelector(`#register-page`)
            .setAttribute("style", "display: none;");
          document
            .querySelector(`#list-page`)
            .setAttribute("style", "display: ;");
        }
      });

    function removeRegisterInfo() {
      document.querySelector("#subject").value = "";
      document.querySelector("#content").value = "";
    }
    // 등록 안 하고 리스트 버튼 눌렀을 때
    document
      .querySelector("#btn-write-to-list")
      .addEventListener("click", function () {
        if (
          confirm("취소를 하시면 작성한 글은 삭제됩니다.\n취소하시겠습니까?")
        ) {
          document
            .querySelector(`#register-page`)
            .setAttribute("style", "display: none;");
          document
            .querySelector(`#list-page`)
            .setAttribute("style", "display: ;");
        }
      });

    // 상세 보기
    function noticeView(el) {
      document.querySelector(`#view-page`).setAttribute("style", "display: ;");
      document
        .querySelector(`#list-page`)
        .setAttribute("style", "display: none;");

      let id = el.getAttribute("data-id");
      fetch(`${root}/notice/\${id}`)
        .then((response) => response.json())
        .then((data) => {
          document.querySelector("#varticleno").innerText = data.articleNo;
          document.querySelector("#vsubject").innerText = data.subject;
          document.querySelector("#vuserid").innerText = data.userId;
          document.querySelector("#vregistertime").innerText =
            data.registerTime;
          document.querySelector("#vhit").innerText = data.hit;
          document.querySelector("#vcontent").innerText = data.content;
        });
      loadList();
    }

    // 뷰에서 목록으로 이동
    document
      .querySelector("#btn-view-to-list")
      .addEventListener("click", function () {
        document
          .querySelector(`#view-page`)
          .setAttribute("style", "display: none;");
        document
          .querySelector(`#list-page`)
          .setAttribute("style", "display: ;");
      });

    // 공지 삭제.
    function noticeDelete() {
      if (confirm("정말 삭제하시겠습니까?")) {
        let id = document.getElementById("varticleno").innerHTML;
        let config = {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        };
        fetch(`${root}/notice/\${id}`, config)
          .then((response) => response.json())
          .then((data) => makeList(data));
      }
      document
        .querySelector(`#view-page`)
        .setAttribute("style", "display: none;");
      document.querySelector(`#list-page`).setAttribute("style", "display: ;");
    }

     // 정보 수정 보기.
     function viewModify() {
       document
         .querySelector(`#view-page`)
         .setAttribute("style", "display: none;");
       document.querySelector(`#modify-page`).setAttribute("style", "display: ;");
       let subject = document.querySelector(`#vsubject`).innerHTML;
       let content = document.querySelector(`#vcontent`).innerHTML;
       let articleNo = document.querySelector(`#varticleno`).innerHTML;
      document
         .querySelector(`#msubject`).value = subject;
      document
         .querySelector(`#mcontent`).value = content;
      document
         .querySelector(`#marticleno`).value = articleNo;
      }

    document.querySelector("#btn-modify").addEventListener("click", function () {
        if (!document.querySelector("#msubject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#mcontent").value) {
          alert("내용 입력!!");
          return;
        } else {
          
        }
    });
    // 공지 정보 수정.
    function noticeModify() {
      let articleno = document.getElementById("marticleno").value;
      let subject = document.getElementById("msubject").value;
      let content = document.getElementById("mcontent").value;
      let modifyinfo = {
        articleno: articleno,
        subject: subject,
        content: content,
      };
      let config = {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(modifyinfo),
      };
      fetch(`${root}/notice`, config)
        .then((response) => response.json())
        .then((data) => makeList(data));

      document
          .querySelector(`#modify-page`)
          .setAttribute("style", "display: none;");
      document
          .querySelector(`#list-page`)
          .setAttribute("style", "display: ;");
    }
    //  수정에서 글목록으로
    document.querySelector("#btn-modify-to-list").addEventListener("click", function () {
      	if(confirm("취소 하시면 작성중인 글은 취소됩니다.\n취소하시겠습니까?")) {
    		  document
          .querySelector(`#modify-page`)
          .setAttribute("style", "display: none;");
        document
          .querySelector(`#list-page`)
          .setAttribute("style", "display: ;");
        }
      });
    // <c:if test="${userinfo != null && userinfo.isManager != 0}">
    // </c:if>
    /*


      let pageInfo = {
        pgNo: ,
        key: ,
        word: ,
      }

          var pageno;
           let titles = document.querySelectorAll(".article-title");
           titles.forEach(function (title) {
             title.addEventListener("click", function () {
           	  mvView(this.getAttribute("data-no"));
             });
           });

           function mvView(articleno) {
               document.querySelector("#articleno").value = articleno;
               document.querySelector("#pgno").value = pageno;
               document.querySelector("#form-no-param").submit();
            }

           <c:if test="${userinfo != null && userinfo.isManager != 0}">
           document.querySelector("#btn-mv-register").addEventListener("click", function () {
             location.href = "${root}/notice?act=mvwrite&pgno="+pageno+"&key="+"${param.key}"+"&word="+"${param.word}";
           });
           </c:if>

           document.querySelector("#btn-search").addEventListener("click", function () {
         	  let form = document.querySelector("#form-search");
               form.setAttribute("action", "${root}/notice?act=");
               form.submit();
           });


         //pagination
           window.onload = function() {
           	paging(${param.pgno});
           };

           function paging(currentPage) {
           	pageno = currentPage;
           	const dataPerPage = ${dataPerPage};
           	const pageCount = ${pageCount};
           	const totalData = ${totalData};


           	const totalPage = Math.ceil(totalData / dataPerPage);
           	const pageGroup = Math.ceil(currentPage / pageCount);

           	let first = (pageGroup - 1) * pageCount + 1;
           	let last = pageGroup * pageCount;
           	if (last > totalPage) last = totalPage;
           	// let first = last - (pageCount - 1);
           	const next = last + 1;
           	const prev = first - 1;

           	if (totalPage < 1) first = last;

           	let pageContent = ``;
           	if (first > pageCount) {
           		pageContent += `
           			<li class="page-item">
                       	<a class="page-link" onclick="getTarget(\${prev})">이전</a>
                     	</li>
           		`;
           	}
           	for (let idx = first; idx <= last; idx++) {
           		if (currentPage == idx) {
           			pageContent += `
           				<li class="page-item active"><a class="page-link" onclick="getTarget(\${idx})">\${idx}</a></li>
           			`;
           		} else if (idx > 0) {
           			pageContent += `
           				<li class="page-item"><a class="page-link" onclick="getTarget(\${idx})">\${idx}</a></li>
           			`;
           		}
           	}
           	if (next > pageCount && last < totalPage) {
           		pageContent += `
           			<li class="page-item"><a class="page-link" onclick="getTarget(\${next})">다음</a></li>
           		`;
           	}

           	document.querySelector("#notice-pagination").innerHTML = pageContent;
           };


        function getTarget(page) {
        	fetch("${root}/notice?act=listcontent&pgno="+page)
        	.then(response => response.json())
        	.then(data => showList(data.list));
        	paging(page);
        }

        function showList(list) {
        	let bodycontent = ``;
        	list.forEach(function(article) {
        		bodycontent += `
        			<tr class="text-center">
        		      <th scope="row">\${article.articleNo}</th>
        		      <td class="text-start">
        		        <a
        		          href="#"
        		          onclick="mvView(\${article.articleNo})"
        		          class="article-title link-dark"
        		          data-no="\${article.articleNo}"
        		          style="text-decoration: none"
        		        >
        		            \${article.subject}
        		        </a>
        		      </td>
        		      <td>\${article.userId}</td>
        		      <td>\${article.hit}</td>
        		      <td>\${article.registerTime}</td>
        		    </tr>
        		`;
        	})
        	document.querySelector("#notice-tbody").innerHTML = bodycontent;
        } */
  </script>
</section>
<!-- 공지사항 end -->
<%@include file="/WEB-INF/views/common/footer.jsp"%>