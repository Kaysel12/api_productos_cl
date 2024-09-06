package com.api.productos.repositories;

import com.api.productos.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {

}
