package cafe.pj.jvx330.menu.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component("fileCopy")
public class FileCopy {
	public void copyImg(String imgPath) throws FileNotFoundException {
		File file = new File(imgPath);
		InputStream is = new FileInputStream(file);
		
	}
}
