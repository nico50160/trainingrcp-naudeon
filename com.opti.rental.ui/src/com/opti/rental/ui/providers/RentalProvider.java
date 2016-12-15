package com.opti.rental.ui.providers;

import java.util.List;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.opti.rental.ui.Palette;
import com.opti.rental.ui.RentalUIActivator;
import com.opti.rental.ui.RentalUIConstants;

public class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstants {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3157281591174406098L;
	
	private static final Object[] EMPTY_RESULT = new Object[0];
		
	@Override
	public Object[] getElements(Object inputElement) {
		
		Object[] result = null;
		
		if (null != inputElement) {
			if (inputElement instanceof List<?>) {
			@SuppressWarnings("unchecked")
			List<RentalAgency> liste = (List<RentalAgency>) inputElement;
			result = liste.toArray();
			}
		}
		return (null == result) ? EMPTY_RESULT : result;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] result = null;
		if (parentElement instanceof RentalAgency) {
			result = new Node[] {new Node(NodeType.CUSTOMER, (RentalAgency) parentElement), new Node(NodeType.LOCATION, (RentalAgency) parentElement), new Node(NodeType.RENTAL_OBJECT, (RentalAgency) parentElement)};
		} else if (parentElement instanceof Node) {
			result = ((Node) parentElement).getChildren();
		}
		return (null == result) ? EMPTY_RESULT : result;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return (element instanceof Node || element instanceof RentalAgency);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		} else if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		} 
		return super.getText(element);
	}

	@Override
	public Color getForeground(Object element) {
		Color result = null;
		final String paletteId = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_PALETTE);
		if (null != paletteId) {
			final Palette palette = RentalUIActivator.getDefault().getPaletteManager().get(paletteId);
			if (null != palette) {
				result = palette.getProvider().getForeground(element);
			}
		}
		return result;
	}

	@Override
	public Color getBackground(Object element) {
		Color result = null;
		final String paletteId = RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_PALETTE);
		if (null != paletteId) {
			final Palette palette = RentalUIActivator.getDefault().getPaletteManager().get(paletteId);
			if (null != palette) {
				result = palette.getProvider().getBackground(element);
			}
		}
		return result;
	}
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof Rental) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_RENTAL);
		} else if (element instanceof Customer) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_CUSTOMER);
		} else if (element instanceof RentalObject) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_RENTAL_OBJECT);
		} else if (element instanceof RentalAgency) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_AGENCY);
		} else if (element instanceof Node) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_NODE);
		}
		return super.getImage(element);
	}
	
	public enum NodeType {
		
		CUSTOMER("Client"),
		LOCATION("Locations"),
		RENTAL_OBJECT("Objets à louer");
		
		private String label;
		
		private NodeType(final String pLabel) {
			label = pLabel;
		}
		
		public String getLabel() {
			return this.label;
		}
	}
	
	public class Node {
		
		private NodeType type;
		private RentalAgency agency;
		
		public Node(NodeType type, RentalAgency agency) {
			super();
			this.type = type;
			this.agency = agency;
		}
		
		public Object[] getChildren() {
			Object[] result = null;
			switch (this.type) {
			case CUSTOMER:
				result = this.agency.getCustomers().toArray();
				break;
			case LOCATION:
				result = this.agency.getRentals().toArray();
				break;
			case RENTAL_OBJECT:
				result = this.agency.getObjectsToRent().toArray();
				break;
			default:
				result = EMPTY_RESULT;
			}
			return (null == result) ? EMPTY_RESULT : result;
		}

		@Override
		public String toString() {
			return this.type.getLabel();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((agency == null) ? 0 : agency.hashCode());
			result = prime * result + ((type == null) ? 0 : type.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (agency == null) {
				if (other.agency != null)
					return false;
			} else if (!agency.equals(other.agency))
				return false;
			if (type != other.type)
				return false;
			return true;
		}

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}
		
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}


}
