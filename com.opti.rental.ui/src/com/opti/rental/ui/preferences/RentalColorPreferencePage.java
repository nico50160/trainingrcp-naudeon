package com.opti.rental.ui.preferences;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.opti.rental.ui.RentalUIActivator;
import com.opti.rental.ui.RentalUIConstants;

public class RentalColorPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage, RentalUIConstants {

	public RentalColorPreferencePage() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Gestion des couleurs");
	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMER_COLOR, "Couleur des clients :", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_OBJECT_COLOR, "Couleur des objets à louer :", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTAL_COLOR, "Couleur des locations :", getFieldEditorParent()));
	}
	
	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

}
