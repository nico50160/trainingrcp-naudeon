package com.opti.rental.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.opti.rental.core.RentalCoreActivator;
import com.opti.rental.ui.RentalUIActivator;
import com.opti.rental.ui.providers.RentalProvider;

public class RentalTreeView extends ViewPart implements IPropertyChangeListener {

	private TreeViewer rentalTreeViewer;
	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}


	@Override
	public void dispose() {
		RentalUIActivator.getDefault().getPreferenceStore().removePropertyChangeListener(this);
		super.dispose();
	}
	
	@Override
	public void createPartControl(Composite parent) {
	
		final RentalProvider rentalProvider = new RentalProvider();
		rentalTreeViewer = new TreeViewer(parent);
		rentalTreeViewer.setLabelProvider(rentalProvider);
		rentalTreeViewer.setContentProvider(rentalProvider);
		List<RentalAgency> liste = new ArrayList<RentalAgency>();
		liste.add(RentalCoreActivator.getAgency());
		rentalTreeViewer.setInput(liste);
		getSite().setSelectionProvider(rentalTreeViewer);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void propertyChange(PropertyChangeEvent event) {
		rentalTreeViewer.refresh();
	}

}
