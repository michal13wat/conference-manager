package my.vaadin.cm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Button;
import com.vaadin.ui.NativeSelect;

public class EditLecturePanel extends EditLecturePanelDesign {
	private LectureService service = LectureService.getInstance();
	private LectureRow lecture;
	private MyUI myUI;
	private UserService userService;
	private LectureService lectureService;
	private PopUpInfo popup;
	private LectureRow lectureRow;
	private Binder<LectureRow> binder = new Binder<>(LectureRow.class);
	
	public EditLecturePanel(MyUI myUI, UserService userService, LectureService lectureService, 
			PopUpInfo popup) {
		this.myUI = myUI;
		this.userService = userService;
		this.lectureService = lectureService;
		this.popup = popup;
		binder.bindInstanceFields(this);
		
		updateUsers();
		
		List<String> subjects = new ArrayList<>();
		subjects.add("Subject A");
		subjects.add("Subject B");
		subjects.add("Subject C");
		
		subject.setItems(subjects);
		
		save.addClickListener(e -> {
			try {
				save();
			}catch (Exception exception) {
				popup.showOnPopup(exception.getMessage());
			}
			setVisible(false);
			myUI.updateList();
		});
		
		cancel.addClickListener(e -> {
			setVisible(false);
			myUI.updateList();
		});
	}
	
	public void sendMail(String to) throws Exception {		
        try (FileWriter f = new FileWriter("email.txt", true);
                BufferedWriter b = new BufferedWriter(f);
                PrintWriter p = new PrintWriter(b);) {
        	
            p.println("email to: " + to + " You are signed on to conference!");

        } catch (IOException i) {
            throw new Exception("Problem with send file...");
        }
	}
	
	public void setLectureRow(LectureRow lectureRow) {
		this.lectureRow = lectureRow;
		binder.setBean(lectureRow);
		
		cancel.setVisible(lectureRow.isPersisted());
	}
	
	public void setSaveBtnVisability(Boolean visability) {
		save.setVisible(visability);
	}
	
	public void updateUsers() {
		this.login.setItems(userService.getUsersAsStringList());
	}
	
	public void save() throws Exception {
		if (login.isEmpty()) throw new Exception("User login is necessary!");
		if (subject.isEmpty()) throw new Exception("Subject is necessary!");
		
		User u = userService.getUserByLogin(login.getSelectedItem().get());
		this.lectureRow.addParticipant(subject.getSelectedItem().get(), u);
		
		lectureService.save(this.lectureRow);
		sendMail(u.getEmail());
	}
}
