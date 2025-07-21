package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.util.JDBCDataSource;

public class StudentModel {
	public void add(StudentBean bean) throws Exception {
		StudentBean exist = findByEmail(bean.getEmail());
		if(exist!=null) {
			throw new Exception("Student already Presant");
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_student values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, getNextPk());
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			
			CollegeModel cModel = new CollegeModel();
			CollegeBean clgBean = cModel.findByPk(bean.getCollegeId());
			if(clgBean!=null) {
				pstmt.setLong(8, bean.getCollegeId());
				pstmt.setString(9, clgBean.getName());
			}
			else {
				throw new Exception("College with this id does not exist");
			}
			
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDateTime());
			pstmt.setTimestamp(13, bean.getModifiedDateTime());
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("New Student data Inserted " + i);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(StudentBean bean) throws Exception {
		StudentBean exist = findByEmail(bean.getEmail());
		if(exist!=null && exist.getId() == bean.getId()) {
			throw new Exception("User Login id already Presant");
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_student set first_name = ?,last_name = ?, dob = ?, gender = ?, mobile_no = ?, email = ?,"
					+ "college_id  = ?, college_name = ?, created_by = ?, modified_by = ?,"
					+ " created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getEmail());
			pstmt.setLong(7, bean.getCollegeId());
			CollegeModel cModel = new CollegeModel();
			CollegeBean clgBean = cModel.findByPk(bean.getCollegeId());
			if(clgBean!=null) {
				pstmt.setString(8, clgBean.getName());
			}
			else {
				throw new Exception("College with this id does not exist");
			}
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDateTime());
			pstmt.setTimestamp(12, bean.getModifiedDateTime());
			pstmt.setLong(13, bean.getId());
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Student data Updated " + i);
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
			PreparedStatement pstmt = conn.prepareStatement("delete from st_student where id = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Student data Deleted " + i);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public StudentBean findByPk(Long id) throws Exception {
		Connection conn = null;
		StudentBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_student where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs  = pstmt.executeQuery();
			while(rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
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
	
	public StudentBean findByEmail(String email) throws Exception {
		Connection conn = null;
		StudentBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_student where email = ?");
			pstmt.setString(1, email);
			ResultSet rs  = pstmt.executeQuery();
			while(rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
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
	
	public List<StudentBean> search(StudentBean bean,int pageNo,int pageSize) throws Exception {
		Connection conn = null;
		List<StudentBean> list = null;
		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("select * from st_student where 1=1 ");
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
				if(bean.getDob()!=null) {
					sql.append("and dob like '"+new java.sql.Date(bean.getDob().getTime())+"%' ");
				}
				if (bean.getGender() != null && bean.getGender().length()>0) {
					sql.append("and gender like '"+bean.getGender()+"%' ");
				}
				if (bean.getMobileNo() != null && bean.getMobileNo().length()>0) {
					sql.append("and mobile_no like '"+bean.getMobileNo()+"%' ");
				}
				if (bean.getEmail() != null && bean.getEmail().length()>0) {
					sql.append("and email like '"+bean.getEmail()+"%' ");
				}
				if (bean.getCollegeId() != null) {
					sql.append("and college_id = "+bean.getCollegeId()+" ");
				}
				if (bean.getCollegeName() != null && bean.getCollegeName().length()>0) {
					sql.append("and college_name like '"+bean.getCollegeName()+"%' ");
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
			list = new ArrayList<StudentBean>();
			while(rs.next()) {
				bean = new StudentBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setGender(rs.getString(5));
				bean.setMobileNo(rs.getString(6));
				bean.setEmail(rs.getString(7));
				bean.setCollegeId(rs.getLong(8));
				bean.setCollegeName(rs.getString(9));
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
	
	public void studentTableMetadeta() throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_student");
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
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_student");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getLong(1);
		}
		JDBCDataSource.closeConnection(conn, pstmt);
		return pk + 1;
	}

}
