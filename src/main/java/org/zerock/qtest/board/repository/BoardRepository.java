package org.zerock.qtest.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.qtest.board.entity.Board;
import org.zerock.qtest.board.repository.dynamic.DynamicBoardRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, DynamicBoardRepository {

    @Query("select b, count(distinct r.board), count(distinct h) from Board b left outer join Reply r on r.board = b left join LikeHistory h on h.board = b group by r.board order by 3 desc , 1 desc ")
    Page<Object[]> getBoardList(Pageable pageable);

    @Query("select h.board, count(distinct r), count( distinct h) from LikeHistory h left join Reply r on r.board = h.board group by r.board , h.board order by 3 desc")
    Page<Object[]> getBoardList2(Pageable pageable);

    @Query("select b, count(distinct r), count(distinct h), i from " +
            "Board b left join Reply r on r.board = b left join LikeHistory h on h.board = b left join BoardImage i on i.board = b " +
            "where i.main = true " +
            "group by r.board ")
    Page<Object[]> getBoardListAll(Pageable pageable);

}
