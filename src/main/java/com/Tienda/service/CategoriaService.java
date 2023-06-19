package com.Tienda.service;

import com.Tienda.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    // Metodo para consultar las categorias. el parametro define si se muestarn solo las activas o todas
    public List<Categoria> getCategorias(boolean activos);
        
    
    
}
