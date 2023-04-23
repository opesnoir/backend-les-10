package com.example.les10opdracht.controller;


import com.example.les10opdracht.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
// met request mapping kan je de root van alle paden aangeven, alles begint met persons
@RequestMapping("/persons")
public class PersonController {

    //variabelen
    private List<Person> persons = new ArrayList<>();


    // een person toevoegen, zodat er iemand in de body zit, door de constructor te maken en een person toe te voegen
    // constructor
    public PersonController (){
        Person p = new Person();
        p.name = "Piet";
        p.dob = LocalDate.of(1995, Month.AUGUST, 5);
        p.gender = 'm';
        //voeg Person p toe aan de List<Person> arraylist
        this.persons.add(p);
    }

    @GetMapping
    //Response entity is een hulpmiddel om vanuit je code (vanuit je endpoint) iets te retourneren naar de client. Je zowel een body terug geven als een Http-status.
    //HttpStatus.OK is gelijk aan code 200
    public ResponseEntity<List<Person>> getPersons(){
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    //om vanuit postman personen toe te voegen gebruiken we post mapping, ook hier hoef je geen pad toe te voegen omdat je dat in de @RequestMapping("/persons") hebt gemaakt
    @PostMapping
    //hier ga je een enkele person als body binnenkrijgen, maar je moet aangeven dat hij uit de request body komt
    // als je een resource aanmaakt heb je een speciale status daarvoor: CREATED:
    // dan ga je naar postman en daar kan je aan de clientside je JSON-informatie op
    public ResponseEntity<Person> createPerson (@RequestBody Person p){
        persons.add(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    //wijzigingen aanbrengen, aan je pad een id toevoegen, zodat de code weet welk element er moet worden aangepast
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person p, @PathVariable int id) {
        if ( id>=0 && id < persons.size()) {
            persons.set(id, p);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
