import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;


public class FlygAlternativPanel extends JPanel implements MouseListener{
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

	public FlygAlternativPanel(MainWindow mw, int numSeats, int numSeatsToFill, List<Integer> freeSeats){
		this.numSeats = numSeats;
		this.freeSeats = freeSeats;
		this.unChosen = numSeatsToFill;
		this.usedSeats = new LinkedList<Integer>();
		this.addMouseListener(this);
		this.setPreferredSize(new Dimension(400,100));
	}
	
	@Override
	public void paintComponent(Graphics g){
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
		for(int seat : freeSeats){
			int m = seat/(this.numSeats/2);
			int n = seat%(this.numSeats/2);
			int x = n%(this.numSeats/(2*this.seatsPerColumn));
			int y = n/(this.numSeats/(2*this.seatsPerColumn));
			g.fillRect(this.xinset+x*(this.seatWidth+this.diffWidth), 
					this.yinset+y*(this.seatHeight+this.diffHeight)+m*(this.midlaneHeight+this.seatsPerColumn*this.seatHeight),
					this.seatWidth, this.seatHeight);
		}
		g.setColor(Color.green);
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

	@Override
	public void mouseClicked(MouseEvent e) {
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
}
