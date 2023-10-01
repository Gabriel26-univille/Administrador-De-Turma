package com.example.application;

import com.example.application.data.entity.Alunos;
import com.example.application.data.entity.Boletim;
import com.example.application.persistencia.Services;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlDataSourceScriptDatabaseInitializer;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationProperties;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "myapp")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) {
        //TESTES DE FUNCIONALIDADE//

        Services Services = new Services();
        Alunos alunos = new Alunos();
        Boletim boletim = new Boletim();
        alunos.setNome("Gabriel");
        boletim.setN1(9);
        boletim.setN2(8);
        boletim.setN3(10);
        boletim.setN4(9);
        boletim.setFaltas(20);
        Services.inserirAluno(alunos);
        Services.inserirNota(boletim,1);

        var opt = Services.obterPelaMatricula(1);
        if(opt.isPresent()){
            Alunos a = opt.get();
            System.out.println(a.getNome());
        }

        //FIM DOS TESTES//



        SpringApplication.run(Application.class, args);
    }
/*
    @Bean
    SqlDataSourceScriptDatabaseInitializer dataSourceScriptDatabaseInitializer (DataSource dataSource, SqlInitializationProperties properties, TurmaRepository repository) {
        // This bean ensures the database is only initialized when empty
        return new SqlDataSourceScriptDatabaseInitializer(dataSource, properties) {
            @Override
            public boolean initializeDatabase() {
                if (repository.count() == 0L) {
                    return super.initializeDatabase();
                }
                return false;
            }
        };
    }

 */
}