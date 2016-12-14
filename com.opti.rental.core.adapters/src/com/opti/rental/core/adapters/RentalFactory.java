package com.opti.rental.core.adapters;

import org.eclipse.core.runtime.IAdapterFactory;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;

public class RentalFactory implements IAdapterFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		T result = null;
		if ((adaptableObject instanceof Rental) && (Customer.class == adapterType)) {
			result = (T) ((Rental) adaptableObject).getCustomer();
		}
		return result;
	}
	

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] {Customer.class};
	}

}
