import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;


public class Kundkorg extends JPanel implements MouseListener{
	private GeneralPath kop,area;
	private Point2D center;
	private ArrayList<Slice> slices;
	private double w,h;
	
	public static void main(String[] args){
		JFrame jf = new JFrame();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setBounds(100, 100, 512, 512);
		jf.getContentPane().add(new Kundkorg());
		jf.setVisible(true);
	}
	
	public Kundkorg(){
		slices = new ArrayList<Slice>();
		Bokning bok = new Bokning();
	}
	
	public void addBooking(Bokning b){
		slices.add(new Slice(b));
		setup();
	}
	
	private void setup(){
		w = this.getWidth();
		h = this.getHeight();
		center = new Point2D.Double(w/2.5,h/2);
		
		double start = -60;
		double deg = 270;
		double n = slices.size();
		deg = deg / n;
		int i = 1;
		GeneralPath bounds;
		
		Arc2D a = new Arc2D.Double(0,0,w,h,start,deg,Arc2D.OPEN);
		
		for (Slice s : slices){
			bounds = new GeneralPath();
			bounds.moveTo(center.getX(), center.getY());
			bounds.append(a, true);
			s.setBounds(bounds);
			a.setAngleStart(start+i*deg);
			i++;
		}
		
	}
	
	public void paint(Graphics graph){
		Graphics2D g = (Graphics2D) graph;
		this.paintComponents(graph);
		for (Slice s : slices){
			g.draw(s.getBounds());
		}
		
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent arg0) {}
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent e) {
		Point2D p = e.getPoint();
		
	}
	public void mouseReleased(MouseEvent e) {}
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
