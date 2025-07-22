package in.co.rays.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.MarksheetBean;
import in.co.rays.model.MarksheetModel;

public class TestMarksheetModel {
	public static void main(String[] args) throws Exception {
		//addMarksheet();
		// updateMarksheet();
		// deleteMarksheet();
		// findByPk();
		//findByRollNo();
		 //search();
	}

	public static void addMarksheet() throws Exception {
		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();
		bean.setStudentId(1l);
		bean.setRollNo("101");
		bean.setPhysics(78);
		bean.setChemistry(89);
		bean.setMaths(76);
		bean.setCreatedBy("Rohan");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void updateMarksheet() throws Exception {
		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = new MarksheetBean();
		bean.setId(1l);
		bean.setStudentId(1l);
		bean.setRollNo("181");
		bean.setName("Aniket Rajput");
		bean.setPhysics(78);
		bean.setChemistry(89);
		bean.setMaths(76);
		bean.setCreatedBy("Rohan");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	public static void deleteMarksheet() throws ClassNotFoundException, SQLException {
		MarksheetModel model = new MarksheetModel();
		model.delete(2l);
	}

	public static void findByPk() throws ClassNotFoundException, SQLException {
		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = model.findByPk(1l);
		System.out.println(bean);
	}

	public static void findByRollNo() throws ClassNotFoundException, SQLException {
		MarksheetModel model = new MarksheetModel();
		MarksheetBean bean = model.findByRollNo("101");
		System.out.println(bean);
	}

	public static void search() throws ClassNotFoundException, SQLException {
		MarksheetModel model = new MarksheetModel();
		List<MarksheetBean> list = model.search(null, 1, 5);
		if (list.size() > 0) {
			MarksheetBean bean = null;
			Iterator<MarksheetBean> it = list.iterator();
			if (it.hasNext()) {
				bean = it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getRollNo());
				System.out.print("\t" + bean.getStudentId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getPhysics());
				System.out.print("\t" + bean.getChemistry());
				System.out.print("\t" + bean.getMaths());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDateTime());
				System.out.println("\t" + bean.getModifiedDateTime());
			}
		}

	}
}
