package cafe.pj.jvx330.menu.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("fileAuxiliaryFunction")
public class FileAuxiliaryFunction {
	
	private String makePath(char menuType, String menuName, String pathForm, HttpServletRequest request) {
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
	
	public void uploadImg(HttpServletRequest request, MultipartFile file,
			char menuType, String menuName)
			throws IllegalStateException, IOException {
		
		String fileName = file.getOriginalFilename();
		String filePath = makePath(menuType, menuName, "\\", request) + fileName;
		
		if(!file.isEmpty()) {
			file.transferTo(new File(filePath));
		}
	}
	
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
