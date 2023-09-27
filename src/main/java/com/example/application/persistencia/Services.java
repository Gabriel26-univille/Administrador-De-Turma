package com.example.application.persistencia;

import com.example.application.data.entity.Alunos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Services extends GenericDAO {

    public void createTable(){
        String sql = """
                
                """;
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            p.execute();
        }catch (SQLException e){
            System.out.println("Erro ao criar tabela");
            e.printStackTrace();
        }
    }

    public void inserirAluno(Alunos aluno){
        String sql = """
                insert into aluno(nome) values(?)
                """;
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            p.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro ao inserir aluno "+aluno);
            e.printStackTrace();
        }
    }

}
