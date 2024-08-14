package pack;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendService {

	@Autowired
	private FriendRepository friendRepository;
	
	public void saveFriend(Friend friend) {
		friendRepository.save(friend);
	}
	
	// Friend 데이터를 모두 읽어서 각 객체의 사진을 Base64로 변환 후, 그 결과를 리스트에 저장
	public List<Friend> findAll(){
		return friendRepository.findAll()
				.stream()
				.map(this::convertToBase64)
				.collect(Collectors.toList());
	}
	
	// 사진을 Base64로 인코딩해서 문자로 변환 (DB에 넣기 위해서)
	private Friend convertToBase64(Friend friend) {
		if(friend.getSajin() != null && friend.getSajin().length > 0) { // 사진이 존재하면~
			String base64Image = Base64.getEncoder().encodeToString(friend.getSajin());
			friend.setBase64Image(base64Image);
		}
		
		return friend;
	}
	
	// bunho값 증가시키기
	private int generateBunho() {
		Integer lastBunho = friendRepository.findLastBunho();
		if(lastBunho == null) {
			return 1;
		} else {
			return lastBunho + 2;
		}
	}
}
