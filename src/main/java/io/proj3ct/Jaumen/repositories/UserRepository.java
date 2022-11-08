package io.proj3ct.Jaumen.repositories;

import io.proj3ct.Jaumen.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
}
