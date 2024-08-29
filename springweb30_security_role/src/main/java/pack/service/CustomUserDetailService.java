package pack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pack.entity.User;

// 원래는 아이디와 비밀번호는 DB와 연동해서 처리
// 편의상 미리 특정한 ID와 비밀번호 사용

// User의 정보를 가져오는 클래스
@Service
public class CustomUserDetailService implements UserDetailsService{
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username : " + username);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String role = "";
		if(username.equals("guest")) {
			role = "ROLE_USER";		// 실제 DB에는 ROLE_XXX 형식으로 저장되어 있어야 한다	
		} else if(username.equals("batman")) {
			role = "ROLE_STAFF"; 
		} else if(username.equals("superman")) {
			role = "ROLE_ADMIN"; 
		}
		
		// DB에서 username을 이용해 사용자 정보(이름, 비번, 이메일, 권한, ...)를 얻어 온다
		User user = User.builder()	// pack.entity.User
						.id(1)
						.userName(username)
						.password(encoder.encode("1234"))
						.email("@")
						.role(role)
						.build();
		
		// 원래는 권한 정보도 따로 테이블로 만들어서 관리해 
		// 하나의 계정이 다양한 권한을 가질 수 있도록 해야 하지만
		// 샘플이므로 생략~
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
		// 여기서 반환된 userDetails 객체를 이용해 security가 계정과 비밀번호 유효성 검증을 하고 권한도 검증을 한다
		return userDetails;
	}
}
