package tgfun.action;

import org.hibernate.validator.constraints.NotEmpty;

public class message_verification {
	@NotEmpty()
	private String articl; 
	@NotEmpty()
   private String leave;

public String getArticl() {
	return articl;
}

public void setArticl(String articl) {
	this.articl = articl;
}

public String getLeave() {
	return leave;
}

public void setLeave(String leave) {
	this.leave = leave;
}	
   	
	
	
}
