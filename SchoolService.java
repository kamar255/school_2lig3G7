package edu.isgb.school.services;

import edu.isgb.school.entities.*;
import edu.isgb.school.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class SchoolService {

    @Autowired private SchoolRepository     schoolRepository;
    @Autowired private StudentRepository    studentRepository;
    @Autowired private InstructorRepository instructorRepository;
    @Autowired private CourseRepository     courseRepository;
    @Autowired private DepartementRepository departementRepository;
    // Q8
    // a. Créer une School avec ses Students, Instructors, Departments
    public School saveSchool(School school) {
        if (school.getDepartments() != null)
            school.getDepartments().forEach(d -> d.setSchool(school));
        if (school.getStudents() != null)
            school.getStudents().forEach(s -> s.setSchool(school));
        if (school.getInstructors() != null)
            school.getInstructors().forEach(i -> i.setSchool(school));
        return schoolRepository.save(school);
    }

    // b. Retourner une School par ID
    @Transactional(readOnly = true)
    public School findSchoolById(Integer id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("School non trouvée avec id: " + id));
    }

    // c. Créer un Student avec son Adresse et sa School
    public Student saveStudent(Student student, Integer schoolId) {
        School school = findSchoolById(schoolId);
        student.setSchool(school);
        return studentRepository.save(student);
    }

    // d. Lister tous les Students
    @Transactional(readOnly = true)
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    // e. Créer un Instructor avec schoolId
    public Instructor saveInstructor(Instructor instructor, Integer schoolId) {
        School school = findSchoolById(schoolId);
        instructor.setSchool(school);
        return instructorRepository.save(instructor);
    }

    // f. Lister les Instructors par nom
    @Transactional(readOnly = true)
    public List<Instructor> findInstructorsByName(String name) {
        return instructorRepository.findByName(name);
    }

    // g. Retourner un Instructor par ID
    @Transactional(readOnly = true)
    public Instructor findInstructorById(Integer id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor non trouvé avec id: " + id));
    }

    // h. Retourner un Course par ID
    @Transactional(readOnly = true)
    public Course findCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course non trouvé avec id: " + id));
    }

    // i. Lister les Courses d'un Instructor
    @Transactional
    public List<Course> findCoursesByInstructorId(Integer instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor non trouvé avec id: " + instructorId));
        instructor.getCourses().size();
        return instructor.getCourses();
    }

    // j. Ajouter un Course à un Instructor
    @Transactional
    public Instructor addCourseToInstructor(Integer instructorId, Course course) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor non trouvé avec id: " + instructorId));
        if (course.getIdCourse() != null) {

            Course existing = courseRepository.findById(course.getIdCourse())
                    .orElseThrow(() -> new RuntimeException("Course non trouvé avec id: " + course.getIdCourse()));
            instructor.getCourses().add(existing);
        } else {

            instructor.getCourses().add(course);
        }
        return instructorRepository.save(instructor);
    }
}