package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.CollegeBean;
import in.co.rays.model.CollegeModel;

public class TestCollegeModel {
	public static void main(String[] args) throws Exception {
		//testAddCollege();
		//testUpdateCollege();
		//testdeleteCollege();
		//testFindByPk();
		//testFindByCollegeName();
		//testSearchCollege();
		//testDisplayTableInfo();
	}
	
	public static void testAddCollege() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
		bean.setName("temp");
		bean.setAddress("Indore");
		bean.setState("Indore");
		bean.setCity("Indore");
		bean.setPhoneNo("9378437974");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}
	
	public static void testUpdateCollege() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean();
		bean.setId(1l);
		bean.setName("Bansal");
		bean.setAddress("Indore");
		bean.setState("Indore");
		bean.setCity("Indore");
		bean.setPhoneNo("9378437974");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}
	
	public static void testdeleteCollege() throws Exception {
		CollegeModel model = new CollegeModel();
		model.delete(3l);
	}
	
	public static void testFindByPk() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = model.findByPk(1l);
		System.out.print(bean.getId());
		System.out.print("\t"+bean.getName());
		System.out.print("\t"+bean.getAddress());
		System.out.print("\t"+bean.getState());
		System.out.print("\t"+bean.getCity());
		System.out.print("\t"+bean.getPhoneNo());
		System.out.print("\t"+bean.getCreatedBy());
		System.out.print("\t"+bean.getModifiedBy());
		System.out.print("\t"+bean.getCreatedDateTime());
		System.out.println("\t"+bean.getModifiedDateTime());
	}
	
	public static void testFindByCollegeName() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = model.findByCollegeName("temp");
		System.out.print(bean.getId());
		System.out.print("\t"+bean.getName());
		System.out.print("\t"+bean.getAddress());
		System.out.print("\t"+bean.getState());
		System.out.print("\t"+bean.getCity());
		System.out.print("\t"+bean.getPhoneNo());
		System.out.print("\t"+bean.getCreatedBy());
		System.out.print("\t"+bean.getModifiedBy());
		System.out.print("\t"+bean.getCreatedDateTime());
		System.out.println("\t"+bean.getModifiedDateTime());
	}
	

	public static void testSearchCollege() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean testBean = new CollegeBean();
		testBean.setId(1l);
		testBean.setName("temp");
		testBean.setAddress("Indore");
		testBean.setState("Indore");
		testBean.setCity("Indore");
		testBean.setPhoneNo("9378437974");
		testBean.setCreatedBy("Aniket");
		testBean.setModifiedBy("Aniket");
		List<CollegeBean> list = model.search(testBean, 1, 5);
		if(list.size()>0) {
			Iterator<CollegeBean> it = list.iterator();
			CollegeBean bean = null;
			while(it.hasNext()) {
				bean = it.next();
				System.out.print(bean.getId());
				System.out.print("\t"+bean.getName());
				System.out.print("\t"+bean.getAddress());
				System.out.print("\t"+bean.getState());
				System.out.print("\t"+bean.getCity());
				System.out.print("\t"+bean.getPhoneNo());
				System.out.print("\t"+bean.getCreatedBy());
				System.out.print("\t"+bean.getModifiedBy());
				System.out.print("\t"+bean.getCreatedDateTime());
				System.out.println("\t"+bean.getModifiedDateTime());

			}
		}
	}
	
	
	public static void testDisplayTableInfo() throws Exception {
		CollegeModel model = new CollegeModel();
		model.collegeTableMetadeta();
	}
}
