package br.com.stocka.stockaspring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.stocka.stockaspring.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<Long, UserModel> {

    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);
    
}
