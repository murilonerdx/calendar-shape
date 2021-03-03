package com.calendar.application.views.listardatas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.calendar.application.data.entity.SamplePerson;
import com.calendar.application.data.service.SamplePersonService;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.calendar.application.views.main.MainView;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.Component;

@JsModule("./views/listardatas/listardatas-view.ts")
@CssImport("./views/listardatas/listardatas-view.css")
@Tag("listardatas-view")
@Route(value = "listar-data", layout = MainView.class)
@PageTitle("Listar datas")
public class ListardatasView extends LitTemplate {

    // This is the Java companion file of a design
    // You can find the design file in
    // /frontend/views/views/listardatas/listardatas-view.js
    // The design can be easily edited by using Vaadin Designer
    // (vaadin.com/designer)

    @Id
    private Grid<SamplePerson> grid;

    @Id
    private TextField nameEvent;
    @Id
    private TextField descriptionEvent;
    @Id
    private DatePicker dateEvent;
    @Id
    private ComboBox<String> importEvent;
    @Id
    private TextField typeEvent;
    @Id
    private Button cancel;
    @Id
    private Button save;
    @Id("delete")
    private Button delete;

    private BeanValidationBinder<SamplePerson> binder;

    private SamplePerson samplePerson;




    public ListardatasView(@Autowired SamplePersonService samplePersonService) {


        importEvent.setItems("Familia", "Namoro", "Evento", "Aniversario");
        grid.addColumn(SamplePerson::getNameEvent).setHeader("Nome do evento").setAutoWidth(true);
        grid.addColumn(SamplePerson::getDescriptionEvent).setHeader("Descrição do evento").setAutoWidth(true);
        grid.addColumn(SamplePerson::getTypeEvent).setHeader("Tipo do evento").setAutoWidth(true);
        grid.addColumn(SamplePerson::getImportEvent).setHeader("Categoria do evento").setAutoWidth(true);
        grid.addColumn(SamplePerson::getDateEvent).setHeader("Data do evento").setAutoWidth(true);
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)))
                .stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<SamplePerson> samplePersonFromBackend = samplePersonService.get(event.getValue().getId());
                // when a row is selected but the data is no longer available, refresh grid
                if (samplePersonFromBackend.isPresent()) {
                    populateForm(samplePersonFromBackend.get());
                } else {
                    refreshGrid();
                }
            } else {
                clearForm();
            }
        });

        // Configure Form
        binder = new BeanValidationBinder<>(SamplePerson.class);

        // Bind fields. This where you'd define e.g. validation rules

        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        save.addClickListener(e -> {
            try {
                if (this.samplePerson == null) {
                    this.samplePerson = new SamplePerson();
                }
                binder.writeBean(this.samplePerson);

                samplePersonService.update(this.samplePerson);
                clearForm();
                refreshGrid();
                Notification.show("Evento salvo");
            } catch (ValidationException validationException) {
                Notification.show("Erro ao tentar salvar evento");
            }
        });

        delete.addClickListener(e -> {
            try {
                if (this.samplePerson == null) {
                    this.samplePerson = new SamplePerson();
                }
                binder.writeBean(this.samplePerson);
                samplePersonService.delete(this.samplePerson.getId());
                clearForm();
                refreshGrid();
                Notification.show("Deletando evento");
            } catch (ValidationException validationException) {
                Notification.show("Erro ao tentar deletar evento");
            }
        });
    }


    private void refreshGrid() {
        grid.select(null);
        grid.getLazyDataView().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(SamplePerson value) {
        this.samplePerson = value;
        binder.readBean(this.samplePerson);

    }
}
