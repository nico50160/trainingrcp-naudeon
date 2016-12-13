package com.opti.rental.ui.providers;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider {
	
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
		
		
	}
}
