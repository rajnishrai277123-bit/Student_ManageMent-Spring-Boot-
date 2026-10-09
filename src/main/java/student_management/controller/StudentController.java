package student_management.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import student_management.dto.StudentRequest;
import student_management.entity.Student;
import student_management.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentService.getStudentsWithPagination(pageable);
    }

    @GetMapping("{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequest studentRequest) {

        return studentService.updateStudent(id, studentRequest);
    }

    @DeleteMapping("{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "student deleted sucessfully";
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return studentService.searchStudentsByName(name);
    }

    @GetMapping("/older-than")
    public List<Student> getStudentsOlderThan(@RequestParam int age) {
        return studentService.getStudentsOlderThan(age);
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody StudentRequest studentRequest) {

        return studentService.saveStudent(studentRequest);
    }
}