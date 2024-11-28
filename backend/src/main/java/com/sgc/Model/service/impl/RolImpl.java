package com.sgc.Model.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgc.Model.dao.RolDao;
import com.sgc.Model.entity.Rol;
import com.sgc.Model.service.IRol;

@Service
public class RolImpl implements IRol{

    @Autowired
    private RolDao rolDao;

    @Transactional
    @Override
    public Rol save(Rol rol) {
        return rolDao.save(rol);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<Rol> findAll(){
        return rolDao.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Rol findById(Integer id){
            return rolDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Rol rol) {
        rolDao.delete(rol);
    }
    
}
