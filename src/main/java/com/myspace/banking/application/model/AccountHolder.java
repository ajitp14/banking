package com.myspace.banking.application.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@SequenceGenerator(name = "entity_sequence", initialValue = 1000000000, allocationSize = 1)
public class AccountHolder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_sequence")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "accountHolder", cascade = CascadeType.ALL)
    private List<AccountDetails> accounts;


}
