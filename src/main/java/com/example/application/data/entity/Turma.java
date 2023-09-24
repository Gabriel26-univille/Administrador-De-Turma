package com.example.application.data.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Turma {

    @Id
    private int Matricula;
    private String Nome;
    private int Nota1;
    private int Nota2;
    private int Nota3;
    private int Nota4;

    public int getMatricula() {
        return Matricula;
    }

    public void setMatricula(int matricula) {
        Matricula = matricula;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getNota1() {
        return Nota1;
    }

    public void setNota1(int nota1) {
        Nota1 = nota1;
    }

    public int getNota2() {
        return Nota2;
    }

    public void setNota2(int nota2) {
        Nota2 = nota2;
    }

    public int getNota3() {
        return Nota3;
    }

    public void setNota3(int nota3) {
        Nota3 = nota3;
    }

    public int getNota4() {
        return Nota4;
    }

    public void setNota4(int nota4) {
        Nota4 = nota4;
    }
}
