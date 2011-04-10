import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class BorderPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private MainWindow parent;
	private JButton okButton;
	private JButton cancelButton;
	private String title;
	
	public BorderPanel(MainWindow parent, String title, Component addable){
		super();
		this.parent = parent;
		this.title = title;
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(addable);
		
		cancelButton = new JButton("Cancel");
		okButton = new JButton("Ok");
		
		cancelButton.addActionListener(this);
		okButton.addActionListener(this);
		
		Box b = new Box(BoxLayout.LINE_AXIS);
		b.add(cancelButton);
		b.add(Box.createVerticalStrut(50));
		b.add(okButton);
		this.add(b);
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource().equals(this.okButton)){
			System.out.println(title);
			this.parent.showOptionPanel(title, false);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).switchButtons(this.title);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).setButtonDone(this.title, true);
		}else if(ae.getSource().equals(this.cancelButton)){
			this.parent.showOptionPanel(title, false);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).switchButtons(this.title);
		}
	}
}
