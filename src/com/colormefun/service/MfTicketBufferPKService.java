package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfTicketBufferPK;

public interface MfTicketBufferPKService {
	
	public void saveMfTicketBufferPK(MfTicketBufferPK o);
	
	public void updateMfTicketBufferPK(MfTicketBufferPK o);
	
	public void deleteMfTicketBufferPK(MfTicketBufferPK o);
	
	public void deleteMfTicketBufferPKByIds(MfTicketBufferPK[] ids);
	
	public MfTicketBufferPK getMfTicketBufferPKById(Serializable id);
	
	public List<MfTicketBufferPK> findAllMfTicketBufferPK();
	
	public List<MfTicketBufferPK> findAllMfTicketBufferPKWithPage();
	
	public List<MfTicketBufferPK> findAllMfTicketBufferPKBySearchWithPage(MfTicketBufferPK o);
	
	public Map findAllMfTicketBufferPKByNameWithIdAndName(String name);
	
	public List<MfTicketBufferPK> findAllMfTicketBufferPKByIds(MfTicketBufferPK[] cids);
	
}
