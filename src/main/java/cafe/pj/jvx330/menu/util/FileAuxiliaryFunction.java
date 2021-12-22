package cafe.pj.jvx330.menu.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cafe.pj.jvx330.domain.Menu;
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
	public String makePath(HttpServletRequest request, char menuType, String menuName, String pathForm) {
		String rootPath = "";
		String attachPath = "";
		String typePath = "";
		String tempPath = "";
		
		if (pathForm.equals("\\")) { // 절대 경로
			String rootPathOrigin = request.getSession().getServletContext().getRealPath("/"); // 서버 context root 절대 경로
			String[] rootPathArr = null;
			
			if (rootPathOrigin.contains("\\")) { // window 일 경우
				rootPathArr = rootPathOrigin.trim().split("\\\\"); // "\"로 배열을 나눈다.
				rootPath = String.join("/", rootPathArr);
			} else if (rootPathOrigin.contains("/")) { // window 외 OS 일 경우
				rootPath = rootPathOrigin; // 그대로 대입
			}
			
		} else { // 상대 경로
			rootPath = request.getContextPath();
		}

		attachPath = "/resources/img/";

		if (menuType == 'C') {
			typePath = "coffee/";
		} else if (menuType == 'B') {
			typePath = "beverage/";
		} else if (menuType == 'F') {
			typePath = "food/";
		}
		
		if (menuType != 'F') { // 카테고리가 푸드이면 ice 나 hot 을 붙히면 안된다.
			if (menuName.substring(0, 1).equals("아")) {
				tempPath = "ice/";
			}else if (menuName.substring(0, 1).equals("핫")) {
				tempPath = "hot/";
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
		String[] arr = imgPath.trim().split("/");
		String imgName = null;
		
		if (imgPath.contains("\\")) { // window 일 경우
			arr = imgPath.trim().split("\\\\");
			imgPath = arr[arr.length-1];
		} else if (imgPath.contains("/")) { // window 외 OS 일 경우
			arr = imgPath.trim().split("/");
			imgPath = arr[arr.length-1];
		}
		
		return imgPath;
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
		return makePath(request, menuType, menuName, "\\");
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
		return makePath(request, menuType, menuName, "/") + imgName;
	}
}
