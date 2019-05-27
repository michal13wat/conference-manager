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
	private PopUpInfo popup;
	
	public RegisterUser(MyUI myUI, PopUpInfo popup) {
		this.myUI = myUI;
		this.popup = popup;
		binder.bindInstanceFields(this);
		registerNewUser.addClickListener(e -> {
        	try {
        		this.save();
        	}catch (Exception exeption) {
    			popup.showOnPopup(exeption.getMessage());
        	}
		});
		
		editUser.addClickListener(e -> {
        	try {
        		this.edit();
        	}catch (Exception exception) {
    			popup.showOnPopup(exception.getMessage());
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
		if (user.getEmail().isEmpty()) throw new Exception("E-mail can not be empty!");
		
		service.save(user, true);
		myUI.updateUsersList();
		setVisible(false);
	}
	
	public void edit() throws Exception {
		if (user.getEmail().isEmpty()) throw new Exception("E-mail can not be empty!");
		
		service.save(user, false);
		myUI.updateUsersList();
		setVisible(false);
	}
}
