package com.calendar.application.data.generator;

import com.vaadin.flow.spring.annotation.SpringComponent;

import com.calendar.application.data.service.SamplePersonRepository;
import com.calendar.application.data.entity.SamplePerson;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.vaadin.artur.exampledata.DataType;
import org.vaadin.artur.exampledata.ExampleDataGenerator;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(SamplePersonRepository samplePersonRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (samplePersonRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 100 Sample Person entities...");
            ExampleDataGenerator<SamplePerson> samplePersonRepositoryGenerator = new ExampleDataGenerator<>(
                    SamplePerson.class, LocalDateTime.of(2021, 3, 3, 0, 0, 0));
            samplePersonRepositoryGenerator.setData(SamplePerson::setId, DataType.ID);
            samplePersonRepositoryGenerator.setData(SamplePerson::setNameEvent, DataType.FIRST_NAME);
            samplePersonRepositoryGenerator.setData(SamplePerson::setDescriptionEvent, DataType.LAST_NAME);
            samplePersonRepositoryGenerator.setData(SamplePerson::setImportEvent, DataType.LAST_NAME);
            samplePersonRepositoryGenerator.setData(SamplePerson::setTypeEvent, DataType.FIRST_NAME);
            samplePersonRepositoryGenerator.setData(SamplePerson::setDateEvent, DataType.DATE_OF_BIRTH);
            samplePersonRepository.saveAll(samplePersonRepositoryGenerator.create(0, seed));
            logger.info("Generated demo data");
        };
    }

}