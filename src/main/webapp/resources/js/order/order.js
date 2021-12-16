/**
	공통
	카테고리 / 메뉴타입 hover시
 */
$('.menuItemCaltCom , .menuItemTypeCom, .addMenuList').hover(function(){
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
let checktuple = true;
$('.mList').on('click',function(e){
	e.preventDefault();
	
	let id = $(this).find($('.menuId')).val();
	let menuName = $(this).find($('.menuName')).text();
	let menuPrice = $(this).find($('.menuPrice')).text();
	menuPrice = menuPrice.replace(",","");
	menuPrice = menuPrice.replace("원","");
	let quantity=1;
	let totalMenuPrice = Number(menuPrice)*quantity;
	
	//중복메뉴이면 
		if($('.addMenuList').length==0) { checktuple = true; }
		for(let i =0; i<$('.addMenuList').length;i++){

			if($('.addMenuList').eq(i).find($('.mlMenuId')).val()==id ){
			
				checktuple = false;
				let tempNum = Number($('.addMenuList').eq(i).find($('.mlMenuQuantity')).text());
				tempNum++;
				$('.addMenuList').eq(i).find($('.mlMenuQuantity')).text(tempNum);
				totalMenuPrice = tempNum*menuPrice;
				$('.addMenuList').eq(i).find($('.mlTotalPrice')).text(numberWithCommas(totalMenuPrice)+'원');
				break;
			}
			else{
				checktuple = true;
			}
			
		}
	
	//중복이 아니면 
	if(checktuple==true){

		$('.orderListTable').append(
				"<tr class='addMenuList'>"+
							"<input type='hidden' class='mlMenuId' value='"+id+"'/>'"+
							"<td class='mlMenuName'>"+menuName+"</td>"+
							"<td class='mlMenuQuantity'>"+quantity+"</td>"+
							"<td class='mlMenuPrice'>"+numberWithCommas(menuPrice)+"</td>"+
							"<td class='mlTotalPrice'>"+numberWithCommas(totalMenuPrice)+"원</td>"+
						"</tr>"
				
			)
		checktuple = false;
	}
	
	calcTotalNum();	
	calcTotalPrice();
	setTotalPrice();	
	
	//let json={"id":id,"menuName":menuName,"menuPrice":menuPrice,"quantity":quantity,"checkQuantity":'up'}
	//orderMenuListAjax(json);
	
	

})

function orderMenuListAjax(json){
	$.ajax({
		url:"orderMenuList",
		type:"post",
		data: JSON.stringify(json),
		contentType: "application/json; charset=UTF-8",
		success: function(data){
			//for (key in json) { console.log('key:' + key + ' / ' + 'value:' + json[key]); }

			//console.log(data["order"][0])
			//alert('successs');
			$('.addMenuList').remove();
		   for(let i=0;i<data["order"].length;i++){
				let totalMenuPrice = data["order"][i].quantity * data["order"][i].menu.menuPrice;
				$('.orderListTable').append(
					"<tr class='addMenuList'>"+
								"<input type='hidden' class='mlMenuId' value='"+data["order"][i].menu.id+"'/>'"+
								"<td class='mlMenuName'>"+data["order"][i].menu.menuName+"</td>"+
								"<td class='mlMenuQuantity'>"+data["order"][i].quantity+"</td>"+
								"<td class='mlMenuPrice'>"+data["order"][i].menu.menuPrice+"</td>"+
								"<td class='mlTotalPrice'>"+numberWithCommas(totalMenuPrice)+"원</td>"+
							"</tr>"
					
				)
			
			}
			
		}
		})
}
function oneValueAjax(value,goPage){
	$.ajax({
		url:goPage,
		type:"post",
		data:{data:value},
		async:false,
		dataType: 'TEXT',
		success:function(data){
	
		}
		
	})
}






/*숫자에 콤마 넣어 주는 정규식*/
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}


/* 장바구니 목록 누르기*/
$(document).on("click",".addMenuList",function(){
	console.log(this);
	if($(this).hasClass('selectedMenuList')){
		//선택해제하기
		$('.addMenuList').removeClass('selectedMenuList')
		//기존에 선택되어있던거 색 되돌려주기
		$('.addMenuList').even().css({backgroundColor:"#f4f4f4"});
		$('.addMenuList').odd().css({backgroundColor:"#eaeaea"});
		
	}else{
		//기존에 선택되어있던거 색 되돌려주기
		$('.addMenuList').even().css({backgroundColor:"#f4f4f4"});
		$('.addMenuList').odd().css({backgroundColor:"#eaeaea"});
		//선택된거 색바꾸기
		$(this).css({backgroundColor:"#9dc970"});
		//기존 선택된 거 해제하기
		$('.addMenuList').removeClass('selectedMenuList')
		//클래스 추가하기
		$(this).addClass('selectedMenuList');
	}
	
})

/* 수량 업다운 만들기 */
/* 수량 다운 */
let id;
$('.orderListDownBtn').on('click',function(){
//$(document).on("click",".orderListDownBtn",function(){
	//e.preventDefault();
	//선택된 메뉴가 있다면 
	if($('.addMenuList').hasClass('selectedMenuList')){
		/*
		id = $('.selectedMenuList').find($('.mlMenuId')).val();
		let menuName = $('.selectedMenuList').find($('.mlMenuName')).text();
		let menuPrice = $('.selectedMenuList').find($('.mlMenuPrice')).text();
		menuPrice = menuPrice.replace(",","");
		menuPrice = menuPrice.replace("원","");
		let quantity =Number($('.selectedMenuList').find($('.mlMenuQuantity')).text()) ;

		let json={"id":id,"menuName":menuName,"menuPrice":menuPrice,"quantity":quantity,"checkQuantity":'down'}
		
		orderMenuListAjax(json);
		*/
		/*for(let i =0; i<$('.addMenuList').length;i++){

			if($('.addMenuList').eq(i).find($('.mlMenuId')).val()==id){
				console.log($('.addMenuList').eq(i))
				$('.addMenuList').eq(i).addClass('selectedMenuList');
				$('.selectedMenuList').eq(i).css({backgroundColor:"#9dc970"});
				break;
			}
		}
		*/
		let quantity =Number($('.selectedMenuList').find($('.mlMenuQuantity')).text()) ;
		if(quantity>1){
			quantity--;
		}
		
		let menuPrice = $('.selectedMenuList').find($('.mlMenuPrice')).text();
		menuPrice = menuPrice.replace(",","");
		menuPrice = Number(menuPrice);
		totalMenuPrice = quantity*menuPrice;
		
		$('.selectedMenuList').find($('.mlMenuQuantity')).text(quantity);
		$('.selectedMenuList').find($('.mlTotalPrice')).text(numberWithCommas(totalMenuPrice)+'원');
		calcTotalNum();
		calcTotalPrice();
		setTotalPrice();	
	}

})


/* 수량 업 */
$('.orderListUpBtn').on('click',function(e){
	e.preventDefault();
	if($('.addMenuList').hasClass('selectedMenuList')){
	let quantity =Number($('.selectedMenuList').find($('.mlMenuQuantity')).text()) ;
		if(quantity<50){
			quantity++;
		}
		let menuPrice = $('.selectedMenuList').find($('.mlMenuPrice')).text();
		menuPrice = menuPrice.replace(",","");
		menuPrice = Number(menuPrice);
		totalMenuPrice = quantity*menuPrice;
		$('.selectedMenuList').find($('.mlMenuQuantity')).text(quantity);
		$('.selectedMenuList').find($('.mlTotalPrice')).text(numberWithCommas(totalMenuPrice)+'원'); 		
		calcTotalNum();	
		calcTotalPrice();
		setTotalPrice();
	}
	
})

/* 총 수량 계산하기 */
function calcTotalNum(){
	let num=0;
	for(let i =0; i<$('.addMenuList').length;i++){
		num += Number($('.addMenuList').eq(i).find($('.mlMenuQuantity')).text());	
	}
	$('.orderListTotalNum').text('수량:'+num);
	
}
/* 총 가격 계산하기 */
function calcTotalPrice(){
	let price=0;
	for(let j =0; j<$('.addMenuList').length;j++){
		let menuPrice = $('.addMenuList').eq(j).find($('.mlTotalPrice')).text();
		console.log(menuPrice);
		menuPrice = menuPrice.replace(",","");
		menuPrice = menuPrice.replace("원","");
		price += Number(menuPrice);	
	}
	//테이블 안에 가격 총합
	$('.orderListTotalPrice').text(numberWithCommas(price)+'원');
	
	//하단 테이블 안에 가격 총합
	$('.totalPriceBeforePoint').text(numberWithCommas(price)+'원');
}


/* 취소 버튼 누르면 */
$('.orderListCancelBtn').on('click',function(){
	if($('.addMenuList').hasClass('selectedMenuList')){
		$('.selectedMenuList').remove();
		calcTotalNum();
		calcTotalPrice();
		setTotalPrice();
	}
})

/* 전체 취소 누르면 */
$('.funcAllCancelBtn').on('click',function(e){
	e.preventDefault();
	$('.addMenuList').remove();
	calcTotalNum();
	calcTotalPrice();
	$('.usePoint').text('0원');
	setTotalPrice();
	resetUserInfo();
	resetInout(); 
})

function resetUserInfo(){
	$('.orderCustName').text('');
	$('.orderCustPhone').text('');
	$('.orderCustBirth').text('');
	$('.orderCustPoint').text('');
}


/* 포장 누르기 */
$('.orderListInOutBtn').on('click',function(e){
	e.preventDefault();
	if($(this).hasClass('selectedMenuList')){
		$(this).removeClass('selectedMenuList');
	}else{
		$(this).addClass('selectedMenuList');
	}
	
})
function resetInout(){
	if($('.orderListInOutBtn').hasClass('selectedMenuList')){
		$('.orderListInOutBtn').removeClass('selectedMenuList');
	}
}
/* 고객 선택 누르기 */
$('.funcCustomerSelectBtn').on('click',function(e){
	e.preventDefault();
	let popupWidth = 500;
	let popupHeight = 500;
	
	let popupX = (document.body.offsetWidth / 2) - (popupWidth / 2);
	// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
	
	let popupY= (document.body.offsetHeight / 2) - (popupHeight / 2);
	// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음

	window.open("findUserForPointForm", "title", "width=500, height = 500, top="+ popupY + ", left="+ popupX + ""); //선언과 초기화 동시에 해도 됨
	
})


/*

		팝업창 제어

*/

/*	회원검색하기 버튼 누르면	*/


/*	회원가입 버튼 누르면	 */


/*	가입하기 버튼 누르면 */


/*	취소 버튼 누르면 */
$('.cancelBtn').on('click',function(e){
	e.preventDefault();
	close();
})




/*	고객선택하면*/
$('.userList').on('click',function(){
//$(document).on("click",".userList",function(){
	if($('.userList').find($('.custName'))!=' '){
		$('.userList').not($(this)).removeClass('selectedCustomer');
		$(this).addClass('selectedCustomer');
	}
	
	
	
})

/* 고객선택하고 확인 누르면 */

$('#popupSubmitBtn').on('click',function(){
	//for(let i=0; i<$('.userList').length;i++){
		if($('.userList').hasClass('selectedCustomer')){
			
		let custId = $('.selectedCustomer').find($('.custId')).val();
		let custName = $('.selectedCustomer').find($('.custName')).text();
		let custPhone = $('.selectedCustomer').find($('.custPhone')).text();
		let custBirth = $('.selectedCustomer').find($('.custBirth')).text();
		let custPoint = $('.selectedCustomer').find($('.custPoint')).text();
	
		//부모화면에 뿌려주기
		/*
		$('.orderCustName').text(custName);
		$('.orderCustPhone').text(custPhone);
		$('.orderCustBirth').text(custBirth);
		$('.orderCustPoint').text(custPoint);
		*/
		//console.log('thisi')	
		//let json={"custName":custName,"custPhone":custPhone,"custBirth":custBirth,"custPoint":custPoint}
		
		opener.parent.setCustomerInfo(custId,custName,custPhone,custBirth,custPoint);
		opener.parent.sendUserInfo(custId,custName,custPhone,custBirth,custPoint);
		/*
		$.ajax({
		url:"findUserResultForPoint",
		type:"post",
		data: JSON.stringify(json),
		contentType: "application/json; charset=UTF-8"	,
		//async:false	,
		success:function(){
			console.log('success')
			console.log($(opener.document))
			$(opener.parent).find($('#orderCustName')).text('dd');
			
			},
		error:function(){
			
			console.log($(opener.document))
		}
		})
		*/
		//break;
	}
	//}
	close();
})

/* 유저 정보 셋팅 해주기 */
function setCustomerInfo(custId, custName,custPhone,custBirth,custPoint){
	$('.orderCustId').val(custId);
	$('.orderCustName').text(custName);
	$('.orderCustPhone').text(custPhone);
	$('.orderCustBirth').text(custBirth);
	$('.orderCustPoint').text(custPoint);
	
}
function sendUserInfo(custId, custName,custPhone,custBirth,custPoint){
		let point = custPoint;
		point = point.replace(",","");
		let json={"userId":custId, "userName":custName,"userPhone":custPhone,"userBirth":custBirth,"userPoint":point}
		
		$.ajax({
		url:"saveUserPoint",
		type:"post",
		data: JSON.stringify(json),
		contentType: "application/json; charset=UTF-8"	,
		//async:false	,
		success:function(){
			
			}
		})
}


/*	검색시 값이 없을 때 유효성 처리하고 전송 */
function searchSubmit(){
	if($('.popUpInputPhoneArea').val()!=''){
		$('#searchUser').attr('action','findUserForPoint');
		$('#searchUser').attr('method','post');
		$('#searchUser').submit();
	}
	else{
		alert('검색할 값이 없습니다');
		return false;
	}
	
}
/*	회원가입이 값이 없을 때 유효성 처리하고 전송 */
function joinSubmit(){
	console.log('joinmethod');
	let userName = $('.joinNameArea').val();
	let userPhone = $('.joinPhoneArea').val();
	let userBirth = $('.joinBirthArea').val();
	if(userName!='' && userPhone!='' && userBirth!=''){
		//ajax호출로 신규회원 가입해주기 
		let json={"userName":userName,"userPhone":userPhone,"userBirth":userBirth}
		$.ajax({
		url:"joinUserResultForPoint",
		type:"post",
		data: JSON.stringify(json),
		contentType: "application/json; charset=UTF-8"	,
		//async:false	,
		success:function(data){
			
			}
		})

		//부모화면에 뿌려주기
		//id값 없어서 안됨
		close();
	}
	else{
		alert('값을 입력 해주세요');
		return false;
	}
}

//포인트 사용 팝업창 열기
$('.orderListPointBtn').on('click',function(){
 
	//고객명에 이름이 있으면 팝업창 띄우기
	if($('.orderCustName').text()!=''){
		let popupWidth = 500;
	let popupHeight = 500;
	
	let popupX = (document.body.offsetWidth / 2) - (popupWidth / 2);
	// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
	
	let popupY= (document.body.offsetHeight / 2) - (popupHeight / 2);
	// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
	
	
	window.open("usePointPopup", "title", "width=500, height = 500, top="+ popupY + ", left="+ popupX + ""); //선언과 초기화 동시에 해도 됨
	}
	
})


$('.usePointBtn').on('click',function(e){
	e.preventDefault();
	//opener.parent.usePoint();
	console.log('hi');
	let canUserPoint = $('.canUsePoint').text();
	canUserPoint = canUserPoint.replace(",","");
	canUserPoint = Number(canUserPoint);
	let usePoint = Number($('.popUpInputUserPointArea').val());
	if(canUserPoint>=usePoint && canUserPoint>=5000 && usePoint>=1000 && (usePoint%100==0)){
		console.log('in?');
		opener.parent.usePoint(usePoint);
	}
	else{
		alert('5,000포인트 이상 보유시, 1,000포인트 이상 100포인트 단위로 사용가능')
	}
	
	close();
})

function usePoint(point){
	let beforePoint = $('.totalPriceBeforePoint').text();
	beforePoint = beforePoint.replace(",","");
	beforePoint = beforePoint.replace("원","");
	beforePoint = Number(beforePoint);
	
	if(beforePoint>0){
		$('.usePoint').text(numberWithCommas(point)+'원');
	}
	else{
		$('.usePoint').text('0원');
	}
	
	//$('.usePoint').text(1000);
	setTotalPrice();
	
}

function setTotalPrice(){
	let beforePoint = $('.totalPriceBeforePoint').text();
	let userPoint = $('.usePoint').text();
	
	beforePoint = beforePoint.replace(",","");
	beforePoint = beforePoint.replace("원","");
	beforePoint = Number(beforePoint);
	
	userPoint = userPoint.replace(",","");
	userPoint = userPoint.replace("원","");
	userPonit = Number(userPoint);
	
	let totalPrice = beforePoint - userPoint;
	if(totalPrice>=0){
		totalPrice = numberWithCommas(totalPrice)+'원';
	}else{
		totalPrice = '0원';
	}

	$('.totalPrice').text(totalPrice);
	
	
			
}

$('.totalMenuItem').on('click',function(){
	resetMenuList();
	$('.mFood').show();
	$('.mBeverage').show();
	$('.mCoffee').show();
})

/*

	주문하기
	
*/

$('.funcOrderBtn').on('click',function(e){
	e.preventDefault();
	//고객 아이디
	let id =$('.orderCustId').val();		
	let customerName = $('.orderCustName').text();
	let phone = $('.orderCustPhone').text();
	let birth = $('.orderCustBirth').text();
	let point = $('.orderCustPoint').text();
	point = point.replace(",","");
	point = point.replace("원","");
	point = Number(point);
	
	if(customerName==''){
		id =1;
		customerName=' ';
		phone = ' ';
		birth = ' ';
		
	}
	
	
	//사용할 포인트
	let usePoint = $('.usePoint').text(); 
	usePoint = usePoint.replace(",","");
	usePoint = usePoint.replace("원","");
	usePoint = Number(usePoint);
	
	let place = "I";
	//매장이냐 포장이냐
	if($('.orderListInOutBtn').hasClass('selectedMenuList')){
		place = "O";
	}
	
	
	//메뉴아이디, 메뉴수량
	let json1={
		"usePoint":usePoint,
		"customer.id":id,
		"customer.customerName":customerName,
		"customer.phone":phone,
		"customer.birth":birth,
		"customer.point":point,
		"place":place,

	}
	console.log('before for');
	let json2;
	for(let i=0;i<$('.addMenuList').length;i++){
		let str1 = "nowOrder["+i+"].quantity";
		let str2 = Number($('.addMenuList').eq(i).find($('.mlMenuQuantity')).text());
		
		let str3 = "nowOrder["+i+"].menuId";
		let str4 = Number($('.addMenuList').eq(i).find($('.mlMenuId')).val());
		console.log('menuid'+$('.mlMenuId').val());
		json2={[str1]:str2,[str3]:str4}
		Object.assign(json1, json2);
		
	}


	console.log(json1);
	
	$.ajax({
		url:"saveOrder",
		type:"post",
		data: json1,
		//data: JSON.stringify(json),
		//data: $('#json').serialize(),
		dataType: 'json',
		//contentType: "application/json; charset=UTF-8"	,
		success:function(data){
			console.log('hi')
			callCompOrder()
			}
		})
	

})

//이건 야매야...

function callCompOrder(){
	$('#goCompOrder').submit();
}









