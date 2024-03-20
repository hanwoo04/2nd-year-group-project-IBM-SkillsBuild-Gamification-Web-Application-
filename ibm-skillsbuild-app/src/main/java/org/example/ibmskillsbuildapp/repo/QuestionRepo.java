package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

}
