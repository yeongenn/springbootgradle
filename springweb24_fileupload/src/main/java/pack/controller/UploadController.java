package pack.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UploadController {
	
	@GetMapping("/upload")
	public String upload(UploadFile uploadFile) {
		return "uploadForm";
	}
	
	@PostMapping("/upload")
	public String submit(UploadFile uploadFile, 
							Model model,
							BindingResult result) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		// 업로드 된 파일 검사
		MultipartFile file = uploadFile.getFile();
		String fileName = file.getOriginalFilename();
		
		if(result.hasErrors()) {
			return "error";
		}
		
		try {
			inputStream = file.getInputStream();
			File newFile = new File("C:\\work2\\sprbtgradle\\springweb24_fileupload\\src\\main\\resources\\static\\pic\\" + fileName); // 파일의 절대 경로
			if(!newFile.exists()) {
				newFile.createNewFile();
			}
			
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024]; // 1KB
			while((read = inputStream.read(bytes)) != - 1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (Exception e) {
			System.out.println("file submit err : " + e);
			return "error";
		} finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		model.addAttribute("filename", fileName);
		return "uploadfile";
		
	}
	
	

}
