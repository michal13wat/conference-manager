package my.vaadin.cm;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.VerticalLayout;

public class PopUpInfo {
	
	private Label contentLabel = new Label();
	private Button okBtn = new Button("OK");
	private VerticalLayout popupContent = new VerticalLayout();
	PopupView popup = new PopupView("", popupContent);
	
	public PopUpInfo() {
	    popupContent.addComponent(contentLabel);
	    popupContent.addComponent(okBtn);
	    
	    okBtn.addClickListener(e -> this.popup.setPopupVisible(false));
	}
	
    public void showOnPopup(String content) {
    	contentLabel.setValue(content);
    	popup.setPopupVisible(true);
    }
    
    public PopupView getPopupView() {
    	return popup;
    }
}
