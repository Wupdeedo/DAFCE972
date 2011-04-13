package mainwindow;
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
	private AnswerPanel ap;
	public static int okCancelOption = 0;
	public static int cancelOption = 1;
	public static int okOption = 2;
	
	public BorderPanel(MainWindow parent, String title, Component addable){
		super();
		this.parent = parent;
		this.title = title;
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		if(addable!=null)
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
	
	public BorderPanel(MainWindow parent, String title, Component addable, AnswerPanel ap){
		super();
		this.parent = parent;
		this.title = title;
		this.ap = ap;
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		if(addable!=null)
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
	
	public BorderPanel(MainWindow parent, String title, Component addable, int comps){
		super();
		this.parent = parent;
		this.title = title;
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		if(addable!=null)
			this.add(addable);
		Box b = new Box(BoxLayout.LINE_AXIS);
		if(comps == cancelOption || comps == okCancelOption){
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(this);
			b.add(cancelButton);
		}
		b.add(Box.createVerticalStrut(50));
		if(comps == okOption || comps == okCancelOption){
			okButton = new JButton("Ok");
			okButton.addActionListener(this);
			b.add(okButton);
		}
		
		this.add(b);
		this.setVisible(false);
	}
	
	public BorderPanel(MainWindow parent, String title, Component addable, String cancelName, String okName){
		super();
		this.parent = parent;
		this.title = title;
		this.setBorder(BorderFactory.createTitledBorder(title));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		if(addable!=null)
			this.add(addable);
		
		cancelButton = new JButton(cancelName);
		okButton = new JButton(okName);
		
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
		if(this.okButton != null && ae.getSource().equals(this.okButton)){
			this.parent.showOptionPanel(title, false);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).switchButtons(this.title);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).setButtonDone(this.title, true);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).showNextPanel(this.title, true);
			if(ap!=null)
				ap.OkAction(); // TODO
			/*
			 * if(this.title.equals("Bekr�ftelse")){
			 * 	if(for all optionpanels, panelOK:
			 * 		kundvagn.add(bokning)
			 * this.getOptionTrees().get(this.parent.getCurrentPanel().reset()) (set visible(false), create new tree when needed)
			 */
		}else if(this.cancelButton != null && ae.getSource().equals(this.cancelButton)){
			this.parent.showOptionPanel(title, false);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).switchButtons(this.title);
			if(ap!=null)
				ap.CancelAction();
		}
	}
}
