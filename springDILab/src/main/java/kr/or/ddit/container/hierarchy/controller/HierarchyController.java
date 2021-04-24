package kr.or.ddit.container.hierarchy.controller;

import org.springframework.stereotype.Controller;

import kr.or.ddit.container.hierarchy.service.HirearchyService;

@Controller
public class HierarchyController {
	private HirearchyService service;

	public HierarchyController(HirearchyService service) {
		super();
		this.service = service;
	}
	
}
