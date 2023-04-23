package com.example.les10opdracht.model;

import java.time.LocalDate;

public class Person {
    //variabelen
    //voor het voorbeeld public, zodat je geen getters en setters hoeft te maken, normaal gesproken maak je de variabelen private en gebruik je wel getters en setters
    //je gaat tussen de client en de server deze objecten heen en weer sturen (de variabelen zijn de objecten)
    public String name;
    public LocalDate dob;
    public char gender;
}
