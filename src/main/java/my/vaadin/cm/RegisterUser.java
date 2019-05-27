package my.vaadin.cm;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.data.Binder;
import com.vaadin.ui.Grid;

public class RegisterUser extends RegisterUserDesign {
	private UserService service = UserService.getInstance();
	private Binder<User> binder = new Binder<>(User.class);
	private User user;
	private MyUI myUI;
	
	private static final Logger LOGGER = Logger.getLogger(LectureService.class.getName());
	
	public RegisterUser(MyUI myUI) {
		this.myUI = myUI;
		binder.bindInstanceFields(this);
		registerNewUser.addClickListener(e -> {
        	try {
        		this.save();
        	}catch (Exception exeption) {
    			LOGGER.log(Level.SEVERE,
    					exeption.getMessage());
    			
        	}
		});
		
		editUser.addClickListener(e -> {
        	try {
        		this.save();
        	}catch (Exception exception) {
    			LOGGER.log(Level.SEVERE,
    					exception.getMessage());
        	}
		});
		
		setVisible(false);
	}
	
	public void setUser(User user) {
		
		this.user = user;
		binder.setBean(user);
		
		editUser.setVisible(user.isPersisted());
		setVisible(true);
		login.selectAll();
	}
	
	public void setRegisterBtnVisability(Boolean visable) {
		registerNewUser.setVisible(visable);
	}
	
	public void loginTextFieldEnabled(Boolean enabled) {
		login.setEnabled(enabled);
	}
	
	public void save() throws Exception {
		if (user.getLogin().isEmpty()) throw new Exception("Login can not be empty!");
		
		service.save(user);
		myUI.updateUsersList();
		setVisible(false);
	}
}
