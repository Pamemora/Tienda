package com.Tienda.service.impl;

import com.Tienda.dao.CategoriaDao;
import com.Tienda.domain.Categoria;
import com.Tienda.service.CategoriaService;
import java.security.spec.NamedParameterSpec;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    
    @Autowired
    CategoriaDao categoriaDao;

    @Override   
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
       List<Categoria> categorias = categoriaDao.findAll();
       
       if(activos){
           categorias.removeIf(x -> !x.isActivo());
       }
       return categorias;
    }
}
