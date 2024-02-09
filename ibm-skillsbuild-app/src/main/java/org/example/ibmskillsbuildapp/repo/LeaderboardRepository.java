package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.model.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaderboardRepository extends CrudRepository<Player, Integer> {
     List<Player> findAllByOrderByScoreDesc();
     List<Player> findByIdInOrderByScoreDesc(List<Integer> ids);

}
