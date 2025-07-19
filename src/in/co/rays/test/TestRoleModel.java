package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import in.co.rays.bean.RoleBean;
import in.co.rays.model.RoleModel;

public class TestRoleModel {
	public static void main(String[] args) throws Exception {
		//testAddRole();
		//testUpdateRole();
		//testdeleteRole();
		//testFindByPk();
		//testFindByRollName();
		//testSearchRole();
		//testDisplayTableInfo();
	}
	
	public static void testAddRole() throws Exception {
		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();
		bean.setName("temp");
		bean.setDescription("He is a normal user");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.add(bean);
	}
	
	public static void testUpdateRole() throws Exception {
		RoleModel model = new RoleModel();
		RoleBean bean = new RoleBean();
		bean.setId(2l);
		bean.setName("HR");
		bean.setDescription("He is Hr");
		bean.setCreatedBy("Aniket");
		bean.setModifiedBy("Aniket");
		bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
		bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
		model.update(bean);
	}
	
	public static void testdeleteRole() throws Exception {
		RoleModel model = new RoleModel();
		model.delete(3l);
	}
	
	public static void testFindByPk() throws Exception {
		RoleModel model = new RoleModel();
		RoleBean bean = model.findByPk(1l);
		System.out.print(bean.getId());
		System.out.print("\t"+bean.getName());
		System.out.print("\t"+bean.getDescription());
		System.out.print("\t"+bean.getCreatedBy());
		System.out.print("\t"+bean.getModifiedBy());
		System.out.print("\t"+bean.getCreatedDateTime());
		System.out.println("\t"+bean.getModifiedDateTime());
	}
	
	public static void testFindByRollName() throws Exception {
		RoleModel model = new RoleModel();
		RoleBean bean = model.findByRoleName("temp");
		System.out.print(bean.getId());
		System.out.print("\t"+bean.getName());
		System.out.print("\t"+bean.getDescription());
		System.out.print("\t"+bean.getCreatedBy());
		System.out.print("\t"+bean.getModifiedBy());
		System.out.print("\t"+bean.getCreatedDateTime());
		System.out.println("\t"+bean.getModifiedDateTime());
	}
	

	public static void testSearchRole() throws Exception {
		RoleModel model = new RoleModel();
		RoleBean testBean = new RoleBean();
		testBean.setId(1l);
		testBean.setName("Admin");
		testBean.setDescription("He controlls everything");
		testBean.setCreatedBy("Aniket");
		testBean.setModifiedBy("Aniket");
		List<RoleBean> list = model.search(testBean, 1, 5);
		if(list.size()>0) {
			Iterator<RoleBean> it = list.iterator();
			RoleBean bean = null;
			while(it.hasNext()) {
				bean = it.next();
				System.out.print(bean.getId());
				System.out.print("\t"+bean.getName());
				System.out.print("\t"+bean.getDescription());
				System.out.print("\t"+bean.getCreatedBy());
				System.out.print("\t"+bean.getModifiedBy());
				System.out.print("\t"+bean.getCreatedDateTime());
				System.out.println("\t"+bean.getModifiedDateTime());

			}
		}
	}
	
	
	public static void testDisplayTableInfo() throws Exception {
		RoleModel model = new RoleModel();
		model.roleTableMetadeta();
	}
}
