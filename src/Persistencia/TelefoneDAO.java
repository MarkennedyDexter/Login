package Persistencia;

import Entidades.Telefone;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TelefoneDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Telefone>{

    @Override
    public void salvar(Telefone telefone) {

        String sql = "INSERT INTO TELEFONE "
                + "(NUMERO) VALUES "
                + "(?);";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, telefone.getNumero());

            pstm.execute();

            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel cadastrar o Telefone " + telefone.getNumero());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(Telefone telefone) {

        String sql = "UPDATE TELEFONE SET "
                + "NUMERO = ?, "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, telefone.getNumero());
            //pstm.setString(2, estado.getSigla());
            //pstm.setInt(3, estado.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o Telefone " + telefone.getNumero());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(Telefone telefone) {
    
        String sql = "DELETE FROM TELEFONE "
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setInt(1, telefone.getIdentificador());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o Telefone" + telefone.getNumero());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public Telefone buscarPorId(Integer id) {
    
        String sql = "SELECT * FROM TELEFONE "
                + "WHERE IDENTIFICADOR = ?;";
        
        Telefone telefone = new Telefone();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 telefone.setIdentificador(lista.getInt("IDENTIFICADOR"));
                 telefone.setNumero(lista.getString("NUMERO"));
                 //telefone.setSigla(lista.getString("SIGLA"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o Telefone " + telefone.getNumero());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return telefone;
    }

    @Override
    public List<Telefone> listarTodos() {
    
       List<Telefone> listaDeTelefone = new ArrayList<Telefone>();
       
       String sql = "SELECT * FROM TELEFONE;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Telefone telefone = new Telefone();
                telefone.setIdentificador(lista.getInt("IDENTIDIGADOR"));
                telefone.setNumero(lista.getString("NUMERO"));
                //telefone.setSigla(lista.getString("SIGLA"));
                
                listaDeTelefone.add(telefone);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o Telefone");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeTelefone;
    }
    
    
}
