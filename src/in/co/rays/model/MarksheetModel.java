package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.bean.StudentBean;
import in.co.rays.util.JDBCDataSource;

public class MarksheetModel {
	public void add(MarksheetBean bean) throws Exception{
		
		StudentModel stuModel = new StudentModel();
		StudentBean stuBean = stuModel.findByPk(bean.getStudentId());
		bean.setName(stuBean.getFirstName()+" "+stuBean.getLastName());
		
		Connection conn = null;
		MarksheetBean exist = findByRollNo(bean.getRollNo());
		if(exist!=null) {
			throw new Exception("Marksheet Data already Presant");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_marksheet values(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, getNextPk());
			pstmt.setString(2,bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, bean.getName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDateTime());
			pstmt.setTimestamp(11, bean.getModifiedDateTime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("New Marksheet data Inserted " + i);
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public void update(MarksheetBean bean) throws Exception{
		StudentModel stuModel = new StudentModel();
		StudentBean stuBean = stuModel.findByPk(bean.getStudentId());
		bean.setName(stuBean.getFirstName()+" "+stuBean.getLastName());
		
		MarksheetBean exist = findByRollNo(bean.getRollNo());
		if(exist!=null && exist.getId() == bean.getId()) {
			throw new Exception("Marksheet already Presant");
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update st_marksheet set roll_no = ? ,student_id = ? ,name = ? , physics = ?, chemistry = ?, maths = ?"
					+ ", created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1,bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			pstmt.setString(3, bean.getName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDateTime());
			pstmt.setTimestamp(10, bean.getModifiedDateTime());
			pstmt.setLong(11, bean.getId());
			int i = pstmt.executeUpdate();
			if(i>0) {
				System.out.println("data updated for "+bean.getName()+" "+bean.getId());
			}
			pstmt.close();
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public void delete(Long id) throws SQLException,ClassNotFoundException{
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_marksheet where id = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Marksheet Data deleted  " + i);
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
	
	public MarksheetBean findByPk(Long id) throws SQLException,ClassNotFoundException {
		Connection conn = null;
		MarksheetBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_marksheet where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDateTime(rs.getTimestamp(10));
				bean.setModifiedDateTime(rs.getTimestamp(11));
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	public MarksheetBean findByRollNo(String name) throws SQLException,ClassNotFoundException{
		Connection conn = null;
		MarksheetBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_marksheet where roll_no = ?");
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDateTime(rs.getTimestamp(10));
				bean.setModifiedDateTime(rs.getTimestamp(11));
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
	
	public List<MarksheetBean> search(MarksheetBean bean,int pageNo,int pageSize) throws SQLException,ClassNotFoundException{
		Connection conn = null;
		List<MarksheetBean> list = null;
		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("select * from st_marksheet where 1=1 ");
			if(bean!=null) {
				if(bean.getId()>0) {
					sql.append("and id = "+bean.getId());
				}
				if(bean.getRollNo()!=null && bean.getRollNo().length()>0) {
					sql.append("and roll_no = "+bean.getRollNo());
				}
				if(bean.getStudentId()>0) {
					sql.append("and student_id = "+bean.getStudentId());
				}
				if(bean.getName()!=null && bean.getName().length()>0) {
					sql.append("and name like '"+bean.getName()+"%'");
				}
				if(bean.getPhysics()>0) {
					sql.append("and physics = "+bean.getPhysics());
				}
				if(bean.getChemistry()>0) {
					sql.append("and chemistry = "+bean.getChemistry());
				}
				if(bean.getMaths()>0) {
					sql.append("and maths = "+bean.getMaths());
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
			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<MarksheetBean>();
			while (rs.next()) {
				bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDateTime(rs.getTimestamp(10));
				bean.setModifiedDateTime(rs.getTimestamp(11));
				list.add(bean);
			}
			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
	
	public static Long getNextPk() throws ClassNotFoundException ,SQLException{
		long pk = 0;
		
		Connection conn = JDBCDataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_marksheet");
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			pk = rs.getInt(1)+1;
		}
		
		return pk;
	}

}
