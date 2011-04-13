package boll;

import java.awt.Dimension;
import java.util.Locale;

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
	public JCheckBox price;
	public JDateChooser dat1;// Borrowed from third party lib
	public JButton search,clear;
	
	public EventPanel(){
		GroupLayout layout = new GroupLayout(this);
		this.setOpaque(false);
		this.setLayout(layout);
		
		Dimension dim = new Dimension(150,20);
		
		search = new JButton("Sök");
		clear = new JButton("Rensa");
		
		price = new JCheckBox("<html><font size=4><u>Prisalmanacka</u></font><br>Sök bästa pris runt valda datum.</html>");
		price.setHorizontalTextPosition(SwingConstants.LEFT);

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
						.addContainerGap(150,150)
						.addComponent(ortlab)
						.addComponent(ortex)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(150,150)
						.addComponent(datlab)
						.addComponent(dat1)
				)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap(220,220)
						.addComponent(price)
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
				.addGap(10)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(datlab)
					.addComponent(dat1)
				)
				.addGap(30)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(price)
				)
				.addGap(200)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
					.addComponent(clear)
					.addComponent(search)
				)
		);
	}
	
}
