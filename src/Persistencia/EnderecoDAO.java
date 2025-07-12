package Persistencia;

import Entidades.Endereco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EnderecoDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, Endereco>{

    @Override
    public void salvar(Endereco endereco) {
    
        String sql = "INSERT INTO ENDERECO "
                + "(COMPLEMENTO, PONTO_REFERENCIA) VALUES "
                + "(?,?);";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, endereco.getComplemento());
            pstm.setString(2, endereco.getPontoReferencia());

            pstm.execute();

            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel cadastrar o Endereço " + endereco.getComplemento());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }


    @Override
    public void modificar(Endereco endereco) {
    
        String sql = "UPDATE ENDERECO SET "
                + "COMPLEMENTO = ?, "
                + "PONTO_REFERENCIA = ? "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, endereco.getComplemento());
            pstm.setString(2, endereco.getPontoReferencia());
            //pstm.setInt(3, cidade.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o Endereço " + endereco.getComplemento());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }


    @Override
    public void deletar(Endereco endereco) {
        
        String sql = "DELETE FROM ENDERECO"
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setInt(1, endereco.getIdentificador());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o Endereço" + endereco.getComplemento());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }


    @Override
    public Endereco buscarPorId(Integer id) {
        
        String sql = "SELECT * FROM ENDERECO "
                + "WHERE IDENTIFICADOR = ?;";
        
        Endereco endereco = new Endereco();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 endereco.setIdentificador(lista.getInt("IDENTIFICADOR"));
                 endereco.setComplemento(lista.getString("COMPLEMENTO"));
                 endereco.setPontoReferencia(lista.getString("PONTO_REFERENCIA"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o Endereço" + endereco.getComplemento());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return endereco;
    }

    @Override
    public List<Endereco> listarTodos() {
    
       List<Endereco> listaDeEndereco = new ArrayList<Endereco>();
       
       String sql = "SELECT * FROM ENDERECO;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                Endereco endereco = new Endereco();
                endereco.setIdentificador(lista.getInt("IDENTIDIGADOR"));
                endereco.setComplemento(lista.getString("COMPLEMENTO"));
                endereco.setPontoReferencia(lista.getString("PONTO_REFERENCIA"));
                
                listaDeEndereco.add(endereco);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o Endereço");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeEndereco;
    }
    
    
}
