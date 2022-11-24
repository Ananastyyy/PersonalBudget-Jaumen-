package io.proj3ct.Jaumen.repositories;

import io.proj3ct.Jaumen.models.Cheque;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChequeRepository extends CrudRepository<Cheque, Long> {
}
