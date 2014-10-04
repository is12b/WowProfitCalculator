package dk.is12b.guiLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import java.awt.Insets;
import java.awt.Font;

import javax.swing.JCheckBox;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import dk.is12b.ctrLayer.HerbCtr;
import dk.is12b.ctrLayer.PigmentCtr;
import dk.is12b.dbLayer.DBPigment;
import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Pigment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewHerbDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtHerbName;
	private JTextField txtComPigmentName;
	private JTextField txtCommonAmount;
	private JTextField txtCommonChanceOff;
	private JTextField txtCommonChanceTo;
	private JTextField txtRarePigmentName;
	private JTextField txtRareChanceOff;
	private JTextField txtRareChanceTo;
	private JTextField txtRareChance;
	private JCheckBox chkCommonGuaranteed;
	private JCheckBox chkExtraCommon;
	private JCheckBox chkPigmentRare;
	private JLabel lblHerbName;
	private JLabel lblCommonPigmentName;
	private JLabel lblCommonAmount;
	private JLabel lblCommonChanceToOff;
	private JLabel lblRarePigmentName;
	private JLabel lblRarePigmentChanceOffTo;
	private JLabel lblRareChancePercent;
	private HerbPanel parent;

	/**
	 * Create the dialog.
	 */
	public NewHerbDialog(HerbPanel parent) {
		this.parent = parent;
		setBounds(100, 100, 488, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.gridwidth = 2;
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			contentPanel.add(panel, gbc_panel);
			{
				JLabel lblNewHarb = new JLabel("New Herb");
				lblNewHarb.setFont(new Font("Tahoma", Font.PLAIN, 18));
				panel.add(lblNewHarb);
			}
		}
		{
			lblHerbName = new JLabel("What would you like to name it?");
			GridBagConstraints gbc_lblHerbName = new GridBagConstraints();
			gbc_lblHerbName.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblHerbName.insets = new Insets(0, 0, 5, 5);
			gbc_lblHerbName.gridx = 0;
			gbc_lblHerbName.gridy = 1;
			contentPanel.add(lblHerbName, gbc_lblHerbName);
		}
		{
			txtHerbName = new JTextField();
			GridBagConstraints gbc_txtHerbName = new GridBagConstraints();
			gbc_txtHerbName.insets = new Insets(0, 0, 5, 0);
			gbc_txtHerbName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHerbName.gridx = 1;
			gbc_txtHerbName.gridy = 1;
			contentPanel.add(txtHerbName, gbc_txtHerbName);
			txtHerbName.setColumns(10);
		}
		{
			lblCommonPigmentName = new JLabel("What is the name of the common Pigment?");
			GridBagConstraints gbc_lblCommonPigmentName = new GridBagConstraints();
			gbc_lblCommonPigmentName.anchor = GridBagConstraints.WEST;
			gbc_lblCommonPigmentName.insets = new Insets(0, 0, 5, 5);
			gbc_lblCommonPigmentName.gridx = 0;
			gbc_lblCommonPigmentName.gridy = 2;
			contentPanel.add(lblCommonPigmentName, gbc_lblCommonPigmentName);
		}
		{
			txtComPigmentName = new JTextField();
			GridBagConstraints gbc_txtComPigmentName = new GridBagConstraints();
			gbc_txtComPigmentName.insets = new Insets(0, 0, 5, 0);
			gbc_txtComPigmentName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtComPigmentName.gridx = 1;
			gbc_txtComPigmentName.gridy = 2;
			contentPanel.add(txtComPigmentName, gbc_txtComPigmentName);
			txtComPigmentName.setColumns(10);
		}
		{
			JLabel lblAreTheyGuaranteed = new JLabel("");
			GridBagConstraints gbc_lblAreTheyGuaranteed = new GridBagConstraints();
			gbc_lblAreTheyGuaranteed.insets = new Insets(0, 0, 5, 5);
			gbc_lblAreTheyGuaranteed.gridx = 0;
			gbc_lblAreTheyGuaranteed.gridy = 3;
			contentPanel.add(lblAreTheyGuaranteed, gbc_lblAreTheyGuaranteed);
		}
		{
			chkCommonGuaranteed = new JCheckBox("Are they guaranteed drop? Yes/No");
			chkCommonGuaranteed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(chkCommonGuaranteed.isSelected()){
						txtCommonAmount.setEditable(true);
						chkExtraCommon.setEnabled(true);
					}else if(!chkCommonGuaranteed.isSelected()){
						txtCommonAmount.setEditable(false);
						chkExtraCommon.setEnabled(false);
					}
				}
			});
			GridBagConstraints gbc_chkCommonGuaranteed = new GridBagConstraints();
			gbc_chkCommonGuaranteed.anchor = GridBagConstraints.WEST;
			gbc_chkCommonGuaranteed.insets = new Insets(0, 0, 5, 5);
			gbc_chkCommonGuaranteed.gridx = 0;
			gbc_chkCommonGuaranteed.gridy = 4;
			contentPanel.add(chkCommonGuaranteed, gbc_chkCommonGuaranteed);
		}
		{
			lblCommonAmount = new JLabel("How many?");
			GridBagConstraints gbc_lblCommonAmount = new GridBagConstraints();
			gbc_lblCommonAmount.anchor = GridBagConstraints.WEST;
			gbc_lblCommonAmount.insets = new Insets(0, 0, 5, 5);
			gbc_lblCommonAmount.gridx = 0;
			gbc_lblCommonAmount.gridy = 5;
			contentPanel.add(lblCommonAmount, gbc_lblCommonAmount);
		}
		{
			txtCommonAmount = new JTextField();
			txtCommonAmount.setEditable(false);
			GridBagConstraints gbc_txtCommonAmount = new GridBagConstraints();
			gbc_txtCommonAmount.insets = new Insets(0, 0, 5, 0);
			gbc_txtCommonAmount.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCommonAmount.gridx = 1;
			gbc_txtCommonAmount.gridy = 5;
			contentPanel.add(txtCommonAmount, gbc_txtCommonAmount);
			txtCommonAmount.setColumns(10);
		}
		{
			chkExtraCommon = new JCheckBox("Chances of extra common pigments on mill?");
			chkExtraCommon.setEnabled(false);
			chkExtraCommon.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(chkExtraCommon.isSelected()){
						txtCommonChanceTo.setEditable(true);
						txtCommonChanceOff.setEditable(true);
					}else if(!chkExtraCommon.isSelected()){
						txtCommonChanceTo.setEditable(false);
						txtCommonChanceOff.setEditable(false);
					}
				}
			});
			GridBagConstraints gbc_chkExtraCommon = new GridBagConstraints();
			gbc_chkExtraCommon.anchor = GridBagConstraints.WEST;
			gbc_chkExtraCommon.insets = new Insets(0, 0, 5, 5);
			gbc_chkExtraCommon.gridx = 0;
			gbc_chkExtraCommon.gridy = 6;
			contentPanel.add(chkExtraCommon, gbc_chkExtraCommon);
		}
		{
			lblCommonChanceToOff = new JLabel("How many / Out of? (Eg: 1-2 with 50% chance = 2/4)");
			GridBagConstraints gbc_lblCommonChanceToOff = new GridBagConstraints();
			gbc_lblCommonChanceToOff.insets = new Insets(0, 0, 5, 5);
			gbc_lblCommonChanceToOff.anchor = GridBagConstraints.WEST;
			gbc_lblCommonChanceToOff.gridx = 0;
			gbc_lblCommonChanceToOff.gridy = 7;
			contentPanel.add(lblCommonChanceToOff, gbc_lblCommonChanceToOff);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 7;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				txtCommonChanceTo = new JTextField();
				txtCommonChanceTo.setEditable(false);
				panel.add(txtCommonChanceTo, "1, 1, fill, default");
				txtCommonChanceTo.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("/");
				panel.add(lblNewLabel_1, "3, 1, right, default");
			}
			{
				txtCommonChanceOff = new JTextField();
				txtCommonChanceOff.setEditable(false);
				panel.add(txtCommonChanceOff, "5, 1, fill, default");
				txtCommonChanceOff.setColumns(10);
			}
		}
		{
			chkPigmentRare = new JCheckBox("Any chance of primary rare pigments?");
			chkPigmentRare.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(chkPigmentRare.isSelected()){
						txtRarePigmentName.setEditable(true);
						txtRareChance.setEditable(true);
						txtRareChanceOff.setEditable(true);
						txtRareChanceTo.setEditable(true);
					}else if(!chkPigmentRare.isSelected()){
						txtRarePigmentName.setEditable(false);
						txtRareChance.setEditable(false);
						txtRareChanceOff.setEditable(false);
						txtRareChanceTo.setEditable(false);
					}
				}
			});
			GridBagConstraints gbc_chkPigmentRare = new GridBagConstraints();
			gbc_chkPigmentRare.anchor = GridBagConstraints.WEST;
			gbc_chkPigmentRare.insets = new Insets(0, 0, 5, 5);
			gbc_chkPigmentRare.gridx = 0;
			gbc_chkPigmentRare.gridy = 8;
			contentPanel.add(chkPigmentRare, gbc_chkPigmentRare);
		}
		{
			lblRarePigmentName = new JLabel("What is the name of the rare Pigment?");
			GridBagConstraints gbc_lblRarePigmentName = new GridBagConstraints();
			gbc_lblRarePigmentName.insets = new Insets(0, 0, 5, 5);
			gbc_lblRarePigmentName.anchor = GridBagConstraints.WEST;
			gbc_lblRarePigmentName.gridx = 0;
			gbc_lblRarePigmentName.gridy = 9;
			contentPanel.add(lblRarePigmentName, gbc_lblRarePigmentName);
		}
		{
			txtRarePigmentName = new JTextField();
			txtRarePigmentName.setEditable(false);
			GridBagConstraints gbc_txtRarePigmentName = new GridBagConstraints();
			gbc_txtRarePigmentName.insets = new Insets(0, 0, 5, 0);
			gbc_txtRarePigmentName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRarePigmentName.gridx = 1;
			gbc_txtRarePigmentName.gridy = 9;
			contentPanel.add(txtRarePigmentName, gbc_txtRarePigmentName);
			txtRarePigmentName.setColumns(10);
		}
		{
			lblRarePigmentChanceOffTo = new JLabel("How many / Out of? (Eg: 1-3 = 3/3)");
			GridBagConstraints gbc_lblRarePigmentChanceOffTo = new GridBagConstraints();
			gbc_lblRarePigmentChanceOffTo.anchor = GridBagConstraints.WEST;
			gbc_lblRarePigmentChanceOffTo.insets = new Insets(0, 0, 5, 5);
			gbc_lblRarePigmentChanceOffTo.gridx = 0;
			gbc_lblRarePigmentChanceOffTo.gridy = 10;
			contentPanel.add(lblRarePigmentChanceOffTo, gbc_lblRarePigmentChanceOffTo);
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 0);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 10;
			contentPanel.add(panel, gbc_panel);
			panel.setLayout(new FormLayout(new ColumnSpec[] {
					ColumnSpec.decode("default:grow"),
					FormFactory.RELATED_GAP_COLSPEC,
					FormFactory.DEFAULT_COLSPEC,
					FormFactory.RELATED_GAP_COLSPEC,
					ColumnSpec.decode("default:grow"),},
				new RowSpec[] {
					FormFactory.DEFAULT_ROWSPEC,}));
			{
				txtRareChanceTo = new JTextField();
				txtRareChanceTo.setEditable(false);
				panel.add(txtRareChanceTo, "1, 1, fill, default");
				txtRareChanceTo.setColumns(10);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("/");
				panel.add(lblNewLabel_2, "3, 1, right, default");
			}
			{
				txtRareChanceOff = new JTextField();
				txtRareChanceOff.setEditable(false);
				panel.add(txtRareChanceOff, "5, 1, fill, default");
				txtRareChanceOff.setColumns(10);
			}
		}
		{
			lblRareChancePercent = new JLabel("What is the chance per mill? (Eg: 50% from Mystic)");
			GridBagConstraints gbc_lblRareChancePercent = new GridBagConstraints();
			gbc_lblRareChancePercent.anchor = GridBagConstraints.WEST;
			gbc_lblRareChancePercent.insets = new Insets(0, 0, 0, 5);
			gbc_lblRareChancePercent.gridx = 0;
			gbc_lblRareChancePercent.gridy = 11;
			contentPanel.add(lblRareChancePercent, gbc_lblRareChancePercent);
		}
		{
			txtRareChance = new JTextField();
			txtRareChance.setEditable(false);
			GridBagConstraints gbc_txtRareChance = new GridBagConstraints();
			gbc_txtRareChance.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRareChance.gridx = 1;
			gbc_txtRareChance.gridy = 11;
			contentPanel.add(txtRareChance, gbc_txtRareChance);
			txtRareChance.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						createNewHerb();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						NewHerbDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void createNewHerb() {
		System.out.println("Step 1");
		if(checkValues()){
			System.out.println("Step 2");
			Herb h = null;
			if(chkCommonGuaranteed.isSelected()){
				System.out.println("Step 3");
				HerbCtr hCtr = new HerbCtr();
				h = hCtr.createHerb(txtHerbName.getText());
				PigmentCtr pCtr = new PigmentCtr();
				Pigment p = null;
				
				if(!chkExtraCommon.isSelected()){
					p = pCtr.createPigment(txtComPigmentName.getText(), 0, 0, Integer.parseInt(txtCommonAmount.getText()), 100);
				}else{
					p = pCtr.createPigment(txtComPigmentName.getText(), Integer.parseInt(txtCommonChanceTo.getText()), Integer.parseInt(txtCommonChanceOff.getText()), Integer.parseInt(txtCommonAmount.getText()), 100);
				}
				
				h.addPigment(p);
			}
			
			if(chkPigmentRare.isSelected()){
				System.out.println("Step 4");
				PigmentCtr pCtr = new PigmentCtr();
				Pigment p = null;
				
				String rareChance = txtRareChance.getText();
				
				if(rareChance.contains("%")){
					rareChance = rareChance.replace("%", "");
				}
				
				p = pCtr.createPigment(txtRarePigmentName.getText(), Integer.parseInt(txtRareChanceTo.getText()), Integer.parseInt(txtRareChanceOff.getText()), 0, Integer.parseInt(rareChance));
				h.addPigment(p);
			}
			parent.updateList();
			this.dispose();
		}
	}

	private boolean checkValues() {
		boolean ret = true;
		
		if(txtHerbName.getText().isEmpty()){
			error(lblHerbName);
			ret = false;
		}
		
		if(chkCommonGuaranteed.isSelected() || chkExtraCommon.isSelected()){
			if(txtComPigmentName.getText().isEmpty()){
				error(lblCommonPigmentName);
				ret = false;
			}
		}
		
		if(chkCommonGuaranteed.isSelected()){
			try{
				Integer.parseInt(txtCommonAmount.getText());
			}catch(Exception e){
				error(lblCommonAmount);
				ret = false;
			}
		}
		
		if(chkExtraCommon.isSelected()){
			try{
				Integer.parseInt(txtCommonChanceOff.getText());
				Integer.parseInt(txtCommonChanceTo.getText());
			}catch(Exception e){
				error(lblCommonChanceToOff);
				ret = false;
			}
		}
		
		if(chkPigmentRare.isSelected()){
			if(txtRarePigmentName.getText().isEmpty()){
				error(lblRarePigmentName);
				ret = false;
			}
			
			try{
				Integer.parseInt(txtRareChanceOff.getText());
				Integer.parseInt(txtRareChanceTo.getText());
			}catch(Exception e){
				error(lblRarePigmentChanceOffTo);
				ret = false;
			}
			
			try{
				String rareChance = txtRareChance.getText();
				if(rareChance.contains("%")){
					rareChance = rareChance.replace("%", "");
				}
				Integer.parseInt(rareChance);
			}catch(Exception e){
				error(lblRareChancePercent);
				ret = false;
			}
		}
		
		return ret;
	}
	
	protected void error(final JLabel lbl){
		Thread errorThread = new Thread(){
			public void run(){
				lbl.setForeground(Color.RED);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lbl.setForeground(Color.BLACK);;
			}
		};
		
		errorThread.start();
	}

}
