package com.dummy.studentsrepo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

	@Autowired
	private StudentsRepository studentsRepository;

	/**
	 * Get all students list.
	 *
	 * @return the list
	 */
	@GetMapping("/students")
	public List<Student> getAllUsers() {
		return (List<Student>) studentsRepository.findAll();
	}

	/**
	 * Gets student by id.
	 *
	 * @param userId the user id
	 * @return the users by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/student/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Integer studentId)
			throws ResourceNotFoundException {

		Student student = studentsRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found on :: " + studentId));
		return ResponseEntity.ok().body(student);
	}

	/**
	 * Create Student student.
	 *
	 * @param student the student
	 * @return the student
	 */
	@PostMapping("/students")
	public Student createUser(@RequestBody Student student) {
		return studentsRepository.save(student);
	}

	/**
	 * Update Student response entity.
	 *
	 * @param studentId      the id
	 * @param studentDetails the student details
	 * @return the response entity
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateUser(@PathVariable(value = "id") Integer studentId,
			@RequestBody Student studentDetails) throws ResourceNotFoundException {
		Student student = studentsRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found on :: " + studentId));
		student.setFname(studentDetails.getFname());
		student.setLname(studentDetails.getLname());
		final Student updatedStudent = studentsRepository.save(student);
		return ResponseEntity.ok(updatedStudent);
	}

	/**
	 * Delete Student map.
	 *
	 * @param userId the user id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/student/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Integer studentId) throws Exception {
		Student student = studentsRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found on :: " + studentId));
		studentsRepository.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
