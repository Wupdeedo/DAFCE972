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
	
	public Kundkorg(){
		
	}
	
	public void addBooking(Bokning b){
		slices.add(new Slice(b));
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
