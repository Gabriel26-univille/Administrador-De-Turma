package com.example.application.data.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Id;

@Entity
public class Boletim {

    @Id
    private int Matricula;
    private String Nome;
    private int Faltas;
    private int N1;
    private int N2;
    private int N3;
    private int N4;
    private boolean Aprovacao;

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

    public int getN1() {
        return N1;
    }

    public void setN1(int nota1) {
        N1 = nota1;
    }

    public int getN2() {
        return N2;
    }

    public void setN2(int nota2) {
        N2 = nota2;
    }

    public int getN3() {
        return N3;
    }

    public void setN3(int nota3) {
        N3 = nota3;
    }

    public int getN4() {
        return N4;
    }

    public void setN4(int nota4) {
        N4 = nota4;
    }

    public int getFaltas() {
        return Faltas;
    }

    public void setFaltas(int falta) {
        Faltas = falta;
    }

    public boolean isAprovacao() {
        return Aprovacao;
    }

    public void setAprovacao(boolean aprovacao) {
        Aprovacao = aprovacao;
    }

    @Override
    public String toString() {
        return "Boletim{" +
                "Matricula=" + Matricula +
                ", Nome='" + Nome + '\'' +
                ", Faltas=" + Faltas +
                ", N1=" + N1 +
                ", N2=" + N2 +
                ", N3=" + N3 +
                ", N4=" + N4 +
                ", Aprovacao=" + Aprovacao +
                '}';
    }
}
