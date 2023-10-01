package com.example.application.persistencia;

import com.example.application.data.entity.Alunos;
import com.example.application.data.entity.Boletim;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
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

    public void delAluno(int matricula){
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
                update aluno set nome = ? where matricula = ?
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

    public List<Alunos> obterTodosAlunos(){
        String sql = """
                select matricula, nome from aluno        
                """;
        List<Alunos> lista = new ArrayList<>();
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            ResultSet r = p.executeQuery();
            while(r.next()){
                Alunos a = new Alunos();
                a.setMatricula(r.getInt("matricula"));
                a.setNome(r.getString("nome"));
                lista.add(a);
            }
        }catch (SQLException e){
            System.out.println("Erro ao obter todos os alunos ");
            e.printStackTrace();
        }
        return lista;
    }

    private void removeNota(int matricula){
        String sql = """
                delete from boletim where matricula = ?
                """;
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setInt(1,matricula);
            p.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro ao remover registro ");
            e.printStackTrace();
        }
    }

    public void inserirNota(Boletim boletim, int matricula){
        String sql = """
                insert into boletim(nota1,nota2,nota3,nota4,matricula,aprovado,faltas) values(?,?,?,?,?,?,?)
                """;
        removeNota(matricula);
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setFloat(1,boletim.getN1());
            p.setFloat(2,boletim.getN2());
            p.setFloat(3,boletim.getN3());
            p.setFloat(4,boletim.getN4());
            p.setInt(5,matricula);
            p.setInt(7,boletim.getFaltas());
            boolean aprovado = false;
            float mean = (boletim.getN1() + boletim.getN2() + boletim.getN3() + boletim.getN4())/4;
            if (mean >= 7.0){aprovado=true;}
            p.setBoolean(6,aprovado);
            p.executeUpdate();
        }catch (SQLException e){
            System.out.println("Erro ao inserir notas ");
            e.printStackTrace();
        }
    }

    public Optional<Boletim> obterBoletim(Integer matricula){
        String sql = """
                select matricula, nota1, nota2, nota3, nota4, faltas, aprovado from aluno where matricula = ?           
                """;
        Boletim t = null;
        String nome = "";
        Optional<Alunos> a = obterPelaMatricula(matricula);
        if(a.isPresent()){
            Alunos b = a.get();
            nome = b.getNome();
        }
        try(Connection c = conn();
            PreparedStatement p = c.prepareStatement(sql)){
            p.setInt(1,matricula);
            ResultSet r = p.executeQuery();
            if(r.next()){
                t = new Boletim();
                t.setMatricula(r.getInt("matricula"));
                t.setN1(r.getInt("nota1"));
                t.setN2(r.getInt("nota2"));
                t.setN3(r.getInt("nota3"));
                t.setN4(r.getInt("nota4"));
                t.setFaltas(r.getInt("faltas"));
                t.setAprovacao(r.getBoolean("aprovado"));
            }
        }catch (SQLException e){
            System.out.println("Erro ao obter boletim do aluno com matricula "+matricula);
            e.printStackTrace();
        }
        return Optional.ofNullable(t);
    }
}
