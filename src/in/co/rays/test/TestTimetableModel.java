package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.TimetableBean;
import in.co.rays.model.TimetableModel;

public class TestTimetableModel {
	public static void main(String[] args) throws Exception {
		testAddTimetable();
		 //testUpdateTimetable();
		 //testDeleteTimetable();
		 //testFindByPk();
		 //testSearchTimetable();
		 //testDisplayTimetableTableInfo();
	}

	public static void testAddTimetable() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		TimetableModel model = new TimetableModel();
		TimetableBean bean = new TimetableBean();
		bean.setSemester("Uday");
		bean.setDescription("Sharma");
		bean.setExamdate(sdf.parse("03-09-1999"));
		bean.setExamTime("2 hours");
		bean.setCourseId(1l);
		bean.setSubjectId(1l);
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void testUpdateTimetable() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		TimetableModel model = new TimetableModel();
		TimetableBean bean = new TimetableBean();
		bean.setId(2l);
		bean.setSemester("Uday");
		bean.setDescription("Sharma");
		bean.setExamdate(sdf.parse("03-09-1999"));
		bean.setExamTime("2 hours");
		bean.setCourseId(1l);
		bean.setSubjectId(1l);
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	public static void testDeleteTimetable() throws Exception {
		TimetableModel model = new TimetableModel();
		model.delete(2l);
	}

	public static void testFindByPk() throws Exception {
		TimetableModel model = new TimetableModel();
		TimetableBean bean = model.findByPk(1l);
		System.out.print("\t" + bean.getId());
		System.out.print("\t" + bean.getSemester());
		System.out.print("\t" + bean.getDescription());
		System.out.print("\t" + bean.getExamdate());
		System.out.print("\t" + bean.getExamTime());
		System.out.print("\t" + bean.getCourseId());
		System.out.print("\t" + bean.getCourseName());
		System.out.print("\t" + bean.getSubjectId());
		System.out.print("\t" + bean.getSubjectName());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());
	}


	public static void testSearchTimetable() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		TimetableModel model = new TimetableModel();
		TimetableBean testBean = new TimetableBean();
		testBean.setId(4l);
		testBean.setSemester("Uday");
		testBean.setDescription("Sharma");
		testBean.setExamdate(sdf.parse("03-09-1999"));
		testBean.setExamTime("2 hours");
		testBean.setCourseId(1l);
		testBean.setSubjectId(2l);
		testBean.setCreatedBy("Aniket");
		testBean.setModifiedBy("Aniket");
		List<TimetableBean> list = model.search(null, 1, 5);
		if(list.size()>0) {
			Iterator<TimetableBean> it = list.iterator();
			TimetableBean bean = null;
			while(it.hasNext()) {
				bean = it.next();
				System.out.print("\t" + bean.getId());
				System.out.print("\t" + bean.getSemester());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getExamdate());
				System.out.print("\t" + bean.getExamTime());
				System.out.print("\t" + bean.getCourseId());
				System.out.print("\t" + bean.getCourseName());
				System.out.print("\t" + bean.getSubjectId());
				System.out.print("\t" + bean.getSubjectName());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDateTime());
				System.out.println("\t" + bean.getModifiedDateTime());
			}
		}
	}

	public static void testDisplayTimetableTableInfo() throws Exception {
		TimetableModel model = new TimetableModel();
		model.TimetableTableMetadeta();
	}
}
