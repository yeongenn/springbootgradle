package pack;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Friend {
	@Id
	private int bunho;
	
	private String irum, junhwa, jikup;
	
	@Lob // 데이터타입이 BLOB(Binary~), CLOB(Character~)인 경우
	private byte[] sajin; // 이진데이터처리
	
	private String imagetype; // jpeg, png, ... etc
	
	@Transient
	private String base64Image; // base64로 인코딩된 이미지 타입
	
	/*
	 @Transient
	 
	 영속 대상에서 제외하기 위한 어노테이션
	 메서드와 필드에 모두 선언 가능
	 영속 대상에서 제외되면 해당 필드나 메서드는 EntityManager(em) 관리 대상에서 제외
	 따라서 em에 의해 변화에 대한 자동감지(dirty checking), CRUD sql 자동 생성 작업 및 일련의 jpa 내부 동작 프로세스를 수행하지 X
	 결국 "(물리) 테이블의 칼럼과 맵핑하지 않는다" 와 동일
	 
	 물리 테이블에는 없는(관련없는) 임시 저장용 필드
	 비즈니스 로직에만 필요한 데이터 관리
	 ex) 비밀번호 재확인용 필드
	 
	 ++
	 JPA의 Entity 접근 방식은 @Id 어노테이션의 위치에 의해 결정
	 
	 
	 
	
	*/

}
