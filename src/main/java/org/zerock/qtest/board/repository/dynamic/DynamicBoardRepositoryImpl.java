package org.zerock.qtest.board.repository.dynamic;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.QuerydslUtils;
import org.zerock.qtest.board.entity.*;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class DynamicBoardRepositoryImpl extends QuerydslRepositorySupport implements DynamicBoardRepository{

    public DynamicBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Page<Object[]> getListWithType(String type, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QLikeHistory likeHistory = QLikeHistory.likeHistory;
        QBoardImage boardImage =QBoardImage.boardImage;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(reply).on(board.eq(reply.board));
        jpqlQuery.leftJoin(likeHistory).on(likeHistory.board.eq(board));
        jpqlQuery.leftJoin(boardImage).on(boardImage.board.eq(board));

        jpqlQuery.where(boardImage.main.eq(true));
        jpqlQuery.groupBy(board);

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, reply.countDistinct(), likeHistory.countDistinct(), boardImage);

        //page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        Sort sort = pageable.getSort();

        if(type.equals("recent")){
            tuple.orderBy(board.bno.desc());
        }else if(type.equals("best")){

            tuple.orderBy(likeHistory.countDistinct().desc());

        }

        List<Tuple> result = tuple.fetch();

        log.info(result);

        long count = tuple.fetchCount();

        log.info("COUNT: " +count);

        return new PageImpl<Object[]>(
                result.stream().map(t -> t.toArray()).collect(Collectors.toList()),
                pageable,
                count);
    }
}










