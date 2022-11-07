package io.proj3ct.Jaumen.repositories;

import io.proj3ct.Jaumen.models.ChatHistory;
import org.springframework.data.repository.CrudRepository;

public interface ChatHistoryRepository extends CrudRepository<ChatHistory, Long> {
}
