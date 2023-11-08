package application;

import application.module.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import application.repository.StudentRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add at least 3 students to the database
		Student student1 = new Student(null, "Nguyen Thi D", 20, "d@gmail.com", 10.0);
		Student student2 = new Student(null, "Nguyen Van A", 22, "a@gmail.com", 8.0);
		Student student3 = new Student(null, "Nguyen Van B", 18, "b@mail.com", 6.5);

		student1 = studentRepository.save(student1);
		student2 = studentRepository.save(student2);
		student3 = studentRepository.save(student3);
		Iterable<Student> students = studentRepository.findAll();
		System.out.println("Student List:");
		students.forEach(System.out::println);

		Student studentToUpdate = studentRepository.findById(student1.getId()).orElse(null);
		if (studentToUpdate != null) {
			studentToUpdate.setName("Le Van C");
			studentToUpdate.setIeltsScore(8.5);
			studentToUpdate = studentRepository.save(studentToUpdate);
			System.out.println("Updated Student: " + studentToUpdate);
		}

		studentRepository.deleteById(student2.getId());
		// list student after update
		Iterable<Student> s = studentRepository.findAll();
		System.out.println("List after deletion:");
		s.forEach(System.out::println);

	}
}
