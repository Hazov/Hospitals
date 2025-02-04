package ru.hazov.javahospitalsoap.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@NoArgsConstructor
@Data
@Description("Заказ медкамента")
public class EOrder {

    @Id
    private Long id;
    @ManyToOne
    private Medicament medicament;
    @Column
    private Date date;
    @Column
    private int quantity;


}
