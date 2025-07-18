package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.util.JDBCDataSource;

public class UserModel {

	public void add(UserBean bean) throws Exception {
		UserBean exist = findByLogin(bean.getLogin());
		if(exist!=null) {
			throw new Exception("User already Presant");
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, getNextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getLogin());
			pstmt.setString(5, bean.getPassword());
			pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setLong(8, bean.getRoleId());
			pstmt.setString(9, bean.getGender());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDateTime());
			pstmt.setTimestamp(13, bean.getModifiedDateTime());
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("New User data Inserted " + i);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(UserBean bean) throws Exception {
		UserBean exist = findByLogin(bean.getLogin());
		if(exist!=null && exist.getId() == bean.getId()) {
			throw new Exception("User Login id already Presant");
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_user set first_name = ?,last_name = ?, login = ?, "
					+ "password  = ?, dob = ?, mobile_no = ?, role_id = ?, gender = ?, created_by = ?, "
					+ "modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getLogin());
			pstmt.setString(4, bean.getPassword());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setLong(7, bean.getRoleId());
			pstmt.setString(8, bean.getGender());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDateTime());
			pstmt.setTimestamp(12, bean.getModifiedDateTime());
			pstmt.setLong(13, bean.getId());
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("User data Updated " + i);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public void delete(Long id) throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("User data Deleted " + i);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public UserBean findByPk(Long id) throws Exception {
		Connection conn = null;
		UserBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs  = pstmt.executeQuery();
			while(rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
			}
			pstmt.close();
			rs.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	public UserBean findByLogin(String login) throws Exception {
		Connection conn = null;
		UserBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user where login = ?");
			pstmt.setString(1, login);
			ResultSet rs  = pstmt.executeQuery();
			while(rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
			}
			pstmt.close();
			rs.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	public List<UserBean> search(UserBean bean,int pageNo,int pageSize) throws Exception {
		Connection conn = null;
		List<UserBean> list = null;
		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("select * from st_user where 1=1 ");
			if (bean != null) {
				if (bean.getId() != null) {
					sql.append("and id = "+bean.getId()+" ");
				}
				if (bean.getFirstName() != null && bean.getFirstName().length()>0) {
					sql.append("and first_name like '"+bean.getFirstName()+"%' ");
				}
				if (bean.getLastName() != null && bean.getLastName().length()>0) {
					sql.append("and last_name like '"+bean.getLastName()+"%' ");
				}
				if (bean.getLogin() != null && bean.getLogin().length()>0) {
					sql.append("and login like '"+bean.getLogin()+"%' ");
				}
				if (bean.getPassword() != null && bean.getPassword().length()>0) {
					sql.append("and password like '"+bean.getPassword()+"%' ");
				}
				if(bean.getDob()!=null) {
					sql.append("and dob like '"+new java.sql.Date(bean.getDob().getTime())+"%' ");
				}
				if (bean.getMobileNo() != null && bean.getMobileNo().length()>0) {
					sql.append("and mobile_no like '"+bean.getMobileNo()+"%' ");
				}
				if (bean.getRoleId() != null) {
					sql.append("and role_id = "+bean.getRoleId()+" ");
				}
				if (bean.getGender() != null && bean.getGender().length()>0) {
					sql.append("and gender like '"+bean.getGender()+"%' ");
				}
				if (bean.getCreatedBy() != null && bean.getCreatedBy().length()>0) {
					sql.append("and created_by like '"+bean.getCreatedBy()+"%' ");
				}
				if (bean.getModifiedBy() != null && bean.getModifiedBy().length()>0) {
					sql.append("and modified_by like '"+bean.getModifiedBy()+"%' ");
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append("limit " + pageNo + "," + pageSize);
			}
			System.out.println(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs  = pstmt.executeQuery();
			list = new ArrayList<UserBean>();
			while(rs.next()) {
				bean = new UserBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setLogin(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setDob(rs.getDate(6));
				bean.setMobileNo(rs.getString(7));
				bean.setRoleId(rs.getLong(8));
				bean.setGender(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
				list.add(bean);
			}
			pstmt.close();
			rs.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
	
	public void userTableMetadeta() throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_user");
			ResultSet rs  = pstmt.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			System.out.println("Catelog Name : "+rsMetaData.getCatalogName(1));
			System.out.println("Table Name : "+rsMetaData.getTableName(1));
			System.out.println("Column Count  : "+rsMetaData.getColumnCount());
			System.out.println();
			for(int i = 1;i<=rsMetaData.getColumnCount();i++) {
				System.out.println("Column : "+ i);
				System.out.println("Label : "+ rsMetaData.getColumnLabel(i));
				System.out.println("Name : "+ rsMetaData.getColumnName(i));
				System.out.println("Type : "+ rsMetaData.getColumnTypeName(i));
				System.out.println("Size : "+ rsMetaData.getColumnDisplaySize(i));
				System.out.println("Precision : "+ rsMetaData.getPrecision(i));
				System.out.println();
			}
			pstmt.close();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public Long getNextPk() throws SQLException {
		Long pk = 0l;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_user");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getLong(1);
		}
		JDBCDataSource.closeConnection(conn, pstmt);
		return pk + 1;
	}
}
