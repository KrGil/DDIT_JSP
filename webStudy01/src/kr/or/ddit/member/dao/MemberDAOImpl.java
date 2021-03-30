package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements IMemberDAO{

	@Override
	public MemberVO selectMemberForAuth(String mem_id) {
		MemberVO member = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT mem_id, mem_pass, mem_name, mem_mail ");
		sql.append("FROM member ");
		sql.append("WHERE mem_id = ? ");
		// ? 를 쿼리파라미터라고 한다.
		try(
			Connection conn = ConnectionFactory.getConnection();
//			Statement stmt = conn.createStatement();
			// 쿼리가 먼저 결정됨
			PreparedStatement pstmt = conn.prepareStatement(sql.toString()); 
		){
			pstmt.setString(1, mem_id);
			//동적으로 sql을 바꾸기 않기 위해 sql파라미터를 얻지않는다.
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_id(rs.getString("MEM_ID"));
				member.setMem_pass(rs.getString("MEM_PASS"));
				member.setMem_name(rs.getString("MEM_NAME"));
				member.setMem_mail(rs.getString("MEM_MAIL"));
			}
			return member;
		} catch (SQLException e) { // unchecked 서버쪽으로 오류가 넘어간다.
			throw new RuntimeException(e);
		}
	}

	@Override
	public MemberVO selectMemberDetail(String mem_id) {
			MemberVO member = null;
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT                                                                           ");
		    sql.append("MEM_ID, MEM_PASS, MEM_NAME,                                                      ");
		    sql.append("MEM_REGNO1, MEM_REGNO2, TO_CHAR(MEM_BIR, 'YYYY-MM-DD') MEM_BIR,                  ");
		    sql.append("MEM_ZIP, MEM_ADD1, MEM_ADD2, MEM_HOMETEL,                                        ");
		    sql.append("MEM_COMTEL, MEM_HP, MEM_MAIL, MEM_JOB,                                           ");
		    sql.append("MEM_LIKE, MEM_MEMORIAL, TO_CHAR(MEM_MEMORIALDAY, 'YYYY-MM-DD') MEM_MEMORIALDAY,  ");
		    sql.append("MEM_MILEAGE, MEM_DELETE                                                          ");
			sql.append("FROM MEMBER                                                                      ");
			sql.append("WHERE MEM_ID = ? ");
			// ? 를 쿼리파라미터라고 한다.
			try(
				Connection conn = ConnectionFactory.getConnection();
//				Statement stmt = conn.createStatement();
				// 쿼리가 먼저 결정됨
				PreparedStatement pstmt = conn.prepareStatement(sql.toString()); 
			){
				pstmt.setString(1, mem_id);
				//동적으로 sql을 바꾸기 않기 위해 sql파라미터를 얻지않는다.
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					member = new MemberVO();
					member.setMem_id(rs.getString("MEM_ID"));
					member.setMem_pass(rs.getString("MEM_PASS"));
					member.setMem_name(rs.getString("MEM_NAME"));
					member.setMem_regno1(rs.getString("MEM_REGNO1"));
					member.setMem_regno2(rs.getString("MEM_REGNO2"));
					member.setMem_bir(rs.getString("MEM_BIR"));
					member.setMem_zip(rs.getString("MEM_ZIP"));
					member.setMem_add1(rs.getString("MEM_ADD1"));
					member.setMem_add2(rs.getString("MEM_ADD2"));
					member.setMem_hometel(rs.getString("MEM_HOMETEL"));
					member.setMem_comtel(rs.getString("MEM_COMTEL"));
					member.setMem_hp(rs.getString("MEM_HP"));
					member.setMem_mail(rs.getString("MEM_MAIL"));
					member.setMem_job(rs.getString("MEM_JOB"));
					member.setMem_like(rs.getString("MEM_LIKE"));
					member.setMem_memorial(rs.getString("MEM_MEMORIAL"));
					member.setMem_memorialday(rs.getString("MEM_MEMORIALDAY"));
					member.setMem_mileage(rs.getInt("MEM_MILEAGE"));
					member.setMem_delete(rs.getString("MEM_DELETE"));
				}
				return member;
			} catch (SQLException e) { // unchecked 서버쪽으로 오류가 넘어간다.
				throw new RuntimeException(e);
			}
	}
}
