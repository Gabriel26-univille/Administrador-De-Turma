package com.example.application.persistencia;

import com.example.application.data.entity.Alunos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

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
            p.setString(1,aluno.getNome());
            p.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro ao inserir aluno "+aluno);
            e.printStackTrace();
        }
    }

    public void delAluno(Integer matricula){
        String sql = """
                delete from aluno where matricula = ?
                """;
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setInt(1,matricula);
            p.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro ao remover aluno "+matricula);
            e.printStackTrace();
        }
    }

    public void atualizaAluno(Alunos aluno){
        String sql = """
                update aluno set nome = ? where id = ?
                """;
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setString(1,aluno.getNome());
            p.setInt(2,aluno.getMatricula());
            p.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro ao atualizar aluno "+aluno.getNome());
            e.printStackTrace();
        }
    }

    public Optional<Alunos> obterPelaMatricula(Integer matricula){
        String sql = """
                select matricula, nome from aluno where matricula = ?           
                """;
        Alunos t = null;
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setLong(1,matricula);
            ResultSet r = p.executeQuery();
            if(r.next()){
                t = new Alunos();
                t.setMatricula(r.getInt("matricula"));
                t.setNome(r.getString("nome"));
            }
        }catch (SQLException e){
            System.out.println("Erro ao obter aluno com matricula "+matricula);
            e.printStackTrace();
        }
        return Optional.ofNullable(t);
    }




}
