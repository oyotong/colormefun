package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfFavorite;

public interface MfFavoriteService {
	
	public void saveMfFavorite(MfFavorite o);
	
	public void updateMfFavorite(MfFavorite o);
	
	public void deleteMfFavorite(MfFavorite o);
	
	public void deleteMfFavoriteByIds(Integer[] ids);
	
	public MfFavorite getMfFavoriteById(Serializable id);
	
	public List<MfFavorite> findAllMfFavorite();
	
	public List<MfFavorite> findAllMfFavoriteWithPage();
	
	public List<MfFavorite> findAllMfFavoriteBySearchWithPage(MfFavorite o);
	
	public Map findAllMfFavoriteByNameWithIdAndName(String name);
	
	public List<MfFavorite> findAllMfFavoriteByIds(Integer[] cids);

    public List<MfFavorite> findMyFavorites();
}
