import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.*;


public class Boll extends JPanel implements MouseListener, ComponentListener{
	
	private double w;
	private double h;
	private Point2D center;
	private GeneralPath fp;
	private GeneralPath hp;
	private GeneralPath bp;
	private GeneralPath ip;
	private GeneralPath ep;
	private GeneralPath pb;
	private GeneralPath p1;
	private GeneralPath p2;
	private GeneralPath p3;
	private GeneralPath p4;
	private JPanel fl;
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
		
		fl = new JPanel();
		fl.setOpaque(false);
		fl.add(new JLabel("Flyg"));
		fl.add(new JLabel("Från: "));
		
		
		
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
			case 5: ep = pb;
					fp = p1;
					hp = p2;
					bp = p3;
					ip = p4;
					break;
			case 4: ip = pb;
					fp = p1;
					hp = p2;
					bp = p3;
					ep = p4;
					break;
			case 3: bp = pb;
					fp = p1;
					hp = p2;
					ip = p3;
					ep = p4;
					break;
			case 2: hp = pb;
					fp = p1;
					bp = p2;
					ip = p3;
					ep = p4;
					break;
			case 1: 
			default:fp = pb;
					hp = p1;
					bp = p2;
					ip = p3;
					ep = p4;
		}
	}
	
	private void setup(){
		w = this.getWidth();
		h = this.getHeight();
		center = new Point2D.Double(w/2-42,h/2);
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
		repaint();
	}
	public void componentShown(ComponentEvent arg0) {}
	public void componentHidden(ComponentEvent arg0) {}
	public void componentMoved(ComponentEvent arg0) {}

}
