package boll;
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




