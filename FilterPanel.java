import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class FilterPanel extends JPanel implements AnswerPanel{
	private static final long serialVersionUID = 1L;
	LabelTextFieldPanel minPris = new LabelTextFieldPanel("Minpris: ", 1);
	LabelTextFieldPanel maxPris = new LabelTextFieldPanel("Maxpris: ", 1);
	LabelTextFieldPanel minByten = new LabelTextFieldPanel("Min. antal byten: ", 1);
	LabelTextFieldPanel maxByten = new LabelTextFieldPanel("Max. antal byten: ", 1);
	LabelTextFieldPanel minVikt = new LabelTextFieldPanel("Min. tillåten bagagevikt(kg): ", 1);
	LabelTextFieldPanel maxVikt = new LabelTextFieldPanel("Max. tillåten bagagevikt(kg): ", 1);
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
		c.gridx = 1;
		this.maxPris.getTextField().setColumns(5);
		this.add(this.maxPris, c);
		c.gridx = 0;
		c.gridy = 1;
		this.minByten.getTextField().setColumns(5);
		this.add(this.minByten, c);
		c.gridx = 1;
		this.maxByten.getTextField().setColumns(5);
		this.add(this.maxByten, c);
		c.gridx = 0;
		c.gridy = 2;
		this.minVikt.getTextField().setColumns(5);
		this.add(this.minVikt, c);
		c.gridx = 1;
		this.maxVikt.getTextField().setColumns(5);
		this.add(this.maxVikt, c);
	}

	@Override
	public void OkAction() {
		// TODO make the search different
		
	}

	@Override
	public void CancelAction() {
		// TODO what to do?
		
	}

}
