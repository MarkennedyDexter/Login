package Persistencia;

import Entidades.Cep;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CepDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Cep>{

    @Override
    public void salvar(Cep cep) {

        String sql = "INSERT INTO CEP "
                + "(CEP,LOGRADOURO) VALUES "
                + "(?,?);";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setInt(1, cep.getCep());
            //pstm.setString(2, cep.getLogradouro());

            pstm.execute();

            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel cadastrar o CEP " + cep.getCep());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(Cep cep) {

        String sql = "UPDATE CEP SET "
                + "CEP = ?, "
                + "LOGRADOURO = ? "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setInt(1, cep.getCep());
            //pstm.setString(2, estado.getSigla());
            //pstm.setInt(3, estado.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o CEP " + cep.getCep());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(Cep cep) {
    
        String sql = "DELETE FROM CEP "
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setInt(1, cep.getCep());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o CEP" + cep.getCep());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public Cep buscarPorId(Integer id) {
    
        String sql = "SELECT * FROM CEP "
                + "WHERE IDENTIFICADOR = ?;";
        
        Cep cep = new Cep();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 cep.setCep(lista.getInt("CEP"));
                 //cep.setDescricao(lista.getString("DESCRIÇÃO"));
                 //cep.setSigla(lista.getString("SIGLA"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o CEP" + cep.getCep());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return cep;
    }

    @Override
    public List<Cep> listarTodos() {
    
       List<Cep> listaDeCep = new ArrayList<Cep>();
       
       String sql = "SELECT * FROM ESTADO;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Cep cep = new Cep();
                cep.setCep(lista.getInt("CEP"));
                //cep.setDescricao(lista.getString("DESCRIÇÃO"));
                //cep.setSigla(lista.getString("SIGLA"));
                
                listaDeCep.add(cep);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o CEP");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeCep;
    }
    
}
