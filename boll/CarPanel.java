package boll;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

class CarPanel extends JPanel{
	
	public JLabel fromLab, toLab, fromDat, toDat, carcatlab, samelab;
	public JTextField fromTex, toTex;
	public JCheckBox same;
	public JDateChooser dat1,dat2;// Borrowed from third party lib
	public JButton search,clear;
	public JComboBox carcat;
	
	public CarPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("S嗅");
		clear = new JButton("Rensa");
		
		same= new JCheckBox();
		
		fromLab = new JLabel("Utl確ningsplats: ");
		toLab = new JLabel("Inl確ningsplats: ");
		fromDat = new JLabel("Utl確ningsdatum: ");
		toDat = new JLabel("Inl確ningsdatum: ");
		carcatlab = new JLabel("Bilkategori: ");
		samelab = new JLabel("<html>Samma som<br>utl確ning</html>");
		samelab.setMaximumSize(new Dimension(30,10));
		
		fromTex = new JTextField();
		fromTex.setMaximumSize(dim);
		toTex = new JTextField();
		toTex.setMaximumSize(dim);
		
		dat1 = new JDateChooser();
		dat1.setLocale(new Locale("se","se"));
		dat1.setDateFormatString("yyyy.MM.dd");
		dat1.setMaximumSize(dim);
		
		dat2 = new JDateChooser();
		dat2.setLocale(new Locale("se","se"));
		dat2.setDateFormatString("yyyy.MM.dd");
		dat2.setMaximumSize(dim);
		
		String[] carcats = { "cat1", "cat2", "cat3"};
		carcat = new JComboBox(carcats);
		carcat.setSelectedIndex(0);
		carcat.setMaximumSize(dim);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(200,200)
						.addComponent(fromDat)
						.addComponent(dat1)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(200,200)
						.addComponent(toDat)
						.addComponent(dat2)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(fromLab)
						.addComponent(fromTex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(toLab)
						.addComponent(toTex)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(same)
							.addComponent(samelab)
						)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(232,232)
						.addComponent(carcatlab)
						.addComponent(carcat)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(210,210)
						.addComponent(clear)
						.addGap(80)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(150,150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromDat)
					.addComponent(dat1)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toDat)
					.addComponent(dat2)
				)
				.addGap(30)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromLab)
					.addComponent(fromTex)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toLab)
					.addComponent(toTex)
					.addComponent(same)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(samelab)
				)
				.addGap(30)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(carcatlab)
					.addComponent(carcat)
				)
				.addGap(50)
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
			img = ImageIO.read(new File("src/bilder/bil.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}