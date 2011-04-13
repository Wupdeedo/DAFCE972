package mainwindow;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class RegisterWindow {
	
	public static void register(DatabaseHandle dbh){
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		LabelTextFieldPanel user = new LabelTextFieldPanel("Anv�ndarnamn: ");
		LabelTextFieldPanel pass1 = new LabelTextFieldPanel("L�senord: ", true);
		LabelTextFieldPanel pass2 = new LabelTextFieldPanel("Upprepa l�senord: ", true);
		LabelTextFieldPanel fornamn = new LabelTextFieldPanel("F�rnamn: ");
		LabelTextFieldPanel efternamn = new LabelTextFieldPanel("Efternamn: ");
		LabelTextFieldPanel telefon = new LabelTextFieldPanel("Telefonnummer: ");
		LabelTextFieldPanel postort = new LabelTextFieldPanel("postort: ");
		LabelTextFieldPanel postnr = new LabelTextFieldPanel("Postnummer: ", 1);
		LabelTextFieldPanel adress = new LabelTextFieldPanel("Adress: ");
		LabelTextFieldPanel email = new LabelTextFieldPanel("E-postadress: ");
		
		p.add(user);
		p.add(pass1);
		p.add(pass2);
		p.add(fornamn);
		p.add(efternamn);
		p.add(telefon);
		p.add(postort);
		p.add(postnr);
		p.add(adress);
		p.add(email);
		
		if(JOptionPane.showConfirmDialog(null, p, "Registrera", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
			if(pass1.getTextField().getText().equals(pass2.getTextField().getText())){
//				dbh.createAccount(user.getTextField().getText(), pass1.getTextField().getText(), 
//						fornamn.getTextField().getText(), efternamn.getTextField().getText(),
//						postort.getTextField().getText(), postnr.getTextField().getText(), 
//						email.getTextField().getText(), adress.getTextField().getText(),
//						telefon.getTextField().getText(), null); //TODO
			}
		}
	}
	
	// TODO remove
	public static void main(String[] args){
		RegisterWindow.register(new DatabaseHandle());
	}
}
