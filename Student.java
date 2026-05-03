package edu.isgb.school.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;



@Entity
@Table(name = "t_student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student implements Serializable {
    //Q3
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Student")
    private Integer idStudent;

    @Column(name = "cl_name_student")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "cl_birthdate")
    private Date birthDate;


    // Q4 — CAS 2
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "address_PK_ADDRESS")
    //private Adresse adresse;


    // Q4 — CAS 3 :


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_PK_ADDRESS")
    private Adresse adresse;
    // Q7
    //Student <--> School (bidirectionnel)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_PK_school")
    @JsonIgnoreProperties({"students", "instructors", "departments", "hibernateLazyInitializer", "handler"})
    private School school;

    // Constructeurs
    public Student() {}
    public Student(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    // Getters and Setters
    public Integer getIdStudent() { return idStudent; }
    public void setIdStudent(Integer idStudent) { this.idStudent = idStudent; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    public Adresse getAdresse() { return adresse; }
    public void setAdresse(Adresse adresse) { this.adresse = adresse; }
    public School getSchool() { return school; }
    public void setSchool(School school) { this.school = school; }
}