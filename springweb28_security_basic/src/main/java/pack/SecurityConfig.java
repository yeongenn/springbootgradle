package pack;

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

@Configuration
@EnableWebSecurity
public class SecurityConfig { // 기본적인 웹 보안 구성 설정
	
	// SecurityFilterChain을 반환하는
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// HttpSecurity 객체를 사용하여 보안설정을 정의
		httpSecurity
				.authorizeHttpRequests(authorizeRequest -> // http 요청에 대한 보안 권한 설정 부분
					authorizeRequest
						.requestMatchers("/login").permitAll() // /login 경로는 인증없이 누구든 접근 허용
						.anyRequest().authenticated() // 그 외 나머지 경로(요청)는 인증이 필요하다
				)
				.formLogin(formLogin -> 
					formLogin
						.loginPage("/login") // 내가 커스텀한 로그인 페이지 사용할게
						.defaultSuccessUrl("/", true)	// 로그인 성공 시 컨텍스트 루트로 이동
						.permitAll()	// login 페이지는 인증없이 누구든 접근 허용
				)
				.logout(logout -> 
					logout
						.logoutUrl("/logout")
						.logoutSuccessUrl("/login?logout")
					.permitAll()	// logout은 인증없이 누구든 접근 허용
						
				)
				.sessionManagement(sessionManagement ->
					sessionManagement
						.maximumSessions(1)	// 최대 동시 세션 수 제한
						.expiredUrl("/login?expired") 	// 세션 만료 시 로그인 화면으로 이동
				)
				;
		
		return httpSecurity.build();
				
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
								.username("myuser")	// DB가 있다면 DB 다녀오면 되겠지?
								.password(passwordEncoder().encode("123"))
								.roles("USER")	// default user 역할
								.build();	//	사용자명과 비밀번호 역할 설정
		return new InMemoryUserDetailsManager(user);
		// InMemoryUserDetailsManager : 사용자 정보를 메모리에 저장하고 관리하는 클래스
		// 주로 어플리케이션, 테스트 환경에서 사용, 영구 저장소는 아니다
		// 어플리케이션 재시작하면 사라진다
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	// 비밀번호만 암호화위해 BCrypt 알고리즘 사용
		// 단방향 해시함수를 이용하여 암호화 수행
	}

}
