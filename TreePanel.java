import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class TreePanel extends ImagePanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private List<JPanel> rightList = new LinkedList<JPanel>();
	private List<JPanel> leftList = new LinkedList<JPanel>();
	
	private int numButtons = 5;
	
	private JButton[] rightButtons;
	private JButton[] leftButtons;
	// Check this array to determine when the order can be put in the cart.
	private int[] buttonUses;
	
	private static int buttonNotDoneValue = 0;
	private static int buttonDoneValue = 1;
	
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private MainWindow parent;
	
	private static Color buttonDoneColor = Color.green;
	private static Color buttonNotDoneColor = Color.white;

	public TreePanel(MainWindow parent, BufferedImage img, String[] buttonNames) {
		super(img);
		
		this.parent = parent;
		
		this.numButtons = buttonNames.length;
		
		rightButtons = new JButton[numButtons];
		leftButtons = new JButton[numButtons];
		buttonUses = new int[numButtons];
		
		for(int n = 0; n < this.numButtons; n++){
			this.rightButtons[n] = new JButton(buttonNames[n]);
			this.rightButtons[n].setMargin(new Insets(0,0,0,0));
			this.rightButtons[n].setBackground(buttonNotDoneColor);
			this.leftButtons[n] = new JButton(buttonNames[n]);
			this.leftButtons[n].setMargin(new Insets(0,0,0,0));
			this.leftButtons[n].setBackground(buttonNotDoneColor);
			this.buttonUses[n] = buttonNotDoneValue;
		}
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.addPanels();
		this.drawTreeButtons();
	}
	
	private static BufferedImage loadImage(String fileName){
		try {
			return ImageIO.read(new File(fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void addPanels(){
		Box box = Box.createHorizontalBox();
		box.add(leftPanel);
		box.add(rightPanel);
		this.add(box);
	}
	
	private void drawTreeButtons(){
		Box leftBox = Box.createVerticalBox();
		Box rightBox = Box.createVerticalBox();
		for(int n = 0; n < this.numButtons; n++){
			JButton left = this.leftButtons[n]; // key is left (in-use)
			JButton right = this.rightButtons[n]; // value is right (unused)
			left.setPreferredSize(new Dimension(100, 20));
			right.setPreferredSize(new Dimension(100, 20));
			
			JPanel p1 = new JPanel(new CardLayout());
			leftList.add(p1);
			left.addActionListener(this);
			p1.add(left, "visible");
			p1.add(Box.createRigidArea(left.getSize()), "invisible");
			leftBox.add(p1);
			leftBox.add(Box.createVerticalStrut(20));
			
			JPanel p2 = new JPanel(new CardLayout());
			rightList.add(p2);
			right.addActionListener(this);
			p2.add(right, "visible");
			p2.add(Box.createRigidArea(right.getSize()), "invisible");
			rightBox.add(p2);
			rightBox.add(Box.createVerticalStrut(20));
		}
		this.leftPanel.add(leftBox);
		this.rightPanel.add(rightBox);
		for(JPanel p : leftList){
			((CardLayout)p.getLayout()).show(p, "invisible");
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		for(int n = 0; n < this.numButtons; n++){
			JButton left = this.leftButtons[n];
			JButton right = this.rightButtons[n];
			if(ae.getSource().equals(left)){
				switchButtons(n);
				this.parent.showOptionPanel(left.getText(), false);
			}else if(ae.getSource().equals(right)){
				switchButtons(n);
				this.parent.showOptionPanel(left.getText(), true);
			}
		}
	}
	
	public void setButtonDone(String buttonName, boolean done){
		for(int n = 0; n < this.rightButtons.length; n++){
			if(buttonName.equals(this.rightButtons[n].getText())){
				if(done){
					this.rightButtons[n].setBackground(buttonDoneColor);
					this.buttonUses[n] = buttonDoneValue;
				}
				else{
					this.rightButtons[n].setBackground(buttonNotDoneColor);
					this.buttonUses[n] = buttonNotDoneValue;
				}
				return;
			}
		}
		
	}
	
	private void switchButtons(int n){
		JPanel pr = this.rightList.get(n);
		JPanel pl = this.leftList.get(n);
		((CardLayout)pr.getLayout()).next(pr);
		((CardLayout)pl.getLayout()).next(pl);
	}
	
	public void switchButtons(String buttonName){
		for(int n = 0; n < leftButtons.length; n++){
			if(leftButtons[n].getText().equals(buttonName)){
				JPanel pr = this.rightList.get(n);
				JPanel pl = this.leftList.get(n);
				((CardLayout)pr.getLayout()).next(pr);
				((CardLayout)pl.getLayout()).next(pl);
				return;
			}
		}
	}
	
	public void showPanel(String buttonName, boolean visible){
		for(int n = 0; n < leftButtons.length; n++){
			if(leftButtons[n].getText().equals(buttonName)){
				JPanel pr = this.rightList.get(n);
				JPanel pl = this.leftList.get(n);
				if(visible){
					((CardLayout)pr.getLayout()).show(pr, "invisible");
					((CardLayout)pl.getLayout()).show(pl, "visible");
					this.parent.showOptionPanel(buttonName, true);
				}else{
					((CardLayout)pr.getLayout()).show(pr, "visible");
					((CardLayout)pl.getLayout()).show(pl, "invisible");
					this.parent.showOptionPanel(buttonName, false);
				}
				return;
			}
		}
	}
	
	// Don't use this method on the last panel(bekr�ftelse)
	public void showNextPanel(String buttonName, boolean show){
		for(int n = 0; n < leftButtons.length-1; n++){
			if(leftButtons[n].getText().equals(buttonName)){
				JPanel pr = this.rightList.get(n+1);
				JPanel pl = this.leftList.get(n+1);
				if(show){
					((CardLayout)pr.getLayout()).show(pr, "invisible");
					((CardLayout)pl.getLayout()).show(pl, "visible");
				}else{
					((CardLayout)pr.getLayout()).show(pr, "visible");
					((CardLayout)pl.getLayout()).show(pl, "invisible");
				}
				this.parent.showOptionPanel(leftButtons[n+1].getText(), show);
				return;
			}
		}
	}
	
	public JPanel getNextPanel(String buttonName){
		for(int n = 0; n < leftButtons.length-1; n++){
			if(leftButtons[n].getText().equals(buttonName)){
				return parent.panelMap.get(parent.getCurrentPanel()).get(leftButtons[n+1]);
			}
		}
		return null;
	}
	
}