package kr.or.ddit.container.hierarchy.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.container.hierarchy.dao.HierarchyDAO;

@Service
public class HirearchyService {
	private HierarchyDAO dao;
	@Inject
	public void setDao(HierarchyDAO dao) {
		this.dao = dao;
	}
}
