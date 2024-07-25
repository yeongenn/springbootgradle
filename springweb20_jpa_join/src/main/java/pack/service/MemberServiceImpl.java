package pack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.transaction.Transactional;
import pack.dto.MemberDto;
import pack.entity.Member;
import pack.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public void getList(Model model) {	
		//방법 1) Member 엔티티를 MemberDto 객체로 전달

		/*
		List<Member> entityList = memberRepository.findAll();
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		// Entity to DTO
		for(Member temp : entityList) {
			MemberDto dto = new MemberDto();
			dto.setNum(temp.getNum());
			dto.setName(dto.getName());
			dto.setAddr(dto.getAddr());
			list.add(dto);
		}
		*/
		
		
		//방법 2) List<Member>를 Stream으로 변경해서 
		//map()을 사용, List<MemberDto>로 변경하기
		
		List<MemberDto> list = memberRepository.findAllByOrderByNumDesc()
												.stream()
												.map(item -> MemberDto.toDto(item))
												.toList();
		
		
		//방법 3) 위 방법을 람다 표현식으로 
		// 메서드 참조 표현식 - [클래스명]::[메서드명]
		/*
		List<MemberDto> list = memberRepository.findAllByOrderByNumDesc()
												.stream()
												.map(MemberDto::toDto) // Lambda Expression
												.toList();
		*/
		
		
		// 컨트롤러에 MemberDto가 담긴 list 전달
		model.addAttribute("list", list);
	}
	
	// 자료 1개 읽기 (수정용)
	@Override
	public void getData(Long num, Model model) {
		// Repository로부터 Member Entity를 받아서
		Member member = memberRepository.findById(num).get();
		// DTO로 변환한 다음 model에 저장한 다음 Controller로 슝
		model.addAttribute("dto", MemberDto.toDto(member));
		
	}
	
	@Override
	public void insert(MemberDto dto) {
		/*
		 JPA 작업 영역 내로 들어갈 때 일반 자료 전달용 (DTO, FormBean) 객체를 대응되는 Entity로 변환하기
		 반대로 JPA 영역에서 나올 때는 Entity를 대응되는 DTO or FormBean으로 변환해야겠지
		 
		 insert, delete, update의 경우 받아와야 할 값 X
		 Controller에서 고객의 요청값을 DTO(정석대로라면 FormBean)를 통해 보내주면
		 그걸 받아서 대응되는 Entity로 변환한 다음
		 Repository로 슝~ (save())
		 
		 */
		
		// insert는 save()만 하면 된다
		memberRepository.save(Member.toEntity(dto));
		
	}
	
	@Override
	public void update(MemberDto dto) {
		// update도 save()
		memberRepository.save(Member.toEntity(dto));
		
	}
	
	@Transactional // 달아줘야 수정된다~
	@Override
	public void update2(MemberDto dto) {
		// 위 update 처럼 요청받은 값(?)으로 바로 save 하지 말고 이 방법으로
		
		// 수정할 회원의 번호를 이용해서 회원 정보 entity 객체 얻어내기
	    Member m1 = memberRepository.findById(dto.getNum()).get();
	    Member m2 = memberRepository.findById(dto.getNum()).get();
	    
	    // 동일성 검사
	    boolean isEqual = m1 == m2;
	    System.out.println("m1과 m2가 같냐?" + isEqual);
	    
	    // setter 메소드를 이용해서 이름과 주소 수정하기
	    m1.setName(dto.getName());
	    m1.setAddr(dto.getAddr());
		
	}
	
	@Override
	public void delete(Long num) {
		memberRepository.deleteById(num);
		
	}

}
