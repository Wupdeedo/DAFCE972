package boll;

import java.awt.Dimension;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

class HotelPanel extends JPanel{
	
	public JLabel ortlab, fromDat, toDat, nroom, nrpeep;
	public JTextField ortex, nrotex, nrptex;
	public JDateChooser dat1,dat2;// Borrowed from third party lib
	public JButton search,clear;
	
	public HotelPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		Dimension dim2 = new Dimension(30,20);
		
		search = new JButton("Sök");
		clear = new JButton("Rensa");

		
		ortlab = new JLabel("Ort: ");
		nroom = new JLabel("Antal rum: ");
		fromDat = new JLabel("Utlämningsdatum: ");
		toDat = new JLabel("Inlämningsdatum: ");
		nrpeep = new JLabel("Antal personer: ");
		
		ortex = new JTextField();
		ortex.setMaximumSize(dim);
		nrotex = new JTextField();
		nrotex.setMaximumSize(dim2);
		nrptex = new JTextField();
		nrptex.setMaximumSize(dim2);
		
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
						.addContainerGap(150,150)
						.addComponent(ortlab)
						.addComponent(ortex)
				)
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
						.addContainerGap(200,200)
						.addComponent(nroom)
						.addComponent(nrotex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(200,200)
						.addComponent(nrpeep)
						.addComponent(nrptex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(80,80)
						.addComponent(clear)
						.addGap(80)
						.addComponent(search)
				)
				
		);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addContainerGap(75,75)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(ortlab)
					.addComponent(ortex)
				)
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(fromDat)
					.addComponent(dat1)
				)
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(toDat)
					.addComponent(dat2)
				)
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(nroom)
					.addComponent(nrotex)
				)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(nrpeep)
					.addComponent(nrptex)
				)
				.addGap(70)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(clear)
					.addComponent(search)
				)
		);
	}
	
}
