package pack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import jakarta.servlet.http.HttpSessionEvent;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// 어플리케이션 보안 설정을 담당
	// 특정 URL 허용 여부, 로그인 및 로그아웃 처리 방법, 사용자 인증 방법 등을 포함
	// 보안 설정, 인증 방법
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
		return authConf.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> 	// 요청에 대한 인증 및 권한 부여 설정
									auth.requestMatchers("/auth/login", "/auth/logout", "/static/**")	// 이 요청들은
										.permitAll()	// 누구에게나 허용
										.anyRequest().authenticated())	// 그 외의 요청은 인증 요구
			.formLogin(formLogin -> 	// 폼(커스텀) 기반 로그인 설정
						formLogin.loginPage("/auth/login")	//	로그인 페이지로는 이 페이지 쓸게
								.loginProcessingUrl("/auth/login")
								.usernameParameter("sabun")
								.passwordParameter("irum")
								.defaultSuccessUrl("/auth/success", true)
								.permitAll())
			.logout(logout -> 
						logout.logoutUrl("/auth/logout")
							.logoutSuccessUrl("/auth/login")
							.invalidateHttpSession(true)	// 세션 무효화
							.clearAuthentication(true)	// 인증 정보 제거
							.deleteCookies("JSESSIONID")	// 세션 쿠키 삭제
							.permitAll());
		return http.build();
					
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 세션 유효시간 설정하기 : application.properties에서도 가능하다
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {	
		return new HttpSessionEventPublisher() {
			@Override
			public void sessionCreated(HttpSessionEvent event) {
				event.getSession().setMaxInactiveInterval(600);		// 600초 : 10분간 유효
				super.sessionCreated(event);
			}
		};
	}

}
