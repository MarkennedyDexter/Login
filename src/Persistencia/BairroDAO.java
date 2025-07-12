package Persistencia;

import Entidades.Bairro;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BairroDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Bairro>{
    
    private BairroDAO bairroDAO = new BairroDAO();
    
    @Override
    public void salvar(Bairro bairro) {
    
        String sql = "INSERT INTO BAIRRO "
                + "(DDESCRICAO, IDENTIFICADOR_CIDADE) VALUES "
                + "(?,?);";
        conectar();
        
        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            
            pstm.setString(1, bairro.getDescricao());
            pstm.setInt(2, bairro.getCidade().getIdentificador());
            pstm.execute();
            
            getConexao().commit();
            
        } catch (SQLException ex) {
           
            System.out.println("Não foi possivel cadastrar o Bairro" + bairro.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(Bairro bairro) {
    
         String sql = "UPDATE BAIRRO SET "
                + "DESCRICAO = ? "
                + "IDENTIFICADOR_CIDADE= ? "
                + "WHERE IDENTIFICADOR = ?:";
        
        conectar();
        
        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            
            pstm.setString(1, bairro.getDescricao());
            pstm.setInt(2, bairro.getCidade().getIdentificador());
            pstm.setInt(3, bairro.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();
            
        } catch (SQLException ex) {
            System.out.println("Não foi possivel modificar o Bairro " + bairro.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(Bairro bairro) {
    
        String sql = "DELETE FROM BAIRRO"
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setInt(1, bairro.getIdentificador());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o Bairro" + bairro.getDescricao());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public Bairro buscarPorId(Integer id) {
    
        Bairro bairro = new Bairro();
        
        String sql = "SELECT * FROM BAIRRO "
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 bairro.setIdentificador(lista.getInt("IDENTIFICADOR"));
                 bairro.setDescricao(lista.getString("DESCRICAO"));
                 //bairro.setCidade(cidadeDAO.buscarPorId(lista.getInt("IDENTIFICADOR_CIDADE")));
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel apresentar o Bairro" + bairro.getDescricao());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return bairro;    
    }

    @Override
    public List<Bairro> listarTodos() {
        
        List<Bairro> listaDeBairro = new ArrayList<Bairro>();
       
       String sql = "SELECT * FROM BAIRRO;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Bairro bairro = new Bairro();
                bairro.setIdentificador(lista.getInt("IDENTIDIGADOR"));
                bairro.setDescricao(lista.getString("BAIRRO"));
                
                listaDeBairro.add(bairro);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o Bairro");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeBairro;
    }       
    
}

