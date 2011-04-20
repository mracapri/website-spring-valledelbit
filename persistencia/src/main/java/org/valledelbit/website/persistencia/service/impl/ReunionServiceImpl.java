package org.valledelbit.website.persistencia.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.valledelbit.website.persistencia.dao.ReunionDao;
import org.valledelbit.website.persistencia.service.ReunionService;
import org.valledelbit.website.persistencia.vo.Reunion;


public class ReunionServiceImpl implements ReunionService{
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	private ReunionDao reunionDao;

	@Override
	public List<Reunion> getReuniones() throws Exception {
		return reunionDao.findAll();
	}
	
	@Override
	public void setReunion(Reunion reunion)
			throws Exception {
		reunionDao.create(reunion);
	}
	
	@Override
	public Reunion getReunion(int id) throws Exception {
		return this.reunionDao.read(id);
	}

	public ReunionDao getReunionDao() {
		return reunionDao;
	}

	public void setReunionDao(ReunionDao reunionDao) {
		this.reunionDao = reunionDao;
	}
	
}
