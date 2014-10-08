package dk.is12b.guiLayer;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JTextField;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import dk.is12b.ctrLayer.CompositCtr;
import dk.is12b.ctrLayer.InkCtr;
import dk.is12b.modelLayer.Ink;
import dk.is12b.modelLayer.Pigment;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateInk extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtInkName;
	private Pigment pigment;
	private JTextField txtAmount;


	/**
	 * Create the dialog.
	 */
	public CreateInk(Pigment pigment) {
		this.pigment = pigment;
		
		setBounds(100, 100, 278, 172);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblInk = new JLabel("Ink:");
			GridBagConstraints gbc_lblInk = new GridBagConstraints();
			gbc_lblInk.insets = new Insets(0, 0, 5, 5);
			gbc_lblInk.gridx = 1;
			gbc_lblInk.gridy = 0;
			contentPanel.add(lblInk, gbc_lblInk);
		}
		{
			JLabel lblName = new JLabel("Name:");
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.anchor = GridBagConstraints.WEST;
			gbc_lblName.gridx = 0;
			gbc_lblName.gridy = 1;
			contentPanel.add(lblName, gbc_lblName);
		}
		{
			txtInkName = new JTextField();
			GridBagConstraints gbc_txtInkName = new GridBagConstraints();
			gbc_txtInkName.insets = new Insets(0, 0, 5, 0);
			gbc_txtInkName.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInkName.gridx = 1;
			gbc_txtInkName.gridy = 1;
			contentPanel.add(txtInkName, gbc_txtInkName);
			txtInkName.setColumns(10);
		}
		{
			JLabel lblRegent = new JLabel("Regent:");
			GridBagConstraints gbc_lblRegent = new GridBagConstraints();
			gbc_lblRegent.insets = new Insets(0, 0, 5, 0);
			gbc_lblRegent.gridx = 1;
			gbc_lblRegent.gridy = 2;
			contentPanel.add(lblRegent, gbc_lblRegent);
		}
		{
			JLabel lblPigment = new JLabel(pigment.getName() + " x ");
			GridBagConstraints gbc_lblPigment = new GridBagConstraints();
			gbc_lblPigment.anchor = GridBagConstraints.WEST;
			gbc_lblPigment.insets = new Insets(0, 0, 0, 5);
			gbc_lblPigment.gridx = 0;
			gbc_lblPigment.gridy = 3;
			contentPanel.add(lblPigment, gbc_lblPigment);
		}
		{
			txtAmount = new JTextField();
			GridBagConstraints gbc_txtAmount = new GridBagConstraints();
			gbc_txtAmount.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAmount.gridx = 1;
			gbc_txtAmount.gridy = 3;
			contentPanel.add(txtAmount, gbc_txtAmount);
			txtAmount.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						createInk();
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
						CreateInk.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	protected void createInk() {
		String name = txtInkName.getText();
		if(checkValues()){
			InkCtr iCtr = new InkCtr();
			Ink i = iCtr.insertInk(name);
			
			CompositCtr cCtr = new CompositCtr();
			
			cCtr.insertComposit(i, pigment, Integer.parseInt(txtAmount.getText()));
			
			this.dispose();
		}
	}
	
	private boolean checkValues(){
		boolean retBool = true;
		if(txtInkName.getText() == null && txtInkName.getText().isEmpty()){
			retBool = false;
		}
		
		try{
			Integer.parseInt(txtAmount.getText());
		}catch(Exception e){
			retBool = false;
		}
		
		return retBool;
	}

}
