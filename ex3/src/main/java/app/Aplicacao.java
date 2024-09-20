package app;

import java.util.List;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Aplicacao {
	
	public static void main(String[] args) throws Exception {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		System.out.println("\n\n==== Inserir usuário === ");
		Usuario novoUsuario = new Usuario(11, "joao", "senha123", 'M');
		if(usuarioDAO.inserir(novoUsuario)) {
			System.out.println("Usuário inserido com sucesso: " + novoUsuario.toString());
		}
		
		System.out.println("\n\n==== Verificando autenticação ===");
		System.out.println("Usuário (" + novoUsuario.getLogin() + "): " + usuarioDAO.autenticar("joao", "senha123"));
			
		System.out.println("\n\n==== Listando usuários masculinos === ");
		List<Usuario> usuarios = usuarioDAO.getSexoMasculino();
		for (Usuario usuarioMasculino : usuarios) {
			System.out.println(usuarioMasculino.toString());
		}

		System.out.println("\n\n==== Alterando senha do usuário (código: " + novoUsuario.getCodigo() + ") === ");
		novoUsuario.setSenha(DAO.toMD5("novaSenha123"));
		usuarioDAO.update(usuario);
		
		System.out.println("\n\n==== Verificando autenticação ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", DAO.toMD5("pablo")));		
		
		System.out.println("\n\n==== Invadir usando SQL Injection ===");
		System.out.println("Usuário (" + usuario.getLogin() + "): " + usuarioDAO.autenticar("pablo", "x' OR 'x' LIKE 'x"));

		System.out.println("\n\n==== Mostrar usuários ordenados por código === ");
		usuarios = usuarioDAO.getOrderByCodigo();
		for (Usuario usuarioMasculino : usuarios) {
			System.out.println(usuarioMasculino.toString());
		}
		
		System.out.println("\n\n==== Excluir usuário (código " + usuario.getCodigo() + ") === ");
		usuarioDAO.delete(usuario.getCodigo());
		
		System.out.println("\n\n==== Mostrar usuários ordenados por login === ");
		usuarios = usuarioDAO.getOrderByLogin();
		for (Usuario usuarioMasculino : usuarios) {
			System.out.println(usuarioMasculino.toString());
		}
	}
}