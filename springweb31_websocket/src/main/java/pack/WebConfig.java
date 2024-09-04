package pack;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	// CORS 설정을 관리하기 위해 사용되는 인터페이스
	// 글로벌 CORS 설정은 모든 HTTP 엔드폰트에 대해 적용한다
	// 필요에 따라 특정 경로에 대해서면 CORS를 설정할 수도 있다
	// 이러한 설정을 통해 다른 도메인에서의 웹소켓 연결 문제를 해결할 수 있다
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("*")	// 모든 도메인 허용
		.allowedMethods("GET", "POST", "DELETE", "PUT")	// 허용할 HTTP 메서드
		.allowedHeaders("*");	// 모든 헤더 허용
	}
}
