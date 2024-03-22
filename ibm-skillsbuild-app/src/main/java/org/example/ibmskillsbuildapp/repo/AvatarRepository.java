package org.example.ibmskillsbuildapp.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.example.ibmskillsbuildapp.model.Avatar;

@Repository
public interface AvatarRepository extends CrudRepository<Avatar, Long> {
    // Add custom query methods if needed
}
