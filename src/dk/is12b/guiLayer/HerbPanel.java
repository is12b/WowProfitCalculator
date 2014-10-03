package dk.is12b.guiLayer;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import dk.is12b.ctrLayer.HerbCtr;
import dk.is12b.modelLayer.Herb;

public class HerbPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel panel_1;
	protected JLabel lblTitle;
	protected JScrollPane scrollPane;
	protected JPanel panel;
	protected JPanel panel_3;
	protected JPanel panel_2;
	protected JPanel panel_4;
	protected JTextField txtItem1;
	protected JLabel lblItem;
	protected JPanel panel_5;
	protected JButton btnSearch;
	protected JButton btnClear;
	protected JTree tree;
	protected ArrayList<Object> selected;
	protected ArrayList<TreePath[]> paths;
	protected HerbTreeModel model;
	protected ArrayList<Herb> herbs;
	
	/**
	 * Create the panel.
	 */
	public HerbPanel() {
		HerbCtr hCtr = new HerbCtr();
		herbs = hCtr.getAllHerbs();
		paths = new ArrayList<TreePath[]>();
		selected = new ArrayList<Object>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{440, 168, 0};
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
		
		lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel_1.add(lblTitle, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		
		
		tree = new JTree();
		scrollPane.setViewportView(tree);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				makeSelections(arg0);
			}
		});
		
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				treePopup(e);
			}
		});
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("15px"),
				RowSpec.decode("fill:default"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_3, "1, 2, fill, fill");
		panel_3.setLayout(new BorderLayout(0, 0));
		
		panel_4 = new JPanel();
		panel_3.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:default"),}));
		
		lblItem = new JLabel("Item1");
		panel_4.add(lblItem, "2, 2, left, default");
		
		txtItem1 = new JTextField();
		panel_4.add(txtItem1, "4, 2, fill, default");
		txtItem1.setColumns(10);
		
		panel_5 = new JPanel();
		panel_4.add(panel_5, "1, 4, 4, 1, fill, fill");
		panel_5.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(35dlu;pref):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(35dlu;pref):grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		btnClear = new JButton("Clear");
		panel_5.add(btnClear, "1, 2");
		
		btnSearch = new JButton("Search");
		panel_5.add(btnSearch, "3, 2");
		
		panel_2 = new JPanel();
		panel.add(panel_2, "1, 4, fill, fill");
		
		model = new HerbTreeModel(herbs);
		tree.setModel(model);
	}

	public JTextField getPrimaryTextField() {
		return txtItem1;
	}

	public JButton getPrimaryButton() {
		return btnSearch;
	}
	
	protected void makeSelections(TreeSelectionEvent arg0) {
		TreePath[] pth = arg0.getPaths();
		for(TreePath p : pth){
			if(arg0.isAddedPath(p)){
				selected.add(p.getLastPathComponent());
			}else{
				selected.remove(p.getLastPathComponent());
			}
		}
	}

	protected void treePopup(MouseEvent e) {
		if(SwingUtilities.isRightMouseButton(e)){
			if(selected.size() > 0){
				boolean trees = false, forest = false, owner = false;
				for(Object sel : selected){
					//if(sel instanceof Owner){
					//	owner = true;
					//}else if(sel instanceof Forest){
					//	forest = true;
					//}else if(sel instanceof Tree){
					//	trees = true;
					//}
				}
				
				JPopupMenu popupMenu = new JPopupMenu();
				if((owner) || (trees) || (forest)){
					JMenuItem mntmAllUpdate = new JMenuItem("Update");
					mntmAllUpdate.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							//update();
						}
					});
					JMenuItem mntmAllRemove = new JMenuItem("Remove");
					mntmAllRemove.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							//remove();
						}
					});
					popupMenu.add(mntmAllUpdate);
					popupMenu.add(mntmAllRemove);
				}
				
				popupMenu.show(tree, e.getX(), e.getY());
			}
		}
	}
	
	/*
	protected void remove(){
		for(Object o : selected){
			if(o instanceof Owner){
				OwnerCtr oCtr = new OwnerCtr();
				oCtr.remove((Owner)o);
			}else if(o instanceof Forest){
				ForestCtr fCtr = new ForestCtr();
				if(((Forest)o).getOwner() != null){
					((Forest)o).getOwner().removeForest((Forest)o);
				}
				fCtr.removeForest((Forest)o);
			}else if(o instanceof Tree){
				ForestCtr fCtr = new ForestCtr();
				fCtr.remove((Tree)o);
			}
		}
		updateData();
	}

	protected void update(){
		if(selected.size() > 0){
			Updater u = new Updater(selected, this);
			u.setVisible(true);
		}
	}
	
	
	protected void updateData(){
		//Nothing
	}
	*/
	
}
