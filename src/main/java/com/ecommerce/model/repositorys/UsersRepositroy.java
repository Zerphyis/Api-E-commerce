package com.ecommerce.model.repositorys;

import com.ecommerce.model.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepositroy extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByIdAndAtivoTrue(Long id);
    Optional<Users> findByEmailAndAtivoTrue(String email);
    @Query("SELECT u.email FROM Users u WHERE u.id = :id")
    Optional<String> findEmailById(Long id);
}
