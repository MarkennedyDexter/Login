package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.Getter;

public abstract class ConexaoComOBancoDeDados {

    private final String usuario = "SA";
    private final String senha = "";
    private final String urlDoBanco = "jdbc:hsqldb:file:BancoDeDados/LoginDB;hsqldb.lock_file=false";

    @Getter
    protected Connection conexao;

    public Connection conectar() {
        try {
            conexao = DriverManager.getConnection(urlDoBanco, usuario, senha); // ESSENCIAL
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a conexão");
            System.out.println("Erro SQL: " + ex);
        }
        return conexao;
    }

    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível realizar a desconexão");
            System.out.println("Erro SQL: " + ex);
        }
    }

    public void executarComandoSql(String sql) {
        try {
            conectar();
            if (conexao != null) {
                PreparedStatement pstm = conexao.prepareStatement(sql);
                pstm.execute();
                System.out.println("Executando o comando SQL --> " + sql);
                System.out.println("Executado com sucesso!");
            } else {
                System.out.println("Conexão nula: verifique o banco e a URL.");
            }
        } catch (SQLException ex) {
            System.out.println("Não foi possível executar o comando SQL");
            System.out.println("Erro SQL: " + ex);
        } finally {
            desconectar();
        }
    }

    public boolean isTabelaVazia(String tabela) {

        String sql = "SELECT * FROM " + tabela;

        boolean resposta = true;

        try {
            conectar();
            PreparedStatement pstm = conexao.prepareStatement(sql);

            ResultSet lista = pstm.executeQuery();

            while (lista.next()) {

                resposta = false;

            }

        } catch (SQLException ex) {
            System.out.println("ERRO SQL: " + ex);
        }

        return resposta;
    }
}
