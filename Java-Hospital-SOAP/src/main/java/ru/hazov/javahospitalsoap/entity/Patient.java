package ru.hazov.javahospitalsoap.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Description("Пациент")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String middleName;

    @Column(nullable = false)
    private Date birthDate;

    @Description("Болезни пережившие в больнице")
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<PatientDisease> diseases;


}
