package com.company.spring_thymeleaf_form.smartphone;

import com.company.spring_thymeleaf_form.firms.Firm;
import com.company.spring_thymeleaf_form.os.Os;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Smartphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "firm_id", referencedColumnName = "id")
    private Firm firm;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "os_id", referencedColumnName = "id")
    private Os os; //Operating system
    private double size; //Размер экрана
    private String color; //Цвет смартфона
}
