package org.valledelbit.website.persistencia.advice;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;


public class ServiceThrowsAdvice {

	Logger log = Logger.getLogger(this.getClass());

	public void doRecoveryActionDataAccess(DataAccessException dataAccessEx)
			throws Exception {
		log.debug(dataAccessEx.getMessage());
		throw new Exception("Error en la capa de persistencia @valledelbit: ", dataAccessEx);
	}

}
