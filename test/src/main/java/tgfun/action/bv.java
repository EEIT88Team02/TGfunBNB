package tgfun.action;

import org.hibernate.validator.constraints.NotEmpty;

public class bv {
	@NotEmpty(message="{userName.not.empty}")
	private String userName;
    
	private int age;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

   

	
	
}
