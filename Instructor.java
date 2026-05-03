package edu.isgb.school.entities;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


//  Q5
@Entity
@Table(name = "t_instructor")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Instructor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_Instructor")
    private Integer idInstructor;

    @Column(name = "name_Instructor")
    private String name;

    // Q6 - CAS 1
    //@ManyToMany(mappedBy = "instructors", fetch = FetchType.LAZY)
    //private List<Course> courses = new ArrayList<>();

    // Q6 - CAS 2

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "t_instructor_t_course",
            joinColumns = @JoinColumn(name = "instructors_pk_Instructor"),
            inverseJoinColumns = @JoinColumn(name = "courses_pk_course")
    )
    @JsonIgnoreProperties({"instructors", "hibernateLazyInitializer", "handler"})
    private List<Course> courses = new ArrayList<>();
    // Q7  Instructor <--> School (bidirectionnel)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_PK_school")
    @JsonIgnoreProperties({"instructors", "students", "departments", "hibernateLazyInitializer", "handler"})
    private School school;

    // Constructeurs
    public Instructor() {}
    public Instructor(String name)
    { this.name = name; }

    // Getters and Setters
    public Integer getIdInstructor()
    { return idInstructor; }
    public void setIdInstructor(Integer idInstructor)
    { this.idInstructor = idInstructor; }
    public String getName()
    { return name; }
    public void setName(String name)
    { this.name = name; }
    public School getSchool()
    { return school; }
    public void setSchool(School school)
    { this.school = school; }
    public List<Course> getCourses()
    { return courses; }
    public void setCourses(List<Course> courses)
    { this.courses = courses; }
}