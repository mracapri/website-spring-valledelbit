package org.valledelbit.website.persistencia.advice;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.valledelbit.website.persistencia.exception.ValleDelBitWebSiteException;


public class ServiceThrowsAdvice {

	Logger log = Logger.getLogger(this.getClass());

	public void doRecoveryActionDataAccess(DataAccessException dataAccessEx)
			throws ValleDelBitWebSiteException {
		log.debug(dataAccessEx.getMessage());
		throw new ValleDelBitWebSiteException("Error en la capa de persistencia @valledelbit: ", dataAccessEx);
	}

}
