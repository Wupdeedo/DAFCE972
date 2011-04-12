import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


/**
 * LabelTextFieldPanel is a class made for 
 * having a label and a textField next to each other.
 *
 */
public class LabelTextFieldPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JTextField textField;
	
	public LabelTextFieldPanel(String labelText){
		this.label = new JLabel(labelText);
		this.textField = new JTextField();
		this.init();
	}
	
	public LabelTextFieldPanel(String labelText, String startText){
		this.label = new JLabel(labelText);
		this.textField = new JTextField(startText);
		this.init();
	}
	
	public LabelTextFieldPanel(String labelText, boolean pass){
		this.label = new JLabel(labelText);
		this.textField = new JPasswordField();
		this.init();
	}
	
	public LabelTextFieldPanel(String labelText, int format){
		this.label = new JLabel(labelText);
		this.textField = new JFormattedTextField(NumberFormat.getInstance());
		this.init();
	}
	
	private void init(){
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		this.add(this.label);
		this.add(this.textField);
	}
	
	public JLabel getLabel(){
		return this.label;
	}
	
	public JTextField getTextField(){
		return this.textField;
	}
}
