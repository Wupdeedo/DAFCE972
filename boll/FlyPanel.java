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

class FlyPanel extends JPanel{
	
	public JLabel fromLab, toLab, fromDat, toDat;
	public JTextField fromTex, toTex;
	public JCheckBox price,filter;
	public JDateChooser dat1,dat2;// Borrowed from third party lib
	public JButton search,clear;
	
	public FlyPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("Sök");
		clear = new JButton("Rensa");
		
		price = new JCheckBox("<html><font size=4><u>Prisalmanacka</u></font><br>Sök bästa pris runt valda datum.</html>");
		price.setHorizontalTextPosition(SwingConstants.LEFT);
		filter = new JCheckBox("<html><font size=4><u>Filter</u></font><br>Filtrera sökning efter specifika parametrar.</html>");
		filter.setHorizontalTextPosition(SwingConstants.LEFT);
		
		fromLab = new JLabel("Från: ");
		toLab = new JLabel("Till:   ");
		fromDat = new JLabel("Avresedatum:   ");
		toDat = new JLabel("Hemkomstdatum: ");
		
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
		
		
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(110,110)
						.addComponent(fromLab)
						.addComponent(fromTex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(110,110)
						.addComponent(toLab)
						.addComponent(toTex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(210,210)
						.addComponent(fromDat)
						.addComponent(dat1)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(210,210)
						.addComponent(toDat)
						.addComponent(dat2)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(230,230)
						.addComponent(price)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(210,210)
						.addComponent(filter)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(90,90)
						.addComponent(clear)
						.addGap(80)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(150,150)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromLab)
					.addComponent(fromTex)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toLab)
					.addComponent(toTex)
				)
				.addGap(20)
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
					.addComponent(price)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(filter)
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
			img = ImageIO.read(new File("src/bilder/flyg.png"));
			g.drawImage(img,0,0,null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.printComponents(gr);
	}
}