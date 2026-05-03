package edu.isgb.school.entities;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "t_course")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "instructors"})
public class Course implements Serializable {
    //Q5
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_course")
    private Integer idCourse;

    @Column(name = "name_course")
    private String name;


    // Q6 — CAS 1 : BIDIRECTIONNEL avec Course comme owner
    //@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    //@JoinTable(
    //        name = "t_course_t_instructor",
    //        joinColumns = @JoinColumn(name = "t_course_pk_course"),
    //        inverseJoinColumns = @JoinColumn(name = "instructors_pk_Instructor")
    //)
    //@JsonIgnoreProperties({"instructors", "hibernateLazyInitializer", "handler"})
    //private List<Instructor> instructors = new ArrayList<>();


    // Q6 — CAS 2  BIDIRECTIONNEL, Instructor est owner

    @ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
    private List<Instructor> instructors;

    // Constructeurs
    public Course() {}
    public Course(String name)
        { this.name = name; }

    // Getters and Setters
    public Integer getIdCourse()
        { return idCourse; }
    public void setIdCourse(Integer idCourse)
        { this.idCourse = idCourse; }
    public String getName()
        { return name; }
    public void setName(String name)
        { this.name = name; }
    public List<Instructor> getInstructors()
        { return instructors; }
    public void setInstructors(List<Instructor> instructors)
        { this.instructors = instructors; }
}