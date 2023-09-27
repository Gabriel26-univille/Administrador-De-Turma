package com.example.application.views;

import com.example.application.data.entity.Boletim;
import com.example.application.data.service.TurmaService;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

@PageTitle("Tela Principal")
@Route(value = "tela-principal", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class TelaPrincipalView extends Composite<VerticalLayout> {

    public TelaPrincipalView() {

        //----------------------Componentes-----------------------

        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();

        H1 h1 = new H1("Turma");
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();

        //----------------------Componentes Grade-----------------------

        Grid<Boletim> turma = new Grid(Boletim.class, false);
        setGridSampleData(turma);

        turma.addColumn(Boletim::getMatricula).setHeader("Matricula");
        turma.addColumn(Boletim::getNome).setHeader("Nome");
        turma.addColumn(Boletim::getFaltas).setHeader("Faltas");
        turma.addColumn(Boletim::getN1).setHeader("Nota1");
        turma.addColumn(Boletim::getN2).setHeader("Nota2");
        turma.addColumn(Boletim::getN3).setHeader("Nota3");
        turma.addColumn(Boletim::getN4).setHeader("Nota4");
        turma.addColumn(Boletim::isAprovacao).setHeader("Aprovação");

        //----------------------Botoes-----------------------

        buttonPrimary.setText("Editar alunos");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary.addClickListener(e ->
                buttonPrimary.getUI().ifPresent(ui ->
                        ui.navigate("editar-alunos"))
        );

        buttonPrimary2.setText("Editar notas");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPrimary2.addClickListener(e ->
                buttonPrimary2.getUI().ifPresent(ui ->
                        ui.navigate("editar-notas"))
        );

        getContent().setHeightFull();
        getContent().setWidthFull();

        //----------------------Alinhamentos-----------------------

        layoutRow.setWidthFull();
        layoutRow.setAlignItems(Alignment.START);
        layoutRow.setJustifyContentMode(JustifyContentMode.CENTER);

        layoutRow2.setWidthFull();
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setAlignItems(Alignment.START);
        layoutRow2.setJustifyContentMode(JustifyContentMode.CENTER);

        layoutRow3.setWidthFull();
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setAlignItems(Alignment.START);
        layoutRow3.setJustifyContentMode(JustifyContentMode.CENTER);

        //-----------------------Adds-----------------------

        getContent().add(layoutRow);
        getContent().add(layoutRow2);
        getContent().add(turma);

        layoutRow.add(h1);

        layoutRow2.add(buttonPrimary);
        layoutRow2.add(buttonPrimary2);



    }

        //-----------------------grade-----------------------
        private void setGridSampleData(Grid grid) {
            grid.setItems(query -> samplePersonService.list(
                            PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                    .stream());
        }

        @Autowired()
        private TurmaService samplePersonService;

        record SampleItem(String value, String label, Boolean disabled) {
        }
}
