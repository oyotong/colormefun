package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfContent;

public interface MfContentService {
	
	public void saveMfContent(MfContent o);
	
	public void updateMfContent(MfContent o);
	
	public void deleteMfContent(MfContent o);
	
	public void deleteMfContentByIds(Integer[] ids);
	
	public MfContent getMfContentById(Serializable id);
	
	public List<MfContent> findAllMfContent();
	
	public List<MfContent> findAllMfContentWithPage();
	
	public List<MfContent> findAllMfContentBySearchWithPage(MfContent o);
	
	public Map findAllMfContentByNameWithIdAndName(String name);
	
	public List<MfContent> findAllMfContentByIds(Integer[] cids);

    public List<MfContent> findTopNMarqueeOrderBySeqNo(Integer n);

    public MfContent getAbout();
	
}
