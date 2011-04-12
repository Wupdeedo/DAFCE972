import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel = new JPanel();
	private JScrollPane mainScrollPane = new JScrollPane(mainPanel);
	private JPanel bannerPanel = new JPanel();
	private JPanel leftExtraPanel = new JPanel();
	private JPanel rightExtraPanel = new JPanel();
	private JPanel adPanel = new JPanel();
	private JPanel searchPanel = new JPanel();
	private JPanel rightUsedPanel = new JPanel();
	private JPanel loginPanel = new JPanel();
	private JPanel treePanel = new JPanel();
	
	Map<String, Map<String, JPanel>> panelMap = createPanelMap();
	
	private final static String flyg = "flyg";
	private final static String hyrbil = "hyrbil";
	private final static String hotell = "hotell";
	private final static String evenemang = "evenemang";
	
	private String currentPanel;
	
	private Map<String, TreePanel> optionTrees = createOptionTrees();
	
	private static List<String> searchOptions = createOptions();
	
	public MainWindow(){
		this.addTrees();
		this.createSearchPanel();
		this.setBorders();
		this.initPanels();
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024+30, 768+41); // TODO dimension?
		this.setVisible(true);
		
		((CardLayout)searchPanel.getLayout()).show(searchPanel, flyg);
		this.showSearchOption(flyg);
	}
	
	private Map<String, Map<String, JPanel>> createPanelMap(){
		Map<String, Map<String, JPanel>> panelMap = new LinkedHashMap<String, Map<String, JPanel>>();
		
		Map<String, JPanel> m = new LinkedHashMap<String, JPanel>();
		m.put("Svar", new JPanel()); // TODO create the option panels
		m.put("FlygAlternativ", createFlygAlternativ(""));
		m.put("Info", new BorderPanel(this, "Info", new JLabel("När jag var ett vårtsvinsbarn.")));
		m.put("Bekräftelse", new BorderPanel(this, "Bekräftelse", new JLabel("SHHHHPOOOOOOFFF")));
		panelMap.put(flyg, m);
		
		m = new LinkedHashMap<String, JPanel>();
		m.put("Svar", new JPanel());
		m.put("BilAlternativ", new JPanel());
		m.put("Info", new JPanel());
		m.put("Bekräftelse", new JPanel());
		panelMap.put(hyrbil, m);
		
		m = new LinkedHashMap<String, JPanel>();
		m.put("Svar", new JPanel());
		m.put("HotellAlternativ", new JPanel());
		m.put("Info", new JPanel());
		m.put("Bekräftelse", new JPanel());
		panelMap.put(hotell, m);
		
		m = new LinkedHashMap<String, JPanel>();
		m.put("Svar", new JPanel());
		m.put("EvenemangsAlternativ", new JPanel());
		m.put("Info", new JPanel());
		m.put("Bekräftelse", new JPanel());
		panelMap.put(evenemang, m);
		
		return panelMap;
	}
	
	private static List<String> createOptions(){
		List<String> ret = new LinkedList<String>();
		ret.add(flyg);
		ret.add(hyrbil);
		ret.add(hotell);
		ret.add(evenemang);
		return ret;
	}
	
	// TODO remove this method in end-version
	private void setBorders(){
		leftExtraPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		rightExtraPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		bannerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		adPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		searchPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		rightUsedPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private Map<String, TreePanel> createOptionTrees(){
		Map<String, TreePanel> ret = new LinkedHashMap<String, TreePanel>();
		
		BufferedImage img;
		try {
			img = ImageIO.read(new File("/Users/niklas/Pictures/firefly.jpg")); //TODO input picture of the tree
			for(String s : this.panelMap.keySet()){
				ret.put(s, new TreePanel(this, img, this.panelMap.get(s).keySet().toArray(new String[0])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	private void addTrees(){
		treePanel.setLayout(new CardLayout());
		int n = 0;
		for(TreePanel tp : this.optionTrees.values()){
			System.out.println(searchOptions.get(n));
			treePanel.add(tp, searchOptions.get(n));
			n++;
		}
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
		c.gridwidth = 1; // ?
		leftExtraPanel.setPreferredSize(new Dimension(32*1,768));
		mainPanel.add(leftExtraPanel,c);
		
		c.gridy = 0;
		c.gridx = 1; // ?
		c.gridheight = 6; //?
		c.gridwidth = 30; // ?
		bannerPanel.setPreferredSize(new Dimension(32*30,32*6));
		mainPanel.add(bannerPanel,c);
		
		c.gridy = 6;
		c.gridx = 1;
		c.gridheight = 58;
		c.gridwidth = 6;
		adPanel.setPreferredSize(new Dimension(32*6,768-32*6));
		mainPanel.add(adPanel,c);
		
		c.gridy = 6;
		c.gridx = 7;
		c.gridheight = 58;
		c.gridwidth = 18;
		searchPanel.setPreferredSize(new Dimension(32*18,768-32*6));
		mainPanel.add(searchPanel,c);
		
		c.gridy = 6;
		c.gridx = 25;
		c.gridheight = 58;
		c.gridwidth = 6;
		
		rightUsedPanel.setLayout(new BoxLayout(this.rightUsedPanel, BoxLayout.PAGE_AXIS));
		rightUsedPanel.add(loginPanel);
		rightUsedPanel.add(treePanel);
		rightUsedPanel.setPreferredSize(new Dimension(32*6,768-32*6));
		mainPanel.add(rightUsedPanel,c);
		
		c.gridy = 0;
		c.gridx = 31;
		c.gridheight = 64;
		c.gridwidth = 1;
		rightExtraPanel.setPreferredSize(new Dimension(32*1,768));
		mainPanel.add(rightExtraPanel,c);
		
		this.add(this.mainScrollPane);
	}
	
	private void createSearchPanel(){
		this.searchPanel.setLayout(new CardLayout());
		for(String s : this.panelMap.keySet()){
			JPanel p = new JPanel();
			p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
			// TODO add search-circles
			p.add(new Boll());
			for(Entry<String, JPanel> e : this.panelMap.get(s).entrySet()){
				p.add(e.getValue());
			}
			this.searchPanel.add(p, s);
		}
	}
	
	private BorderPanel createFlygAlternativ(String s){		
		JPanel p = new JPanel();
		
		LabelTextFieldPanel bagage = new LabelTextFieldPanel("Extravikt bagage(kg): ", "0");
		bagage.getTextField().setColumns(10);
		LabelTextFieldPanel handBagage = new LabelTextFieldPanel("Extravikt bagage(kg): ", "0");
		handBagage.getTextField().setColumns(10);
		
		p.add(bagage);
		p.add(handBagage);
		BorderPanel ret = new BorderPanel(this, "FlygAlternativ" + s, p);
		return ret;
	}
	
	// Shows the correct view (flyg, hyrbil, hotell,...)
	public void showSearchOption(String searchOption){
		((CardLayout)this.searchPanel.getLayout()).show(this.searchPanel, searchOption);
		((CardLayout)this.treePanel.getLayout()).show(this.treePanel, searchOption);
		this.currentPanel = searchOption;
		// TODO needs to be changed when loginPanel and searchPanel are changed
	}
	
	public void showOptionPanel(String panelName, boolean show){ 
		//searchOption = flyg,hyrbil,hotel... panelName = flygalternativ...
		this.panelMap.get(this.currentPanel).get(panelName).setVisible(show);
	}
	
	public Map<String, TreePanel> getOptionTrees(){
		return this.optionTrees;
	}
	
	public String getCurrentPanel(){
		return this.currentPanel;
	}
	
	public static void main(String[] args){
		MainWindow m = new MainWindow();
	}
}
