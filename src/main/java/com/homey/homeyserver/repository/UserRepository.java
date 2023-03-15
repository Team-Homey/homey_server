package com.homey.homeyserver.repository;


import com.homey.homeyserver.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);

    public List<User> findAllByFamilyId(Long id);
    public Optional<User> findById(Long id);
}
