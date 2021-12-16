package cafe.pj.jvx330.menu.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cafe.pj.jvx330.domain.Menu;

/**
 * 이미지 파일 보조 기능
 * @author 김보령
 *
 */
@Component("fileAuxiliaryFunction")
public class FileAuxiliaryFunction {
	
	/**
	 * 이미지 파일의 절대 / 상대 경로 지정
	 * @param menuType
	 * @param menuName
	 * @param pathForm
	 * @param request
	 * @return
	 */
	public String makePath(char menuType, String menuName, String pathForm, HttpServletRequest request) {
		String rootPath = "";
		String attachPath = "";
		String typePath = "";
		String tempPath = "";
		
		if (pathForm.equals("\\")) { // 절대 경로
			rootPath = request.getSession().getServletContext().getRealPath("/");
			attachPath = "resources\\img\\";
		} else { // 상대 경로
			rootPath = request.getContextPath();
			attachPath = "/resources/img/";
		}
		

		if (menuType == 'C') {
			typePath = "coffee" + pathForm;
		} else if (menuType == 'B') {
			typePath = "beverage" + pathForm;
		} else if (menuType == 'F') {
			typePath = "food" + pathForm;
		}
		
		if (menuName.substring(0, 1).equals("아")) {
			tempPath = "ice" + pathForm;
		}else if (menuName.substring(0, 1).equals("핫")) {
			tempPath = "hot" + pathForm;
		}
		
		return rootPath + attachPath + typePath + tempPath;
	}
	
	public String getImgName(String imgPath) {
		String[] arr = imgPath.trim().split("/");
		return arr[arr.length-1];
	}
	
	/**
	 * 기본 폴더의 파일을 복사해서 resource/img 폴더 내 업로드
	 * @param request
	 * @param file
	 * @param menuType
	 * @param menuName
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public boolean uploadImgFile(HttpServletRequest request, MultipartFile file, Menu menu)
			throws IllegalStateException, IOException {
		
		String fileName = file.getOriginalFilename();
		String filePath = getAbsolutePath(request, menu.getMenuType(), menu.getMenuName()) + fileName;
		
		if(!file.isEmpty()) {
			file.transferTo(new File(filePath));
			System.out.println("파일 업로드 완료");
			return true;
		}
		System.out.println("이미 존재하는 파일입니다.");
		return false;
	}
	
	/**
	 * 파일 삭제
	 * @param request
	 * @param menuType
	 * @param removeImgName
	 * @return
	 */
	public boolean removeImgFile(HttpServletRequest request, Menu menu
			, String removeImgName) {
		
		String filePath = getAbsolutePath(request, menu.getMenuType(), menu.getMenuName()) + removeImgName;
		File file = new File(filePath);
		
		if(file.exists()) { // 파일이 있다면 삭제
			file.delete();
			System.out.println("파일 삭제 완료");
			return true;
		}
		System.out.println("파일이 없습니다.");
		return false;
	}
	
	/**
	 * 해당 메뉴의 절대 경로 반환 (확장자 전까지만)
	 * @param request
	 * @param imgName
	 * @param menu
	 * @return
	 */
	public String getAbsolutePath(HttpServletRequest request, char menuType, String menuName) {
		return makePath(menuType, menuName, "\\", request);
	}
	
	/**
	 * 확장자를 포함한 해당 메뉴의 논리 경로 반환
	 * @param request
	 * @param imgName
	 * @param menu
	 * @return
	 */
	public String getRelativePath(HttpServletRequest request, char menuType, String menuName, String imgName) {
		return makePath(menuType, menuName, "/", request) + imgName;
	}
}
