package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.StudentBean;
import in.co.rays.model.StudentModel;

public class TestStudentModel {
	public static void main(String[] args) throws Exception {
		//testAddStudent();
		 //testUpdateStudent();
		 //testDeleteStudent();
		 //testFindByPk();
		//testFindByEmail();
		// testSearchStudent();
		 //testDisplayStudentTableInfo();
	}

	public static void testAddStudent() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		bean.setFirstName("Uday");
		bean.setLastName("Sharma");
		bean.setDob(sdf.parse("03-09-1999"));
		bean.setGender("Male");
		bean.setMobileNo("98266789873");
		bean.setEmail("uday@gmail.com");
		bean.setCollegeId(2l);
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void testUpdateStudent() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		StudentModel model = new StudentModel();
		StudentBean bean = new StudentBean();
		bean.setId(2l);
		bean.setFirstName("malay");
		bean.setLastName("upadyay");
		bean.setDob(sdf.parse("03-09-1999"));
		bean.setGender("Male");
		bean.setMobileNo("98266789873");
		bean.setEmail("malay@gmail.com");
		bean.setCollegeId(1l);
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	public static void testDeleteStudent() throws Exception {
		StudentModel model = new StudentModel();
		model.delete(2l);
	}

	public static void testFindByPk() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = model.findByPk(1l);
		System.out.print("\t" + bean.getId());
		System.out.print("\t" + bean.getFirstName());
		System.out.print("\t" + bean.getLastName());
		System.out.print("\t" + bean.getDob());
		System.out.print("\t" + bean.getGender());
		System.out.print("\t" + bean.getMobileNo());
		System.out.print("\t" + bean.getEmail());
		System.out.print("\t" + bean.getCollegeId());
		System.out.print("\t" + bean.getCollegeName());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());
	}

	public static void testFindByEmail() throws Exception {
		StudentModel model = new StudentModel();
		StudentBean bean = model.findByEmail("rohan@gmail.com");
		System.out.print("\t" + bean.getId());
		System.out.print("\t" + bean.getFirstName());
		System.out.print("\t" + bean.getLastName());
		System.out.print("\t" + bean.getDob());
		System.out.print("\t" + bean.getGender());
		System.out.print("\t" + bean.getMobileNo());
		System.out.print("\t" + bean.getEmail());
		System.out.print("\t" + bean.getCollegeId());
		System.out.print("\t" + bean.getCollegeName());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());
	}

	public static void testSearchStudent() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		StudentModel model = new StudentModel();
		StudentBean testBean = new StudentBean();
		testBean.setId(4l);
		testBean.setFirstName("rohan");
		testBean.setLastName("kerketta");
		testBean.setDob(sdf.parse("03-09-1999"));
		testBean.setGender("Male");
		testBean.setMobileNo("98266789873");
		testBean.setEmail("rohan@gmail.com");
		testBean.setCollegeId(2l);
		testBean.setCreatedBy("Aniket");
		testBean.setModifiedBy("Aniket");
		List<StudentBean> list = model.search(null, 1, 5);
		if(list.size()>0) {
			Iterator<StudentBean> it = list.iterator();
			StudentBean bean = null;
			while(it.hasNext()) {
				bean = it.next();
				System.out.print("\t" + bean.getId());
				System.out.print("\t" + bean.getFirstName());
				System.out.print("\t" + bean.getLastName());
				System.out.print("\t" + bean.getDob());
				System.out.print("\t" + bean.getGender());
				System.out.print("\t" + bean.getMobileNo());
				System.out.print("\t" + bean.getEmail());
				System.out.print("\t" + bean.getCollegeId());
				System.out.print("\t" + bean.getCollegeName());
				System.out.print("\t" + bean.getCreatedBy());
				System.out.print("\t" + bean.getModifiedBy());
				System.out.print("\t" + bean.getCreatedDateTime());
				System.out.println("\t" + bean.getModifiedDateTime());
			}
		}
	}

	public static void testDisplayStudentTableInfo() throws Exception {
		StudentModel model = new StudentModel();
		model.studentTableMetadeta();
	}
}
