package org.valledelbit.website.persistencia.dao;

import org.valledelbit.website.persistencia.vo.Reunion;

public interface ReunionDao extends Dao<Reunion, Integer> {
	Reunion read(String nombreLink);
}
