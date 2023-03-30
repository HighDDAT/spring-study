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
	        <input class="form-control input-sm" id="newReplyText" type="text" placeholder="댓글 입력...">
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

<!-- replyTemplate -->
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
            <span class="description"></span>
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
    

});
</script>
</body>
</html>
