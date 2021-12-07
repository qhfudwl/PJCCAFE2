package cafe.pj.jvx330.menu.service;

import cafe.pj.jvx330.domain.Menu;
import java.util.List;
/**
 * 
 * @author 김보령
 *
 */
public interface MenuService {
	/**
	 * 메뉴 객체(타입, 이름, 가격, 재고)를 넣어 새로운 메뉴 추가
	 * @param menu
	 * @return
	 * @author 김보령
	 */
	Menu addMenu(Menu menu);
	
	/**
	 * 메뉴 이름으로 메뉴 객체 찾기
	 * @param menuName
	 * @return
	 */
	Menu findMenuByMenuName(String menuName);
	
	/**
	 * 모든 메뉴 리스트 뽑기
	 * @return
	 * @author 김보령
	 */
	List<Menu> findAllMenus();
	
	/**
	 * 메뉴 타입에 따라 메뉴 리스트
	 * 타입 = 음식 F, 음료 B, 커피 C
	 * @return
	 * @author 김보령
	 */
	List<Menu> findAllMenusByMenuType();
	
	/**
	 * 업데이트 정보가 담긴 메뉴 객체를 받아
	 * 해당 id 로 메뉴 정보 업데이트
	 * id가 반드시 있어야한다.
	 * @param menu
	 * @return
	 * @author 김보령
	 */
	Menu UpdateMenuById(Menu menu);
	
	/**
	 * 메뉴 객체를 넣어 메뉴 삭제
	 * id 가 있어야한다.
	 * 추후 수정 가능
	 * > 그냥 이름으로 삭제해도 되지 않을까??
	 * @param menu
	 * @author 김보령
	 */
	void RemoveMenuById(Menu menu); 
}
