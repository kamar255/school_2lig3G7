package edu.isgb.school.test;

import edu.isgb.school.entities.*;
import edu.isgb.school.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestSchoolController {

    @Autowired
    private SchoolService schoolService;

    // a. Créer une nouvelle School
    @PostMapping("/school")
    public ResponseEntity<School>
    createSchool(@RequestBody School school) {
        return ResponseEntity.ok(schoolService.saveSchool(school));
    }

    // b. Retourner une School par son id
    @GetMapping("/school/{id}")
    public ResponseEntity<School>
    getSchoolById(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.findSchoolById(id));
    }

    // c. Créer un Student dans une School
    @PostMapping("/student/{schoolId}")
    public ResponseEntity<Student>
    createStudent(@RequestBody Student student,
                                                 @PathVariable Integer schoolId) {
        return ResponseEntity.ok(schoolService.saveStudent(student, schoolId));
    }

    // d. Lister tous les Students
    @GetMapping("/students")
    public ResponseEntity<List<Student>>
    getAllStudents() {
        return ResponseEntity.ok(schoolService.findAllStudents());
    }

    // e. Créer un Instructor dans une School
    @PostMapping("/instructor/{schoolId}")
    public ResponseEntity<Instructor>
    createInstructor(@RequestBody Instructor instructor,
                                                       @PathVariable Integer schoolId) {
        return ResponseEntity.ok(schoolService.saveInstructor(instructor, schoolId));
    }

    // f. Lister les Instructors par nom

    @GetMapping("/instructor/search")
    public ResponseEntity<List<Instructor>>
    getInstructorsByName(@RequestParam String name) {
        return ResponseEntity.ok(schoolService.findInstructorsByName(name));
    }

    // g. Retourner un Instructor par id
    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor>
    getInstructorById(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.findInstructorById(id));
    }

    // h. Retourner un Course par id
    @GetMapping("/course/{id}")
    public ResponseEntity<Course>
    getCourseById(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.findCourseById(id));
    }

    // i. Lister les Courses d'un Instructor
    @GetMapping("/instructor/{id}/courses")
    public ResponseEntity<List<Course>>
    getInstructorCourses(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.findCoursesByInstructorId(id));
    }

    // j. Ajouter un Course à un Instructor existant
    @PostMapping("/instructor/{id}/course")
    public ResponseEntity<Instructor>
    addCourseToInstructor(@PathVariable Integer id,
                                                            @RequestBody Course course) {
        return ResponseEntity.ok(schoolService.addCourseToInstructor(id, course));
    }
}