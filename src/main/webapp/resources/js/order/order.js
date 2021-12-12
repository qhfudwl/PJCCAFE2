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




/* 모든 메뉴 목록 화면에서 없애기 */
function resetMenuList(){
	$('.mCoffeeHot').hide();
	$('.mCoffeeIce').hide();
	$('.mCoffee').hide();
	
	$('.mBeverageHot').hide();
	$('.mBeverageIce').hide();
	$('.mBeverage').hide();
	
	$('.mFood').hide();	
}


/* 커피 누를 때  - 모든 커피 메뉴 보이게 하기 */

$('.mItemCoffee').on('click',function(e){
	e.preventDefault();
	/* 다른 메뉴 목록 지우기 */
	resetMenuList();
	/* 서브 메뉴 지우기 */
	$('.mCBSubType').show();
	$('.mFSubType').hide();
	/* 커피메뉴 보여주기 */
	$('.mCoffee').show();
	
})
/* 음료 누를 때  - 모든 음료 메뉴 보이게 하기 */
$('.mItemBeverage').on('click',function(e){
	e.preventDefault();
	/* 다른 메뉴 목록 지우기 */
	resetMenuList();
	/* 서브 메뉴 지우기 */
	$('.mCBSubType').show();
	$('.mFSubType').hide();
	/* 커피메뉴 보여주기 */
	$('.mBeverage').show();
	
})
/* 푸드 누를 때  - 모든 푸드 메뉴 보이게 하기 */
$('.mItemFood').on('click',function(e){
	e.preventDefault();
	/* 다른 메뉴 목록 지우기 */
	resetMenuList();
	/* 서브 메뉴 지우기 */
	$('.mCBSubType').hide()
	$('.mFSubType').show();
	/* 커피메뉴 보여주기 */
	$('.mFood').show();
	
})

/* 핫 눌렀을 때 */
$('.mItemSubTypeHot').on('click',function(e){
	e.preventDefault();
	/* 다른 메뉴 목록 지우기 */
	resetMenuList();
	/* 커피메뉴면 */
	if($('.mItemCoffee').hasClass('typeSelected')){
		$('.mCoffeeHot').show();
	}
	/* 음료메뉴*/
	else if($('.mItemBeverage').hasClass('typeSelected')){
		$('.mBeverageHot').show();
	}	
})
/* 아이스 눌렀을 때 */
$('.mItemSubTypeIce').on('click',function(e){
	e.preventDefault();
	/* 다른 메뉴 목록 지우기 */
	resetMenuList();
	/* 커피메뉴면 */
	if($('.mItemCoffee').hasClass('typeSelected')){
		$('.mCoffeeIce').show();
	}
	/* 음료메뉴*/
	else if($('.mItemBeverage').hasClass('typeSelected')){
		$('.mBeverageIce').show();
	}	
})

/* 메뉴아이템 선택 시 좌측 창에 메뉴 추가 */

$('.mList').on('click',function(e){
	e.preventDefault();
	
	let id = $(this).find($('.menuId')).val();
	let menuName = $(this).find($('.menuName')).text();
	let menuPrice = $(this).find($('.menuPrice')).text();
	menuPrice = menuPrice.replace(",","");
	menuPrice = menuPrice.replace("원","");
	console.log(menuPrice);
	let quantity =1;
	
	let json={"id":id,"menuName":menuName,"menuPrice":menuPrice,"quantity":quantity}
	
	$.ajax({
		url:"orderMenuList",
		type:"post",
		data: JSON.stringify(json),
		contentType: "application/json; charset=UTF-8",
		success: function(data){
			//for (key in json) { console.log('key:' + key + ' / ' + 'value:' + json[key]); }

			console.log(data)
			//console.log(data["order"][0])
			//alert('successs');
			$('.addMenuList').remove();
		   for(let i=0;i<data["order"].length;i++){
				let totalMenuPrice = data["order"][i].quantity * data["order"][i].menu.menuPrice;
				$('.orderListTable').append(
					"<tr class='addMenuList'>"+
								"<td>"+data["order"][i].menu.menuName+"</td>"+
								"<td>"+data["order"][i].quantity+"</td>"+
								"<td>"+data["order"][i].menu.menuPrice+"</td>"+
								"<td>"+numberWithCommas(totalMenuPrice)+"원</td>"+
							"</tr>"
					
				)
			
			}
			
		}
		})
	

})

/*숫자에 콤마 넣어 주는 정규*/
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}











