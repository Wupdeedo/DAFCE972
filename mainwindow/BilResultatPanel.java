package mainwindow;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class BilResultatPanel extends JPanel implements AnswerPanel{
	private static final long serialVersionUID = 1L;
	MainWindow parent;
	Map<String, Integer> cars;
	ButtonGroup bg;
	
	public BilResultatPanel(MainWindow parent, Map<String, Integer> cars){ // Every string gives an explanation of the flight
		this.parent = parent;
		this.cars = cars;
		this.bg = new ButtonGroup();
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		for(String s : cars.keySet()){
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
		parent.bilBokning.setBilID(this.cars.get(this.bg.getSelection().getActionCommand()));
	}

	@Override
	public void CancelAction() {
		// TODO Auto-generated method stub
		
	}
}
