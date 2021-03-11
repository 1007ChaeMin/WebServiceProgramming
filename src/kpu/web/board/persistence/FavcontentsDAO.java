package kpu.web.board.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kpu.web.board.domain.FavcontentsVO;

public class FavcontentsDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost/jspdb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";

	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "jspbook", "passwd");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean favadd(String id, String menu, String pkid, String bbsConfigFK, String title) {
		connect();
		String sql = "insert into favcontents(id, menu, pkid, bbsConfigFK, title) values (?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, menu);
			pstmt.setString(3, pkid);
			pstmt.setString(4, bbsConfigFK);
			pstmt.setString(5, title);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}

		return true;
	}
	
	public boolean favdel(String id, String menu, String pkid, String bbsConfigFK) {
		connect();
		String sql = "delete from favcontents where id=? and menu=? and pkid=? and bbsConfigFK=?";
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, menu);
			pstmt.setString(3, pkid);
			pstmt.setString(4, bbsConfigFK);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}

		return true;
	}
	
	public boolean favchk(String id, String menu, String pkid, String bbsConfigFK) {
		connect();
		String sql = "select * from favcontents where id=? and menu=? and pkid=? and bbsConfigFK=?";
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, menu);
			pstmt.setString(3, pkid);
			pstmt.setString(4, bbsConfigFK);

			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}

		return false;
	}
	
	public ArrayList<FavcontentsVO> getfavlist(String id) {
		connect();
		ArrayList<FavcontentsVO> favlist = new ArrayList<FavcontentsVO>();
		String sql = "select * from favcontents where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FavcontentsVO vo = new FavcontentsVO();
				vo.setId(rs.getString("id"));
				vo.setMenu(rs.getString("menu"));
				vo.setPkid(rs.getString("pkid"));
				vo.setBbsConfigFK(rs.getString("bbsConfigFK"));
				vo.SetTitle(rs.getString("title"));
				favlist.add(0, vo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		return favlist;
	}
}
