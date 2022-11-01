package io.proj3ct.Jaumen.repositories;

import io.proj3ct.Jaumen.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
