package org.valledelbit.website.view.service;

import org.valledelbit.website.persistencia.exception.ValleDelBitWebSiteException;
public interface ShortenerService{
	String shortenerLongUrl(String longUrl) throws ValleDelBitWebSiteException;
}