package dk.is12b.guiLayer;
import java.util.ArrayList;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import dk.is12b.dbLayer.DBComposit;
import dk.is12b.modelLayer.Composit;
import dk.is12b.modelLayer.Herb;
import dk.is12b.modelLayer.Ink;
import dk.is12b.modelLayer.Pigment;


public class HerbTreeModel implements TreeModel{
	private ArrayList<Herb> herbs;
	private String root;
	
	public HerbTreeModel(ArrayList<Herb> herbs){
		this.herbs = herbs;
		this.root = "Herbs";
	}

	
	public void addTreeModelListener(TreeModelListener arg0) {}

	
	public Object getChild(Object parent, int index) {
		Object o = null;
		
		if(parent instanceof String){
			if(herbs.size() < index){
				return null;
			}else{
				o = herbs.get(index);
			}
		}else if(parent instanceof Herb){
			ArrayList<Pigment> pigs = ((Herb)parent).getPigments();
			if(pigs.size() < index){ 
				return null;
			}else{
				o = pigs.get(index);
			}
		}else if(parent instanceof Pigment){
			DBComposit dbComp = new DBComposit();
			ArrayList<Composit> comps = dbComp.getCompositsByPigment((Pigment)parent);
			if(comps.size() < index){ 
				return null;
			}else{
				o = comps.get(index);
			}
		}else{
			o = null;
		}
		
		return o;
	}

	
	public int getChildCount(Object parent) {
		int count = 0;
		if(parent instanceof String){
			count = herbs.size();
		}else if(parent instanceof Herb){
			count = ((Herb)parent).getPigments().size();
		}else if(parent instanceof Pigment){
			DBComposit dbComp = new DBComposit();
			count = dbComp.getCompositsByPigment((Pigment)parent).size();
		}
		
		return count;
	}

	
	public int getIndexOfChild(Object parent, Object child) {
		int res = -1;
		if(parent instanceof String){
			res = herbs.indexOf(child);
		}else if(parent instanceof Herb){
			res = ((Herb)parent).getPigments().indexOf(child);
		}else if(parent instanceof Pigment){
			DBComposit dbComp = new DBComposit();
			res = dbComp.getCompositsByPigment((Pigment)parent).indexOf(child);
		}
		
		return res;
	}

	
	public Object getRoot() {
		return root;
	}

	
	public boolean isLeaf(Object node) {
		
		boolean res = true;
		
		if(node instanceof Herb || node instanceof Pigment || node instanceof Ink || node instanceof String){
			res = false;
		}
		
		return res;
	}

	
	public void removeTreeModelListener(TreeModelListener arg0) {}

	
	public void valueForPathChanged(TreePath arg0, Object arg1) {}
	
}
