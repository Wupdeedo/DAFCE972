import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import boll.Boll;


public class MainWindow extends JFrame implements ActionListener, ComponentListener{
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
	FlygBokning flygBokning = new FlygBokning();
	HotellBokning hotellBokning = new HotellBokning();
	BilBokning bilBokning = new BilBokning();
	EventBokning eventBokning = new EventBokning();
	LabelTextFieldPanel user = new LabelTextFieldPanel("Anvï¿½ndare: ");
	LabelTextFieldPanel pass = new LabelTextFieldPanel("Lï¿½senord: ", true);
	JButton loginButton = new JButton("Logga in");
	JLabel welcomeLabel = new JLabel();
	JButton changeBookingButton = new JButton("Ändra bokning");
	JButton logoutButton = new JButton("Logga ut");
	DatabaseHandle dbh = new DatabaseHandle(); // TODO create later?
	private boolean userLoggedIn = false;
	
	Map<String, Map<String, JPanel>> panelMap = createPanelMap(false, true);
	
	public final static String flyg = "flyg";
	public final static String hyrbil = "hyrbil";
	public final static String hotell = "hotell";
	public final static String evenemang = "evenemang";
	
	private String currentPanel;
	
	private Map<String, TreePanel> optionTrees = createOptionTrees();
	
	private static List<String> searchOptions = createOptions();
	
	public MainWindow(){
		this.addTrees();
		this.createSearchPanel();
		this.setBorders();
		this.initPanels();
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(1024+30, 768+41); // TODO dimension?
		this.setSize(1400, 768+41);
		this.setVisible(true);
		
		((CardLayout)searchPanel.getLayout()).show(searchPanel, flyg);
		this.showSearchOption(flyg);
	}
	
	private Map<String, JPanel> createInnerPanelMap(String outerPanel, boolean almanacka, boolean filter){
		Map<String, JPanel> m = new LinkedHashMap<String, JPanel>();
		if(outerPanel.equals(flyg)){
			if(almanacka){
				// TODO create Prisalmanacka
			}
			if(filter){
				FilterPanel fp = new FilterPanel(this);
				m.put("filter", new BorderPanel(this,"filter",fp,fp));
			}
			Map<String, Integer> flightMap = new TreeMap<String, Integer>();
//			try {
//				ResultSet rs = dbh.searchFlight(this.flygBokning.getAnkomstDatum(), 
//						this.flygBokning.getAnkomstOrt(), this.flygBokning.getAvgangsOrt());
//				while(rs.next()){
//					int flightID = rs.getInt("FlightID");
//					Date avgangsTid = rs.getDate("Avgangstid");
//					Date ankomstTid = rs.getDate("Ankomsttid");
//					flightMap.put("AvgÃ¥ngstid: " + avgangsTid.toString() + " Ankomsttid: " + 
//							ankomstTid.toString(), flightID);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
			// TODO create a map through reading from the database, ResultSet -> Map
			FlygResultatPanel fr = new FlygResultatPanel(this, flightMap);
			m.put("Svar", new BorderPanel(this, "Svar", fr, fr)); // TODO create the option panels
			List<Integer> l = new LinkedList<Integer>(); // TODO
			l.add(1);
			l.add(5);
			l.add(2);
			l.add(7);
			l.add(15);
			// TODO create a list of available seats, taken from the database
			//FlygAlternativPanel fa = new FlygAlternativPanel(this, 156, 2, l);
			FlygAlternativPanel fa = new FlygAlternativPanel(this);
			m.put("FlygAlternativ",  new BorderPanel(this, "FlygAlternativ", fa, fa));
			// ------------------------------------------
			// TODO take info from the database
			//JLabel label = new JLabel("<html>Line One<br>Line Two</br></html>");
			m.put("Info", new BorderPanel(this,"Info", new InfoTreePanel(), "BakÃ¥t", "FramÃ¥t"));
			BekraftelsePanel bp = new BekraftelsePanel(this);
			m.put("Bekräftelse", new BorderPanel(this, "Bekräftelse", bp, bp));
			return m;
		}else if(outerPanel.equals(hotell)){
			// TODO HotellResultatPanel br = new HotellResultatPanel(this, );
			m.put("Svar", new JPanel());
			m.put("HotellAlternativ", new JPanel());
			m.put("Info", new InfoTreePanel());
			BekraftelsePanel bp = new BekraftelsePanel(this);
			m.put("Bekrï¿½ftelse", new BorderPanel(this, "Bekrï¿½ftelse", bp, bp));;
			return m;
		}else if(outerPanel.equals(hyrbil)){
			// TODO BilResultatPanel br = new BilResultatPanel(this, );
			m.put("Svar", new JPanel());
			m.put("BilAlternativ", new JPanel());
			m.put("Info", new InfoTreePanel());
			BekraftelsePanel bp = new BekraftelsePanel(this);
			m.put("Bekrï¿½ftelse", new BorderPanel(this, "Bekrï¿½ftelse", bp, bp));
			return m;
		}else if(outerPanel.equals(evenemang)){
			// TODO EventResultatPanel br = new EventResultatPanel(this, );
			m.put("Svar", new JPanel());
			m.put("EvenemangsAlternativ", new JPanel());
			m.put("Info", new InfoTreePanel());
			BekraftelsePanel bp = new BekraftelsePanel(this);
			m.put("Bekrï¿½ftelse", new BorderPanel(this, "Bekrï¿½ftelse", bp, bp));
			return m;
		}
		return null;
	}
	
	private Map<String, Map<String, JPanel>> createPanelMap(boolean almanacka, boolean filter){
		Map<String, Map<String, JPanel>> panelMap = new LinkedHashMap<String, Map<String, JPanel>>();
		
		Map<String, JPanel> m = new LinkedHashMap<String, JPanel>();
		if(filter){
			FilterPanel fp = new FilterPanel(this);
			m.put("filter", new BorderPanel(this,"filter",fp,fp));
		}
		panelMap.put(flyg, this.createInnerPanelMap(flyg, almanacka, filter));
		panelMap.put(hyrbil, this.createInnerPanelMap(hyrbil, almanacka, filter));
		panelMap.put(hotell, this.createInnerPanelMap(hotell, almanacka, filter));
		panelMap.put(evenemang, this.createInnerPanelMap(evenemang, almanacka, filter));
		
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
		for(String s : this.panelMap.keySet()){
			ret.put(s, new TreePanel(this, null, this.panelMap.get(s).keySet().toArray(new String[0])));
		}
		return ret;
	}
	
	private void addTrees(){
		treePanel.setLayout(new CardLayout());
		int n = 0;
		for(TreePanel tp : this.optionTrees.values()){
			//System.out.println(searchOptions.get(n));
			treePanel.add(tp, searchOptions.get(n));
			n++;
		}
	}
	
	private void initPanels(){
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//mainPanel.setLayout(null);
		
		/**
		 * Units of 32 pixels - gridwidth = 3 -> height in pixels = 96. (if the dimensions are 1024x768)
		 */
		c.gridy = 0;
		c.gridx = 0;
		c.gridheight = 64;
		c.gridwidth = 1; // ?
		try {
			leftExtraPanel = new ImagePanel(ImageIO.read(new File("src/bilder/design4_01.png")), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		leftExtraPanel.setPreferredSize(new Dimension(32*1,768));
		
		
		
		mainPanel.add(leftExtraPanel,c);
		
		c.gridy = 0;
		c.gridx = 1; // ?
		c.gridheight = 6; //?
		c.gridwidth = 30; // ?
		try {
			bannerPanel = new ImagePanel(ImageIO.read(new File("src/bilder/design4_02.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bannerPanel.setPreferredSize(new Dimension(32*30,32*6));
		mainPanel.add(bannerPanel,c);
		
		c.gridy = 6;
		c.gridx = 1;
		c.gridheight = 58;
		c.gridwidth = 6;
		try {
			adPanel = new ImagePanel(ImageIO.read(new File("src/bilder/design4_04.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adPanel.setPreferredSize(new Dimension(32*6,768-32*6));
		mainPanel.add(adPanel,c);
		
		c.gridy = 6;
		c.gridx = 7;
		c.gridheight = 58;
		c.gridwidth = 18;
		//c.fill = GridBagConstraints.BOTH;
		//TODO add some content to the searchPanel so that it expands correctly
		//searchPanel.setPreferredSize(new Dimension(32*18,768-32*6));
		this.searchPanel.addComponentListener(this);
		mainPanel.add(searchPanel,c);
		
		c.gridy = 6;
		c.gridx = 25;
		c.gridheight = 58;
		c.gridwidth = 6;
		
		try {
			rightUsedPanel = new ImagePanel(ImageIO.read(new File("src/bilder/design4_06.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//rightUsedPanel.setLayout(new BoxLayout(this.rightUsedPanel, BoxLayout.PAGE_AXIS));
		rightUsedPanel.setLayout(new BoxLayout(rightUsedPanel, BoxLayout.PAGE_AXIS));
		
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		p.add(this.user);
		p.add(Box.createVerticalStrut(10));
		this.user.getTextField().setColumns(6);
		p.add(this.pass);
		p.add(Box.createVerticalStrut(10));
		this.pass.getTextField().setColumns(6);
		loginButton.addActionListener(this);
		p.add(this.loginButton);
		p.add(Box.createVerticalStrut(500));
		// TODO add actionlistener to the loginbutton to be able to log in.
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.PAGE_AXIS));
		this.changeBookingButton.addActionListener(this);
		this.logoutButton.addActionListener(this);

		p2.add(this.welcomeLabel);
		p2.add(this.changeBookingButton);
		p2.add(this.logoutButton);
		p2.add(Box.createVerticalStrut(500));
		
		loginPanel.setLayout(new CardLayout());
		// TODO add the 'inlogged' version of loginPanel
		loginPanel.add(p, "loggedout");
		loginPanel.add(p2, "loggedin");
		loginPanel.setVisible(true);	
		
		rightUsedPanel.add(loginPanel);
		treePanel.setVisible(true); // TODO false
		rightUsedPanel.add(treePanel);
		//rightUsedPanel.setPreferredSize(new Dimension(32*6,768-32*6));
		mainPanel.add(rightUsedPanel,c);
		
		c.gridy = 0;
		c.gridx = 31;
		c.gridheight = 64;
		c.gridwidth = 1;
		try {
			rightExtraPanel = new ImagePanel(ImageIO.read(new File("src/bilder/design4_03.png")), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rightExtraPanel.setPreferredSize(new Dimension(32*1,768));
		
		mainPanel.add(rightExtraPanel,c);
		
		this.mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(this.mainScrollPane);
		this.updateSizes();
		repaint();
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
	
	public BorderPanel createFlygAlternativ(){
		try {
			Map<Integer, String> m = this.dbh.getAvailableChairsbyPlace(this.flygBokning.getFlightID());
			FlygAlternativPanel fa = new FlygAlternativPanel(this, 154, 1, m);
			return(new BorderPanel(this, "FlygAlternativ", fa, fa));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public BorderPanel createInfoPanel(){
//		try {
//			// TODO
//			String s = dbh.getCountry(this.getCurrentBokning().getOrt());
//			InfoTreePanel ip = new InfoTreePanel(s);
//			return(new BorderPanel(this, "Info", ip));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return null;
	}
	
	public BorderPanel createBekraftelse(){
		BekraftelsePanel bp = new BekraftelsePanel(this, this.getCurrentBokning().toString());
		return new BorderPanel(this, "Bekräftelse", bp, bp);
	}
	
	public Bokning getCurrentBokning(){
		if(this.currentPanel.equals(flyg)){
			return this.flygBokning;
		}else if(this.currentPanel.equals(hotell)){
			return this.hotellBokning;
		}else if(this.currentPanel.equals(hyrbil)){
			return this.bilBokning;
		}else if(this.currentPanel.equals(evenemang)){
			return this.eventBokning;
		}else{
			return null;
		}
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
		new MainWindow();
	}
	
	public void resetTree(String treeName, boolean almanacka, boolean filter){
		this.panelMap.put(treeName, this.createInnerPanelMap(treeName, almanacka, filter));
		this.optionTrees.put(treeName, new TreePanel(this, null, this.panelMap.get(treeName).keySet().toArray(new String[0])));
	}
	
	public void setPanel(String treeName, String panelName, JPanel p){
		for(String s : this.panelMap.keySet()){
			if(s.equals(treeName)){
				for(String panel : this.panelMap.get(s).keySet()){
					if(panel.equals(panelName)){
						this.panelMap.get(s).put(panel, p);
					}
				}
			}
		}
	}
	
	public void flightSearch(String avgangsOrt, String ankomstOrt, Date avgangsDatum,
			Date ankomstDatum, boolean almanacka, boolean filter){
		if(!almanacka && !filter){
			this.flygBokning = new FlygBokning(avgangsOrt, ankomstOrt, avgangsDatum, ankomstDatum);
			this.panelMap.put(flyg, this.createInnerPanelMap(flyg, almanacka, filter));
			this.optionTrees.put(flyg, new TreePanel(this, null, this.panelMap.get(flyg).keySet().toArray(new String[0])));
			this.optionTrees.get(flyg).showPanel("Svar", true);
		}// TODO almanacka/filter?
	}
	
	public void carSearch(Date utlamningsDatum, Date inlamningsDatum, int utlamningsOrt,
			String inlamningsOrt, String kategori){
		this.bilBokning = new BilBokning(utlamningsDatum, inlamningsDatum, utlamningsOrt,inlamningsOrt, kategori);
		this.panelMap.put(hyrbil, this.createInnerPanelMap(hyrbil, false, false));
		this.optionTrees.put(hyrbil, new TreePanel(this, null, this.panelMap.get(hyrbil).keySet().toArray(new String[0])));
		this.optionTrees.get(hyrbil).showPanel("Svar", true);
	}
	
	public void hotelSearch(String ort, Date checkInDatum, Date checkUtDatum, int antalRum, int antalPersoner){
		this.hotellBokning = new HotellBokning(ort, checkInDatum, checkUtDatum, antalRum, antalPersoner);
		this.panelMap.put(hotell, this.createInnerPanelMap(hotell, false, false));
		this.optionTrees.put(hotell, new TreePanel(this, null, this.panelMap.get(hotell).keySet().toArray(new String[0])));
		this.optionTrees.get(hotell).showPanel("Svar", true);
	}
	
	public void eventSearch(String ort, Date datum, boolean almanacka){
		this.eventBokning = new EventBokning(ort, datum);
		this.panelMap.put(evenemang, this.createInnerPanelMap(evenemang, almanacka, false));
		this.optionTrees.put(evenemang, new TreePanel(this, null, this.panelMap.get(evenemang).keySet().toArray(new String[0])));
		this.optionTrees.get(evenemang).showPanel("Svar", true);
	}
	
	public void infoSearch(String land){
		// TODO don't do shit
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(this.loginButton)){
			// TODO if(this.dbh.LOGIN!?? this.user.getTextField().getText());
					//this.welcomeLabel.setText("Welcome " + this.user.getTextField().getText());
					//((CardLayout)this.loginPanel.getLayout()).next(loginPanel);
					//this.userLoggedIn = true;
		}else if(e.getSource().equals(this.changeBookingButton)){
			// TODO ...
		}else if(e.getSource().equals(this.logoutButton)){
			// TODO something.
			((CardLayout)this.loginPanel.getLayout()).next(loginPanel);
			this.user.getTextField().setText("");
			this.pass.getTextField().setText("");
			this.userLoggedIn = false;
		}
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		/*// TODO make it work! + remove error-output
		if(e.getSource().equals(this.searchPanel)){
			//this.adPanel.setPreferredSize(new Dimension(this.adPanel.getWidth(), this.bannerPanel.getHeight()+this.searchPanel.getHeight()));
			this.adPanel.setPreferredSize(new Dimension(this.adPanel.getWidth(), this.mainPanel.getHeight()-this.bannerPanel.getHeight()));
			this.adPanel.setMinimumSize(new Dimension(this.adPanel.getWidth(), this.mainPanel.getHeight()-this.bannerPanel.getHeight()));
//			System.out.println("main-banner: " + (this.mainPanel.getHeight()-this.bannerPanel.getHeight()) +
//					", search: " + this.searchPanel.getHeight() + ", ad: " + this.adPanel.getHeight());
			this.adPanel.repaint();
			leftExtraPanel.setPreferredSize(new Dimension(leftExtraPanel.getWidth(), this.mainPanel.getHeight()));
			rightExtraPanel.setPreferredSize(new Dimension(rightExtraPanel.getWidth(), this.mainPanel.getHeight()));
			this.mainScrollPane.getVerticalScrollBar().setValue(this.mainScrollPane.getVerticalScrollBar().getMaximum());
			// TODO Change here to make the scrollpane work differently when panels are shown/hidden.
		}*/
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
	}
	public void paint(Graphics g){
		this.updateSizes();
		super.paint(g);
	}
	
	void updateSizes(){
		System.out.println("Update");
		double h,w;
		LayoutManager l;
		
		l = bannerPanel.getLayout();
		l.layoutContainer(bannerPanel);
		h = l.preferredLayoutSize(bannerPanel).getHeight();
		w = 960;
		//bannerPanel.setBounds(32, 0, (int)w, (int)h);
		bannerPanel.setPreferredSize(new Dimension((int)w, (int)h));
		bannerPanel.setMinimumSize(new Dimension((int)w, (int)h));
		
		l = leftExtraPanel.getLayout();
		l.layoutContainer(leftExtraPanel);
		h = l.preferredLayoutSize(leftExtraPanel).getHeight();
		w = 32;
		//leftExtraPanel.setBounds(0, 0, (int)w, (int)h);
		leftExtraPanel.setPreferredSize(new Dimension((int)w, (int)h));
		leftExtraPanel.setMinimumSize(new Dimension((int)w, (int)h));
		
		
		l = rightExtraPanel.getLayout();
		l.layoutContainer(rightExtraPanel);
		h = l.preferredLayoutSize(rightExtraPanel).getHeight();
		w = 32;
		//rightExtraPanel.setBounds(992, 0, (int)w, (int)h);
		rightExtraPanel.setPreferredSize(new Dimension((int)w, (int)h));
		rightExtraPanel.setMinimumSize(new Dimension((int)w, (int)h));
		
		l = adPanel.getLayout();
		l.layoutContainer(adPanel);
		h = l.preferredLayoutSize(adPanel).getHeight();
		w = 192;
		//adPanel.setBounds(32, 192, (int)w, (int)h);
		adPanel.setPreferredSize(new Dimension((int)w, (int)h));
		adPanel.setMinimumSize(new Dimension((int)w, (int)h));
		
		l = searchPanel.getLayout();
		l.layoutContainer(searchPanel);
		h = l.preferredLayoutSize(searchPanel).getHeight();
		w = 576;
		//searchPanel.setBounds(224, 192, (int)w, (int)h);
		searchPanel.setPreferredSize(new Dimension((int)w, (int)h));
		searchPanel.setMinimumSize(new Dimension((int)w, (int)h));
		
		l = rightUsedPanel.getLayout();
		l.layoutContainer(rightUsedPanel);
		h = l.preferredLayoutSize(rightUsedPanel).getHeight();
		w = 192;
		//rightUsedPanel.setBounds(736, 192, (int)w, (int)h);
		rightUsedPanel.setPreferredSize(new Dimension((int)w, (int)h));
		rightUsedPanel.setMinimumSize(new Dimension((int)w, (int)h));
		
		l = loginPanel.getLayout();
		l.layoutContainer(loginPanel);
		h = l.preferredLayoutSize(loginPanel).getHeight();
		w = 192;
		//loginPanel.setBounds(736, 192, (int)w, (int)h);
		loginPanel.setPreferredSize(new Dimension((int)w, (int)h));
		loginPanel.setMinimumSize(new Dimension((int)w, (int)h));
		
		l = treePanel.getLayout();
		l.layoutContainer(treePanel);
		h = l.preferredLayoutSize(treePanel).getHeight();
		w = 192;
		//treePanel.setBounds(736, 768, (int)w, (int)h);
		treePanel.setPreferredSize(new Dimension((int)w, (int)h));
		treePanel.setMinimumSize(new Dimension((int)w, (int)h));
	}
}
