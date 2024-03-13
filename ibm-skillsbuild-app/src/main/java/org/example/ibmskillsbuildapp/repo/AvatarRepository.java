package com.example.avatarcreation.repo;

import com.example.avatarcreation.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    // Add custom query methods if needed
}

