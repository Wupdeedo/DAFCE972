import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

import com.toedter.calendar.*;// This is a borrowed third party lib


public class Boll extends JPanel implements MouseListener, ComponentListener{
	
	private double w,h;
	private Point2D center;
	private GeneralPath fp,hp,bp,ip,ep,pb,p1,p2,p3,p4;
	private FlyPanel fl;
	private JPanel ho;
	private JPanel bi;
	private JPanel in;
	private JPanel ev;
	private CardLayout cl;
	private int state;
	
	public Boll(){
		state = 1;
		Dimension dim = new Dimension(576,576);
		this.setMinimumSize(dim);
		this.setMaximumSize(dim);
		this.setPreferredSize(dim);
		this.setOpaque(false);
		cl = new CardLayout();
		this.setLayout(cl);
		
		setup();
		
		this.addMouseListener(this);
		this.addComponentListener(this);
		
		fl = new FlyPanel();
		ho = new HotelPanel();
		bi = new CarPanel();
		in = new InfoPanel();
		ev = new EventPanel();

		this.add(fl,"flyg");
		this.add(bi,"bil");
		this.add(ho,"hotell");
		this.add(in,"info");
		this.add(ev,"evenemang");
	}
/*
	public void paint(Graphics graph){
		Graphics2D g = (Graphics2D) graph;
		state();
		this.paintComponents(graph);
		g.setColor(Color.cyan);
		g.draw(fp);
		g.setColor(Color.magenta);
		g.draw(hp);
		g.setColor(Color.yellow);
		g.draw(bp);
		g.setColor(Color.red); 
		g.draw(ip);
		g.setColor(Color.green); 
		g.draw(ep);
	}*/
	private void state(){
		switch(state){
			case 5: ep = pb;fp = p1;hp = p2;bp = p3;ip = p4;break;
			case 4: ip = pb;fp = p1;hp = p2;bp = p3;ep = p4;break;
			case 3: bp = pb;fp = p1;hp = p2;ep = p3;ip = p4;break;
			case 2: hp = pb;fp = p1;bp = p2;ep = p3;ip = p4;break;
			case 1: 
			default:fp = pb;hp = p1;bp = p2;ep = p3;ip = p4;
		}
	}
	
	private void setup(){
		w = this.getWidth();
		h = this.getHeight();
		center = new Point2D.Double(224,296);
		double ex = 22.4;
		double start = 138;
		Arc2D a = new Arc2D.Double(33,37,w-64,h-64,start,ex,Arc2D.OPEN);
		p1 = new GeneralPath();
		p1.moveTo(center.getX(), center.getY());
		p1.append(a, true);
		a.setAngleStart(start+1*ex);
		p2 = new GeneralPath();
		p2.moveTo(center.getX(), center.getY());
		p2.append(a, true);
		a.setAngleStart(start+2*ex);
		p3 = new GeneralPath();
		p3.moveTo(center.getX(), center.getY());
		p3.append(a, true);
		a.setAngleStart(start+3*ex);
		p4 = new GeneralPath();
		p4.moveTo(center.getX(), center.getY());
		p4.append(a, true);
		a.setAngleStart(start+4*ex);
		a.setAngleExtent(360-4*ex);
		pb = new GeneralPath();
		pb.moveTo(center.getX(), center.getY());
		pb.append(a, true);
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {
		Point2D p = e.getPoint();
		if(fp.contains(p)){
			cl.show(this,"flyg");
			state = 1;
		}else if(hp.contains(p)){
			cl.show(this,"hotell");
			state = 2;
		}else if(bp.contains(p)){
			cl.show(this,"bil");
			state = 3;
		}else if(ip.contains(p)){
			cl.show(this,"info");
			state = 4;
		}else if(ep.contains(p)){
			cl.show(this,"evenemang");
			state = 5;
		}
		state();
		this.repaint();
	}
	public void mouseReleased(MouseEvent arg0) {}
	public void componentResized(ComponentEvent arg0) {
		setup();
		state();
		invalidate();
		repaint();
	}
	public void componentShown(ComponentEvent arg0) {}
	public void componentHidden(ComponentEvent arg0) {}
	public void componentMoved(ComponentEvent arg0) {}

}

class FlyPanel extends JPanel{
	
	public JLabel fromLab, toLab, fromDat, toDat;
	public JTextField fromTex, toTex;
	public JCheckBox price,filter;
	public JDateChooser dat1,dat2;// Borrowed from third party lib
	public JButton search,clear;
	
	public FlyPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("Sök");
		clear = new JButton("Rensa");
		
		price = new JCheckBox("<html><font size=4><u>Prisalmanacka</u></font><br>Sök bästa pris runt valda datum.</html>");
		price.setHorizontalTextPosition(SwingConstants.LEFT);
		filter = new JCheckBox("<html><font size=4><u>Filter</u></font><br>Filtrera sökning efter specifika parametrar.</html>");
		filter.setHorizontalTextPosition(SwingConstants.LEFT);
		
		fromLab = new JLabel("Från: ");
		toLab = new JLabel("Till:   ");
		fromDat = new JLabel("Avresedatum:   ");
		toDat = new JLabel("Hemkomstdatum: ");
		
		fromTex = new JTextField();
		fromTex.setMaximumSize(dim);
		toTex = new JTextField();
		toTex.setMaximumSize(dim);
		
		dat1 = new JDateChooser();
		dat1.setLocale(new Locale("se","se"));
		dat1.setDateFormatString("yyyy.MM.dd");
		dat1.setMaximumSize(dim);
		
		dat2 = new JDateChooser();
		dat2.setLocale(new Locale("se","se"));
		dat2.setDateFormatString("yyyy.MM.dd");
		dat2.setMaximumSize(dim);
		
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(110,110)
						.addComponent(fromLab)
						.addComponent(fromTex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(110,110)
						.addComponent(toLab)
						.addComponent(toTex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(210,210)
						.addComponent(fromDat)
						.addComponent(dat1)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(210,210)
						.addComponent(toDat)
						.addComponent(dat2)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(230,230)
						.addComponent(price)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(210,210)
						.addComponent(filter)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(90,90)
						.addComponent(clear)
						.addGap(80)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(150,150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromLab)
					.addComponent(fromTex)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toLab)
					.addComponent(toTex)
				)
				.addGap(20)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromDat)
					.addComponent(dat1)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toDat)
					.addComponent(dat2)
				)
				.addGap(30)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(price)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(filter)
				)
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(clear)
					.addComponent(search)
				)
		);
	}
	public void paint(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		BufferedImage img;
		try {
			img = ImageIO.read(new File("src/bilder/flyg.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}

class CarPanel extends JPanel{
	
	public JLabel fromLab, toLab, fromDat, toDat, carcatlab, samelab;
	public JTextField fromTex, toTex;
	public JCheckBox same;
	public JDateChooser dat1,dat2;// Borrowed from third party lib
	public JButton search,clear;
	public JComboBox carcat;
	
	public CarPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("Sök");
		clear = new JButton("Rensa");
		
		same= new JCheckBox();
		
		fromLab = new JLabel("Utlämningsplats: ");
		toLab = new JLabel("Inlämningsplats: ");
		fromDat = new JLabel("Utlämningsdatum: ");
		toDat = new JLabel("Inlämningsdatum: ");
		carcatlab = new JLabel("Bilkategori: ");
		samelab = new JLabel("<html>Samma som<br>utlämning</html>");
		samelab.setMaximumSize(new Dimension(30,10));
		
		fromTex = new JTextField();
		fromTex.setMaximumSize(dim);
		toTex = new JTextField();
		toTex.setMaximumSize(dim);
		
		dat1 = new JDateChooser();
		dat1.setLocale(new Locale("se","se"));
		dat1.setDateFormatString("yyyy.MM.dd");
		dat1.setMaximumSize(dim);
		
		dat2 = new JDateChooser();
		dat2.setLocale(new Locale("se","se"));
		dat2.setDateFormatString("yyyy.MM.dd");
		dat2.setMaximumSize(dim);
		
		String[] carcats = { "cat1", "cat2", "cat3"};
		carcat = new JComboBox(carcats);
		carcat.setSelectedIndex(0);
		carcat.setMaximumSize(dim);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(200,200)
						.addComponent(fromDat)
						.addComponent(dat1)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(200,200)
						.addComponent(toDat)
						.addComponent(dat2)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(fromLab)
						.addComponent(fromTex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(toLab)
						.addComponent(toTex)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(same)
							.addComponent(samelab)
						)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(carcatlab)
						.addComponent(carcat)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(210,210)
						.addComponent(clear)
						.addGap(80)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(150,150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromDat)
					.addComponent(dat1)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toDat)
					.addComponent(dat2)
				)
				.addGap(30)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromLab)
					.addComponent(fromTex)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toLab)
					.addComponent(toTex)
					.addComponent(same)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(samelab)
				)
				.addGap(30)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(carcatlab)
					.addComponent(carcat)
				)
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(clear)
					.addComponent(search)
				)
		);
	}
	public void paint(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		BufferedImage img;
		try {
			img = ImageIO.read(new File("src/bilder/bil.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}



class HotelPanel extends JPanel{
	
	public JLabel ortlab, fromDat, toDat, nroom, nrpeep;
	public JTextField ortex, nrotex, nrptex;
	public JDateChooser dat1,dat2;// Borrowed from third party lib
	public JButton search,clear;
	
	public HotelPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		Dimension dim2 = new Dimension(30,20);
		
		search = new JButton("Sök");
		clear = new JButton("Rensa");

		
		ortlab = new JLabel("Ort: ");
		nroom = new JLabel("Antal rum: ");
		fromDat = new JLabel("Incheckningsdatum: ");
		toDat = new JLabel("Utcheckningsdatum: ");
		nrpeep = new JLabel("Antal personer: ");
		
		ortex = new JTextField();
		ortex.setMaximumSize(dim);
		nrotex = new JTextField();
		nrotex.setMaximumSize(dim2);
		nrptex = new JTextField();
		nrptex.setMaximumSize(dim2);
		
		dat1 = new JDateChooser();
		dat1.setLocale(new Locale("se","se"));
		dat1.setDateFormatString("yyyy.MM.dd");
		dat1.setMaximumSize(dim);
		
		dat2 = new JDateChooser();
		dat2.setLocale(new Locale("se","se"));
		dat2.setDateFormatString("yyyy.MM.dd");
		dat2.setMaximumSize(dim);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(150,150)
						.addComponent(ortlab)
						.addComponent(ortex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(fromDat)
						.addComponent(dat1)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(toDat)
						.addComponent(dat2)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(nroom)
						.addComponent(nrotex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(nrpeep)
						.addComponent(nrptex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(110,110)
						.addComponent(clear)
						.addGap(80)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(150,150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(ortlab)
					.addComponent(ortex)
				)
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromDat)
					.addComponent(dat1)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toDat)
					.addComponent(dat2)
				)
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(nroom)
					.addComponent(nrotex)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(nrpeep)
					.addComponent(nrptex)
				)
				.addGap(70)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(clear)
					.addComponent(search)
				)
		);
	}
	public void paint(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		BufferedImage img;
		try {
			img = ImageIO.read(new File("src/bilder/hotell.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}


class InfoPanel extends JPanel{
	
	public JLabel infolab;
	public JTextField infotex;
	public JButton search;
	
	public InfoPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("Sök");

		
		infolab = new JLabel("Land: ");
		
		infotex = new JTextField();
		infotex.setMaximumSize(dim);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(280,280)
						.addComponent(infolab)
						.addComponent(infotex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(180,180)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(infolab)
						.addComponent(infotex)
				)
				.addGap(150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(search)
				)
		);
	}
	public void paint(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		BufferedImage img;
		try {
			img = ImageIO.read(new File("src/bilder/info.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}


class EventPanel extends JPanel{
	
	public JLabel ortlab, datlab;
	public JTextField ortex;
	public JDateChooser dat1;// Borrowed from third party lib
	public JButton search,clear;
	
	public EventPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("Sök");
		clear = new JButton("Rensa");

		ortlab = new JLabel("Ort: ");
		datlab = new JLabel("Datum:   ");
		
		ortex = new JTextField();
		ortex.setMaximumSize(dim);
		
		dat1 = new JDateChooser();
		dat1.setLocale(new Locale("se","se"));
		dat1.setDateFormatString("yyyy.MM.dd");
		dat1.setMaximumSize(dim);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(180,180)
						.addComponent(ortlab)
						.addComponent(ortex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(180,180)
						.addComponent(datlab)
						.addComponent(dat1)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(200,200)
						.addComponent(clear)
						.addGap(80)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(150,150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(ortlab)
					.addComponent(ortex)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(datlab)
					.addComponent(dat1)
				)
				.addGap(30)
				.addGap(100)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(clear)
					.addComponent(search)
				)
		);
	}
	public void paint(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		BufferedImage img;
		try {
			img = ImageIO.read(new File("src/bilder/event.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}