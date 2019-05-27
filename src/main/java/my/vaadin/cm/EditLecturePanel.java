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
	private PopUpInfo popup;
	
	public EditLecturePanel(MyUI myUI, UserService userService, LectureService lectureService, PopUpInfo popup) {
		this.myUI = myUI;
		this.userService = userService;
		this.lectureService = lectureService;
		this.popup = popup;
		
		updateUsers();
		
		List<String> subjects = new ArrayList<>();
		subjects.add("Subject A");
		subjects.add("Subject B");
		subjects.add("Subject C");
		
		subject.setItems(subjects);
		dateTime.setItems(lectureService.getAllTermsAsString());
		
		save.addClickListener(e -> {
			try {
				save();
			}catch (Exception exception) {
				popup.showOnPopup(exception.getMessage());
			}
		});
	}
	
	public void updateUsers() {
		this.login.setItems(userService.getUsersAsStringList());
	}
	
	public void save() throws Exception {
		if (login.isEmpty()) throw new Exception("User login is necessary!");
		if (dateTime.isEmpty()) throw new Exception("Date and time are necessary!");
		if (subject.isEmpty()) throw new Exception("Subject is necessary!");
		
		lectureService.save(new LectureRow());
	}
}
