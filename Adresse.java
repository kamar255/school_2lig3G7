package edu.isgb.school.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "t_address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Adresse implements Serializable {
    // Q3 :
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ADDRESS")
    private Integer idAdresse;

    @Column(name = "cl_street")
    private String street;

    @Column(name = "cl_city")
    private String city;

    @Column(name = "cl_postal_Code")
    private String postalCode;


    // Q4 — CAS 1 UNIDIRECTIONNEL
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "student_PK_Student")
    //private Student student;


    //
      //Q4 - CAS 2 : BIDIRECTIONNEL complet
     // @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    // @JsonIgnoreProperties("address")
    // private Student student;
     //
    // Q4 - CAS 3  : UNIDIRECTIONNEL (Student -> Address)

    // Constructeurs
    public Adresse() {}
    public Adresse(String street, String city, String postalCode) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
    }

    // Getters and Setters
    public Integer getIdAdresse()
     { return idAdresse; }
    public void setIdAdresse(Integer idAdresse)
     { this.idAdresse = idAdresse; }
    public String getStreet()
        { return street; }
    public void setStreet(String street)
        { this.street = street; }
    public String getCity()
        { return city; }
    public void setCity(String city)
        { this.city = city; }
    public String getPostalCode()
        { return postalCode; }
    public void setPostalCode(String postalCode)
        { this.postalCode = postalCode; }
   // public Student getStudent() { return student; }
   // public void setStudent(Student student) { this.student = student; }
}