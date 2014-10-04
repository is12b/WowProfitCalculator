package dk.is12b.guiLayer;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;

import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Pigment;

public class DynamicMain extends JPanel {

	/**
	 * Create the panel.
	 * @param amount 
	 */
	public DynamicMain(int amount, Herb h) {
		setLayout(null);
		
		generatePanel(amount, h);

	}
	
	private void generatePanel(int amount, Herb h){
		ArrayList<Pigment> pigs = h.getPigments();
		
		if(pigs.size() > 0){
			int y = 11;
			for(Pigment p : pigs){
				JLabel lblPigment = new JLabel(p.getName() + ":");
				lblPigment.setBounds(10, y, 113, 14);
				add(lblPigment);
				
				JLabel lblResult = new JLabel("" + h.getRes(amount, p.getName()));
				lblResult.setBounds(133, y, 46, 14);
				add(lblResult);
				y += 20;
			}
		}
	}

}
