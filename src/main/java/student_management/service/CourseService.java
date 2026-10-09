package student_management.service;

import org.springframework.stereotype.Service;
import student_management.dto.CourseRequest;
import student_management.dto.CourseUpdateRequest;
import student_management.entity.Course;
import student_management.entity.Student;
import student_management.exception.CourseNotFoundException;
import student_management.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public Course saveCourse(CourseRequest courseRequest) {

        Course course = new Course();

        course.setName(courseRequest.getName());
        course.setDuration(courseRequest.getDuration());
        course.setFees(courseRequest.getFees());

        return courseRepository.save(course);
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {

        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: " + id
                        )
                );
    }

    public List<Student> getStudentsByCourseId(Long courseId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: " + courseId
                        )
                );

        return course.getStudents();
    }
    public void deleteCourse(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        courseRepository.delete(course);
    }
    public Course updateCourse(
            Long id,
            CourseUpdateRequest courseUpdateRequest) {

        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        existingCourse.setName(courseUpdateRequest.getName());
        existingCourse.setDuration(courseUpdateRequest.getDuration());
        existingCourse.setFees(courseUpdateRequest.getFees());

        return courseRepository.save(existingCourse);
    }
}