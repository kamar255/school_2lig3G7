package edu.isgb.school.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "t_department")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Departement implements Serializable {
    //  Q1 :
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_department")
    private Integer idDepartement;

    @Column(name = "cl_name")
    private String name;

    // Q2 — CAS 2 ET CAS 3 BIDIRECTIONNEL
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_PK_school")
    @JsonIgnoreProperties({"departments", "students", "instructors", "hibernateLazyInitializer", "handler"})
    private School school;

    // Constructeurs
    public Departement() {}
    public Departement(String name)
        { this.name = name; }

    // Getters and Setters
    public Integer getIdDepartement()
        { return idDepartement; }
    public void setIdDepartement(Integer idDepartement)
        { this.idDepartement = idDepartement; }
    public String getName()
        { return name; }
    public void setName(String name)
        { this.name = name; }
    public School getSchool()
        { return school; }
    public void setSchool(School school)
        { this.school = school; }
}