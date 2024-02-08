package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.UserStreak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStreakRepository extends JpaRepository<UserStreak, Long> {
}
