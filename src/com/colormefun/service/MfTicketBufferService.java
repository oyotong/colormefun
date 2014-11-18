package com.colormefun.service;

import java.io.Serializable;
import java.util.*;

import com.colormefun.entity.MfTicketBuffer;
import com.colormefun.entity.MfTicketBufferPK;

public interface MfTicketBufferService {
	
	public void saveMfTicketBuffer(MfTicketBuffer o);
	
	public void updateMfTicketBuffer(MfTicketBuffer o);
	
	public void deleteMfTicketBuffer(MfTicketBuffer o);
	
	public void deleteMfTicketBufferByIds(MfTicketBufferPK[] ids);
	
	public MfTicketBuffer getMfTicketBufferById(Serializable id);
	
	public List<MfTicketBuffer> findAllMfTicketBuffer();
	
	public List<MfTicketBuffer> findAllMfTicketBufferWithPage();
	
	public List<MfTicketBuffer> findAllMfTicketBufferBySearchWithPage(MfTicketBuffer o);
	
	public Map findAllMfTicketBufferByNameWithIdAndName(String name);
	
	public List<MfTicketBuffer> findAllMfTicketBufferByIds(MfTicketBufferPK[] cids);

    void addToTicketBuffer(MfTicketBuffer mfTicketBuffer);
}
