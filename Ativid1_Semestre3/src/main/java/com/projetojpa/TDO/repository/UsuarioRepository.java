package com.projetojpa.TDO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetojpa.TDO.entities.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario , Long>{

}
