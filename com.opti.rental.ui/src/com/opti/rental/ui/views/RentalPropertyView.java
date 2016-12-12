package com.opti.rental.ui.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.opti.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart {

	private Label rentalObjectLabel;
	private Label rentalOwnerLabel;
		
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
	
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Information");
		infoGroup.setLayout(new GridLayout(2, false));
		
		this.rentalObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		this.rentalObjectLabel.setLayoutData(gd);
				
		this.rentalOwnerLabel = new Label(infoGroup, SWT.NONE);
		gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		this.rentalOwnerLabel.setLayoutData(gd);
		this.setRental(RentalCoreActivator.getAgency().getRentals().get(0));
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void setRental(final Rental pRental) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.rentalObjectLabel.setText(pRental.getRentedObject().getName());
		this.rentalOwnerLabel.setText(String.format("Loué à : %s", pRental.getCustomer().getDisplayName()));
	}
}
