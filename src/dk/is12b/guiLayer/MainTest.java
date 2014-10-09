package dk.is12b.guiLayer;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import dk.is12b.ctrLayer.CompositCtr;
import dk.is12b.ctrLayer.HerbCtr;
import dk.is12b.ctrLayer.InkCtr;
import dk.is12b.modelLayer.Composit;
import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Ink;
import dk.is12b.modelLayer.Pigment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import java.awt.CardLayout;

public class MainTest extends JPanel {
	private JComboBox cboxHerbs;
	private String selected;
	private JPanel test;
	private JTextField txtAmount;
	private JComboBox cboxPigment;
	private JComboBox cboxInk;
	private JTextField txtHerbPrice;
	private JTextField txtInkPrice;

	/**
	 * Create the panel.
	 */
	public MainTest() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 165, 165, 165, 276, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblHerb = new JLabel("Herb");
		GridBagConstraints gbc_lblHerb = new GridBagConstraints();
		gbc_lblHerb.insets = new Insets(0, 0, 5, 5);
		gbc_lblHerb.gridx = 1;
		gbc_lblHerb.gridy = 0;
		add(lblHerb, gbc_lblHerb);
		
		JLabel lblPigment = new JLabel("Pigment");
		GridBagConstraints gbc_lblPigment = new GridBagConstraints();
		gbc_lblPigment.insets = new Insets(0, 0, 5, 5);
		gbc_lblPigment.gridx = 2;
		gbc_lblPigment.gridy = 0;
		add(lblPigment, gbc_lblPigment);
		
		JLabel lblInk = new JLabel("Ink");
		GridBagConstraints gbc_lblInk = new GridBagConstraints();
		gbc_lblInk.insets = new Insets(0, 0, 5, 5);
		gbc_lblInk.gridx = 3;
		gbc_lblInk.gridy = 0;
		add(lblInk, gbc_lblInk);
		
		cboxHerbs = new JComboBox();
		cboxHerbs.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        updatePigmentModel((Herb)cboxHerbs.getSelectedItem());
		    }
		});
		GridBagConstraints gbc_cboxHerbs = new GridBagConstraints();
		gbc_cboxHerbs.insets = new Insets(0, 0, 5, 5);
		gbc_cboxHerbs.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxHerbs.gridx = 1;
		gbc_cboxHerbs.gridy = 1;
		add(cboxHerbs, gbc_cboxHerbs);
		
		cboxPigment = new JComboBox();
		cboxPigment.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	updateInkModel((Pigment)cboxPigment.getSelectedItem());
		    }
		});
		GridBagConstraints gbc_cboxPigment = new GridBagConstraints();
		gbc_cboxPigment.insets = new Insets(0, 0, 5, 5);
		gbc_cboxPigment.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxPigment.gridx = 2;
		gbc_cboxPigment.gridy = 1;
		add(cboxPigment, gbc_cboxPigment);
		
		cboxInk = new JComboBox();
		GridBagConstraints gbc_cboxInk = new GridBagConstraints();
		gbc_cboxInk.insets = new Insets(0, 0, 5, 5);
		gbc_cboxInk.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxInk.gridx = 3;
		gbc_cboxInk.gridy = 1;
		add(cboxInk, gbc_cboxInk);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 4;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		
		JLabel lblAmount = new JLabel("Amount");
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.anchor = GridBagConstraints.EAST;
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.gridx = 0;
		gbc_lblAmount.gridy = 2;
		add(lblAmount, gbc_lblAmount);
		
		txtAmount = new JTextField();
		GridBagConstraints gbc_txtAmount = new GridBagConstraints();
		gbc_txtAmount.insets = new Insets(0, 0, 5, 5);
		gbc_txtAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAmount.gridx = 1;
		gbc_txtAmount.gridy = 2;
		add(txtAmount, gbc_txtAmount);
		txtAmount.setColumns(10);
		
		JButton btnCalc = new JButton("Calc");
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtAmount.getText().isEmpty()){
					try{
						generatePanel((Herb)cboxHerbs.getSelectedItem(), (Pigment)cboxPigment.getSelectedItem(), (Ink)cboxInk.getSelectedItem(), Integer.parseInt(txtAmount.getText()), Double.parseDouble(txtHerbPrice.getText()), Double.parseDouble(txtInkPrice.getText()));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		});
		
		JLabel lblHerbPrice = new JLabel("Herb Price (AH)");
		GridBagConstraints gbc_lblHerbPrice = new GridBagConstraints();
		gbc_lblHerbPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblHerbPrice.gridx = 1;
		gbc_lblHerbPrice.gridy = 3;
		add(lblHerbPrice, gbc_lblHerbPrice);
		
		JLabel lblInkPriceah = new JLabel("Ink Price (AH)");
		GridBagConstraints gbc_lblInkPriceah = new GridBagConstraints();
		gbc_lblInkPriceah.insets = new Insets(0, 0, 5, 5);
		gbc_lblInkPriceah.gridx = 3;
		gbc_lblInkPriceah.gridy = 3;
		add(lblInkPriceah, gbc_lblInkPriceah);
		
		txtHerbPrice = new JTextField();
		GridBagConstraints gbc_txtHerbPrice = new GridBagConstraints();
		gbc_txtHerbPrice.insets = new Insets(0, 0, 5, 5);
		gbc_txtHerbPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtHerbPrice.gridx = 1;
		gbc_txtHerbPrice.gridy = 4;
		add(txtHerbPrice, gbc_txtHerbPrice);
		txtHerbPrice.setColumns(10);
		
		txtInkPrice = new JTextField();
		GridBagConstraints gbc_txtInkPrice = new GridBagConstraints();
		gbc_txtInkPrice.insets = new Insets(0, 0, 5, 5);
		gbc_txtInkPrice.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInkPrice.gridx = 3;
		gbc_txtInkPrice.gridy = 4;
		add(txtInkPrice, gbc_txtInkPrice);
		txtInkPrice.setColumns(10);
		GridBagConstraints gbc_btnCalc = new GridBagConstraints();
		gbc_btnCalc.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalc.gridx = 1;
		gbc_btnCalc.gridy = 5;
		add(btnCalc, gbc_btnCalc);
		
		test = new JPanel();
		GridBagConstraints gbc_test = new GridBagConstraints();
		gbc_test.gridwidth = 3;
		gbc_test.insets = new Insets(0, 0, 0, 5);
		gbc_test.fill = GridBagConstraints.BOTH;
		gbc_test.gridx = 0;
		gbc_test.gridy = 6;
		add(test, gbc_test);
		test.setLayout(new CardLayout(0, 0));

		updateModel();
	}
	
	protected void generatePanel(Herb h, Pigment p, Ink i, int amount, double herbPrice, double inkPrice) {
		JPanel j = new DynamicMain(amount, h, p, i, herbPrice, inkPrice);
		test.add(j, h.getName());
		CardLayout cl = (CardLayout)(test.getLayout());
		cl.show(test, h.getName());
		this.revalidate();
	}

	private void updateModel(){
		DefaultComboBoxModel<Herb> herbModel = new DefaultComboBoxModel<Herb>();
		
		HerbCtr hCtr = new HerbCtr();
		ArrayList<Herb> herbs = hCtr.getAllHerbs();
		
		for(Herb h : herbs){
			herbModel.addElement(h);
		}
		
		cboxHerbs.setModel(herbModel);
		
		if(herbs.size() > 0){
			cboxHerbs.setSelectedIndex(0);
		}
	}
	
	private void updatePigmentModel(Herb h){
		DefaultComboBoxModel<Pigment> pigmentModel = new DefaultComboBoxModel<Pigment>();
		
		ArrayList<Pigment> pigments = h.getPigments();
		
		for(Pigment p : pigments){
			pigmentModel.addElement(p);
		}
		
		cboxPigment.setModel(pigmentModel);
		
		if(pigments.size() > 0){
			cboxPigment.setSelectedIndex(0);
		}
	}
	
	private void updateInkModel(Pigment p){
		DefaultComboBoxModel<Ink> inkModel = new DefaultComboBoxModel<Ink>();
		CompositCtr cCtr = new CompositCtr();
		
		ArrayList<Composit> composits = cCtr.getCompositListByPigment(p);
		
		for(Composit c : composits){
			Ink i = c.getInk();
			i.setAmount(c.getAmount());
			inkModel.addElement(i);
		}
		
		cboxInk.setModel(inkModel);
		
		if(composits.size() > 0){
			cboxInk.setSelectedIndex(0);
		}
	}
}
