package org.example.ibmskillsbuildapp.repo;

import org.example.ibmskillsbuildapp.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,String> {
    User findByUserName(String userName);

}
