package pack.controller;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

// dto 등 모두 controller 패키지 안에 작성

@Data
public class UploadDto {
	
	private String myName;
	private MultipartFile myFile;

}
