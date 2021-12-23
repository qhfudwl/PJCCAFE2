package cafe.pj.jvx330.menu.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cafe.pj.jvx330.menu.domain.Menu;
import lombok.extern.java.Log;

/**
 * 이미지 파일 보조 기능
 * 2021/12/23_refactoring - OS 별로 영향을 받지 않도록 기존의 "\" -> "/" 형태로 경로를 잡도록 변경
 * @author 김보령
 *
 */
@Component("fileAuxiliaryFunction")
@Log
public class FileAuxiliaryFunction {
	
	/**
	 * 이미지 파일의 절대 / 상대 경로 지정
	 * @param menuType
	 * @param menuName
	 * @param pathForm
	 * @param request
	 * @return
	 */
	public String makePath(HttpServletRequest request, char menuType, String menuName, char pathType) {
		String rootPath = "";
		String attachPath = "";
		String typePath = "";
		String tempPath = "";
		String pathForm = File.separator; // 해당 OS의 구분자
		
		if (pathType == 'A') { // 절대 경로
			rootPath = request.getSession().getServletContext().getRealPath("/"); // 서버 context root 절대 경로
		} else if (pathType == 'R') { // 상대 경로
			rootPath = request.getContextPath() + pathForm;
		}

		attachPath = "resources" + pathForm + "img" + pathForm;

		if (menuType == 'C') {
			typePath = "coffee" + pathForm;
		} else if (menuType == 'B') {
			typePath = "beverage" + pathForm;
		} else if (menuType == 'F') {
			typePath = "food" + pathForm;
		}
		
		if (menuType != 'F') { // 카테고리가 푸드이면 ice 나 hot 을 붙히면 안된다.
			if (menuName.substring(0, 1).equals("아")) {
				tempPath = "ice" + pathForm;
			}else if (menuName.substring(0, 1).equals("핫")) {
				tempPath = "hot" + pathForm;
			}
		}
		
		return rootPath + attachPath + typePath + tempPath;
	}
	
	/**
	 * 해당 파일 경로의 가장 뒤에 확장자가 붙은 것만 가져온다.
	 * @param imgPath
	 * @return
	 */
	public String getImgName(String imgPath) {
		File file = new File(imgPath);
		return file.getName();
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
	public boolean uploadImgFile(HttpServletRequest request, MultipartFile file, char menuType, String menuName)
			throws IllegalStateException, IOException {
		
		String fileName = file.getOriginalFilename();
		String filePath = getAbsolutePath(request, menuType, menuName) + fileName;
		
		log.info(filePath);
		
		if(!file.isEmpty()) {
			file.transferTo(new File(filePath));
			return true;
		}
		return false;
	}
	
	/**
	 * 파일 삭제
	 * @param request
	 * @param menuType
	 * @param menuName
	 * @param removeImgName
	 * @return
	 */
	public boolean removeImgFile(HttpServletRequest request, char menuType, String menuName, String removeImgName) {
		
		String filePath = getAbsolutePath(request, menuType, menuName) + removeImgName;
		File file = new File(filePath);
		
		if(file.exists()) { // 파일이 있다면 삭제
			file.delete();
			return true;
		}
		return false;
	}
	
	/**
	 * 해당 메뉴의 절대 경로 반환 (확장자 전까지만)
	 * @param request
	 * @param menuType
	 * @param menuName
	 * @return
	 */
	public String getAbsolutePath(HttpServletRequest request, char menuType, String menuName) {
		return makePath(request, menuType, menuName, 'A');
	}
	
	/**
	 * 확장자를 포함한 해당 메뉴의 논리 경로 반환
	 * @param request
	 * @param menuType
	 * @param menuName
	 * @param imgName
	 * @return
	 */
	public String getRelativePath(HttpServletRequest request, char menuType, String menuName, String imgName) {
		if (imgName.contains("placeholdImg")) { // 빈 이미지일 경우
			return request.getContextPath() + "/resources/img/" + imgName;
		}
		log.info(makePath(request, menuType, menuName, 'R') + imgName);
		return makePath(request, menuType, menuName, 'R') + imgName;
	}
}
