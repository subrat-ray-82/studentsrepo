package com.dummy.studentsrepo;

import com.dummy.studentsrepo.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentsRepository extends CrudRepository<Student, Integer>{}
