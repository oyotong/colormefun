package com.colormefun.dao;

import java.io.Serializable;
import java.util.*;
import org.springframework.stereotype.Repository;
import shop.common.dao.auto.AbstractDAO;
import shop.common.dao.auto.AutoDAO;
import shop.common.dao.auto.BaseDAO;
import com.colormefun.entity.MfTicketBufferPK;
import shop.common.util.ArraysUtils;

@Repository
@AutoDAO
public class MfTicketBufferPKDAOImpl extends AbstractDAO<MfTicketBufferPK> implements
		BaseDAO<MfTicketBufferPK>, MfTicketBufferPKDAO {

    public MfTicketBufferPKDAOImpl(){
        super(MfTicketBufferPK.class);
    }

    public MfTicketBufferPKDAOImpl(Class<MfTicketBufferPK> type) {
        super(type);
    }

    @Override
    public List<MfTicketBufferPK> findAllBySearchWithPage(MfTicketBufferPK o) {
        return null;
    }

    @Override
    public Map findAllByNameWithIdAndName(String name) {
        return null;
    }

    @Override
    public List<MfTicketBufferPK> findAllByIds(Serializable[] cids) {
        return null;
    }
}
