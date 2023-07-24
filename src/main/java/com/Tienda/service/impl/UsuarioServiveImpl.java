
package com.Tienda.service.impl;

import com.Tienda.dao.UsuarioDao;
import com.Tienda.domain.Rol;
import com.Tienda.domain.Usuario;
import com.Tienda.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioServiveImpl implements UsuarioService, UserDetailsService{
    
   @Autowired
   private UsuarioDao usuarioDao;
   
   @Autowired
   private HttpSession session;
   
   @Override
   @Transactional (readOnly =true)
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       //busca el usuario en base de datos
        Usuario usuario = usuarioDao.findByUsername(username);
        // si no existe en base tira execitom
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        
        var roles = new ArrayList<GrantedAuthority>();
        
        for (Rol rol : usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        //se retorna un objeto de tipo User
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
