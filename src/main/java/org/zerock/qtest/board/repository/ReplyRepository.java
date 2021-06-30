package org.zerock.qtest.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.qtest.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
