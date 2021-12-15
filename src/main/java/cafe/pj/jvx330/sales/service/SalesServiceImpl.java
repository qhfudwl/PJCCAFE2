package cafe.pj.jvx330.sales.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Menu;
import cafe.pj.jvx330.domain.Product;
import cafe.pj.jvx330.domain.Sales;
import cafe.pj.jvx330.menu.service.MenuService;
import cafe.pj.jvx330.sales.dao.SalesDao;
import cafe.pj.jvx330.web.util.OrderStorage;
import cafe.pj.jvx330.web.util.Setter_Date;

@Component("salesService")
public class SalesServiceImpl implements SalesService{
	private SalesDao sd;
	
	private Setter_Date sed = new Setter_Date();
	private List<Product> order = new ArrayList<Product>();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private MenuService ms;
	
	@Autowired
	public SalesServiceImpl(SalesDao salesDao) {
		this.sd = salesDao;
	}

	@Override
	public void addSales(Sales sales) {
		sd.addSales(sales);
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

	@Override
	public void removeSales(String orderNumber) {
		sd.removeSales(orderNumber);
	}
	
	public List<OrderStorage> findOrderRecordForMenu(char dateType, char menuType){
		Menu menu = null;
		Calendar now = Calendar.getInstance(); // 오늘 날짜 
		Calendar cal = Calendar.getInstance(); // 다음 범위 지정을 위해 범위 시작 날짜를 기억하는 용도
		List<OrderStorage> osList = new ArrayList<OrderStorage>();
		
		int nof = 0;
		if(dateType == 'M') {// 기간 Type 별로 반복 횟수 지정
			nof = now.get(Calendar.MONTH)+1;
		} else if(dateType == 'D') {
			if(now.get(Calendar.DAY_OF_YEAR) <= 30) {
				nof = now.get(Calendar.DAY_OF_YEAR);
			} else { // 1월 31보다 클 경우
				nof = 30;
			}

		} else { // dateType == 'W'
			if(now.get(Calendar.DAY_OF_YEAR) > 77) { // 11주보다 큰 값일 경우.
				nof = 12;
			} else if (now.get(Calendar.DAY_OF_YEAR) / 7 == 0){ // 70 처럼 나누어 떨어질 경우
				nof = now.get(Calendar.DAY_OF_YEAR) / 7;
			} else {
				nof = now.get(Calendar.DAY_OF_YEAR) / 7 + 1;
			}
		}
		
		int iNum = 0;
		
		for(int i=0; i<nof; i++) {
			List<String> sDateList = sed.chooseDate(cal, i, dateType); // 범위 시작날과 끝날을 List<String>에 0, 1 인덱스에 각각 넣기
			
			order.addAll(sd.findOrderRecordForMenu(sDateList.get(0), sDateList.get(1))); // order List에 전부 추가
			
			for(int j = iNum; j<order.size();j++) { // osList 세팅
				OrderStorage os = new OrderStorage();
				menu = ms.findMenuById(order.get(j).getMenu().getId());
				System.out.println(menu.getMenuType());
				if(menuType == 'T' || menuType == menu.getMenuType()) { 
					// menuType 에 따라 세팅.
					order.get(j).setMenu(menu);
					
					os.setMenuName(order.get(j).getMenu().getMenuName());
					os.setWeekDate(sDateList.get(0)+" ~ "+sDateList.get(1));
					os.setQuantity(order.get(j).getQuantity());
					os.setPrice(order.get(j).getMenu().getMenuPrice() * order.get(i).getQuantity());
				
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
		List<Product> order = new ArrayList<Product>();
		Product product = new Product();
		Menu menu = new Menu();
		int temp = 0;
		List<Long> preventRep = new ArrayList<>();
		preventRep.add((long) 0);
		
		for(int i = 0; i<temp_order.size(); i++) {
			for(long pr : preventRep) {
				if(pr == temp_order.get(i).getMenu().getId()) {
					// 이미 더한 menuId 차례일 때 넘어감
				} else {
					preventRep.add(temp_order.get(i).getMenu().getId());
					for(int j=i; j<temp_order.size(); j++) {
						if(temp_order.get(i).getMenu().getId() 
							== temp_order.get(j).getMenu().getId()) {
							temp = temp_order.get(i).getQuantity() 
									+ temp_order.get(j).getQuantity();
//							product // product 세팅 하고 list에 add.
							menu.setId(temp_order.get(i).getMenu().getId());
							product.setMenu(menu);
							product.setQuantity(temp); // Sum of Quantity setting
							order.add(product);
						}
					}
				}
			}
		}
		return order;
	}
	
	//batch.
	public void addOrderRecord(List<Product> order) {
		int batchSize = 0;

		batchSize = sd.addOrderRecord(order).length;
	}
	
}
