import javax.swing.JLabel;
import javax.swing.JPanel;


public class InfoTreePanel extends JPanel implements AnswerPanel{
	
	public InfoTreePanel(){
		JLabel l = new JLabel("Ni måste välja ett land att vara i innan ni kan få information om det.");
		this.add(l);
	}
	
	public InfoTreePanel(String info){
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
	}

	@Override
	public void OkAction() {
		// TODO Auto-generated method stub
	}

	@Override
	public void CancelAction() {
		// TODO Auto-generated method stub
	}

}
