package com.opti.rental.ui;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements RentalUIConstants {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.opti.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;
	
	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(this.getClass());
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_RENTAL_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
		reg.put(IMG_NODE, ImageDescriptor.createFromURL(b.getEntry(IMG_NODE)));
	}

}
