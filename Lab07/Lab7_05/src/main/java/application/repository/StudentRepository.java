package application.repository;
import application.module.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.age > :age")
    List<Student> findByAgeGreaterThanEqual(int age);

    @Query("SELECT COUNT(s) FROM Student s WHERE s.ieltsScore >= :score")
    long countByIeltsScore(@Param("score") double ieltsScore);

    @Query("SELECT s FROM Student s WHERE s.email LIKE %:name%")
    List<Student> findByNameContainingIgnoreCase(@Param("name") String name);

}
