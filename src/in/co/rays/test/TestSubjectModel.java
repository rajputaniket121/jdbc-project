package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.SubjectBean;
import in.co.rays.model.SubjectModel;

public class TestSubjectModel {
	public static void main(String[] args) throws Exception {
		// testAddSubject();
		 //testUpdateSubject();
		// testdeleteSubject();
		// testFindByPk();
		 //testFindBySubjectName();
		 //testSearchSubject();
		// testDisplayTableInfo();
	}

	public static void testAddSubject() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();
		bean.setName("Core Java");
		bean.setCourseId(1l);
		bean.setDescription(" Basics of java  ");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void testUpdateSubject() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();
		bean.setId(1l);
		bean.setName("physics");
		bean.setCourseId(1l);
		bean.setCourseName("java");
		bean.setDescription("Multiplication ");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	public static void testdeleteSubject() throws Exception {
		SubjectModel model = new SubjectModel();
		model.delete(2l);
	}

	public static void testFindByPk() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByPk(1l);
		System.out.print(bean.getId());
		System.out.print("\t" + bean.getName());
		System.out.print("\t" + bean.getCourseId());
		System.out.print("\t" + bean.getCourseName());
		System.out.print("\t" + bean.getDescription());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());
	}

	public static void testFindBySubjectName() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findBySubjectName("maths");
		System.out.print(bean.getId());
		System.out.print("\t" + bean.getName());
		System.out.print("\t" + bean.getCourseId());
		System.out.print("\t" + bean.getCourseName());
		System.out.print("\t" + bean.getDescription());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());
	}

	public static void testSearchSubject() throws Exception {
		SubjectModel model = new SubjectModel();
		SubjectBean testBean = new SubjectBean();
		testBean.setId(1l);
		testBean.setName("maths");
		testBean.setCourseId(1l);
		testBean.setCourseName("Java");
		testBean.setDescription("He controlls everything");
		testBean.setCreatedBy("Aniket");
		testBean.setModifiedBy("Aniket");
		List<SubjectBean> list = model.search(null, 1, 5);
		if (list.size() > 0) {
			Iterator<SubjectBean> it = list.iterator();
			SubjectBean bean = null;
			while (it.hasNext()) {
				bean = it.next();
				System.out.print(bean.getId());
				System.out.print("\t" + bean.getName());
				System.out.print("\t" + bean.getCourseId());
				System.out.print("\t" + bean.getCourseName());
				System.out.print("\t" + bean.getDescription());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDateTime());
				System.out.println("\t" + bean.getModifiedDateTime());
			}
		}
	}

	public static void testDisplayTableInfo() throws Exception {
		SubjectModel model = new SubjectModel();
		model.subjectTableMetadeta();
	}
}
