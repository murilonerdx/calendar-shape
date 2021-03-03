package com.calendar.application.views.criardata;

import com.calendar.application.data.entity.SamplePerson;
import com.calendar.application.data.service.SamplePersonService;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.calendar.application.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * A Designer generated component for the person-form-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but does not overwrite
 * or otherwise change this file.
 */
@JsModule("./views/criardata/criardata-view.ts")
@CssImport("./views/criardata/criardata-view.css")
@Tag("criardata-view")
@Route(value = "criar-data", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Criar data")
public class CriardataView extends LitTemplate {

    @Id("nameEvent")
    private TextField nameEvent;
    @Id("descriptionEvent")
    private TextField descriptionEvent;
    @Id("typeEvent")
    private TextField typeEvent;
    @Id("dateEvent")
    private DatePicker dateEvent;
    @Id("importEvent")
    private ComboBox<String> importEvent;
//    @Id("pnNumber")
//    private TextField number;
//    @Id("phoneNumber")
//    private PhoneNumberField phone;

    @Id("save")
    private Button save;
    @Id("cancel")
    private Button cancel;


    private Binder<SamplePerson> binder = new Binder(SamplePerson.class);

    public CriardataView(SamplePersonService personService) {



        importEvent.setItems("Familia", "Namoro", "Evento", "Aniversario");
        importEvent.addCustomValueSetListener(e -> importEvent.setValue(e.getDetail()));


        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            if (nameEvent.getValue().equals("") | descriptionEvent.getValue().equals("") | typeEvent == null | dateEvent == null | importEvent == null) {
                Notification.show("Necessario todos detalhes do evento");
            }else{
//                System.out.println(dateEvent.getValue());
//                LocalDate newDateFormatted = LocalDate.parse(this.dateEvent.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//                dateEvent.setValue(newDateFormatted);
                personService.update(binder.getBean());
                Notification.show("Evento salvo com sucesso");
                clearForm();
            }

        });
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }


}
