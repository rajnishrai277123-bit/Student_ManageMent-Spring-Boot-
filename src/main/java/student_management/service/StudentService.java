
package student_management.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student_management.dto.StudentRequest;
import student_management.entity.Course;
import student_management.entity.Student;
import student_management.exception.CourseNotFoundException;
import student_management.exception.DuplicateEmailException;
import student_management.exception.StudentNotFoundException;
import student_management.repository.CourseRepository;
import student_management.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(
            StudentRepository studentRepository,
            CourseRepository courseRepository) {

        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // Part A: Create student
    public Student saveStudent(StudentRequest studentRequest) {

        // Check whether email already exists
        if (studentRepository.existsByEmail(studentRequest.getEmail())) {
            throw new DuplicateEmailException("Email already exists");
        }

        // Find the course
        Course course = courseRepository.findById(studentRequest.getCourseId())
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: "
                                        + studentRequest.getCourseId()
                        )
                );

        // Create student object
        Student student = new Student();

        student.setName(studentRequest.getName());
        student.setEmail(studentRequest.getEmail());
        student.setAge(studentRequest.getAge());
        student.setCourse(course);

        return studentRepository.save(student);
    }

    // Get students with pagination
    public Page<Student> getStudentsWithPagination(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );
    }

    // Part B: Update student
    @Transactional
    public Student updateStudent(
            Long id,
            StudentRequest studentRequest) {

        // Find the student to update
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        // Check whether another student already uses this email
        if (studentRepository.existsByEmailAndIdNot(
                studentRequest.getEmail(), id)) {

            throw new DuplicateEmailException("Email already exists");
        }

        // Find the course
        Course course = courseRepository.findById(
                studentRequest.getCourseId()
        ).orElseThrow(() ->
                new CourseNotFoundException(
                        "Course not found with id: "
                                + studentRequest.getCourseId()
                )
        );

        // Update student details
        existingStudent.setName(studentRequest.getName());
        existingStudent.setEmail(studentRequest.getEmail());
        existingStudent.setAge(studentRequest.getAge());
        existingStudent.setCourse(course);

        return studentRepository.save(existingStudent);
    }

    // Delete student
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        studentRepository.delete(student);
    }

    // Search students by name
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByName(name);
    }

    // Find students older than the given age
    public List<Student> getStudentsOlderThan(int age) {
        return studentRepository.findStudentsOlderThan(age);
    }
}