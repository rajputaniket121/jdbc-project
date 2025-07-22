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
import in.co.rays.bean.TimetableBean;
import in.co.rays.util.JDBCDataSource;

public class TimetableModel {
	public void add(TimetableBean bean) throws Exception {
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());
		
		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subBean = subjectModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subBean.getName());
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_timetable values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, getNextPk());
			pstmt.setString(2, bean.getSemester());
			pstmt.setString(3, bean.getDescription());
			pstmt.setDate(4, new java.sql.Date(bean.getExamdate().getTime()));
			pstmt.setString(5, bean.getExamTime());
			pstmt.setLong(6, bean.getCourseId());
			pstmt.setString(7, bean.getCourseName());
			pstmt.setLong(8, bean.getSubjectId());
			pstmt.setString(9, bean.getSubjectName());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDateTime());
			pstmt.setTimestamp(13, bean.getModifiedDateTime());
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("New Timetable data Inserted " + i);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(TimetableBean bean) throws Exception {
		CourseModel courseModel = new CourseModel();
		CourseBean courseBean = courseModel.findByPk(bean.getCourseId());
		bean.setCourseName(courseBean.getName());
		
		SubjectModel subjectModel = new SubjectModel();
		SubjectBean subBean = subjectModel.findByPk(bean.getSubjectId());
		bean.setSubjectName(subBean.getName());
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_timetable set semester = ?,description = ?, exam_date = ?, exam_time = ?, course_id  = ?, course_name = ?, subject_id  = ?, subject_name = ?, created_by = ?, modified_by = ?,"
							+ " created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getSemester());
			pstmt.setString(2, bean.getDescription());
			pstmt.setDate(3, new java.sql.Date(bean.getExamdate().getTime()));
			pstmt.setString(4, bean.getExamTime());
			pstmt.setLong(5, bean.getCourseId());
			pstmt.setString(6, bean.getCourseName());
			pstmt.setLong(7, bean.getSubjectId());
			pstmt.setString(8, bean.getSubjectName());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDateTime());
			pstmt.setTimestamp(12, bean.getModifiedDateTime());
			pstmt.setLong(13, bean.getId());
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Timetable data Updated " + i);
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
			PreparedStatement pstmt = conn.prepareStatement("delete from st_timetable where id = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			pstmt.close();
			System.out.println("Timetable data Deleted " + i);
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public TimetableBean findByPk(Long id) throws Exception {
		Connection conn = null;
		TimetableBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_timetable where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setSemester(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setExamdate(rs.getDate(4));
				bean.setExamTime(rs.getString(5));
				bean.setCourseId(rs.getLong(6));
				bean.setCourseName(rs.getString(7));
				bean.setSubjectId(rs.getLong(8));
				bean.setSubjectName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
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

	public List<TimetableBean> search(TimetableBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = null;
		List<TimetableBean> list = null;
		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("select * from st_timetable where 1=1 ");
			if (bean != null) {
				if (bean.getId() != null) {
					sql.append("and id = " + bean.getId() + " ");
				}
				if (bean.getSemester() != null && bean.getSemester().length() > 0) {
					sql.append("and semester like '" + bean.getSemester() + "%' ");
				}
				if (bean.getDescription() != null && bean.getDescription().length() > 0) {
					sql.append("and description like '" + bean.getDescription() + "%' ");
				}
				if (bean.getExamdate() != null) {
					sql.append("and exam_date like '" + new java.sql.Date(bean.getExamdate().getTime()) + "%' ");
				}
				if (bean.getExamTime() != null && bean.getExamTime().length() > 0) {
					sql.append("and exam_time like '" + bean.getExamTime() + "%' ");
				}
				if (bean.getCourseId() != null) {
					sql.append("and course_id = " + bean.getCourseId() + " ");
				}
				if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
					sql.append("and course_name like '" + bean.getCourseName() + "%' ");
				}
				if (bean.getSubjectId() != null) {
					sql.append("and subject_id = " + bean.getSubjectId() + " ");
				}
				if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
					sql.append("and subject_name like '" + bean.getSubjectName() + "%' ");
				}
				if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
					sql.append("and created_by like '" + bean.getCreatedBy() + "%' ");
				}
				if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
					sql.append("and modified_by like '" + bean.getModifiedBy() + "%' ");
				}
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append("limit " + pageNo + "," + pageSize);
			}
			System.out.println(sql);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			list = new ArrayList<TimetableBean>();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setSemester(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setExamdate(rs.getDate(4));
				bean.setExamTime(rs.getString(5));
				bean.setCourseId(rs.getLong(6));
				bean.setCourseName(rs.getString(7));
				bean.setSubjectId(rs.getLong(8));
				bean.setSubjectName(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
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

	public void TimetableTableMetadeta() throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_timetable");
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
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_timetable");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getLong(1);
		}
		JDBCDataSource.closeConnection(conn, pstmt);
		return pk + 1;
	}
}
