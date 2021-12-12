<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <header>
      <h1 class="hidden">PJCCAFE</h1>
      <nav id="gnb">
       	<h2 class="hidden">주요메뉴</h2>
        <ul>
          <form class="" action="" method="">
            <li><a href="#">주문현황</a></li>
          </form>
          <form class="" action="" method="">
            <li><a href="#">주문하기</a></li>
          </form>
          <form class="" action="" method="">
            <li><a href="#">회원목록</a></li>
          </form>
           <li>
            <form name="viewMenuList" action="${pageContext.request.contextPath}/menu/viewMenuList" method="get">
            	<input class="hidden" type="text" value="C" name="choiceMenu" />
            	<input type="submit" value="메뉴목록"/>
            </form>
           </li>
          <form class="" action="" method="">
            <li><a href="#">판매내역</a></li>
          </form>
          <form class="" action="" method="">
            <li><a href="#">메뉴별 판매내역</a></li>
          </form>
        </ul>
      </nav>
    </header>
