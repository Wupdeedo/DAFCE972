package boll;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

class EventPanel extends JPanel{
	
	public JLabel ortlab, datlab;
	public JTextField ortex;
	public JDateChooser dat1;// Borrowed from third party lib
	public JButton search,clear;
	
	public EventPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("Sšk");
		clear = new JButton("Rensa");
		ortlab = new JLabel("Ort: ");
		datlab = new JLabel("Datum:   ");
		
		ortex = new JTextField();
		ortex.setMaximumSize(dim);
		
		dat1 = new JDateChooser();
		dat1.setLocale(new Locale("se","se"));
		dat1.setDateFormatString("yyyy.MM.dd");
		dat1.setMaximumSize(dim);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(180,180)
						.addComponent(ortlab)
						.addComponent(ortex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(180,180)
						.addComponent(datlab)
						.addComponent(dat1)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(200,200)
						.addComponent(clear)
						.addGap(80)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(150,150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(ortlab)
					.addComponent(ortex)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(datlab)
					.addComponent(dat1)
				)
				.addGap(30)
				.addGap(100)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(clear)
					.addComponent(search)
				)
		);
	}
	public void paint(Graphics gr){
		Graphics2D g = (Graphics2D)gr;
		BufferedImage img;
		try {
			img = ImageIO.read(new File("src/bilder/event.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}
