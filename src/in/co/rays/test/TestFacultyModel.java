package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.FacultyBean;
import in.co.rays.model.FacultyModel;

public class TestFacultyModel {

	public static void main(String[] args) throws Exception {
		//testAddFaculty();
		 //testUpdateFaculty();
		 //testDeleteFaculty();
		 //testFindByPk();
		//testFindByEmail();
		 //testSearchFaculty();
		// testDisplayFacultyTableInfo();
	}

	public static void testAddFaculty() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		FacultyModel model = new FacultyModel();
		FacultyBean bean = new FacultyBean();
		bean.setFirstName("Anshul");
		bean.setLastName("Sharma");
		bean.setDob(sdf.parse("03-09-1999"));
		bean.setGender("Male");
		bean.setMobileNo("98266789873");
		bean.setEmail("anshul@gmail.com");
		bean.setCollegeId(1l);
		bean.setCourseId(1l);
		bean.setSubjectId(1l);
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}

	public static void testUpdateFaculty() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		FacultyModel model = new FacultyModel();
		FacultyBean bean = new FacultyBean();
		bean.setId(2l);
		bean.setFirstName("malay");
		bean.setLastName("upadyay");
		bean.setDob(sdf.parse("03-09-1999"));
		bean.setGender("Male");
		bean.setMobileNo("98266789873");
		bean.setEmail("malay@gmail.com");
		bean.setCollegeId(1l);
		bean.setCourseId(1l);
		bean.setSubjectId(1l);
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}

	public static void testDeleteFaculty() throws Exception {
		FacultyModel model = new FacultyModel();
		model.delete(2l);
	}

	public static void testFindByPk() throws Exception {
		FacultyModel model = new FacultyModel();
		FacultyBean bean = model.findByPk(1l);
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
		FacultyModel model = new FacultyModel();
		FacultyBean bean = model.findByEmail("rohan@gmail.com");
		System.out.print("\t" + bean.getId());
		System.out.print("\t" + bean.getFirstName());
		System.out.print("\t" + bean.getLastName());
		System.out.print("\t" + bean.getDob());
		System.out.print("\t" + bean.getGender());
		System.out.print("\t" + bean.getMobileNo());
		System.out.print("\t" + bean.getEmail());
		System.out.print("\t" + bean.getCollegeId());
		System.out.print("\t" + bean.getCollegeName());
		System.out.print("\t" + bean.getCourseId());
		System.out.print("\t" + bean.getCourseName());
		System.out.print("\t" + bean.getSubjectId());
		System.out.print("\t" + bean.getSubjectName());
		System.out.print("\t" + bean.getCreatedBy());
		System.out.print("\t" + bean.getModifiedBy());
		System.out.print("\t" + bean.getCreatedDateTime());
		System.out.println("\t" + bean.getModifiedDateTime());
	}

	public static void testSearchFaculty() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		FacultyModel model = new FacultyModel();
		FacultyBean testBean = new FacultyBean();
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
		List<FacultyBean> list = model.search(null, 1, 5);
		if(list.size()>0) {
			Iterator<FacultyBean> it = list.iterator();
			FacultyBean bean = null;
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

	public static void testDisplayFacultyTableInfo() throws Exception {
		FacultyModel model = new FacultyModel();
		model.facultyTableMetadeta();
	}

}
