import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class MainWindow extends JFrame{
	private JPanel mainPanel = new JPanel();
	private JScrollPane mainScrollPane = new JScrollPane(mainPanel);
	private JPanel bannerPanel = new JPanel();
	private JPanel leftExtraPanel = new JPanel();
	private JPanel rightExtraPanel = new JPanel();
	private JPanel adPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	
	public MainWindow(){
		this.addButtons();
		this.setBorders();
		this.initPanels();
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		this.setVisible(true);
	}
	
	private void setBorders(){
		leftExtraPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		rightExtraPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		bannerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		adPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		loginPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void addButtons(){
		leftExtraPanel.add(new JButton("left"));
		rightExtraPanel.add(new JButton("right"));
		bannerPanel.add(new JButton("banner"));
		adPanel.add(new JButton("ad"));
		searchPanel.add(new JButton("search"));
		loginPanel.add(new JButton("log in"));
	}
	
	private void initPanels(){
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
	 
		/**
		 * Units of 32 pixels - gridwidth = 3 -> height in pixels = 96. (if the dimensions are 1024x768)
		 */
		c.gridy = 0;
		c.gridx = 0;
		c.gridheight = 64;
		c.gridwidth = 3; // ?
		mainPanel.add(leftExtraPanel,c);
	
		c.gridy = 0;
		c.gridx = 3; // ?
		c.gridheight = 6; //?
		c.gridwidth = 26; // ?
		mainPanel.add(bannerPanel,c);
	
		c.gridy = 6;
		c.gridx = 3;
		c.gridheight = 58; //?
		c.gridwidth = 6; // ?
		mainPanel.add(adPanel,c);
	 
		c.gridy = 6;
		c.gridx = 9;
		c.gridheight = 58; //?
		c.gridwidth = 18; // ?
		mainPanel.add(searchPanel,c);
		
		c.gridy = 6;
		c.gridx = 27;
		c.gridheight = 58; //?
		c.gridwidth = 2; // ?
		mainPanel.add(loginPanel,c);
		
		c.gridy = 0;
		c.gridx = 29;
		c.gridheight = 64; //?
		c.gridwidth = 3; // ?
		mainPanel.add(rightExtraPanel,c);
		
		this.add(this.mainScrollPane);
	}
	
	public static void main(String[] args){
			new MainWindow();
	}
}