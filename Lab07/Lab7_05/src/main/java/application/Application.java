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
		Student student1 = new Student(null, "Le Dinh H", 20, "h@gmail.com", 10.0);
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
			studentToUpdate.setName("Le Thi D");
			studentToUpdate.setIeltsScore(8.5);
			studentToUpdate = studentRepository.save(studentToUpdate);
			System.out.println("Updated Student: " + studentToUpdate);
		}

		// Delete a student from the database and print the result after deleting
		studentRepository.deleteById(student2.getId());
		System.out.println("Deleted Student with ID " + student2.getId());

		// Read a list of students whose age is greater than or equal to 21
		System.out.println("Students whose age is greater than or equal to 21:");
		Iterable<Student> studentsByAge = studentRepository.findByAgeGreaterThanEqual(21);
		studentsByAge.forEach(System.out::println);

		// Count the number of students whose ielts score is equal to 7.5
		Long countByIeltsScore = studentRepository.countByIeltsScore(7.5);
		System.out.println("Number of students whose IELTS score is 7.5: " + countByIeltsScore);

		System.out.println("Students whose name contains the word 'A':");
		Iterable<Student> studentsByNameContaining = studentRepository.findByNameContainingIgnoreCase("A");
		studentsByNameContaining.forEach(System.out::println);
	}
}
