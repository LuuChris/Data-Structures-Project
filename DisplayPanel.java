import java.awt.*;
import javax.swing.*;
public class DisplayPanel extends JPanel{
	private JTextArea currentItems;
	
	public DisplayPanel(String[] item, int[] num, double a){
		currentItems=new JTextArea(10,20);
		String s="";
		s= s.format("%10s  %15s    %-10s\n-------------------------------------", "Money in Machine", "Item quantity", "Item name");
		currentItems.append(s);
		for(int i=0; i<item.length; i++){
			s= s.format("\n     $%-15.2f   %15d             %-10s", a, num[i], item[i]);
			currentItems.append(s);
		}
		add(currentItems);
		currentItems.setEditable(false);
		setPreferredSize(new Dimension(400, 200));
		setBackground(Color.lightGray);
	}
	

}
