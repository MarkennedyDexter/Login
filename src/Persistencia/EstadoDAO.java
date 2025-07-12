package Persistencia;

import Entidades.Estado;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EstadoDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Estado> {

    @Override
    public void salvar(Estado estado) {

        String sql = "INSERT INTO ESTADO "
                + "(DESCRICACAO, SIGLA) VALUES "
                + "(?,?);";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, estado.getDescricao());
            pstm.setString(2, estado.getSigla());

            pstm.execute();

            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel cadastrar o Estado " + estado.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(Estado estado) {

        String sql = "UPDATE ESTADO SET "
                + "DESCRICAO = ?, "
                + "SIGLA = ? "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, estado.getDescricao());
            pstm.setString(2, estado.getSigla());
            pstm.setInt(3, estado.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o Estado " + estado.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(Estado estado) {
    
        String sql = "DELETE FROM ESTADO"
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setInt(1, estado.getIdentificador());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o Estado" + estado.getDescricao());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public Estado buscarPorId(Integer id) {
    
        String sql = "SELECT * FROM ESTADO "
                + "WHERE IDENTIFICADOR = ?;";
        
        Estado estado = new Estado();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 estado.setIdentificador(lista.getInt("IDENTIFICADOR"));
                 estado.setDescricao(lista.getString("DESCRIÇÃO"));
                 estado.setSigla(lista.getString("SIGLA"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o Estado" + estado.getDescricao());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return estado;
    }

    @Override
    public List<Estado> listarTodos() {
    
       List<Estado> listaDeEstados = new ArrayList<Estado>();
       
       String sql = "SELECT * FROM ESTADO;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Estado estado = new Estado();
                estado.setIdentificador(lista.getInt("IDENTIDIGADOR"));
                estado.setDescricao(lista.getString("DESCRIÇÃO"));
                estado.setSigla(lista.getString("SIGLA"));
                
                listaDeEstados.add(estado);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar os Estados");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeEstados;
    }
}
