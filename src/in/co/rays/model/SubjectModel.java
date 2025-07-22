package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.bean.SubjectBean;
import in.co.rays.util.JDBCDataSource;

public class SubjectModel {
	public void add(SubjectBean bean) throws Exception {
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());
		
		Connection conn = null;
		SubjectBean exist = findBySubjectName(bean.getName());
		if(exist!=null) {
			throw new Exception("subject already Presant");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_subject values(?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, getNextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCourseName());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDateTime());
			pstmt.setTimestamp(9, bean.getModifiedDateTime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("New subject Inserted " + i);
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(SubjectBean bean) throws Exception {
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());
		
		SubjectBean exist = findBySubjectName(bean.getName());
		if(exist!=null && exist.getId() == bean.getId()) {
			throw new Exception("subject already Presant");
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update st_subject set name = ?, course_id = ?, course_name = ?"
					+ ", description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ?"
					+ " where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDateTime());
			pstmt.setTimestamp(8, bean.getModifiedDateTime());
			pstmt.setLong(9, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Subject Data Updated  " + i);
			pstmt.close();
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
			PreparedStatement pstmt = conn.prepareStatement("delete from st_subject where id = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("subject Data deleted  " + i);
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public SubjectBean findByPk(Long id) throws Exception {
		Connection conn = null;
		SubjectBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_subject where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDateTime(rs.getTimestamp(8));
				bean.setModifiedDateTime(rs.getTimestamp(9));
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

	public SubjectBean findBySubjectName(String subjectName) throws Exception {
		Connection conn = null;
		SubjectBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_subject where name = ?");
			pstmt.setString(1, subjectName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDateTime(rs.getTimestamp(8));
				bean.setModifiedDateTime(rs.getTimestamp(9));
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

	public List<SubjectBean> search(SubjectBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = null;
		List<SubjectBean> list = null;
		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("select * from st_subject where 1=1 ");
			if (bean != null) {
				if (bean.getId() != null) {
					sql.append("and id = "+bean.getId()+" ");
				}
				if (bean.getName() != null && bean.getName().length()>0) {
					sql.append("and name like '"+bean.getName()+"%' ");
				}
				if (bean.getCourseId() != null) {
					sql.append("and course_id = "+bean.getCourseId()+" ");
				}
				if (bean.getCourseName() != null && bean.getCourseName().length()>0) {
					sql.append("and course_name like '"+bean.getCourseName()+"%' ");
				}
				if (bean.getDescription() != null && bean.getDescription().length()>0) {
					sql.append("and description like '"+bean.getDescription()+"%' ");
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
			list = new ArrayList<SubjectBean>();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setCourseId(rs.getLong(3));
				bean.setCourseName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDateTime(rs.getTimestamp(8));
				bean.setModifiedDateTime(rs.getTimestamp(9));
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

	public void subjectTableMetadeta() throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_subject");
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsMetaData = rs.getMetaData();
			System.out.println("Catelog Name : " + rsMetaData.getCatalogName(1));
			System.out.println("Table Name : " + rsMetaData.getTableName(1));
			System.out.println("Column Count  : " + rsMetaData.getColumnCount());
			System.out.println();
			for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {
				System.out.println("Column : " + i);
				System.out.println("Label : " + rsMetaData.getColumnLabel(i));
				System.out.println("Name : " + rsMetaData.getColumnName(i));
				System.out.println("Type : " + rsMetaData.getColumnTypeName(i));
				System.out.println("Size : " + rsMetaData.getColumnDisplaySize(i));
				System.out.println("Precision : " + rsMetaData.getPrecision(i));
				System.out.println();
			}
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public Long getNextPk() throws SQLException {
		Long pk = 0l;
		Connection conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_subject");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getLong(1);
		}
		JDBCDataSource.closeConnection(conn, pstmt);
		return pk + 1;
	}

}
