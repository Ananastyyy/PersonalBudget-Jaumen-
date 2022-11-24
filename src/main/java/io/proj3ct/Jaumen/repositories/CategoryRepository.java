package io.proj3ct.Jaumen.repositories;

import io.proj3ct.Jaumen.models.Category;
import io.proj3ct.Jaumen.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByUserAndNameCategory(User user, String nameCategory);
    List<Category> findAllByNameCategoryAndUserLogin(String nameCategory, String userLogin);
    List<Category> findAllByUserLogin(String userLogin);
}
