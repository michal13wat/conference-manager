package my.vaadin.cm;

public class RegisterUser extends RegisterUserDesign {
	private UserService service = UserService.getInstance();
	private User user;
	private MyUI myUI;
	
	public RegisterUser(MyUI myUI) {
		this.myUI = myUI;
	}
}
