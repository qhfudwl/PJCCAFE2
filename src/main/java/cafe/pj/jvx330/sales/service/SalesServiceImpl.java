package cafe.pj.jvx330.sales.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cafe.pj.jvx330.config.DataSourceConfig;
import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.sales.dao.SalesDao;
import cafe.pj.jvx330.sales.util.SalesStorage;
import cafe.pj.jvx330.user.service.UserService;
import cafe.pj.jvx330.web.util.OrderStorage;
import cafe.pj.jvx330.web.util.SetterDate;

@Component("salesService")
public class SalesServiceImpl implements SalesService{
	
	@Autowired
	private SalesDao sd;
	
	@Resource(name="userService")
	private UserService us;
	
	@Autowired
	private MenuService ms;
	
	@Resource(name="setterDate")
	private SetterDate sed;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public SalesServiceImpl(SalesDao salesDao) {
		this.sd = salesDao;
	}

	@Transactional
	@Override
	public void addSales(Sales sales) {
		sd.addSales(sales);
		us.updatePointById(sales.getUser());
	}

	@Override
	public List<Sales> findSalesByDate(Date date1, Date date2) {
		return sd.findSalesByDate(date1, date2);
	}
	
	@Override
	public List<Sales> findSalesByDate(Date date) {
		return sd.findSalesByDate(date);
	}

	@Override
	public List<Sales> findSalesByMenuNameAndDate(String menuName, Date date1, Date date2) {		
		return sd.findSalesByMenuNameAndDate(menuName, date1, date2);
	}
	
	@Override
	public List<Sales> findSalesByDate(char type, Date date1, Date date2) {
		return sd.findSalesByDate(date1, date2);
	}

	@Override
	public Sales findSalesByOrderNumber(String orderNumber) {
		return sd.findSalesByOrderNumber(orderNumber);
	}
	
	@Transactional
	@Override
	public void removeSales(String orderNumber) {
		sd.removeSales(orderNumber);
	}
	
	public List<OrderStorage> findOrderRecordForMenu(char dateType, char menuType){
		Menu menu = null;
		List<Product> order = new ArrayList<Product>();
		Calendar now = Calendar.getInstance(); // 오늘 날짜 
		Calendar cal = Calendar.getInstance(); // 다음 범위 지정을 위해 범위 시작 날짜를 기억하는 용도
		List<OrderStorage> osList = new ArrayList<OrderStorage>();
		
		int nof = 0;
		if(dateType == 'M') {// 기간 Type 별로 반복 횟수 지정
			//월별 일 경우
			nof = now.get(Calendar.MONTH)+1;
			//Month는 0부터 나오기 때문에 +1 해준다
		} else if(dateType == 'D') { //구하는 기간이 하루 하루 하루
			if(now.get(Calendar.DAY_OF_YEAR) <= 30) {
				nof = now.get(Calendar.DAY_OF_YEAR); //나오는 값이 31개보다 적거나 같을 때
			} else { // 1월 31보다 클 경우
				nof = 30;
			}

		} else { // dateType == 'W'
			if(now.get(Calendar.DAY_OF_YEAR) > 77) { // 11주보다 큰 값일 경우.
				nof = 12;
			} else if (now.get(Calendar.DAY_OF_YEAR) / 7 == 0){ // 70 처럼 나누어 떨어질 경우
				nof = now.get(Calendar.DAY_OF_YEAR) / 7; // 나눈 수 만큼 반복
			} else {
				nof = now.get(Calendar.DAY_OF_YEAR) / 7 + 1; //나눈 나머지 수를 위해 한번 더 반복
			}
		}
		
		int iNum = 0;
		
		for(int i=0; i<nof; i++) {
			List<String> sDateList = sed.chooseDate(cal, i, dateType); // 범위 시작날과 끝날을 List<String>에 0, 1 인덱스에 각각 넣기
			//chooseDate 값을 sDateList리스트에 저장
			order.addAll(sd.findOrderRecordForMenu(sDateList.get(0), sDateList.get(1))); // order List에 전부 추가
			
			for(int j = iNum; j<order.size();j++) { // osList 세팅
				OrderStorage os = new OrderStorage();
				menu = ms.findMenuById(order.get(j).getMenu().getId());
				if(menuType == 'T' || menuType == menu.getMenuType()) { 
					// menuType 에 따라 세팅.
					order.get(j).setMenu(menu);
					
					os.setMenuName(order.get(j).getMenu().getMenuName());
					os.setWeekDate(sDateList.get(0)+" ~ "+sDateList.get(1));
					os.setQuantity(order.get(j).getQuantity());
					os.setPrice(order.get(j).getMenu().getMenuPrice() * order.get(j).getQuantity());
					os.setMenuId(order.get(i).getMenu().getId());
					 
					iNum++;
					
					osList.add(os);
				} else {
					
				}
			}
			try { // String to Calendar
				Date date = format.parse(sDateList.get(0)); 
				cal.setTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return osList;   
	}
	
	public List<Product> sumOrder(List<Product> temp_order){
		Map<Long, Integer> map = new HashMap<Long, Integer>();
		map = temp_order.stream().collect(Collectors.toMap(e -> e.getMenu().getId(), e -> e.getQuantity(), Integer::sum));
		Product product = new Product();
		
		
		List<Long> keyList = new ArrayList<Long>(map.keySet());
		List<Integer> valueList = new ArrayList<Integer>(map.values());
		
		List<Product> order = new ArrayList<Product>();
		
		for(int i=0;i<keyList.size();i++) {			
			order.add(new Product(new Menu(keyList.get(i)),valueList.get(i)));
		}
		
		return order;
	}
	/**
	 * 현재날짜 기준으로 날짜 뽑아서 일, 주, 월 별로 계산해서 출력하기!!!!!
	 */
	@Override
	public List<SalesStorage> findSaleRecord(char dateType) {
		List<Sales> sList = new ArrayList<Sales>();
		Calendar now = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		double tAmount = 0;
		double tusePoint = 0;
		List<SalesStorage> sstorageList = new ArrayList<>();
		
		int a = 0;
		if(dateType == 'D') {
			//1년의 수 중에 30보다 작을 때
			if(now.get(Calendar.DAY_OF_YEAR) <=30) {
				a = now.get(Calendar.DAY_OF_YEAR);
			}else{
				a = 30;
			}
			//1년의 수 중에 12주 보다 클 때
		}else if(dateType =='W') {
			if(now.get(Calendar.DAY_OF_YEAR) >77) {
				a = 12;
			}else if(now.get(Calendar.DAY_OF_YEAR) / 7 == 0) {
				a = now.get(Calendar.DAY_OF_YEAR) / 7;
			}else {
				a = now.get(Calendar.DAY_OF_YEAR) / 7 + 1;
			}
			//월별로 뽑을 때
		}else if(dateType == 'M') {
			a = now.get(Calendar.MONTH) + 1;
		}
		//날짜를 몇번 뽑을지 정했으니, 반복문 돌리기
		//String 날짜 값을 Date로 변경 , db에서 값 찾아오기
		
		for(int i=0 ; i<a ; i++) {

			SalesStorage sstorage = new SalesStorage();
			List<String> sDateList = sed.chooseDate(cal, i, dateType);
			//sDateList 반환값에는
			//Date 앞날(0번), 뒷날(1번)을 반환 해 준다.
			//String 값을 Date로 변경
			Date s1 = java.sql.Date.valueOf(sDateList.get(0));
			Date s2 = java.sql.Date.valueOf(sDateList.get(1));
			//변경 후 앞의 데이터값 ~ 뒤의 데이터값 구하깅
			
			sList = sd.findSalesByDate(s1, s2);
			
			
			if(sList.size()==0) {
				
			}else {
			for(Sales s : sList) {
				tAmount += s.getAmount();
				tusePoint += s.getUsePoint();
			} try { // String to Calendar
				Date date = format.parse(sDateList.get(0)); 
				cal.setTime(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			//for에서 얻어낸 List값을 하나하나 세팅 
			sstorage.setTotalusePoint(tusePoint);			
			sstorage.setTotalAmount(tAmount);
			sstorage.setTotalDate(sDateList.get(0)+" ~ "+sDateList.get(1));
			sstorage.setTotalorder(sList.size());
			sstorage.setTotalPrice(tAmount + tusePoint);
			//유즈포인트랑 어마운트 합친 토탈금액
			
			//어마운트와 유즈포인트 토탈 초기화
			tAmount = 0;
			tusePoint = 0;
			
			//리스트에 저장
			sstorageList.add(sstorage);
			}
			//System.out.println();
			//System.out.println();
			//for(SalesStorage s : sstorageList) {
				//System.out.println(s);
			//}
		}
		
		return sstorageList;
		
	}
	@Override
	public List<Sales> findSalesByCustomerId(long customerId) {
		return sd.findSalesByCustomerId(customerId);
	}

	//batch.
	public void addOrderRecord(List<Product> order) {
		int batchSize = 0;

		batchSize = sd.addOrderRecord(order).length;
	}
	
	/*
	public static void main(String[] args) {
		GenericApplicationContext context = 
				new AnnotationConfigApplicationContext(DataSourceConfig.class);
		SalesService test = context.getBean("salesService", SalesService.class);
		test.findSaleRecord('W');
		
		
		
	}*/
}
