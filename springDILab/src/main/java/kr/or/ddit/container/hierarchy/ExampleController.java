package kr.or.ddit.container.hierarchy;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import kr.or.ddit.example.service.IExampleService;

//@Controller
public class ExampleController {
	private IExampleService service;
	@Inject
	public void setService(IExampleService service) {
		this.service = service;
	}
	
	public void process(String pk) {
		String info = service.readData(pk);
		System.out.println(info);
	}
}
