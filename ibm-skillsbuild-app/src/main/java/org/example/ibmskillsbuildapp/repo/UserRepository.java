package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);

    List<User> findByIdInOrderByScoreDesc(List<Long> friends); //Friends Only Leaderboard
    List<User> findAllByOrderByScoreDesc();//Global Leaderboard

}
