package cafe.pj.jvx330.menu.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import cafe.pj.jvx330.domain.Menu;

/**
 * 
 * @author 김보령
 */
public interface MenuDao {
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
	 * @author 김보령
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
	 * @param menuType
	 * @return
	 * @author 김보령
	 */
	List<Menu> findAllMenusByMenuType(char menuType);
	
	/**
	 * 업데이트 정보가 담긴 메뉴 객체를 받아
	 * 해당 id 로 메뉴 정보 업데이트
	 * id가 반드시 있어야한다.
	 * > 업데이트는 어차피 모든 정보가 다 있어야하기 때문에 일단 하나만
	 * > 추후에 오버로딩 가능성이 있다.
	 * @param menu
	 * @author 김보령
	 */
	void updateMenuById(Menu menu);
	
	/**
	 * 메뉴 id를 넣어 메뉴 삭제
	 * @param menu
	 * @author 김보령
	 */
	void removeMenuById(long id); 
}
