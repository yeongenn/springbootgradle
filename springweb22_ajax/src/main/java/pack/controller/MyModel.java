package pack.controller;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data

@Component
public class MyModel {
	private String name;
	private String skills[];
}
