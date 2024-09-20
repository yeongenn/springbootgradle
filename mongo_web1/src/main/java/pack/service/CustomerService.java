package pack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pack.model.Customer;
import pack.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public void printAllData() {
		customerRepository.findAll().forEach(customer -> {
			System.out.println(customer);
		});
	}

	public void updateData(String name) {
		Customer customer = customerRepository.findByName(name);	// 이름으로 수정 대상 자료 찾기
		if(customer != null) {
			// 나이와 성별 수정
			customer.setAge("35");
			customer.setGender("여성");
			customerRepository.save(customer);
			System.out.println("자료 수정 성공 : " + customer);
		} else {
			System.out.println(name + " 자료는 없어요");
		}
	}
	
	public void deleteData(String name) {
		Customer customer = customerRepository.findByName(name);	// 이름으로 삭제 대상 자료 찾기
		if(customer != null) {
			customerRepository.delete(customer);
			System.out.println("자료 삭제 성공");
		} else {
			System.out.println(name + " 자료는 없어요");
		}
	}
}
