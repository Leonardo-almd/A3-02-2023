package com.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class CriarDados {
    public static void main(String[] args) {
        try (Connection conexao = ConexaoMySQL.obterConexao();
                Statement statement = conexao.createStatement()) {
                
            String caminhoRelativo = "src/main/java/com/example/migration/criacao_de_dados.sql";
            String caminhoCompleto = Paths.get(System.getProperty("user.dir"), caminhoRelativo).toString();

            String scriptSQL = lerScriptSQL(caminhoCompleto);

            String[] comandos = scriptSQL.split(";");

            for (String comando : comandos) {
                if (!comando.trim().isEmpty()) {
                    statement.execute(comando);
                }
            }

            System.out.println("Script executado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String lerScriptSQL(String path) {
        StringBuilder script = new StringBuilder();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                script.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return script.toString();
    }

}
