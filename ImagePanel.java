import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class ImagePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	BufferedImage img;
	
	public ImagePanel(BufferedImage img){
		this.img = img;
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(this.img, 0, 0, null);
	}
	
	public BufferedImage getImage(){
		return this.img;
	}
}
