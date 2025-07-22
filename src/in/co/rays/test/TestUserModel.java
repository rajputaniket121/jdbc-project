package in.co.rays.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.UserBean;
import in.co.rays.model.UserModel;

public class TestUserModel {
	public static void main(String[] args) throws Exception {
		//testAddUser();
		//testUpdateUser();
		//testDeleteUser();
		//testFindByPk();
		//testFindByLogin();
		//testSearchUser();
		//testDisplayUserTableInfo();
	}
	
	public static void testAddUser() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean.setFirstName("Rohan");
		bean.setLastName("Rajput");
		bean.setLogin("rohan@gmail.com");
		bean.setPassword("password");
		bean.setDob(sdf.parse("26-07-1999"));
		bean.setMobileNo("7898870487");
		bean.setRoleId(2l);
		bean.setGender("Male");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}
	
	public static void testUpdateUser() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean.setId(2l);
		bean.setFirstName("Ajay");
		bean.setLastName("kerketta");
		bean.setLogin("ajay@gmail.com");
		bean.setPassword("123");
		bean.setDob(sdf.parse("03-09-1999"));
		bean.setMobileNo("79792749379");
		bean.setRoleId(2l);
		bean.setGender("Male");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}
	
	public static void testDeleteUser() throws Exception {
		UserModel model = new UserModel();
		model.delete(4l);
	}
	
	
	public static void testFindByPk() throws Exception {
		UserModel model = new UserModel();
		UserBean bean =  model.findByPk(1l);
		System.out.print("\t"+bean.getId());
		System.out.print("\t"+bean.getFirstName());
		System.out.print("\t"+bean.getLastName());
		System.out.print("\t"+bean.getLogin());
		System.out.print("\t"+bean.getPassword());
		System.out.print("\t"+bean.getDob());
		System.out.print("\t"+bean.getMobileNo());
		System.out.print("\t"+bean.getRoleId());
		System.out.print("\t"+bean.getGender());
		System.out.print("\t"+bean.getCreatedBy());
		System.out.print("\t"+bean.getModifiedBy());
		System.out.print("\t"+bean.getCreatedDateTime());
		System.out.println("\t"+bean.getModifiedDateTime());
	}
	
	public static void testFindByLogin() throws Exception {
		UserModel model = new UserModel();
		UserBean bean =  model.findByLogin("ajay@gmail.com");
		System.out.print("\t"+bean.getId());
		System.out.print("\t"+bean.getFirstName());
		System.out.print("\t"+bean.getLastName());
		System.out.print("\t"+bean.getLogin());
		System.out.print("\t"+bean.getPassword());
		System.out.print("\t"+bean.getDob());
		System.out.print("\t"+bean.getMobileNo());
		System.out.print("\t"+bean.getRoleId());
		System.out.print("\t"+bean.getGender());
		System.out.print("\t"+bean.getCreatedBy());
		System.out.print("\t"+bean.getModifiedBy());
		System.out.print("\t"+bean.getCreatedDateTime());
		System.out.println("\t"+bean.getModifiedDateTime());
	}
	
	public static void testSearchUser() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		UserModel model = new UserModel();
		UserBean testBean = new UserBean();
		testBean.setId(4l);
		testBean.setFirstName("rohan");
		testBean.setLastName("kerketta");
		testBean.setLogin("rohan@gmail.com");
		testBean.setPassword("123");
		testBean.setDob(sdf.parse("03-09-1999"));
		testBean.setMobileNo("98266789873");
		testBean.setRoleId(2l);
		testBean.setGender("Male");
		testBean.setCreatedBy("Aniket");
		testBean.setModifiedBy("Aniket");
		List<UserBean> list = model.search(null, 1, 5);
		if(list.size()>0) {
			Iterator<UserBean> it = list.iterator();
			UserBean bean = null;
			while(it.hasNext()) {
				bean = it.next();
				System.out.print("\t"+bean.getId());
				System.out.print("\t"+bean.getFirstName());
				System.out.print("\t"+bean.getLastName());
				System.out.print("\t"+bean.getLogin());
				System.out.print("\t"+bean.getPassword());
				System.out.print("\t"+bean.getDob());
				System.out.print("\t"+bean.getMobileNo());
				System.out.print("\t"+bean.getRoleId());
				System.out.print("\t"+bean.getGender());
				System.out.print("\t"+bean.getCreatedBy());
				System.out.print("\t"+bean.getModifiedBy());
				System.out.print("\t"+bean.getCreatedDateTime());
				System.out.println("\t"+bean.getModifiedDateTime());
			}
		}
	}
	
	
	public static void testDisplayUserTableInfo() throws Exception {
		UserModel model = new UserModel();
		model.userTableMetadeta();
	}
}
