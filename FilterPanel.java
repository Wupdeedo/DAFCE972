import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class FilterPanel extends JPanel implements AnswerPanel{
	private static final long serialVersionUID = 1L;
	LabelTextFieldPanel minPris = new LabelTextFieldPanel("Minpris: ", 1);
	LabelTextFieldPanel maxPris = new LabelTextFieldPanel("Maxpris: ", 1);
	ButtonGroup bg = new ButtonGroup();
	MainWindow parent;
	
	public FilterPanel(MainWindow mw){
		this.parent = mw;
		JLabel klass = new JLabel("Klass: ");
		JLabel specialBagage = new JLabel("SpecialBagage: ");
		JLabel mat = new JLabel("Mat: ");
		// TODO check if these labels are needed
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		this.minPris.getTextField().setColumns(5);
		this.add(this.minPris, c);
		c.gridy = 1;
		this.maxPris.getTextField().setColumns(5);
		this.add(this.maxPris, c);
		c.gridx = 1;
		c.gridy = 0;
		JPanel radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(0,1));
		JRadioButton b = new JRadioButton("Business");
		b.setActionCommand("B");
		JRadioButton e = new JRadioButton("Economy");
		e.setActionCommand("E");
		JRadioButton f = new JRadioButton("First class");
		f.setActionCommand("F");
		radioPanel.add(b);
		radioPanel.add(e);
		radioPanel.add(f);
		bg.add(b);
		bg.add(e);
		bg.add(f);
		this.add(radioPanel, c);
	}

	@Override
	public void OkAction() {
		// TODO make the search different
		this.parent.flygBokning.setKlass(this.bg.getSelection().getActionCommand());
	}

	@Override
	public void CancelAction() {
		// TODO what to do?
		
	}

}
