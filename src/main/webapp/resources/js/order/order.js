/**
	공통
	카테고리 / 메뉴타입 hover시
 */
$('.menuItemCaltCom , .menuItemTypeCom').hover(function(){
	$(this).addClass('menuItemHover');
},function(){
	$(this).removeClass('menuItemHover');
})
/**
	공통
	서브메뉴타입 hover시
 */
$('.menuItemSubTypeCom').hover(function(){
	/* 메뉴타입이 선택 되어있고, 서브메뉴타입의 내용이 공백이 아닌경우면 */
	if($('.menuItemTypeCom').hasClass('typeSelected') && ($(this).text()!='')){
			$(this).addClass('menuItemHover');
	}
},function(){
	$(this).removeClass('menuItemHover');
})
/**
	공통
	기능버튼 hover시
 */
$('.funcButtonCom').hover(function(){
	if($(this).text()!=''){
		$(this).addClass('funcButtonHover');
	}
},function(){
	$(this).removeClass('funcButtonHover');
})





/**
 * 공통 카테고리 버튼 클릭시 
 */
$('.menuItemCaltCom').on('click',function(e){
	e.preventDefault();
	/* 선택시 배경색 변경*/
	$(this).addClass('calSelected');
	/* 선택 안된 카테고리는 디폴트색으로 변경*/
	$('.menuItemCaltCom').not($(this)).removeClass('calSelected');
	/* 메뉴타입 선택 풀어주기*/
	$('.menuItemTypeCom').removeClass('typeSelected');
	/* 서브메뉴 선택 풀어주기*/
	$('.menuItemSubTypeCom').removeClass('subTypeSelected');
})

/**
	메뉴 타입 버튼 클릭시(커피 / 음료 / 푸드)
 */
$('.menuItemTypeCom').on('click',function(e){
	e.preventDefault();
	/* 선택시 배경색 변경*/
	$(this).addClass('typeSelected');
	/* 선택 안된 타입은 디폴트색으로 변경*/
	$('.menuItemTypeCom').not($(this)).removeClass('typeSelected');
	/* 카테고리 선택 풀어주기*/
	$('.menuItemCaltCom').removeClass('calSelected');
	/* 서브메뉴 선택 풀어주기*/
	$('.menuItemSubTypeCom').removeClass('subTypeSelected');

})

/**
	메뉴 서브 버튼 클릭시(뜨거운음료 / 차가운음료)
 */
$('.menuItemSubTypeCom').on('click',function(e){
	e.preventDefault();
	/* 메뉴 타입이 선택되어 있을 때만 변경가능 */
	if($('.menuItemTypeCom').hasClass('typeSelected') && ($(this).text()!='')){
		/* 선택시 배경색 변경*/
		$(this).addClass('subTypeSelected');
		/* 선택 안된 서브타입은 디폴트색으로 변경*/
		$('.menuItemSubTypeCom').not($(this)).removeClass('subTypeSelected');
		/* 카테고리 선택 풀어주기*/
		$('.menuItemCaltCom').removeClass('calSelected');
	}
	
})




















