import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;


public class FlygAlternativPanel extends JPanel implements MouseListener, AnswerPanel{
	private static final long serialVersionUID = 1L;
	private int numSeats;
	private int xinset = 40;
	private int yinset = 40;
	private int seatWidth = 5;
	private int diffWidth = 2;
	private int seatHeight = 5;
	private int diffHeight = 5;
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

	public FlygAlternativPanel(MainWindow mw, int numSeats, int numSeatsToFill, List<Integer> freeSeats){
		this.parent = mw;
		this.numSeats = numSeats;
		this.freeSeats = freeSeats;
		this.unChosen = numSeatsToFill;
		this.usedSeats = new LinkedList<Integer>();
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(400,100));
		hasInfo = true;
	}
	
	public FlygAlternativPanel(MainWindow mw, int numSeats, int numSeatsToFill, Map<Integer, String> freeSeats){
		this.parent = mw;
		this.numSeats = numSeats;
		this.freeSeatsbyClass = freeSeats;
		this.unChosen = numSeatsToFill;
		this.usedSeats = new LinkedList<Integer>();
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(400,100));
		hasInfo = true;
		this.hasClassInfo = true;
	}
	
	public FlygAlternativPanel(MainWindow mw){
		this.parent = mw;
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(400,100));
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(hasInfo){
			g.drawString("Klicka på en plats för att välja/avvälja den", 0, 0);
			g.drawString("färgkodning: röd: plats upptagen, blå: plats ledig, grön: plats vald av er", 0, 10);
			// TODO g.drawImage(ImageIO.read(new File("flygaltpanel.jpg")), 0, 0, null);
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
			g.drawString("Ni måste välja ett flyg innan ni väljer ett en plats", this.xinset, this.yinset);
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
							usedSeats.remove((Object)(m*this.numSeats/2+n)); // TODO check if correct
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
		// TODO Auto-generated method stub
		this.parent.flygBokning.setPlatsNummer(this.usedSeats);
	}
}
