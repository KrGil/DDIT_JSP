package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.CustomException;
import kr.or.ddit.prod.dao.IProdDAO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Service
public class ProdServiceImpl implements IProdService {
	@Inject
	private IProdDAO dao;
	
	@Value("#{appInfo.prodImages}")
	String saveFolderUrl;
	File saveFolder;
	
	@Inject
	private WebApplicationContext container;
	private ServletContext application;
	
	@PostConstruct
	public void init() {
		application = container.getServletContext();
		saveFolder = new File(application.getRealPath(saveFolderUrl));
		if(!saveFolder.exists()) {
			saveFolder.mkdirs();
		}
	}
	

	@Override
	public ProdVO retrieveProd(String prod_id) {
		ProdVO prod = dao.selectProd(prod_id);
		if(prod==null)
			throw new CustomException(
					String.format("%s 에 해당하는 상품이 없음.", prod_id)
				);
		return prod;
	}
	@Override
	public List<ProdVO> retrieveProdList(PagingVO<ProdVO> pagingVO) {
		return dao.selectProdList(pagingVO);
	}
	@Override
	public int retrieveProdCount(PagingVO<ProdVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}
	@Override
	public ServiceResult createProd(ProdVO prod) {
		int cnt = dao.insertProd(prod);
		try {
			prod.saveTo(saveFolder);
			ServiceResult result = ServiceResult.FAIL;
			if(cnt>0)
				result = ServiceResult.OK;
			return result;
		} catch (IOException e) {
			throw new CustomException(e);
		}
	}
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		retrieveProd(prod.getProd_id());
		int rowcnt = dao.updateProd(prod);
		try {
			prod.saveTo(saveFolder);
			ServiceResult result = ServiceResult.FAIL;
			if(rowcnt > 0) 
				result = ServiceResult.OK;
			return result;
		} catch (IOException e) {
			throw new CustomException(e);
		}
	}

}





