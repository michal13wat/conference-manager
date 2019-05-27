package my.vaadin.cm;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.event.FieldEvents;
import com.vaadin.ui.NativeSelect;

public class EditLecturePanel extends EditLecturePanelDesign {
	private LectureService service = LectureService.getInstance();
	private LectureRow lecture;
	private MyUI myUI;
	private UserService userService;
	
	public EditLecturePanel(MyUI myUI, UserService userService) {
		this.myUI = myUI;
		this.userService = userService;
		
		updateUsers();
		
		List<String> subjects = new ArrayList<>();
		subjects.add("subA");
		subjects.add("subB");
		
		subject.setItems(subjects);
	}
	
	public void updateUsers() {
		this.login.setItems(userService.getUsersAsStringList());
	}
}
