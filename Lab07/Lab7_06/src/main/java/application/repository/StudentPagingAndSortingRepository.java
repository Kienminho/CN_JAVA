package application.repository;

import application.module.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentPagingAndSortingRepository extends PagingAndSortingRepository<Student, Long> {

    Page<Student> findAllByOrderByAgeDescIeltsScoreAsc(Pageable pageable);
}