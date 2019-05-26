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

import my.vaadin.cm.LectureService;

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

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
                
        grid.setColumns("dateTime", "subjectA", "subjectB", "subjectC");
        
        HorizontalLayout main = new HorizontalLayout(grid);
        main.setSizeFull();
        grid.setSizeFull();
        main.setExpandRatio(grid, 1);
        
        layout.addComponent(main);
        
        updateList();
        
        setContent(layout);
    }
    
    private void updateList() {
    	List<LectureRow> lectures = service.findAll();
    	grid.setItems(lectures);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
