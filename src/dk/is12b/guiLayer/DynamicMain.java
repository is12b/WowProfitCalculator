package dk.is12b.guiLayer;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;

import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Ink;
import dk.is12b.modelLayer.Pigment;

public class DynamicMain extends JPanel {

	/**
	 * Create the panel.
	 * @param amount 
	 * @param i 
	 * @param p 
	 */
	public DynamicMain(int amount, Herb h, Pigment p, Ink i) {
		setLayout(null);
		generatePanel(amount, h, p, i);

	}
	
	private void generatePanel(int amount, Herb h, Pigment p, Ink i){
		JLabel lblPigment = new JLabel(p.getName() + ":");
		lblPigment.setBounds(10, 11, 113, 14);
		add(lblPigment);
		String result = "";
		try{
			int resPigment = h.getRes(amount, p.getName());
			int resInk = resPigment / i.getAmount();
			result = resPigment + "/" + i.getAmount() + " = " + resInk;
		}catch(Exception e){
			result = "Unable to calculate!";
		}
		JLabel lblResult = new JLabel(result);
		lblResult.setBounds(133, 11, 46, 14);
		add(lblResult);
	}

}
