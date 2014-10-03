package dk.is12b.guiLayer;

import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;

import javax.swing.JList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import dk.is12b.ctrLayer.PigmentCtr;
import dk.is12b.modelLayer.Pigment;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class PigmentPanel extends JPanel {
	private JTextField txtName;
	private JTextField txtMin;
	private JTextField txtPercent;
	private JTextField txtChanceOff;
	private JTextField txtChanceTo;
	private Pigment selectedPigment;
	private PigmentCtr pCtr;
	private JList list;
	private JPanel panel_1;
	private DefaultListModel model;

	/**
	 * Create the panel.
	 */
	public PigmentPanel() {
		pCtr = new PigmentCtr();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 232, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		list = new JList(createModel());
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				selectionChange();
			}
		});
		panel_1.add(list, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:200px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(99dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:max(148dlu;default):grow"),}));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Create", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3, "1, 2, fill, fill");
		
		JPanel panel_4 = new JPanel();
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(41, Short.MAX_VALUE))
		);
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default:grow"),}));
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7, "1, 1, fill, fill");
		panel_7.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		panel_7.add(lblNewLabel_1, "2, 2, left, default");
		
		txtName = new JTextField();
		panel_7.add(txtName, "4, 2, fill, default");
		txtName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ChanceTO:");
		panel_7.add(lblNewLabel_2, "2, 4, left, default");
		
		JPanel panel_8 = new JPanel();
		panel_7.add(panel_8, "4, 4, fill, fill");
		panel_8.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.DEFAULT_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.DEFAULT_ROWSPEC,}));
		
		txtChanceTo = new JTextField();
		panel_8.add(txtChanceTo, "1, 1, fill, default");
		txtChanceTo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("/");
		panel_8.add(lblNewLabel, "2, 1, right, default");
		
		txtChanceOff = new JTextField();
		panel_8.add(txtChanceOff, "3, 1, fill, default");
		txtChanceOff.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Min:");
		panel_7.add(lblNewLabel_3, "2, 6, left, default");
		
		txtMin = new JTextField();
		panel_7.add(txtMin, "4, 6, fill, default");
		txtMin.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Percent:");
		panel_7.add(lblNewLabel_4, "2, 8, left, default");
		
		txtPercent = new JTextField();
		panel_7.add(txtPercent, "4, 8, fill, default");
		txtPercent.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6, "1, 3, fill, fill");
		panel_6.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.GROWING_BUTTON_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:25px"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("25px"),
				FormFactory.LINE_GAP_ROWSPEC,}));
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				create();
			}
		});
		panel_6.add(btnCreate, "2, 2");
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		panel_6.add(btnNewButton, "4, 2");
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		panel_6.add(btnUpdate, "2, 4");
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		panel_6.add(btnNewButton_1, "4, 4");
		panel_3.setLayout(gl_panel_3);

	}

	private ListModel createModel() {
		model = new DefaultListModel();
		ArrayList<Pigment> pigments = pCtr.getAllPigments();
		for(Pigment p : pigments){
			model.addElement(p);
		}
		
		return model;
	}

	protected void selectionChange() {
		selectedPigment = (Pigment) list.getSelectedValue();
		if(selectedPigment != null){
			txtChanceOff.setText(String.valueOf(selectedPigment.getChanceOff()));
			txtChanceTo.setText(String.valueOf(selectedPigment.getChanceTo()));
			txtMin.setText(String.valueOf(selectedPigment.getMin()));
			txtName.setText(selectedPigment.getName());
			txtPercent.setText(String.valueOf(selectedPigment.getPercent()));
		}
	}

	protected void create() {
		if(checkValues()){
			model.addElement(pCtr.createPigment(txtName.getText(), Integer.parseInt(txtChanceTo.getText()), Integer.parseInt(txtChanceOff.getText()), Integer.parseInt(txtMin.getText()), Integer.parseInt(txtPercent.getText())));
		}
	}

	protected void delete() {
		if(selectedPigment != null){
			pCtr.deletePigment(selectedPigment);
			model.removeElement(selectedPigment);
		}
	}

	protected void update() {
		if(selectedPigment != null){
			if(checkValues()){
				pCtr.updatePigment(selectedPigment, txtName.getText(), Integer.parseInt(txtChanceTo.getText()), Integer.parseInt(txtChanceOff.getText()), Integer.parseInt(txtMin.getText()), Integer.parseInt(txtPercent.getText()));
				list.repaint();
			}
		}
	}

	protected void clear() {
		selectedPigment = null;
		txtChanceOff.setText("");
		txtChanceTo.setText("");
		txtMin.setText("");
		txtName.setText("");
		txtPercent.setText("");
	}
	
	protected boolean checkValues(){
		boolean retBool = true;
		
		try{
			if(txtName.getText().isEmpty() || txtName.getText() == null){
				retBool = false;
			}else{
				Integer.parseInt(txtChanceTo.getText());
				Integer.parseInt(txtChanceOff.getText());
				Integer.parseInt(txtMin.getText()) ;
				Integer.parseInt(txtPercent.getText());
			}
		}catch(Exception e){
			retBool = false;
		}
		
		return retBool;
	}
}
