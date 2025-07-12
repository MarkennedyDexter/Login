package Persistencia;

import Entidades.Perfil;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PerfilDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Perfil> {

    @Override
    public void salvar(Perfil perfil) {
    
        String sql = "INSERT INTO PERFIL"
                + "(NOME) VALUE "
                + "?,?;";
        
        conectar();
        
       try {
           PreparedStatement pstm = getConexao().prepareStatement(sql);
           
            pstm.setString(1, perfil.getNome());            
            pstm.execute();
            
            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel identificar o Perfil" + perfil.getIdentificador());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(Perfil perfil) {
        
        String sql = "UPDATE PERFIL SET "
                + "NOME = ? "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, perfil.getNome());
            pstm.setInt(2, perfil.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o Perfil " + perfil.getNome());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(Perfil perfil) {
    
        String sql = "DELETE FROM PERFIL"
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setInt(1, perfil.getIdentificador());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o estado" + perfil.getNome());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public Perfil buscarPorId(Integer id) {
    
        String sql = "SELECT * FROM PERFIL "
                + "WHERE IDENTIFICADOR = ?;";
        
        Perfil perfil = new Perfil();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 perfil.setIdentificador(lista.getInt("IDENTIFICADOR"));
                 perfil.setNome(lista.getString("NOME"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o Perfil" + perfil.getNome());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return perfil;
    }

    @Override
    public List<Perfil> listarTodos() {
 List<Perfil> listaDePerfil = new ArrayList<Perfil>();
       
       String sql = "SELECT * FROM PERFIL;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Perfil perfil = new Perfil();
                perfil.setIdentificador(lista.getInt("IDENTIDIGADOR"));
                perfil.setNome(lista.getString("NOME"));
                
                listaDePerfil.add(perfil);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o perfil");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDePerfil;
    }    
    
}
