package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.bean.RoleBean;
import in.co.rays.util.JDBCDataSource;

public class RoleModel {
	public void add(RoleBean bean) throws Exception {
		Connection conn = null;
		RoleBean exist = findByRoleName(bean.getName());
		if(exist!=null) {
			throw new Exception("Role already Presant");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_role values(?,?,?,?,?,?,?)");
			pstmt.setLong(1, getNextPk());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDateTime());
			pstmt.setTimestamp(7, bean.getModifiedDateTime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("New Role Inserted " + i);
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void update(RoleBean bean) throws Exception {
		RoleBean exist = findByRoleName(bean.getName());
		if(exist!=null && exist.getId() == bean.getId()) {
			throw new Exception("Role already Presant");
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update st_role set name = ?,"
					+ " description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ?"
					+ " where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDateTime());
			pstmt.setTimestamp(6, bean.getModifiedDateTime());
			pstmt.setLong(7, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Role Date Updated  " + i);
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
			PreparedStatement pstmt = conn.prepareStatement("delete from st_role where id = ?");
			pstmt.setLong(1, id);
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("Role Data deleted  " + i);
			pstmt.close();
		} catch (Exception e) {
			conn.rollback();
			System.out.println(e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public RoleBean findByPk(Long id) throws Exception {
		Connection conn = null;
		RoleBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_role where id = ?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
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

	public RoleBean findByRoleName(String roleName) throws Exception {
		Connection conn = null;
		RoleBean bean = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_role where name = ?");
			pstmt.setString(1, roleName);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
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

	public List<RoleBean> search(RoleBean bean, int pageNo, int pageSize) throws Exception {
		Connection conn = null;
		List<RoleBean> list = null;
		try {
			conn = JDBCDataSource.getConnection();
			StringBuffer sql = new StringBuffer("select * from st_role where 1=1 ");
			if (bean != null) {
				if (bean.getId() != null) {
					sql.append("and id = "+bean.getId()+" ");
				}
				if (bean.getName() != null && bean.getName().length()>0) {
					sql.append("and name like '"+bean.getName()+"%' ");
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
			list = new ArrayList<RoleBean>();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDateTime(rs.getTimestamp(6));
				bean.setModifiedDateTime(rs.getTimestamp(7));
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

	public void roleTableMetadeta() throws Exception {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select * from st_role");
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
		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_role");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			pk = rs.getLong(1);
		}
		JDBCDataSource.closeConnection(conn, pstmt);
		return pk + 1;
	}

}
