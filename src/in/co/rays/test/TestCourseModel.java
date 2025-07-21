package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CourseBean;
import in.co.rays.model.CourseModel;

public class TestCourseModel {
	public static void main(String[] args) throws Exception {
		 //testAddCourse();
		// testUpdateCourse();
		 //testdeleteCourse();
		// testFindByPk();
		 //testFindByCourseName();
		//testSearchCourse();
		// testDisplayTableInfo();
	}

	public static void testAddCourse() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		bean.setName("maths");
		bean.setDuration("5 hours");
		bean.setDescription("Addition ");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void testUpdateCourse() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		bean.setId(1l);
		bean.setName("physics");
		bean.setDuration("java");
		bean.setDescription("Multiplication ");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	public static void testdeleteCourse() throws Exception {
		CourseModel model = new CourseModel();
		model.delete(2l);
	}

	public static void testFindByPk() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = model.findByPk(1l);
		System.out.print(bean.getId());
		System.out.print("\t" + bean.getName());
		System.out.print("\t" + bean.getDuration());
		System.out.print("\t" + bean.getDescription());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());
	}

	public static void testFindByCourseName() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = model.findByCourseName("physics");
		System.out.print(bean.getId());
		System.out.print("\t" + bean.getName());
		System.out.print("\t" + bean.getDuration());
		System.out.print("\t" + bean.getDescription());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());
	}

	public static void testSearchCourse() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean testBean = new CourseBean();
		testBean.setId(1l);
		testBean.setName("maths");
		testBean.setDuration("Java");
		testBean.setDescription("He controlls everything");
		testBean.setCreatedBy("Aniket");
		testBean.setModifiedBy("Aniket");
		List<CourseBean> list = model.search(null, 1, 5);
		if (list.size() > 0) {
			Iterator<CourseBean> it = list.iterator();
			CourseBean bean = null;
			while (it.hasNext()) {
				bean = it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getDuration());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDateTime());
				System.out.println("\t" + bean.getModifiedDateTime());
			}
		}
	}

	public static void testDisplayTableInfo() throws Exception {
		CourseModel model = new CourseModel();
		model.courseTableMetadeta();
	}
}
