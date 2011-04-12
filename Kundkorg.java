import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Kundkorg extends JPanel implements MouseListener, ComponentListener{
	private GeneralPath kop;
	private Point2D center;
	private ArrayList<Slice> slices;
	private double w,h;
	private JLabel sumlab, sum;
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds(100, 100, 576, 576);
		jf.getContentPane().add(new Kundkorg());
		jf.setVisible(true);
	}
	
	public Kundkorg(){
		slices = new ArrayList<Slice>();
		FlygBokning fb1 = new FlygBokning();
		FlygBokning fb2 = new FlygBokning();
		FlygBokning fb3 = new FlygBokning();
		FlygBokning fb4 = new FlygBokning();
		FlygBokning fb5 = new FlygBokning();
		slices.add(new Slice(fb1));
		slices.add(new Slice(fb2));
		slices.add(new Slice(fb3));
		slices.add(new Slice(fb4));
		slices.add(new Slice(fb5));
		setup();
		kop = new GeneralPath();
		Arc2D a = new Arc2D.Double(30,20,515,515,-77.9,47.5,Arc2D.OPEN);
		kop.moveTo(341,408);
		kop.append(a, true);
		
		this.setLayout(null);
		
		sumlab = new JLabel("<html><h2>Summa: </h2></html>");
		sum = new JLabel("<html><h2>1337 kr</h2></html>");
		this.add(sum);
		this.add(sumlab);
		sumlab.setBounds(150, 450,100,20);
		sumlab.setForeground(Color.red);
		sum.setBounds(250, 450,100,20);
		sum.setForeground(Color.red);
		
		repaint();
		
		this.addComponentListener(this);
		this.addMouseListener(this);
	}
	
	public void addBooking(Bokning b){
		slices.add(new Slice(b));
		setup();
	}
	
	private void setup(){
		w = this.getWidth();
		h = this.getHeight();
		center = new Point2D.Double(w/2,408);
		
		double start = -30.5;
		double deg = 241;
		double n = slices.size();
		deg = deg / n;
		int i = 1;
		GeneralPath bounds;
		
		Arc2D a = new Arc2D.Double(30,20,515,515,start,deg,Arc2D.OPEN);
		
		for (Slice s : slices){
			bounds = new GeneralPath();
			bounds.moveTo(center.getX(), center.getY());
			bounds.append(a, true);
			s.setBounds(bounds);
			a.setAngleStart(start+i*deg);
			i++;
		}
		repaint();
	}
	
	public void paint(Graphics graph){
		Graphics2D g = (Graphics2D) graph;
		try {
			g.drawImage(ImageIO.read(new File("src/kundkorg.png")),0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Slice s : slices){
			Shape b = s.getBounds();
			g.fill(b);
			g.setColor(Color.blue);
			g.draw(b);
			g.setColor(Color.black);
		}
		g.setColor(Color.red);
		g.draw(kop);
		this.paintComponents(graph);
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {
		Point2D p = e.getPoint();
		
	}
	public void mouseReleased(MouseEvent e) {}
	public void componentResized(ComponentEvent arg0) {
		setup();
		invalidate();
		repaint();
	}
	public void componentShown(ComponentEvent arg0) {}
	public void componentHidden(ComponentEvent arg0) {}
	public void componentMoved(ComponentEvent arg0) {}
}

class Slice {
	private Bokning bokning;
	private Shape bounds;
	public Slice(Bokning bok){
		bounds = null;
		this.setBokning(bok);
	}
	public void setBokning(Bokning bokning) {
		this.bokning = bokning;
	}
	public Bokning getBokning() {
		return bokning;
	}
	public void setBounds(Shape bounds) {
		this.bounds = bounds;
	}
	public Shape getBounds() {
		return bounds;
	}
}
