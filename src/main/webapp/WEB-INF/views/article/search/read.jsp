<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<%@ include file = "../../include/head.jsp" %>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

  <!-- Navbar -->
  <%@ include file = "../../include/main_header.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <%@ include file = "../../include/left_column.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">Paging Search Reply Board</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Starter Page</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container-fluid">
      <div class="col-lg-12">
    <div class="card">
        <div class="card-header">
            <h3 class="card-title">글제목 : ${article.title}</h3>
        </div>
        <div class="card-body" style="height: 700px">
            ${article.content}
        </div>
        <div class="card-footer">
            <div class="user-block">
                <img class="img-circle img-bordered-sm" src="${cPath}/dist/img/user1-128x128.jpg" alt="user image">
                <span class="username">
                    <a href="#">${article.writer}</a>
                </span>
                <span class="description"><fmt:formatDate pattern="yyyy-MM-dd" value="${article.regDate}"/></span>
            </div>
        </div>
        <div class="card-footer">
		    <form role="form" method="post">
		        <input type="hidden" name="article_no" value="${article.article_no}">
		        <input type="hidden" name="page" value="${searchSection.page}">
		        <input type="hidden" name="perPageNum" value="${searchSection.perPageNum}">
		        <input type="hidden" name="searchType" value="${searchSection.searchType}">
		        <input type="hidden" name="keyword" value="${searchSection.keyword}">
		    </form>
		    <button type="submit" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
		    <div class="float-right">
		        <button type="submit" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정</button>
		        <button type="submit" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제</button>
		    </div>
			</div>
	    </div>
	</div>
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->
    
    <!-- reply insert -->
    <div class="card-body">
	  <form class="form-horizontal">
	    <div class="row">
	      <div class="form-group col-sm-8">
	        <input class="form-control input-sm" id="newReplyText" type="text" placeholder="내용을 입력하세요">
	      </div>
	      <div class="form-group col-sm-2">
	        <input class="form-control input-sm" id="newReplyWriter" type="text" placeholder="작성자">
	      </div>
	      <div class="form-group col-sm-2">
	        <button type="button" class="btn btn-primary btn-sm btn-block replyAddBtn">
	        <i class="fa fa-save"></i> 저장
	      </button>
	      </div>
	    </div>	
	  </form>	
	</div>
	<!-- /.reply insert -->
	
	<!-- reply list & paging -->
	<div class="card card-primary card-outline">
	<!-- 리플 개수, 접기 펼치기 -->
	  <div class="card-header">
	  <a href="" class="link-black text-lg"><i class="fas fa-comments margin-r-5 replyCount"></i></a>
	    <div class="card-tools">
	      <button type="button" class="btn primary"  data-widget="collapse">
	          <i class="fa fa-plus"></i>
	      </button>
	    </div>
	  </div>
	  
	  <!-- 리플 리스트 -->
	  <div class="card-body repliesDiv">
	  </div>
	  
	  <!-- 리플 페이징 -->
	  <div class="card-footer">
	    <nav aria-label="Contacts Page Navigation">
	    <ul class="pagination pagination-sm no-margin justify-content-center m-0">
	    </ul>
	    </nav>
	  </div>
	</div>
    <!-- /.reply list & paging -->
    
		<!--리플 수정 modal 영역-->
		<div class="modal fade" id="modModal">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title">수정</h4>
		            </div>
		            <div class="modal-body" data-rno>
		                <input type="hidden" class="replyNo"/>
		                <%--<input type="text" id="replytext" class="form-control"/>--%>
		                <textarea class="form-control" id="replyText" rows="3" style="resize: none"></textarea>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">닫기</button>
		                <button type="button" class="btn btn-primary modalModBtn">수정</button>
		            </div>
		        </div>
		    </div>
		</div>

		<!--리플 삭제 modal 영역-->
		<div class="modal fade" id="delModal">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                    <span aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title">삭제</h4>
		                <input type="hidden" class="rno"/>
		            </div>
		            <div class="modal-body" data-rno>
		                <p>삭제하시겠습니까?</p>
		                <input type="hidden" class="rno"/>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">아니요.</button>
		                <button type="button" class="btn btn-primary modalDelBtn">네. 삭제합니다.</button>
		            </div>
		        </div>
		    </div>
		</div>
    
  </div>
  <!-- /.content-wrapper -->

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
    <div class="p-3">
      <h5>Title</h5>
      <p>Sidebar content</p>
    </div>
  </aside>
  <!-- /.control-sidebar -->

  <!-- Main Footer -->
  <%@ include file = "../../include/main_footer.jsp" %>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->
<%@ include file = "../../include/plugin_js.jsp" %>
<script id="replyTemplate" type="text/x-handlebars-template">
    {{#each.}}
    <div class="post replyDiv" data-reply_no={{reply_no}}>
        <div class="user-block">
            <img class="img-circle img-bordered-sm" src="${cPath}/dist/img/user1-128x128.jpg" alt="user image">
            <span class="username">
                <a href="#">{{reply_writer}}</a>
                <a href="#" class="float-right btn-box-tool replyDelBtn" data-toggle="modal" data-target="#delModal">
                    <i class="fa fa-times"> 삭제</i>
                </a>
                <a href="#" class="float-right btn-box-tool replyModBtn" data-toggle="modal" data-target="#modModal">
                    <i class="fa fa-edit"> 수정</i>
                </a>
            </span>
            <span class="description">{{prettifyDate regDate}}</span>
        </div>
        <div class="oldReplyText">{{reply_text}}</div>
        <br/>
    </div>
    {{/each}}
</script>

<script>
$(document).ready(function () {

    var formObj = $("form[role='form']");
    console.log(formObj);

    $(".modBtn").on("click", function () {
    	console.log('수정 버튼 이벤트')
    	
        formObj.attr("action", "${cPath}/article/paging/search/modify");
        formObj.attr("method", "get");
        formObj.submit();
    });

    $(".delBtn").on("click", function () {
       console.log('삭제 버튼 이벤트')
    	
       formObj.attr("action", "${cPath}/article/paging/search/remove");
       formObj.submit();
    });

    $(".listBtn").on("click", function () {
       console.log('목록 버튼 이벤트')    	
    	
       formObj.attr("action", "${cPath}/article/paging/search/list");
       formObj.attr("method", "get");
       formObj.submit();
    });
    
    // 리플 관련 함수 작성
    var article_no = "${article.article_no}"; // 현재 아티클 번호
    var replyPageNum = 1; // 리플 페이지 번호 초기화
    
    // reply text : 줄바꿈 및 공백처리
    Handlebars.registerHelper("escape", function(reply_text) {
    	var text = Handlebars.Utils.escapeExpression(reply_text);
    	
    	text = text.replace(/(\r\n|\n|\r)/gm, "<br/>");
        text = text.replace(/( )/gm, "&nbsp;");
    	
    	return new Handlebars.SafeString(text);
		
	}) // 줄바꿈 공백처리 end
	
	// reply insert date : 날짜 및 시간 2자리로 변형
	Handlebars.registerHelper("prettifyDate", function(timeValue) {
		
		var dateObj = new Date(timeValue);
		
		var year = dateObj.getFullYear(); // 년
		var month = dateObj.getMonth(); // 월
		var date = dateObj.getDate(); // 일
		
		var hours = dateObj.getHours(); // 시
		var minutes = dateObj.getMinutes(); // 분
		
		// 2자리로 변환
		month < 10 ? month = '0' + month : month;
		date < 10 ? date = '0' + date : date;
		hours < 10 ? hours = '0' + hours : hours;
		minutes < 10 ? minutes = '0' + minutes : minutes;
		
		return year + "-" + month + "-" + date;
		
	}) // reply date end
	
	// reply list get
	getReplies("${cPath}/replies/" + article_no + "/" + replyPageNum);
	
	// reply list get function
	function getReplies(repliesUri) {
		$.getJSON(repliesUri, function(data) {
			printReplyCount(data.pageMaker.totalCount);
			printReplies(data.replies, $(".repliesDiv"), $("#replyTemplate"));
			printReplyPaging(data.pageMaker, $(".pagination"));
		})
	} // reply list get end

	// reply count
	function printReplyCount(totalCount) {
		var replyCount = $(".replyCount");
		var collapsedBox = $(".collapsed-box");
		
		// reply 유무에 따라
		// 없을때
		if(totalCount === 0) {
			replyCount.html("리플을 남겨보세요.");
			collapsedBox.find(".btn-box-tool").remove();
			return;
		}
		
		// 있을때
		replyCount.html("list (" + totalCount + ")");
		collapsedBox.find(".box-tools").html(
		"<button type='button' class='btn btn-box-tool' data-widget='collapse'>"
		+ "<i class='fa fa-plus'></i>"
		+"</button>"
		);
	} // reply 유무 메시지 end
	
	// reply list print
	function printReplies(replyArr, targetArea, templateObj) {
		var replyTemplate = Handlebars.compile(templateObj.html());
		var html = replyTemplate(replyArr);
		
		$(".replyDiv").remove();
		targetArea.html(html);
		
	} // reply list print end
	
	// reply paging print
	function printReplyPaging(pageMaker,targetArea) {
		var str = "";
		
		// 이전 버튼 활성화
		if(pageMaker.prev) {
			str += "<li class=\"page-item\"><a class=\"page-link\" href='"+(pageMaker.startPage-1)+"'>이전</a></li>";
			
		}
		
		// page number
		for (var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
	        var strCalss = pageMaker.section.page == i ? 'class=active' : '';
	        str += "<li class=\"page-item\" "+strCalss+"><a class=\"page-link\" href='"+i+"'>"+i+"</a></li>";
	    }
		
		// 다음 버튼 활성화
		if (pageMaker.next) {
	        str += "<li class=\"page-item\"><a class=\"page-link\" href='"+(pageMaker.endPage + 1)+"'>다음</a></li>";
	    }
        targetArea.html(str);
	} // reply paging end
	
	// relpy number click event
    $(".pagination").on("click", "li a", function (event) {
        event.preventDefault();
        replyPageNum = $(this).attr("href");
        getReplies("${cPath}/replies/" + article_no + "/" + replyPageNum);
    });
	
	// reply add click event
	$(".replyAddBtn").on("click", function() {
		console.log('저장 버튼 이벤트')
		
		// 입력 form 선택자
		var reply_writerObj = $("#newReplyWriter");
		var reply_textObj = $("#newReplyText");
		var reply_writer = reply_writerObj.val();
		var reply_text = reply_textObj.val();
		
		// 입력처리 수행
		$.ajax({
			type : "post",
			url : "${cPath}/replies/",
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : "text",
			data : JSON.stringify({
				article_no : article_no,
				reply_writer : reply_writer,
				reply_text : reply_text
			}),
			success : function(result) {
				console.log("실행 결과 : " + result);
				if(result === "regSuccess") {
					alert("리플 작성 성공");
					replyPageNum = 1; // 페이지 1로 초기화
					getReplies("${cPath}/replies/" + article_no + "/" + replyPageNum); // 리플 리스트 호출
					reply_textObj.val("");   // text 인풋 공백처리
                    reply_writerObj.val("");   // writer 인풋 공백처리
				}
			}
		})
	}) // 입력처리 수행 end
	
	// 리플 수정을 위해 modal로 값 넘겨주기
	$(".repliesDiv").on("click", ".replyDiv", function (event) {
        var reply = $(this);
        $(".reply_no").val(reply.attr("data-reply_no"));
        $("#reply_text").val(reply.find(".oldReplyText").text());
    });
	
	// modal modify button event
	$(".modalModBtn").on("click", function () {
        var reply_no = $(".reply_no").val();
        var reply_text = $("#reply_text").val();
        $.ajax({
            type : "put",
            url : "${cPath}/replies/" + reply_no,
            headers : {
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "PUT"
            },
            dataType : "text",
            data: JSON.stringify({
            	reply_text : reply_text
            }),
            success: function (result) {
                console.log("result : " + result);
                if (result === "modSuccess") {
                    alert("리플 수정 완료");
                    getReplies("${cPath}/replies/" + article_no + "/" + replyPageNum); // 댓글 목록 호출
                    $("#modModal").modal("hide"); // modal 창 닫기
                }
            }
        })
    }); // modal modify button event end
	
	// modal delete button event
	$(".modalDelBtn").on("click", function() {
		console.log('삭제 버튼 클릭 이벤트');
		
		var reply_no = $(".reply_no").val();
		
		$.ajax({
			type : "delete",
			url : "${cPath}/replies/" + reply_no,
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "DELETE"
			},
			dataType : "text",
			success : function(result) {
				console.log('실행 결과 : ' + result);
				if(result === "delSuccess") {
					alert("리플 삭제 완료");
					getReplies("${cPath}/replies/" + article_no + "/" + replyPageNum); // 리플 리스트 호출
                    $("#delModal").modal("hide"); // modal 창 닫기
				}
			}
		})
	}) // modal delete button event end
	
	
});
</script>
</body>
</html>
