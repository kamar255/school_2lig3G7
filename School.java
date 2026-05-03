package edu.isgb.school.entities;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
//  Q1
@Entity
@Table(name = "t_school")
public class School implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_school")
    private Integer idSchool;

    @Column(name = "cl_name_school")
    private String name;

    private Integer phone;
    // Q2 — CAS 1 : Unidirectionnel School → Departement
    //@OneToMany(cascade = CascadeType.ALL)
    //private List<Departement> departments = new ArrayList<>();
    // Q2 — CAS 2 : Bidirectionnel
    //@OneToMany(cascade = CascadeType.ALL)
    //@JsonIgnoreProperties({"school", "hibernateLazyInitializer", "handler"})
    //private List<Departement> departments = new ArrayList<>();
    // Q2 — CAS 3 : Bidirectionnel
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"school", "hibernateLazyInitializer", "handler"})
    private List<Departement> departments = new ArrayList<>();


    // Q7

    // Mapping bidirectionnel School <--> Student
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"school", "hibernateLazyInitializer", "handler"})
    private List<Student> students = new ArrayList<>();

    // Mapping bidirectionnel School <--> Instructor
    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"school", "hibernateLazyInitializer", "handler"})
    private List<Instructor> instructors = new ArrayList<>();

    // Constructeurs
    public School() {}
    public School(String name, Integer phone) {
        this.name = name;
        this.phone = phone;
    }

    // Getters and Setters
    public Integer getIdSchool()
    { return idSchool; }
    public void setIdSchool(Integer idSchool)
    { this.idSchool = idSchool; }
    public String getName()
    { return name; }
    public void setName(String name)
        { this.name = name; }
    public Integer getPhone()
        { return phone; }
    public void setPhone(Integer phone)
        { this.phone = phone; }
    public List<Departement> getDepartments()
        { return departments; }
    public void setDepartments(List<Departement>departments)
        { this.departments = departments; }
    public List<Student> getStudents()
        { return students; }
    public void setStudents(List<Student> students)
        { this.students = students; }
    public List<Instructor> getInstructors()
        { return instructors; }
    public void setInstructors(List<Instructor> instructors)
        { this.instructors = instructors; }
}