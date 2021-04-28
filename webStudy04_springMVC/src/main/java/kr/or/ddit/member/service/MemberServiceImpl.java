package kr.or.ddit.member.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.UserNotFoundException;
import kr.or.ddit.member.dao.IMemberDAO;
import kr.or.ddit.utils.CryptoUtil;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

@Service
public class MemberServiceImpl implements IMemberService {
	@Inject
	private IMemberDAO dao;
	@Inject
	private IAuthenticateService authService;
	
	@Override
	public MemberVO retrieveMember(String mem_id){
		MemberVO savedMember = dao.selectMemberDetail(mem_id);
		if(savedMember==null) {
			// custom exception 발생
			throw new UserNotFoundException("아이디에 해당하는 회원이 존재하지 않음.");
		}
		return savedMember;
	}

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(dao.selectMemberDetail(member.getMem_id())==null) {
			String inputPass = member.getMem_pass();
			try {
				String encodedPass = CryptoUtil.sha512(inputPass);
				member.setMem_pass(encodedPass);
				int rowcnt = dao.insertMember(member);
				if(rowcnt>0) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAIL;
				}// if~else~end
			}catch (Exception e) {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		retrieveMember(member.getMem_id());
		ServiceResult result = 
				authService.authenticate(new MemberVO(member.getMem_id(), member.getMem_pass()));
		if(ServiceResult.OK.equals(result)) {
			 int rowcnt = dao.updateMember(member);
			 if(rowcnt>0) {
				 result = ServiceResult.OK;
			 }else {
				 result = ServiceResult.FAIL;
			 }
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		retrieveMember(member.getMem_id());
		ServiceResult result = 
				authService.authenticate(new MemberVO(member.getMem_id(), member.getMem_pass()));
		if(ServiceResult.OK.equals(result)) {
			 int rowcnt = dao.deleteMember(member.getMem_id());
			 if(rowcnt>0) {
				 result = ServiceResult.OK;
			 }else {
				 result = ServiceResult.FAIL;
			 }
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList(PagingVO pagingVO) {
		return dao.selectMemberList(pagingVO);
	}
	
	@Override
	public int retrieveMemberCount(PagingVO<MemberVO> pagingVO) {
		return dao.selectTotalRecord(pagingVO);
	}

}
















