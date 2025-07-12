package Persistencia;

import Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Usuario>{

    @Override
    public void salvar(Usuario usuario) {

        String sql = "INSERT INTO USUARIO "
                + "(CPF,NOME,USER_NAME,SENHA,) VALUES "
                + "(?,?,?,?);";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, usuario.getCpf());
            pstm.setString(2, usuario.getNome());
            pstm.setString(3, usuario.getUserName());
            pstm.setString(4, usuario.getSenha());

            pstm.execute();

            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel cadastrar o Usuario " + usuario.getCpf());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(Usuario usuario) {

        String sql = "UPDATE USUARIO SET "
                + "CPF = ?, "
                + "NOME = ? "
                + "USER_NAME = ? "
                + "SENHA = ? "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, usuario.getCpf());
            pstm.setString(2, usuario.getNome());
            //pstm.setInt(3, usuario.getUserName());
            //pstm.setInt(4, usuario.getSenha());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o Usuario " + usuario.getCpf());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(Usuario usuario) {
    
        String sql = "DELETE FROM USUARIO "
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setString(1, usuario.getCpf());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o Usuario" + usuario.getCpf());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
    
        String sql = "SELECT * FROM USUARIO "
                + "WHERE IDENTIFICADOR = ?;";
        
        Usuario usuario = new Usuario();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 usuario.setCpf(lista.getString("CPF"));
                 usuario.setNome(lista.getString("NOME"));
                 usuario.setUserName(lista.getString("USER_NAME"));
                 usuario.setSenha(lista.getString("SENHA"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o Usuario " + usuario.getCpf());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return usuario;
    }


    @Override
    public List<Usuario> listarTodos() {
    
       List<Usuario> listaDeUsuario = new ArrayList<Usuario>();
       
       String sql = "SELECT * FROM USUARIO;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Usuario usuario = new Usuario();
                usuario.setCpf(lista.getString("CPF"));
                usuario.setNome(lista.getString("NOME"));
                usuario.setUserName(lista.getString("SENHA"));
                usuario.setSenha(lista.getString("SENHA"));
                
                listaDeUsuario.add(usuario);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o Usuario");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeUsuario;
    }
    
}
