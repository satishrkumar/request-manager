package net.pay.you.back.request.manager.dao;

import net.pay.you.back.request.manager.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends MongoRepository<User, Long> {
    @Query("{ 'emailId' : ?0 }")
    Optional<User> findLoanByLenderEmailId(String emailId);
}
