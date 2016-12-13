package com.opti.rental.ui.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.opti.rental.core.RentalCoreActivator;

public class RentalPropertyView extends ViewPart implements ISelectionListener {

	private Label rentalObjectLabel;
	private Label rentalOwnerLabel;
	private Group grpDateDeLocation;
	
	private Label lblStartDateValue;
	private Label lblEndDateValue;
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		site.getPage().addSelectionListener(this);
	}


	@Override
	public void dispose() {
		getSite().getPage().removeSelectionListener(this);
		super.dispose();
	}


	@Override
	public void createPartControl(Composite parent) {
	
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Information");
		infoGroup.setLayout(new GridLayout(2, false));
		
		this.rentalObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.FILL;
		this.rentalObjectLabel.setLayoutData(gd);
		
		Label lblOwnerLabel = new Label(infoGroup, SWT.NONE);
		lblOwnerLabel.setText("Loué à :");
		this.rentalOwnerLabel = new Label(infoGroup, SWT.NONE);
		
		
		grpDateDeLocation = new Group(parent, SWT.NONE);
		grpDateDeLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpDateDeLocation.setText("Date de location");
		grpDateDeLocation.setLayout(new GridLayout(2, false));
		
		Label lblStartDateLabel = new Label(grpDateDeLocation, SWT.NONE);
		lblStartDateLabel.setText("Du :");
		
		lblStartDateValue = new Label(grpDateDeLocation, SWT.NONE);
		lblStartDateValue.setText("New Label");
		
		Label lblEndDateLabel = new Label(grpDateDeLocation, SWT.NONE);
		lblEndDateLabel.setText("Au :");
		
		lblEndDateValue = new Label(grpDateDeLocation, SWT.NONE);
	
		this.setLabelAsDragSource(rentalObjectLabel);
		this.setLabelAsDragSource(rentalOwnerLabel);
		this.setLabelAsDragSource(lblStartDateValue);
		this.setLabelAsDragSource(lblEndDateValue);
		
		this.setRental(RentalCoreActivator.getAgency().getRentals().get(0));
		
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void setRental(final Rental pRental) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		this.rentalObjectLabel.setText(pRental.getRentedObject().getName());
		this.rentalOwnerLabel.setText(pRental.getCustomer().getDisplayName());
		this.lblStartDateValue.setText(df.format(pRental.getStartDate()));
		this.lblEndDateValue.setText(df.format(pRental.getEndDate()));
	}


	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	
		if (selection instanceof IStructuredSelection) {
			final Object selected = ((IStructuredSelection) selection).getFirstElement();
			if (null != selected && selected instanceof Rental) {
				this.setRental((Rental) selected);
			}
		}
		
	}
	
	public void setLabelAsDragSource(final Label pLabel) {
		DragSource source = new DragSource(pLabel, DND.DROP_MOVE | DND.DROP_COPY);
		source.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		source.addDragListener(new DragSourceAdapter(){

			@Override
			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = pLabel.getText();
				}
			}
			
		});
	}
}
