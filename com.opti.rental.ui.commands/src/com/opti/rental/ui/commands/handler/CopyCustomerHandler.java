package com.opti.rental.ui.commands.handler;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.opcoach.training.rental.Customer;
import com.opti.rental.ui.RentalUIActivator;
import com.opti.rental.ui.RentalUIConstants;

public class CopyCustomerHandler extends AbstractHandler implements RentalUIConstants {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		if (null != event) {
			ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
			if (null != currentSelection && currentSelection instanceof IStructuredSelection) {
				IStructuredSelection iss = (IStructuredSelection) currentSelection;
				String textData = "";
				for (Iterator<?> it = iss.iterator(); it.hasNext();) {
					Customer customer = (Customer) it.next();
					textData = textData + customer.getDisplayName();
				}
				Clipboard clipboard = new Clipboard(Display.getCurrent());
				TextTransfer textTransfer = TextTransfer.getInstance();
				ImageTransfer imageTransfer = ImageTransfer.getInstance();
				Transfer[] transfers = new Transfer[]{textTransfer, imageTransfer};
				Object[] data = new Object[]{textData, RentalUIActivator.getDefault().getImageRegistry().get(IMG_AGENCY).getImageData()};
				clipboard.setContents(data, transfers);
				clipboard.dispose();
			}
		}		
		return null;
	}

}
