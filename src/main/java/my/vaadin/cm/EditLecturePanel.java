package my.vaadin.cm;

public class EditLecturePanel extends EditLecturePanelDesign {
	private LectureService service = LectureService.getInstance();
	private LectureRow lecture;
	private MyUI myUI;
	
	public EditLecturePanel(MyUI myUI) {
		this.myUI = myUI;
	}
}
