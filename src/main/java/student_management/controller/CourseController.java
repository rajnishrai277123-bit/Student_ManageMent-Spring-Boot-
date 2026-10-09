package student_management.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import student_management.dto.CourseRequest;
import student_management.dto.CourseUpdateRequest;
import student_management.entity.Course;
import student_management.entity.Student;
import student_management.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // CREATE COURSE
    @PostMapping
    public ResponseEntity<Course> createCourse(
            @Valid @RequestBody CourseRequest courseRequest) {

        Course savedCourse = courseService.saveCourse(courseRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCourse);
    }

    // GET ALL COURSES
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    // GET COURSE BY ID
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // UPDATE COURSE
    @PutMapping("/{id}")
    public Course updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseUpdateRequest courseUpdateRequest) {

        return courseService.updateCourse(id, courseUpdateRequest);
    }

    // DELETE COURSE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }

    // GET STUDENTS OF A COURSE
    @GetMapping("/{id}/students")
    public List<Student> getStudentsByCourseId(@PathVariable Long id) {
        return courseService.getStudentsByCourseId(id);
    }
}