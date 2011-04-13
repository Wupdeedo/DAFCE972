package boll;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class InfoPanel extends JPanel{
	
	public JLabel infolab;
	public JTextField infotex;
	public JButton search;
	
	public InfoPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("Sšk");
		
		infolab = new JLabel("Land: ");
		
		infotex = new JTextField();
		infotex.setMaximumSize(dim);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(280,280)
						.addComponent(infolab)
						.addComponent(infotex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(180,180)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(infolab)
						.addComponent(infotex)
				)
				.addGap(150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(search)
				)
		);
	}
	public void paint(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		BufferedImage img;
		try {
			img = ImageIO.read(new File("src/bilder/info.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}