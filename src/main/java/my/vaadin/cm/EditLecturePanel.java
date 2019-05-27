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
	private LectureService lectureService;
	
	public EditLecturePanel(MyUI myUI, UserService userService, LectureService lectureService) {
		this.myUI = myUI;
		this.userService = userService;
		this.lectureService = lectureService;
		
		updateUsers();
		
		List<String> subjects = new ArrayList<>();
		subjects.add("Subject A");
		subjects.add("Subject B");
		subjects.add("Subject C");
		
		subject.setItems(subjects);
		dateTime.setItems(lectureService.getAllTermsAsString());
	}
	
	public void updateUsers() {
		this.login.setItems(userService.getUsersAsStringList());
	}
}
