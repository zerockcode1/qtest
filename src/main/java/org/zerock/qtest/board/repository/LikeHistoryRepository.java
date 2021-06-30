package org.zerock.qtest.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.qtest.board.entity.LikeHistory;

public interface LikeHistoryRepository extends JpaRepository<LikeHistory, Long> {
}
