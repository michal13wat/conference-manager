package my.vaadin.cm;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
<<<<<<< HEAD
	
	private UserService userService = UserService.getInstance();
	private Grid<User> usersGrid = new Grid<>(User.class);
=======
	private EditLecturePanel editLecture = new EditLecturePanel(this);
>>>>>>> login-panel

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
        
<<<<<<< HEAD
        //------------------------------------------
        
        usersGrid.setColumns("login", "email");
        
        HorizontalLayout restComponents = new HorizontalLayout(usersGrid);
        layout.addComponent(restComponents);
        
        updateUsersList();
=======
        HorizontalLayout otherConent = new HorizontalLayout(editLecture);
        
        layout.addComponent(otherConent);
        
>>>>>>> login-panel
        
        setContent(layout);
    }
    
    private void updateList() {
    	List<LectureRow> lectures = service.findAll();
    	grid.setItems(lectures);
    }
    
    private void updateUsersList() {
    	List<User> users = userService.findAll();
    	usersGrid.setItems(users);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
