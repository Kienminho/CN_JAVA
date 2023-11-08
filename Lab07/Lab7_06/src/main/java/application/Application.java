package application;

import application.module.Student;
import application.repository.StudentPagingAndSortingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import application.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentPagingAndSortingRepository studentRepositorys;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add at least 3 students to the database
		Student student1 = new Student(null, "Le Dinh H", 20, "h@gmail.com", 10.0);
		Student student2 = new Student(null, "Nguyen Van A", 22, "a@gmail.com", 8.0);
		Student student3 = new Student(null, "Nguyen Van B", 18, "b@mail.com", 6.5);
		Student student4 = new Student(null, "Nguyen Van E", 19, "e@mail.com", 6.5);
		Student student5 = new Student(null, "Nguyen Van R", 18, "r@mail.com", 7.5);
		Student student6 = new Student(null, "Nguyen Van T", 20, "t@mail.com", 6.5);
		Student student7 = new Student(null, "Nguyen Van Y", 21, "y@mail.com", 7.5);
		Student student8 = new Student(null, "Nguyen Van U", 22, "u@mail.com", 7.0);
		Student student9 = new Student(null, "Nguyen Van I", 18, "i@mail.com", 5.5);
		Student student10 = new Student(null, "Nguyen Van O", 20, "o@mail.com", 5.5);
		Student student11 = new Student(null, "Nguyen Van N", 20, "n@mail.com", 5.5);

		student1 = studentRepository.save(student1);
		student2 = studentRepository.save(student2);
		student3 = studentRepository.save(student3);
		student4 = studentRepository.save(student4);
		student5 = studentRepository.save(student5);
		student6 = studentRepository.save(student6);
		student7 = studentRepository.save(student7);
		student8 = studentRepository.save(student8);
		student9 = studentRepository.save(student9);
		student10 = studentRepository.save(student10);
		student11 = studentRepository.save(student11);

		Iterable<Student> students = studentRepository.findAll();
		System.out.println("Student List:");
		students.forEach(System.out::println);

		// Read the list of students, sorted by age in descending order and then by ielts score in ascending order
		Page<Student> sortedStudents = studentRepositorys.findAllByOrderByAgeDescIeltsScoreAsc(PageRequest.of(0, Integer.MAX_VALUE));
		System.out.println("Student list sorted by age in descending order and then by ielts score in ascending order:");
		sortedStudents.forEach(student -> System.out.println(student));

		// the list has more than 10 students
		if (sortedStudents.getTotalElements() > 10) {
			// read students 4-5-6 and print them to the console
			Page<Student> students456 = studentRepositorys.findAllByOrderByAgeDescIeltsScoreAsc(PageRequest.of(1, 3));
			students456.forEach(student -> System.out.println(student));
		}


	}
}
