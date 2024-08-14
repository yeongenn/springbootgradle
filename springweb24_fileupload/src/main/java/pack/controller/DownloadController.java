package pack.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class DownloadController {

	@PostMapping("download")
	@ResponseBody
	public byte[] downProcess(HttpServletResponse res,
								@RequestParam("filename") String filename) throws Exception {
		//System.out.println("filename : " + filename);
		
		File file = new File("C:\\work2\\sprbtgradle\\springweb24_fileupload\\src\\main\\resources\\static\\pic\\" + filename);
		
		byte[] bytes = FileCopyUtils.copyToByteArray(file);
		String fn = new String(file.getName().getBytes(), "iso_8859_1"); // iso_8859_1 : window 기본 charset(?)
		
		// 브라우저에 다운로드를 지시
		// 이 파일은 열지말고 다운로드 받아라~
		res.setHeader("Content-Disposition", "attachment;filename=\"" + fn + "\"");
		res.setContentLength(bytes.length); // 파일 총 크기
		
		
		return bytes;
	}
}
