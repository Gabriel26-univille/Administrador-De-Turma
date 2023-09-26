package com.example.application.views;

import com.example.application.data.entity.Alunos;
import com.example.application.data.service.AlunosService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Editar Alunos")
@Route(value = "editar-alunos", layout = MainLayout.class)
@Uses(Icon.class)
public class EditarAlunosView extends Composite<VerticalLayout> {

    public EditarAlunosView() {

        //----------------------Componentes-----------------------

        HorizontalLayout addAluno = new HorizontalLayout();
        HorizontalLayout ediAluno = new HorizontalLayout();
        HorizontalLayout excAluno = new HorizontalLayout();

        H1 h1 = new H1("Alunos");
        TabSheet tabSheet = new TabSheet();
        Button voltar = new Button(new Icon(VaadinIcon.ARROW_BACKWARD));

        NumberField matriculaEditar = new NumberField();
        NumberField matriculaExcluir = new NumberField();
        TextField novoAlunoNome = new TextField();
        TextField editarAlunoNome = new TextField();

        Button adicionarAluno = new Button("Adicionar");
        Button editarAluno = new Button("Editar");
        Button excluirAluno = new Button("Excluir");

        //----------------------Componentes Grade-----------------------

        Grid<Alunos> alunos = new Grid(Alunos.class, false);
        setGridSampleData(alunos);

        alunos.addColumn(Alunos::getMatricula)
                .setHeader("Matricula")
                .setFlexGrow(0)
                .setWidth("100px");
        alunos.addColumn(Alunos::getNome).setHeader("Nome");

        //----------------------Alinhamentos-----------------------

        addAluno.setWidthFull();
        addAluno.setAlignItems(FlexComponent.Alignment.START);
        addAluno.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        ediAluno.setWidthFull();
        ediAluno.setAlignItems(FlexComponent.Alignment.START);
        ediAluno.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        excAluno.setWidthFull();
        excAluno.setAlignItems(FlexComponent.Alignment.START);
        excAluno.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        getContent().setHeightFull();
        getContent().setWidthFull();
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h1);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, tabSheet);

        //----------------------Botoes-----------------------

        novoAlunoNome.setPlaceholder("Nome");
        editarAlunoNome.setPlaceholder("Novo nome");
        matriculaEditar.setPlaceholder("Matricula");
        matriculaExcluir.setPlaceholder("Matricula");

        voltar.addClickListener(e ->
                voltar.getUI().ifPresent(ui ->
                        ui.navigate("tela-principal"))
        );

        /*
        adicionarAluno.addClickListener();
        editarAluno.addClickListener();
        excluirAluno.addClickListener();
        */

        //----------------------Abas-----------------------

        tabSheet.add("Adicionar aluno",
                new Div(
                        addAluno
                ));
        tabSheet.add("Editar aluno",
                new Div(
                        ediAluno
                ));
        tabSheet.add("Excluir aluno",
                new Div(
                        excAluno
                ));

        //-----------------------Adds-----------------------

        addAluno.add(novoAlunoNome, adicionarAluno);
        ediAluno.add(matriculaEditar, editarAlunoNome, editarAluno);
        excAluno.add(matriculaExcluir, excluirAluno);

        getContent().add(voltar);
        getContent().add(h1);
        getContent().add(alunos);
        getContent().add(tabSheet);
    }

    //-----------------------grade-----------------------

    private void setGridSampleData(Grid grid) {
        grid.setItems(query -> samplePersonService.list(
                        PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
    }

    @Autowired()
    private AlunosService samplePersonService;

    record SampleItem(String value, String label, Boolean disabled) {
    }
}