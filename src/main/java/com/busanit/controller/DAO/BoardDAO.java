package com.busanit.controller.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.busanit.controller.DTO.BoardVO;
import com.busanit.controller.DTO.JoinVO;

import util.DBManager;

public class BoardDAO {
	private BoardDAO() {
	}
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;	
	}
	public List<JoinVO> selectIdBoards() {
		String sql = "SELECT id,pw FROM joinMember ORDER BY NUM DESC";
	      
	      List<JoinVO> list = new ArrayList<JoinVO>();
	      Connection conn = null;
	      Statement stmt = null;
	      ResultSet rs = null;
	      
	      try {
	         conn = DBManager.getConnection();
	         stmt = conn.createStatement();
	         
	         rs = stmt.executeQuery(sql);
	         
	         while(rs.next()) {
	            JoinVO jVo = new JoinVO();
	            
	            jVo.setId(rs.getString("id"));
	            jVo.setPw(rs.getString("pw"));
	            
	            
	            
	            list.add(jVo);
	         }
	      }catch(SQLException | ClassNotFoundException e) {
	    	  e.printStackTrace();
	      }finally {
	    	  DBManager.close(conn, stmt, rs);
	      }
	      return list;
	}
	// 게시판의 모든 내용 불러오기
	public List<BoardVO> selectAllBoards() {
		String sql = "SELECT * FROM recipeboard ORDER BY NUM DESC";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setUserId(rs.getString("id"));
				bVo.setNick(rs.getString("nick"));
				bVo.setLevel(rs.getString("level"));
				bVo.setImage(rs.getString("image"));
				bVo.setFood(rs.getString("food"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
				list.add(bVo);
			}
		} catch (Exception e) {
			e.printStackTrace();		
		} finally {
			DBManager.close(conn, stmt, rs);
		}
		return list;
	}
	
	// 게시글 입력
	public void insertBoard(BoardVO bVo) {
		String sql = "INSERT INTO recipeboard(ID, NICK, FOOD, LEVEL, IMAGE, TITLE, CONTENT) " 
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  bVo.getUserId());
			pstmt.setString(2,  bVo.getNick());
			pstmt.setString(3,  bVo.getFood());
			pstmt.setString(4,  bVo.getLevel());
			pstmt.setString(5,  bVo.getImage());
			pstmt.setString(6,  bVo.getTitle());
			pstmt.setString(7,  bVo.getContent());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 조회수 업데이트
	public void updateReadCount(String num) {
		String sql = "UPDATE recipeboard SET READCOUNT = READCOUNT + 1 WHERE NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 게시판 글 상세 내용 보기 :글번호로 찾아옴. :실패 null
	public BoardVO selectOneBoardByNum(String num) {
		String sql = "SELECT * FROM recipeboard WHERE NUM = ?";
		
		BoardVO bVo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setNick(rs.getString("nick"));
				bVo.setUserId(rs.getString("id"));
				bVo.setFood(rs.getString("food"));
				bVo.setLevel(rs.getString("level"));
				bVo.setImage(rs.getString("image"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return bVo;
	}
	
	// 게시글 업데이트
	public void updateBoard(BoardVO bVo) {
		String sql = "UPDATE recipeboard SET NICK = ?, Level = ?, IMAGE = ?, FOOD = ?, TITLE = ?, CONTENT = ? "
				+ "WHERE NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  bVo.getNick());
			pstmt.setString(2,  bVo.getLevel());
			pstmt.setString(3,	bVo.getImage());
			pstmt.setString(4,  bVo.getFood());
			pstmt.setString(5,  bVo.getTitle());
			pstmt.setString(6,  bVo.getContent());
			pstmt.setInt(7, bVo.getNum());
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	// 게시글 비밀번호 check
	public BoardVO checkPassword(String pass, String num) {
		String sql = "SELECT * FROM recipeboard WHERE PASS = ? AND NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO bVo = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pass);
			pstmt.setString(2, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bVo = new BoardVO();
				
				bVo.setNum(rs.getInt("num"));
				bVo.setFood(rs.getString("food"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 		
		return bVo;
	}
	
	// 게시글 삭제
	public void deleteBoard(String num) {
		String sql = "DELETE FROM recipeboard WHERE NUM = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 게시글 필터 정렬
	public String selectFilter(String filterType) {
		String sql="";
		if(filterType != null) {
			switch(filterType) {
			case "numDESC": sql = "NUM DESC "; break;
			case "readcountDESC": sql = "READCOUNT DESC "; break;
			case "numASC": sql = "NUM ASC "; break;
			default : sql="NUM DESC ";
			}
		} else {
			sql="NUM DESC ";
		}
		return sql;
	}
	
	// 게시글 페이지 리스트 조회
	public List<BoardVO> selectBoardsPaging(int offset, int pageSize, String searchType, String searchText, String filterType) {
		String sql = "";
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("searchType : "+searchType);
		System.out.println("filterType : "+filterType);
		try {
			conn = DBManager.getConnection();
			
			if(searchType != null && searchText.length() > 0) {
				if(searchType.equals("all")) {	// 검색타입(제목+내용)
					sql = "SELECT * "
						+ "  FROM recipeboard "
						+ " WHERE TITLE LIKE ? OR CONTENT LIKE ? "
						+ "ORDER BY "+selectFilter(filterType)
						+ "LIMIT ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchText + "%");
					pstmt.setString(2, "%" + searchText + "%");
					pstmt.setInt(3, offset);
					pstmt.setInt(4, pageSize);
				} else {
					switch(searchType) {
						case "title" :		// 검색타입(제목)
							sql = "SELECT * "
								+ "  FROM recipeboard "
								+ " WHERE TITLE LIKE ? "
								+ "ORDER BY "+selectFilter(filterType) 
								+ "LIMIT ?, ?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, "%" + searchText + "%");
							pstmt.setInt(2, offset);
							pstmt.setInt(3, pageSize);
							break;
						case "content" :	// 검색타입(내용)
							sql = "SELECT * "
								+ "  FROM recipeboard "
								+ " WHERE CONTENT LIKE ? "
								+ "ORDER BY "+selectFilter(filterType)
								+ "LIMIT ?, ?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, "%" + searchText + "%");
							pstmt.setInt(2, offset);
							pstmt.setInt(3, pageSize);
							break;
						case "author" :		// 검색타입(작성자)
							sql = "SELECT * "
								+ "  FROM recipeboard "
								+ " WHERE NICK LIKE ? "
								+ "ORDER BY "+selectFilter(filterType)
								+ "LIMIT ?, ?";
							pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, "%" + searchText + "%");
							pstmt.setInt(2, offset);
							pstmt.setInt(3, pageSize);
							break;
					}
				}
			} else {	// 검색어를 입력하지 않았을 때(전체 목록)
				sql = "SELECT * "
					+ "  FROM recipeboard "
					+ "ORDER BY "+selectFilter(filterType)
					+ "LIMIT ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, offset);
				pstmt.setInt(2, pageSize);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setUserId(rs.getString("id"));
				bVo.setNum(rs.getInt("num"));
				bVo.setNick(rs.getString("nick"));
				bVo.setFood(rs.getString("food"));
				bVo.setLevel(rs.getString("Level"));
				bVo.setImage(rs.getString("image"));
				bVo.setTitle(rs.getString("title"));
				bVo.setContent(rs.getString("content"));
				bVo.setReadcount(rs.getInt("readcount"));
				bVo.setWritedate(rs.getTimestamp("writedate"));
				
				list.add(bVo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}

	// 전체 게시글 수
	public int selectAllBoardsCount(String searchType, String searchText) {
		String sql = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int boardCnt = 0;
		
		try {
			conn = DBManager.getConnection();
			
			if(searchType != null && searchText.length() > 0) {
				if(searchType.equals("all")) {	// 검색타입(제목+내용)
					sql = "SELECT COUNT(*) FROM recipeboard WHERE TITLE LIKE ? OR CONTENT LIKE ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchText + "%");
					pstmt.setString(2, "%" + searchText + "%");
				} else {
					switch(searchType) {
						case "title" :		// 검색타입(제목)
							sql = "SELECT COUNT(*) FROM recipeboard WHERE TITLE LIKE ?";
							break;
						case "content" :	// 검색타입(내용)
							sql = "SELECT COUNT(*) FROM recipeboard WHERE CONTENT LIKE ?";
							break;
						case "author" :		// 검색타입(작성자)
							sql = "SELECT COUNT(*) FROM recipeboard WHERE NICK LIKE ?";
							break;
					}
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%" + searchText + "%");
				}
			} else {	// 검색어를 입력하지 않았을 때(전체 목록)
				sql = "SELECT COUNT(*) FROM recipeboard";
				pstmt = conn.prepareStatement(sql);
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardCnt = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return boardCnt;
	}
	
	public ArrayList<BoardVO> imageFileNameFinder(String count) {
		String sql;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<>();
		String c = count;
		
		try {
			conn = DBManager.getConnection();
			
			sql = "SELECT num, image, food FROM recipeboard WHERE image is not null ORDER BY num DESC limit 0, "+c+";";
			pstmt = conn.prepareStatement(sql);
					
				
		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bVo = new BoardVO();
				bVo.setNum(rs.getInt("num"));
				bVo.setImage(rs.getString("image"));
				bVo.setFood(rs.getString("food"));
				list.add(bVo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		
		return list;
	}

	
	
	
}









