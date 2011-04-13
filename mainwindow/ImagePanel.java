package mainwindow;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class ImagePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	BufferedImage img;
	BufferedImage img2;
	BufferedImage img3;
	boolean rePaint = false;
	
	public ImagePanel(BufferedImage img){
		this.setOpaque(false);
		this.img = img;
	}
	
	public ImagePanel(BufferedImage img, boolean lol){
		this.img = img;
		this.img2 = img2;
		this.img3 = img3;
		this.rePaint = true;
	}
	
	public ImagePanel(BufferedImage img, BufferedImage img2, BufferedImage img3){
		this.img = img;
		this.img2 = img2;
		this.img3 = img3;
	}
	
//	@Override
//	public void paintComponent(Graphics g){
//		g.drawImage(this.img, 0, 0, null);
//		if(this.img2 != null)
//			g.drawImage(this.img2,0, this.img.getHeight(), null);
//		if(this.img3 != null)
//			g.drawImage(this.img3, 0, this.img.getHeight()+this.img2.getHeight(), null);
//		//System.out.println(this.getComponentCount());
//		//System.out.println(this.getBounds());
//		//g.drawImage(this.img3, 0, this.img.getHeight()+this.img2.getHeight(), this.getWidth(), this.getHeight(), arg5)
//	}
	
	public void paint(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		g.drawImage(this.img, 0, 0, null);
//		if(rePaint){
//			g.drawImage(this.img, 0, this.img.getHeight(), null);
//			g.drawImage(this.img, 0, this.img.getHeight(), null);
//			g.drawImage(this.img, 0, this.img.getHeight(), null);
//		}
		this.printComponents(gr);
	}
	
	public BufferedImage getImage(){
		return this.img;
	}
}
