package in.co.rays.test;

import java.util.ResourceBundle;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ResourceBundle bundle = ResourceBundle.getBundle("in.co.rays.bundle.system");
		System.out.println(bundle.getString("driver"));

	}

}
