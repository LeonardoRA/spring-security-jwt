package com.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounts.entity.AccountEntity;
@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>{
	Optional<AccountEntity> findByName(String name);
}
