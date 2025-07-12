package Persistencia;

import Entidades.TipoEndereco;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoEnderecoDAO extends ConexaoComOBancoDeDados implements InterfaceDAO<Integer, TipoEndereco>{

    @Override
    public void salvar(TipoEndereco tipoEndereco) {

        String sql = "INSERT INTO TIPO_ENDERECO "
                + "(DESCRICSO) VALUES "
                + "(?);";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, tipoEndereco.getDescricao());
            //pstm.setString(2, cep.getLogradouro());

            pstm.execute();

            getConexao().commit();

        } catch (SQLException ex) {
            System.out.println("Não foi possivel cadastrar o Tipo de Endereço " + tipoEndereco.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void modificar(TipoEndereco tipoEndereco) {

        String sql = "UPDATE TIPO_ENDERECO SET "
                + "DESCRICAO = ?, "
                + "WHERE ID = ?;";

        conectar();

        try {
            PreparedStatement pstm = getConexao().prepareStatement(sql);

            pstm.setString(1, tipoEndereco.getDescricao());
            //pstm.setString(2, estado.getSigla());
            //pstm.setInt(3, estado.getIdentificador());
            
            pstm.execute();
            
            getConexao().commit();

        } catch (Exception ex) {
            System.out.println("Não foi possivel atualizar o Tipo de Endereço " + tipoEndereco.getDescricao());
            System.out.println("Erro sql: " + ex);
        }
        desconectar();
    }

    @Override
    public void deletar(TipoEndereco tipoEndereco) {
    
        String sql = "DELETE FROM TIPO_ENDERECO "
                + "WHERE IDENTIFICADOR = ?;";
        
        conectar();
        
        
        try {
            
              PreparedStatement pstm = getConexao().prepareStatement(sql);              
              pstm.setString(1, tipoEndereco.getDescricao());
              pstm.execute();
              getConexao().commit();
              
        } catch (SQLException ex) {
            
            System.out.println("Não foi possivel excluir o Topo de Endereço" + tipoEndereco.getDescricao());
            System.out.println("Erro sql" + ex);
        }
        desconectar();
    }

    @Override
    public TipoEndereco buscarPorId(Integer id) {
    
        String sql = "SELECT * FROM TIPO_ENDERECO "
                + "WHERE IDENTIFICADOR = ?;";
        
        TipoEndereco tipoEndereco = new TipoEndereco();
        
        conectar();
        
        try {
            
             PreparedStatement pstm = getConexao().prepareStatement(sql);
             pstm.setInt(1, id);
             ResultSet lista = pstm.executeQuery();
             
             while (lista.next()){
                 
                 tipoEndereco.setDescricao(lista.getString("TIPO DE ENDERECO"));
                 //cep.setDescricao(lista.getString("DESCRIÇÃO"));
                 //cep.setSigla(lista.getString("SIGLA"));
                 
             }
        }catch (SQLException ex){
            
            System.out.println("Não foi possivel encontrar o Tipo de Endereço" + tipoEndereco.getDescricao());
            System.out.println("Erro SQL" + ex);
            
        }
        desconectar();
        return tipoEndereco;
    }

    @Override
    public List<TipoEndereco> listarTodos() {
    
       List<TipoEndereco> listaDeTipoEnderecos = new ArrayList<TipoEndereco>();
       
       String sql = "SELECT * FROM TIPO_ENDERECO;";
       
       conectar();
       
        try {
            
            PreparedStatement pstm = getConexao().prepareStatement(sql);
            ResultSet lista = pstm.executeQuery();
            
            while(lista.next()){
                TipoEndereco tipoEndereco = new TipoEndereco();
                tipoEndereco.setDescricao(lista.getString("TIPO DE ENDERECO"));
                //cep.setDescricao(lista.getString("DESCRIÇÃO"));
                //cep.setSigla(lista.getString("SIGLA"));
                
                listaDeTipoEnderecos.add(tipoEndereco);
            }
            
        } catch (SQLException ex) {
        
            System.out.println("Não foi possivel encontrar o Tipo do Endereço ");
            System.out.println("Erro SQL: " + ex);        
        }
       desconectar();
    return listaDeTipoEnderecos;
    }
    
}
