<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="index3.html" class="brand-link">
      <img src="${cPath}/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
           style="opacity: .8">
      <span class="brand-text font-weight-light">Spring mvcBoard</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
    
    
      <!-- Sidebar user panel (optional) -->
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <c:if test="${empty login}">
	        <div class="image">
	          <img src="${cPath}/dist/img/default-user-image.jpg" class="img-circle elevation-2" alt="User Image">
	        </div>
	        <div class="info">
                <%-- Status --%>
            	<a href="#"><i class="fa fa-circle text-danger"></i> Guest</a>
	        </div>
        </c:if>
        
        <c:if test="${not empty login}">
	        <div class="image">
	          <img src="${cPath}/dist/img/default-user-image.jpg" class="img-circle elevation-2" alt="User Image">
	        </div>
	        <div class="info">
                <%-- Status --%>
            	<a href="#"><i class="d-block"></i> ${login.userName}</a>
	        </div>
        </c:if>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
          <li class="nav-item has-treeview menu-open">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Basic CRUD Board
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${cPath}/article/write" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Write</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="${cPath}/article/list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>List Basic Page</p>
                </a>
              </li>
            </ul>
          </li>
 			
 			<li class="nav-item has-treeview menu-open">
            <a href="#" class="nav-link">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                Paging CRUD Board
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="${cPath}/article/paging/write" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Write</p>
                </a>
                </ul>
              </li>
              	 
            <!-- List Paging 목록 버튼 추가 -->
              <li class="nav-item">
                <a href="${cPath}/article/paging/list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>List Paging Page</p>
                </a>
              </li>
          
		          <!-- 검색기능 추가 -->
			          <li class="nav-item has-treeview menu-open">
			            <a href="#" class="nav-link">
			              <i class="nav-icon fas fa-tachometer-alt"></i>
			              <p>
			                 Paging Search Board
			                <i class="right fas fa-angle-left"></i>
			              </p>
			            </a>
					   <ul class="nav nav-treeview">
					    <li class="nav-item">
					      <a href="${cPath}/article/paging/search/write" class="nav-link">
					        <i class="far fa-circle nav-icon"></i>
					        <p>Write</p>
					      </a>
					  	</li>
					    <!-- List Paging 목록 버튼 추가 -->
					    <li class="nav-item">
					      <a href="${cPath}/article/paging/search/list" class="nav-link">
					        <i class="far fa-circle nav-icon"></i>
					        <p>List Paging Search Page</p>
					      </a>
					    </li>
					  </ul>
					</li>
       		 </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>