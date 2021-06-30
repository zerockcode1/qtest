package org.zerock.qtest.board.repository.dynamic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DynamicBoardRepository {

    Page<Object[]> getListWithType(String type, Pageable pageable);
}
