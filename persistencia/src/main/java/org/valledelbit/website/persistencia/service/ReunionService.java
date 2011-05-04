package org.valledelbit.website.persistencia.service;

import java.util.List;

import org.valledelbit.website.persistencia.exception.ValleDelBitWebSiteException;
import org.valledelbit.website.persistencia.vo.Reunion;

public interface ReunionService {
	void setReunion(Reunion reunion) throws ValleDelBitWebSiteException;
	List<Reunion> getReuniones() throws ValleDelBitWebSiteException;
	Reunion getReunion(int id) throws ValleDelBitWebSiteException;
	Reunion getReunion(String nombreLink) throws ValleDelBitWebSiteException;
	void removeReunion(Reunion reunion) throws ValleDelBitWebSiteException;
	void updateReunion(Reunion reunion) throws ValleDelBitWebSiteException;
}
