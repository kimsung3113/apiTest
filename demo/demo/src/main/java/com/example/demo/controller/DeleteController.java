package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/delete-api")
public class DeleteController {
	
	// 변수명을 동일하게 해줘야 한다. 보통 삭제하고 Status값을 return 하는게 일반적이다.
	// 삭제역할 - DB에서 특정할 수 있는값을 변수로 보내 삭제 처리!!!!!!!!!
	@DeleteMapping(value = "/delete/{variable}")
	public String deleteVariable(@PathVariable String variable) {return variable;}
}
