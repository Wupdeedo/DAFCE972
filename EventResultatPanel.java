import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class EventResultatPanel extends JPanel implements AnswerPanel{
	private static final long serialVersionUID = 1L;
	MainWindow parent;
	Map<String, Integer> events;
	ButtonGroup bg;
	
	public EventResultatPanel(MainWindow parent, Map<String, Integer> events){ // Every string gives an explanation of the flight
		this.parent = parent;
		this.events = events;
		this.bg = new ButtonGroup();
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		for(String s : events.keySet()){
			JRadioButton button = new JRadioButton(s);
			button.setActionCommand(s);
			bg.add(button);
			radioPanel.add(button);
		}
		bg.getElements().nextElement().setSelected(true);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(radioPanel);
		this.setVisible(true);
	}

	@Override
	public void OkAction() {
		// TODO
		parent.eventBokning.setEventID(this.events.get(this.bg.getSelection().getActionCommand()));
	}

	@Override
	public void CancelAction() {
		// TODO Auto-generated method stub
		
	}
}
