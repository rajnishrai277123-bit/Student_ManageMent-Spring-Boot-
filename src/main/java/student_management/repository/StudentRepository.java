package student_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import student_management.entity.Student;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student,Long>{
    List<Student> findByName(String name);
}