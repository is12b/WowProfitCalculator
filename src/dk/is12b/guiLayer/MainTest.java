package dk.is12b.guiLayer;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import dk.is12b.ctrLayer.HerbCtr;
import dk.is12b.modelLayer.Herb;
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

	/**
	 * Create the panel.
	 */
	public MainTest() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 165, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblHerb = new JLabel("Herb");
		GridBagConstraints gbc_lblHerb = new GridBagConstraints();
		gbc_lblHerb.insets = new Insets(0, 0, 5, 5);
		gbc_lblHerb.anchor = GridBagConstraints.WEST;
		gbc_lblHerb.gridx = 0;
		gbc_lblHerb.gridy = 0;
		add(lblHerb, gbc_lblHerb);
		
		cboxHerbs = new JComboBox();
		GridBagConstraints gbc_cboxHerbs = new GridBagConstraints();
		gbc_cboxHerbs.insets = new Insets(0, 0, 5, 5);
		gbc_cboxHerbs.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboxHerbs.gridx = 1;
		gbc_cboxHerbs.gridy = 0;
		add(cboxHerbs, gbc_cboxHerbs);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		
		JLabel lblAmount = new JLabel("Amount");
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.anchor = GridBagConstraints.EAST;
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.gridx = 0;
		gbc_lblAmount.gridy = 1;
		add(lblAmount, gbc_lblAmount);
		
		txtAmount = new JTextField();
		GridBagConstraints gbc_txtAmount = new GridBagConstraints();
		gbc_txtAmount.insets = new Insets(0, 0, 5, 5);
		gbc_txtAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAmount.gridx = 1;
		gbc_txtAmount.gridy = 1;
		add(txtAmount, gbc_txtAmount);
		txtAmount.setColumns(10);
		
		JButton btnCalc = new JButton("Calc");
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!txtAmount.getText().isEmpty()){
					try{
						generatePanel((String)cboxHerbs.getSelectedItem(), Integer.parseInt(txtAmount.getText()));
					}catch(Exception e){
						
					}
				}
			}
		});
		GridBagConstraints gbc_btnCalc = new GridBagConstraints();
		gbc_btnCalc.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalc.gridx = 1;
		gbc_btnCalc.gridy = 2;
		add(btnCalc, gbc_btnCalc);
		
		test = new JPanel();
		GridBagConstraints gbc_test = new GridBagConstraints();
		gbc_test.gridwidth = 3;
		gbc_test.insets = new Insets(0, 0, 0, 5);
		gbc_test.fill = GridBagConstraints.BOTH;
		gbc_test.gridx = 0;
		gbc_test.gridy = 3;
		add(test, gbc_test);
		test.setLayout(new CardLayout(0, 0));

		updateModel();
	}
	
	protected void generatePanel(String selected, int amount) {
		HerbCtr hCtr = new HerbCtr();
		String[] str = selected.split("-");
		JPanel j = new DynamicMain(amount, hCtr.getHerb(Integer.parseInt(str[0])));
		test.add(j, str[1]);
		CardLayout cl = (CardLayout)(test.getLayout());
		cl.show(test, str[1]);
		this.revalidate();
	}

	private void updateModel(){
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		HerbCtr hCtr = new HerbCtr();
		ArrayList<Herb> herbs = hCtr.getAllHerbs();
		
		for(Herb h : herbs){
			model.addElement(h.getId() + "-" + h.getName());
		}
		
		cboxHerbs.setModel(model);
		
		if(herbs.size() > 0){
			cboxHerbs.setSelectedIndex(0);
		}
	}
}
