package ru.hazov.javahospitalsoap.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;



@NamedEntityGraphs({
        @NamedEntityGraph(
                name = "withMedicaments",
                attributeNodes = {
                        @NamedAttributeNode(value = "medicaments")
                }
        )
}
)
@NoArgsConstructor
@Data
@Entity
public class PatientDisease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private Disease disease;

    @Description("Дата обращения в больницу с болезнью")
    @Column(nullable = false)
    private Date startDate;

    @Description(value = "Дата окончания болезни")
    @Column()
    private Date endDate;

    @Description(value = "Болезнь закончилась летальным исходом")
    @Column
    private boolean fatalOutcome;

    @Column(nullable = false, columnDefinition = "boolean default false")

    private boolean sentToEsb = false;

    @Description("Какими лекарствами лечили болезнь")
    @ManyToMany
    private List<Medicament> medicaments;
}
