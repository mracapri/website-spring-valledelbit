package org.valledelbit.website.persistencia.service;

import java.util.List;

import org.valledelbit.website.persistencia.vo.Reunion;

public interface ReunionService {
	void setReunion(Reunion reunion) throws Exception;
	List<Reunion> getReuniones() throws Exception;
	Reunion getReunion(int id) throws Exception;
}
