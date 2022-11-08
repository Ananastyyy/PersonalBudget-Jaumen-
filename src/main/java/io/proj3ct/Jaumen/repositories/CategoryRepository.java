package io.proj3ct.Jaumen.repositories;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    public List<Category> findAllByUserAndNameCategory(User user, String nameCategory);
}
