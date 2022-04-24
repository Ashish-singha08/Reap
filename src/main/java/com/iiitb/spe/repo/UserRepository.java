package com.iiitb.spe.repo;
import org.springframework.transaction.annotation.Transactional;
import com.iiitb.spe.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUsername(String username);

    public UserEntity findById(long id);

    @Transactional
    @Modifying
    @Query (value ="UPDATE User SET CoinBalance =?1 where Id=?2",nativeQuery = true)
    public void updateCoins(int coins ,long id);

    @Transactional
    @Modifying
    @Query (value ="SELECT Id ,FullName,RoleTypeId FROM User WHERE `Id` <> ?1",nativeQuery = true)
    public List<Object> findAllUsers(long id);

}
