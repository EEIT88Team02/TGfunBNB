package tgfun.action;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class article_verification {
	@NotEmpty()
	@Length(min=1,max=50)
	private String theme;
	@NotEmpty()
	@Length(min=1,max=1600)
	private String context;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
	
	
	
	
}
