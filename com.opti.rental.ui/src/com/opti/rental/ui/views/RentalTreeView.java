package com.opti.rental.ui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.opti.rental.core.RentalCoreActivator;
import com.opti.rental.ui.providers.RentalProvider;

public class RentalTreeView extends ViewPart {

	
	@Override
	public void createPartControl(Composite parent) {
	
		final RentalProvider rentalProvider = new RentalProvider();
		TreeViewer rentalTreeViewer = new TreeViewer(parent);
		rentalTreeViewer.setLabelProvider(rentalProvider);
		rentalTreeViewer.setContentProvider(rentalProvider);
		List<RentalAgency> liste = new ArrayList<RentalAgency>();
		liste.add(RentalCoreActivator.getAgency());
		rentalTreeViewer.setInput(liste);
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
