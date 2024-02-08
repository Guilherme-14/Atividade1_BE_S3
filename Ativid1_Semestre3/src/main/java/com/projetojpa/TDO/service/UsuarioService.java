package com.projetojpa.TDO.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetojpa.TDO.entities.Usuario;
import com.projetojpa.TDO.repository.UsuarioRepository;
import com.projetojpa.TDO.tdo.UsuarioDTO;


@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioService (UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	//Método modificado para utilizar o DTO
	public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario(usuarioDTO.nome(),usuarioDTO.senha());
		Usuario salvarUsuario = usuarioRepository.save(usuario);
		return new UsuarioDTO(salvarUsuario.getId(), salvarUsuario.getNome(), salvarUsuario.getSenha());
	}
	
	public UsuarioDTO atualizar(Long id, UsuarioDTO usuarioDTO) {
		Usuario existeUsuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
		
		existeUsuario.setNome(usuarioDTO.nome());
		existeUsuario.setSenha(usuarioDTO.senha());
		
		Usuario updateUsuario = usuarioRepository.save(existeUsuario);
		return new UsuarioDTO(updateUsuario.getId(), updateUsuario.getNome(), updateUsuario.getSenha()); 
	}
	
	public boolean deletarUsuario(Long id) {
		Optional <Usuario> existeUsuario = usuarioRepository.findById(id);
		if (existeUsuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Usuario> buscaTodosUsuario(){
		return usuarioRepository.findAll();
	}
	
	public Usuario BuscaUsuarioId(Long id) {
		Optional <Usuario> Usuario = usuarioRepository.findById(id);
		return Usuario.orElse(null);
	}
}
