import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class FlygResultatPanel extends JPanel implements AnswerPanel{
	private static final long serialVersionUID = 1L;
	MainWindow parent;
	Map<String, Integer> flights;
	ButtonGroup bg;
	
	public FlygResultatPanel(MainWindow parent, Map<String, Integer> flights){ // Every string gives an explanation of the flight
		this.parent = parent;
		this.flights = flights;
		this.bg = new ButtonGroup();
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		for(String s : flights.keySet()){
			JRadioButton flight = new JRadioButton(s);
			flight.setActionCommand(s);
			bg.add(flight);
			radioPanel.add(flight);
		}
		if(bg.getElements().hasMoreElements())
			bg.getElements().nextElement().setSelected(true);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(radioPanel);
		this.setVisible(true);
	}

	@Override
	public void OkAction() {
		//parent.flygBokning.setFlightID(this.flightID);
		// TODO
		parent.flygBokning.setFlightID(this.flights.get(this.bg.getSelection().getActionCommand()));
		parent.setPanel(MainWindow.flyg, "FlygAlternativ", parent.createFlygAlternativ());
	}

	@Override
	public void CancelAction() {
		// TODO Auto-generated method stub
		
	}
}
