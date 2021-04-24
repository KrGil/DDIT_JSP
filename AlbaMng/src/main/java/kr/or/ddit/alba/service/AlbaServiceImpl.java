package kr.or.ddit.alba.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.alba.dao.AlbaDAOImpl;
import kr.or.ddit.alba.dao.IAlbaDAO;
import kr.or.ddit.alba.dao.ILicenseDAO;
import kr.or.ddit.alba.dao.LicenseDAOImpl;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.DataNotFoundException;
import kr.or.ddit.mvc.filter.wrapper.MultipartFile;
import kr.or.ddit.vo.AlbaVO;
import kr.or.ddit.vo.LicenseVO;
import kr.or.ddit.vo.PagingVO;

public class AlbaServiceImpl implements IAlbaService {
	
	private static AlbaServiceImpl self;
	private AlbaServiceImpl() {}
	public static AlbaServiceImpl getInstance() {
		if(self==null) self = new AlbaServiceImpl();
		return self;
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	private IAlbaDAO albaDAO = AlbaDAOImpl.getInstance();
	private ILicenseDAO licDAO = LicenseDAOImpl.getInstance();
	
//	=============== File process===================================================================
	private File profileFolder;
	public void setProfileFolder(File profileFolder) {
		this.profileFolder = profileFolder;
	}
	
	private void deleteProfile(String alId) {
		String savedImg = albaDAO.selectAlba(alId).getAlImg();
		if(StringUtils.isNotBlank(savedImg)) {
			(new File(profileFolder, savedImg)).delete();
		}
	}
	private void processProfile(AlbaVO alba) throws IOException {
		MultipartFile profile = alba.getAlImage();
		if(profile!=null)
			profile.saveTo(profileFolder);
	}
//	===============================================================================================

	@Override
	public ServiceResult createAlba(AlbaVO alba) {
		try(
			SqlSession session = sqlSessionFactory.openSession();	
		){
			int rowcnt = albaDAO.insertAlba(alba, session);
			ServiceResult result = ServiceResult.FAILED;
			if(alba.getLicCodes()!=null) {
				rowcnt += licDAO.insertLicenses(alba, session);
			}
			if(rowcnt>0) {
				processProfile(alba);
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int retrieveAlbaCount(PagingVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaCount(pagingVO);
	}

	@Override
	public List<AlbaVO> retrieveAlbaList(PagingVO<AlbaVO> pagingVO) {
		return albaDAO.selectAlbaList(pagingVO);
	}
	
	@Override
	public AlbaVO retrieveAlba(String al_id) {
		AlbaVO alba = albaDAO.selectAlba(al_id);
		if(alba==null)
			throw new DataNotFoundException(String.format("PK-'%s' 로 식별되는 데이터가 없음.", al_id));
		return alba;
	}

	@Override
	public ServiceResult modifyAlba(AlbaVO alba) {
		try(
			SqlSession session = sqlSessionFactory.openSession();	
		){
			int rowcnt = albaDAO.updateAlba(alba, session);
			ServiceResult result = ServiceResult.FAILED;
			String[] deleteLicCodes = alba.getDeleteLicCodes();
			if(deleteLicCodes!=null && deleteLicCodes.length>0) {
				rowcnt += licDAO.deleteLicenses(alba, session);
			}
			if(alba.getLicenseList()!=null) {
				rowcnt += licDAO.insertLicenses(alba, session);
			}
			if(rowcnt>0) {
				if(alba.getAlImage()!=null) {
					deleteProfile(alba.getAlId());
					processProfile(alba);
				}
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ServiceResult removeAlba(String al_id) {
		try(
			SqlSession session = sqlSessionFactory.openSession();	
		){
			int rowcnt = albaDAO.deleteAlba(al_id, session);
			ServiceResult result = ServiceResult.FAILED;
			if(rowcnt>0) {
				deleteProfile(al_id);
				result = ServiceResult.OK;
				session.commit();
			}
			return result;
		}
	}
	
	@Override
	public LicenseVO retrieveLicense(LicenseVO licAlba) {
		return licDAO.selectLicense(licAlba);
	}
}
