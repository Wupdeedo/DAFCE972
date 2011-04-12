import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BekraftelsePanel extends JPanel implements AnswerPanel{
	private MainWindow parent;
	private boolean hasInfo = false;
	
	public BekraftelsePanel(MainWindow mw){
		this.parent = mw;
		JLabel l = new JLabel("F�r att en bokning ska kunna bekr�ftas m�ste den f�rst genomf�ras.");
		this.add(l);
	}
	
	public BekraftelsePanel(MainWindow mw, String info){
		this.parent = mw;
		String[] array = info.split("\n");
		StringBuilder s = new StringBuilder("<html>");
		for(int n = 0; n < array.length; n++){
			s.append("<br>");
			s.append(array[n]);
			s.append("</br>");
		}
		s.append("</html>");
		JLabel l = new JLabel(s.toString());
		this.add(l);
		this.hasInfo = true;
	}

	@Override
	public void OkAction() {
		// TODO Check if everything is chosen that needs to be chosen to put the booking in the cart
		// then put it in the cart.
		if(this.hasInfo){ // && correct?
			
		}else{
			this.parent.showOptionPanel("Bekr�ftelse", true);
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).switchButtons("Bekr�ftelse");
			this.parent.getOptionTrees().get(this.parent.getCurrentPanel()).setButtonDone("Bekr�ftelse", false);
		}
	}

	@Override
	public void CancelAction() {
		// TODO Auto-generated method stub
	}

}
