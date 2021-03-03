package com.calendar.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.calendar.application.data.AbstractEntity;
import com.sun.istack.NotNull;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class SamplePerson extends AbstractEntity {
    private String nameEvent;
    private String descriptionEvent;
    private LocalDate dateEvent;
    private String importEvent;
    private String typeEvent;
    @Transient
    private LocalDate dateFormated;
//    private LocalDate newDateFormatted = LocalDate.parse(this.dateEvent.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

//    dateEvent.setValue(LocalDate.parse(dateEvent.getValue().format(formatador)));


    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public String getImportEvent() {
        return importEvent;
    }

    public void setImportEvent(String importEvent) {
        this.importEvent = importEvent;
    }


}
