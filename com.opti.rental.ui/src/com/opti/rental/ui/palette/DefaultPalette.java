package com.opti.rental.ui.palette;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;
import com.opti.rental.ui.RentalUIActivator;
import com.opti.rental.ui.RentalUIConstants;

public class DefaultPalette implements IColorProvider, RentalUIConstants {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		Color result = null;
		if (element instanceof Rental) {
			result = this.getColorFromString(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_COLOR));
		} else if (element instanceof Customer) {
			result = this.getColorFromString(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_CUSTOMER_COLOR));
		} else if (element instanceof RentalObject) {
			result = this.getColorFromString(RentalUIActivator.getDefault().getPreferenceStore().getString(PREF_RENTAL_OBJECT_COLOR));
		} 
		return result;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	private Color getColorFromString(final String pRGBKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get(pRGBKey);
		if (null == col) {
			colorRegistry.put(pRGBKey, StringConverter.asRGB(pRGBKey));
			col = colorRegistry.get(pRGBKey);
		}
		return col;
	}
}
