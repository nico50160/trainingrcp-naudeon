package com.opti.rental.ui.providers;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

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
			final RentalAgency agency = (RentalAgency) parentElement;
			result = agency.getCustomers().toArray();
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
		boolean result = false;
		if (element instanceof RentalAgency) {
			final RentalAgency agency = (RentalAgency) element;
			if (null != agency.getCustomers() && (!agency.getCustomers().isEmpty())) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		} else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		}
		return super.getText(element);
	}

}
