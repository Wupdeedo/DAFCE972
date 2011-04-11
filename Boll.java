import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
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
	
	public static void main(String[] args){
		Boll boll = new Boll();
		JFrame jf = new JFrame();
		jf.setBounds(100, 100, 512, 512);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().add(boll);
		jf.setVisible(true);
	}
	
	public Boll(){
		state = 1;
		Dimension dim = new Dimension(512,512);
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
		
		
		ho = new JPanel();
		ho.setOpaque(false);
		ho.add(new JButton("Hotell"));
		
		
		bi = new JPanel();
		bi.setOpaque(false);
		bi.add(new JButton("Bil"));
		
		
		in = new JPanel();
		in.setOpaque(false);
		in.add(new JButton("Info"));
		
		ev = new JPanel();
		ev.setOpaque(false);
		ev.add(new JButton("Evenemang"));
		
		
		this.add(fl,"flyg");
		this.add(bi,"bil");
		this.add(ho,"hotell");
		this.add(in,"info");
		this.add(ev,"evenemang");
	}
	
	public void paint(Graphics graph){
		Graphics2D g = (Graphics2D) graph;
		state();
		g.setColor(Color.cyan);
		g.fill(fp);
		g.setColor(Color.magenta);
		g.fill(hp);
		g.setColor(Color.yellow);
		g.fill(bp);
		g.setColor(Color.black); 
		g.fill(ip);
		g.setColor(Color.green); 
		g.fill(ep);
		this.paintComponents(graph);
	}
	private void state(){
		switch(state){
			case 5: ep = pb;fp = p1;hp = p2;bp = p3;ip = p4;break;
			case 4: ip = pb;fp = p1;hp = p2;bp = p3;ep = p4;break;
			case 3: bp = pb;fp = p1;hp = p2;ip = p3;ep = p4;break;
			case 2: hp = pb;fp = p1;bp = p2;ip = p3;ep = p4;break;
			case 1: 
			default:fp = pb;hp = p1;bp = p2;ip = p3;ep = p4;
		}
	}
	
	private void setup(){
		w = this.getWidth();
		h = this.getHeight();
		center = new Point2D.Double(w/2.5,h/2);
		Arc2D a = new Arc2D.Double(0,0,w,h,120,30,Arc2D.OPEN);
		p1 = new GeneralPath();
		p1.moveTo(center.getX(), center.getY());
		p1.append(a, true);
		a.setAngleStart(150);
		p2 = new GeneralPath();
		p2.moveTo(center.getX(), center.getY());
		p2.append(a, true);
		a.setAngleStart(180);
		p3 = new GeneralPath();
		p3.moveTo(center.getX(), center.getY());
		p3.append(a, true);
		a.setAngleStart(210);
		p4 = new GeneralPath();
		p4.moveTo(center.getX(), center.getY());
		p4.append(a, true);
		a.setAngleStart(240);
		a.setAngleExtent(240);
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
	JDateChooser dat1,dat2;// Borrowed from third party lib
	
	public FlyPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
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
						.addContainerGap(150,150)
						.addComponent(fromLab)
						.addComponent(fromTex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(150,150)
						.addComponent(toLab)
						.addComponent(toTex)
				)
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
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(75,75)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromLab)
					.addComponent(fromTex)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toLab)
					.addComponent(toTex)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromDat)
					.addComponent(dat1)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toDat)
					.addComponent(dat2)
				)
		);
	}
	
}
