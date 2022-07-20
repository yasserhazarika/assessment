package com.mastercard.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mastercard.user.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, String> {

	public List<UserEntity> findAll();

}
