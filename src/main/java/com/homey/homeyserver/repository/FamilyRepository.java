package com.homey.homeyserver.repository;


import com.homey.homeyserver.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FamilyRepository extends JpaRepository<Family, Long> {
    public Optional<Family> findByHashCode(String hashCode);
}
