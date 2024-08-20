package pack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration	// spring의 설정을 위한 클래스
@EnableWebSecurity

/*
 @EnableWebSecurity
 spring security 기능을 활성화
 보안설정 자동화, 인증 및 권한 부여, 비밀번호 암호화, CSRF 보호,
 기본 로그인 폼 제공, user라는 기본 사용자 계정 생성 ... 
 */
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((requests) -> 
				requests.requestMatchers("/").permitAll() // root 경로는 모두 허용
				.anyRequest().authenticated())	// 그 외는 인증 필요
			.formLogin((form) -> form.loginPage("/login").permitAll())	// /login 경로 허용, 커스텀 로그인 페이지 설정
			.logout((logout) -> 	// 로그아웃 설정을 정의
					logout.logoutUrl("/logout")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.permitAll())
			.csrf((csrf) -> csrf.disable());	// 
		return http.build();
	}
	
	@Bean
	// 사용자 세부 정보를 관리하는 서비스
	public UserDetailsService userDetailsService() {
		// 사용자 정보를 빌드
		UserDetails user = 
				User.builder()
				.username("user")
				.password(passwordEncoder().encode("password"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
				
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCrypt는 암호화 해시 함수로 현재 사용 중인 강력한 해시 메커니즘 중 하나
		// 해시 : 입력 데이터를 고정된 길이의 고유값(해시값)으로 변환하는 과정 또는 결과
		return new BCryptPasswordEncoder();
		
	}
	
	

}
