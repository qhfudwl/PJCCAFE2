package cafe.pj.jvx330.web.utill;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("auxiliaryFunction")
public class AuxiliaryFunction {
	
	public void uploadImg(HttpServletRequest request, MultipartFile file)
			throws IllegalStateException, IOException {
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String attachPath = "resources\\img\\";
		String fileName = file.getOriginalFilename();
		String filePath = rootPath + attachPath + fileName;
		
		if(!file.isEmpty()) {
			file.transferTo(new File(filePath));
		}
	}
	
	public String getName(HttpServletRequest request, String imgFullPath, 
			MultipartFile file) {

		String rootPath = request.getContextPath();
		String attachPath = "/resources/img/";
		
		if (!file.isEmpty()){
			String[] strArr = imgFullPath.trim().split("\\\\");
			String imgPath = rootPath + attachPath + strArr[strArr.length - 1];
			return imgPath;
		}
		
		String filePath = rootPath + attachPath + imgFullPath;
		
		return filePath;
	}
}
