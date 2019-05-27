package my.vaadin.cm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import my.vaadin.cm.UserService;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	private LectureService service = LectureService.getInstance();
	private Grid<LectureRow> grid = new Grid<>(LectureRow.class);
	
	private UserService userService = UserService.getInstance();
	private Grid<User> usersGrid = new Grid<>(User.class);
	
	private PopUpInfo popup = new PopUpInfo();
	private EditLecturePanel editLecture = new EditLecturePanel(this, userService, service, popup);
	private RegisterUser registerUser = new RegisterUser(this, popup, editLecture);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
                
        grid.setColumns("dateTime", "subjectA", "subjectB", "subjectC");
        
        HorizontalLayout conferencesTable = new HorizontalLayout(grid);
        conferencesTable.setSizeFull();
        grid.setSizeFull();
        conferencesTable.setExpandRatio(grid, 1);
        
        layout.addComponent(conferencesTable);
        
        updateList();

        //------------------------------------------
        
        usersGrid.setColumns("login", "email");
        
        usersGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
                registerUser.setVisible(false);
            } else {
            	registerUser.setUser(event.getValue());	
                registerUser.setRegisterBtnVisability(false);
                registerUser.loginTextFieldEnabled(false);
            }
        });
        
        Button addUserBtn = new Button("Add new user");
        addUserBtn.addClickListener(e -> {
            usersGrid.asSingleSelect().clear();
        	registerUser.setUser(new User());
            registerUser.setRegisterBtnVisability(true);
            registerUser.loginTextFieldEnabled(true);
        });
        
        
        HorizontalLayout restComponents = new HorizontalLayout(editLecture, usersGrid, registerUser, addUserBtn, popup.getPopupView());
        layout.addComponent(restComponents);
        
        updateUsersList();
        
        setContent(layout);
    }
    
    public void updateList() {
    	List<LectureRow> lectures = service.findAll();
    	grid.setItems(lectures);
    }
    
    public void updateUsersList() {
    	List<User> users = userService.findAll();
    	usersGrid.setItems(users);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
