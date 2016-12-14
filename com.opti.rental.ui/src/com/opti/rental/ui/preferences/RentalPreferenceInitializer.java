package com.opti.rental.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.opti.rental.ui.RentalUIActivator;
import com.opti.rental.ui.RentalUIConstants;

public class RentalPreferenceInitializer extends AbstractPreferenceInitializer implements RentalUIConstants {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		store.setDefault(PREF_CUSTOMER_COLOR, StringConverter.asString(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE).getRGB()));
		store.setDefault(PREF_RENTAL_COLOR, StringConverter.asString(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN).getRGB()));
		store.setDefault(PREF_RENTAL_OBJECT_COLOR, StringConverter.asString(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY).getRGB()));
	}

}
