package org.valledelbit.website.persistencia.exception;

import org.springframework.core.NestedCheckedException;

public class ValleDelBitWebSiteException extends NestedCheckedException {

	private static final long serialVersionUID = 1L;

	public ValleDelBitWebSiteException(String msg) {
		super(msg);
	}
	
	public ValleDelBitWebSiteException(String mensaje, Throwable causa) {
		super(mensaje,causa);
	}

}