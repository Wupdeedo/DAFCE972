package mainwindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class FlygAlternativPanel extends JPanel implements MouseListener, AnswerPanel{
	private static final long serialVersionUID = 1L;
	private int numSeats;
	private int xinset = 120;
	private int yinset = 65;
	private int seatWidth = 5;
	private int diffWidth = 5;
	private int seatHeight = 5;
	private int diffHeight = 3;
	private int midlaneHeight = 20;
	private int unChosen;
	private int seatsPerColumn = 3;
	private List<Integer> freeSeats; // Booked by others
	private List<Integer> usedSeats; // To be booked by the user
	private Map<Integer, String> freeSeatsbyClass;
	private Map<Integer, String> usedSeatsbyClass;
	private MainWindow parent;
	private boolean hasInfo = false;
	private boolean hasClassInfo = false;
	BufferedImage img;
	private int planeLength = 400;
	private String reprimand = "Ni måste välja ett flyg innan ni väljer en plats";
	
	public FlygAlternativPanel(MainWindow mw, int numSeats, int numSeatsToFill, List<Integer> freeSeats){
		this.parent = mw;
		this.numSeats = numSeats;
		this.freeSeats = freeSeats;
		this.unChosen = numSeatsToFill;
		this.usedSeats = new LinkedList<Integer>();
		this.addMouseListener(this);
		hasInfo = true;
		try {
			this.img = ImageIO.read(new File("src/bilder/Flygplan.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
		this.seatWidth = (planeLength*this.seatsPerColumn*2-diffWidth*numSeats)/numSeats;
	}
	
	public FlygAlternativPanel(MainWindow mw, int numSeats, int numSeatsToFill, Map<Integer, String> freeSeats){
		this.parent = mw;
		this.numSeats = numSeats;
		this.freeSeatsbyClass = freeSeats;
		this.unChosen = numSeatsToFill;
		this.usedSeats = new LinkedList<Integer>();
		this.addMouseListener(this);
		hasInfo = true;
		this.hasClassInfo = true;
		try {
			this.img = ImageIO.read(new File("src/bilder/Flygplan.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}
	
	public FlygAlternativPanel(MainWindow mw){
		this.parent = mw;
		this.addMouseListener(this);
		try {
			this.img = ImageIO.read(new File("src/bilder/Flygplan.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		this.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(hasInfo){
			g.drawString("Klicka på en plats för att välja/avvälja den", 0, 0);
			g.drawString("färgkodning: röd: plats upptagen, blå: plats ledig, grön: plats vald av er", 0, 10);
			g.drawImage(this.img, 0, 0, null);
			
			g.setColor(Color.red);
			for(int m = 0; m < 2; m++){
				for(int n = 0; n < this.numSeats/2; n++){
					int x = n%(this.numSeats/(2*this.seatsPerColumn));
					int y = n/(this.numSeats/(2*this.seatsPerColumn));
					g.fillRect(this.xinset+x*(this.seatWidth+this.diffWidth), 
							this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight),
							this.seatWidth, this.seatHeight);
					//System.out.println("x: " + x + " y: " + y + " ycoord: " + (this.yinset+y*(this.seatHeight+this.diffHeight)));
				}
			}
			g.setColor(Color.blue);
			if(this.hasClassInfo){
				for(Entry<Integer, String> e : freeSeatsbyClass.entrySet()){
					int m = e.getKey()/(this.numSeats/2);
					int n = e.getKey()%(this.numSeats/2);
					int x = n%(this.numSeats/(2*this.seatsPerColumn));
					int y = n/(this.numSeats/(2*this.seatsPerColumn));
					g.fillRect(this.xinset+x*(this.seatWidth+this.diffWidth), 
							this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight),
							this.seatWidth, this.seatHeight);
					g.drawString(e.getValue(), this.xinset+x*(this.seatWidth+this.diffWidth), 
							this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight));
				}
			}else{
				for(int seat : freeSeats){
					int m = seat/(this.numSeats/2);
					int n = seat%(this.numSeats/2);
					int x = n%(this.numSeats/(2*this.seatsPerColumn));
					int y = n/(this.numSeats/(2*this.seatsPerColumn));
					g.fillRect(this.xinset+x*(this.seatWidth+this.diffWidth), 
							this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight),
							this.seatWidth, this.seatHeight);
				}
			}
			g.setColor(Color.green);
			if(this.hasClassInfo){
				for(Entry<Integer, String> e : usedSeatsbyClass.entrySet()){
					int m = e.getKey()/(this.numSeats/2);
					int n = e.getKey()%(this.numSeats/2);
					int x = n%(this.numSeats/(2*this.seatsPerColumn));
					int y = n/(this.numSeats/(2*this.seatsPerColumn));
					g.fillRect(this.xinset+x*(this.seatWidth+this.diffWidth), 
							this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight),
							this.seatWidth, this.seatHeight);
					g.drawString(e.getValue(), this.xinset+x*(this.seatWidth+this.diffWidth), 
							this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight));
				}
			}else{
				for(int seat : usedSeats){
					int m = seat/(this.numSeats/2);
					int n = seat%(this.numSeats/2);
					int x = n%(this.numSeats/(2*this.seatsPerColumn));
					int y = n/(this.numSeats/(2*this.seatsPerColumn));
					g.fillRect(this.xinset+x*(this.seatWidth+this.diffWidth), 
							this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight),
							this.seatWidth, this.seatHeight);
				}
			}
		}else{
			g.drawString(this.reprimand, this.xinset, this.yinset);
		}
	}
	
	public void setInfo(int numSeats, int numSeatsToFill, List<Integer> freeSeats){
		this.numSeats = numSeats;
		this.freeSeats = freeSeats;
		this.unChosen = numSeatsToFill;
		this.usedSeats = new LinkedList<Integer>();
		this.hasInfo = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(hasInfo){
			for(int m = 0; m < 2; m++){
				for(int n = 0; n < this.numSeats/2; n++){
					int x = n%(this.numSeats/(2*this.seatsPerColumn));
					int y = n/(this.numSeats/(2*this.seatsPerColumn));
					if(e.getX() >= this.xinset+x*(this.seatWidth+this.diffWidth) && 
							e.getX() <= this.xinset+x*(this.seatWidth+this.diffWidth)+this.seatWidth &&
							e.getY() >= this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight) &&
							e.getY() <= this.yinset+y*(this.seatHeight+this.diffHeight)+this.seatHeight+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight)){
						if(!freeSeats.contains(m*this.numSeats/2+n)){
							return;
						}else if(usedSeats.contains(m*this.numSeats/2+n)){
							usedSeats.remove((Object)(m*this.numSeats/2+n));
							this.repaint();
							return;
						}else if(this.unChosen > this.usedSeats.size()){
							usedSeats.add(m*this.numSeats/2+n);
							this.repaint();
							return;
						}else{
							return;
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	@Override
	public void CancelAction() {
		// TODO
	}

	@Override
	public void OkAction() {
		if(hasInfo){
			if(this.hasClassInfo){
				List<Integer> l = new LinkedList<Integer>();
				for(int i : this.usedSeatsbyClass.keySet())
					l.add(i);
				this.parent.flygBokning.setPlatsNummer(l);
			}else{
				this.parent.flygBokning.setPlatsNummer(this.usedSeats);
			}
			this.parent.setPanel(MainWindow.flyg, "Bekr�ftelse", parent.createBekraftelse()); // TODO add this on all the middlepanels.
		}else{
			this.parent.showOptionPanel("FlygAlternativ", true);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).switchButtons("FlygAlternativ");
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).setButtonDone("FlygAlternativ", false);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).showNextPanel("FlygAlternativ", false);
		}
	}
}
