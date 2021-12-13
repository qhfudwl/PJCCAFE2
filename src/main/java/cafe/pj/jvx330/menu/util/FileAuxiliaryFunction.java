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
	 * 이미지 파일의 경로를 새로 지정
	 * rootContext/resources/img/... 으로
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
		
		if (pathForm.equals("\\")) {
			rootPath = request.getSession().getServletContext().getRealPath("/");
			attachPath = "resources\\img\\";
		} else {
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
	
	/**
	 * 기본 폴더의 파일을 복사해서 resource/img 폴더 내 업로드
	 * @param request
	 * @param file
	 * @param menuType
	 * @param menuName
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public void uploadImg(HttpServletRequest request, MultipartFile file,
			char menuType, String menuName)
			throws IllegalStateException, IOException {
		
		String fileName = file.getOriginalFilename();
		String filePath = makePath(menuType, menuName, "\\", request) + fileName;
		if(!file.isEmpty()) {
			file.transferTo(new File(filePath));
		}
	}
	
	/**
	 * 파일 이름(확장자가 붙어있는 곳) 반환
	 * @param request
	 * @param imgFullPath
	 * @param file
	 * @param menuType
	 * @param menuName
	 * @return
	 */
	public String getName(HttpServletRequest request, String imgFullPath, 
			MultipartFile file, char menuType, String menuName) {
		
		if (!file.isEmpty()){
			String[] strArr = imgFullPath.trim().split("\\\\");
			String imgPath = makePath(menuType, menuName, "/", request) + strArr[strArr.length - 1];
			return imgPath;
		}
		
		String filePath = makePath(menuType, menuName, "/", request) + imgFullPath;
		
		return filePath;
	}
}
