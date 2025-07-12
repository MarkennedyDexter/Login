package Persistencia;

import Entidades.Cidade;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CidadeDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Cidade>{
    
    private EstadoDAO estadoDAO = new EstadoDAO();
    
    @Override
    public void salvar(Cidade cidade) {
    
        String sql = "INSERT INTO CIDADE "
                + "(DDESCRICAO, IDENTIFICADOR_ESTADO) VALUES "
                + "(?,?);";
        
        conectar();
        
        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            
            pstm.setString(1, cidade.getDescricao());
            pstm.setInt(2, cidade.getEstado().getIdentificador());
            pstm.execute();
            
            getConexao().commit();
            
        } catch (SQLException ex) {
           
            System.out.println("Não foi possivel cadastrar a Cidade" + cidade.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(Cidade cidade) {
    
        String sql = "UPDATE CIDADE SET "
                + "DESCRICAO = ? "
                + "IDENTIFICADOR_ESTADO = ? "
                + "WHERE IDENTIFICADOR = ?:";
        
        conectar();
        
        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            
            pstm.setString(1, cidade.getDescricao());
            pstm.setInt(2, cidade.getEstado().getIdentificador());
            pstm.setInt(3, cidade.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();
            
        } catch (SQLException ex) {
            System.out.println("Não foi possivel modificar a Cidade " + cidade.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(Cidade cidade) {
    
        String sql = "DELETE FROM CIDADE"
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setInt(1, cidade.getIdentificador());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir a Cidade" + cidade.getDescricao());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public Cidade buscarPorId(Integer id) {
            
        Cidade cidade = new Cidade();
        
        String sql = "SELECT * FROM CIDADE "
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 cidade.setIdentificador(lista.getInt("IDENTIFICADOR"));
                 cidade.setDescricao(lista.getString("DESCRICAO"));
                 cidade.setEstado(estadoDAO.buscarPorId(lista.getInt("IDENTIFICADOR_ESTADO")));                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel apresentar a Cidade" + cidade.getDescricao());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return cidade;    
    }

    @Override
    public List<Cidade> listarTodos() {
       List<Cidade> listaDeCidade = new ArrayList<Cidade>();
       
       String sql = "SELECT * FROM CIDADE;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Cidade cidade = new Cidade();
                cidade.setIdentificador(lista.getInt("IDENTIDIGADOR"));
                cidade.setDescricao(lista.getString("CIDADE"));
                
                listaDeCidade.add(cidade);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar a Cidade");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeCidade;
    }       
    
}
