package dk.is12b.guiLayer;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JLabel;

import dk.is12b.ctrLayer.CompositCtr;
import dk.is12b.modelLayer.Composit;
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
	public DynamicMain(int amount, Herb h, Pigment p, Ink i, double herbPrice, double inkPrice) {
		setLayout(null);
		generatePanel(amount, h, p, i, herbPrice, inkPrice);

	}
	
	private void generatePanel(int amount, Herb h, Pigment p, Ink i, double herbPrice, double inkPrice){
		
		ArrayList<Pigment> pigments = h.getPigments();
		
		int y = 11;
		
		for(Pigment pig : pigments){
			
			CompositCtr cCtr = new CompositCtr();
			ArrayList<Composit> comps = cCtr.getCompositListByPigment(pig);
			for(Composit com : comps){
				Ink in = com.getInk();
				in.setAmount(com.getAmount());
				
				JLabel lblPigment = new JLabel(pig.getName() + ":");
				lblPigment.setBounds(10, y, 113, 14);
				add(lblPigment);
				String result = "";
				int resInk = 0;
				try{
					int resPigment = h.getRes(amount, pig.getName());
					resInk = resPigment / in.getAmount();
					result = resPigment + "/" + in.getAmount() + " = " + resInk + " " + in.getName();
				}catch(Exception e){
					result = "Unable to calculate!";
				}
				JLabel lblResult = new JLabel(result);
				lblResult.setBounds(133, y, 307, 14);
				add(lblResult);
				
				y += 14;
			}
			
			
		}
		
		/*
		JLabel lblProfit = new JLabel("Profit:");
		lblProfit.setBounds(10, 36, 46, 14);
		add(lblProfit);
		
		double resInkProfit = inkPrice*resInk;
		double profit = resInkProfit - herbPrice;
		
		JLabel label = new JLabel(resInkProfit + "-" + herbPrice + " = " + profit);
		label.setBounds(133, 36, 307, 14);
		add(label);
		*/
	}
}
