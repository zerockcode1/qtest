package org.zerock.qtest.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.qtest.board.entity.BoardImage;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
}
