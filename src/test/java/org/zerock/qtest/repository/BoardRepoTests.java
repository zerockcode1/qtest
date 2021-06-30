package org.zerock.qtest.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.qtest.board.entity.*;
import org.zerock.qtest.board.repository.BoardImageRepository;
import org.zerock.qtest.board.repository.BoardRepository;
import org.zerock.qtest.board.repository.LikeHistoryRepository;
import org.zerock.qtest.board.repository.ReplyRepository;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepoTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private LikeHistoryRepository likeHistoryRepository;

    @Autowired
    private BoardImageRepository imageRepository;

    @Test
    public void testInsertBoard() {

        IntStream.rangeClosed(1,100).forEach(i -> {
            Board board = Board.builder().title("title..."+i).content("content..." + i).writer("user"+ (i%10)).build();

            boardRepository.save(board);

        });

    }

    @Test
    public void testInserImages() {

        IntStream.rangeClosed(0,299).forEach(i -> {

            long bno = (i / 3) +1;

            Board board = Board.builder().bno(bno).build();

            String fileName = "img"+i+".jpg";

            BoardImage boardImage = BoardImage.builder().board(board).fileName(fileName).main(i%3 == 0).build();

            imageRepository.save(boardImage);


        });

    }

    @Test
    public void testInsertReply() {

        IntStream.rangeClosed(1,300).forEach(i -> {

            long bno = (int)(Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder().replyText("reply........"+i).board(board).build();

            replyRepository.save(reply);

        });
    }

    @Test
    public void testInsertLikes() {

        IntStream.rangeClosed(1,400).forEach(i -> {

            long bno = (int)(Math.random() * 100) + 1;

            Board board = Board.builder().bno(bno).build();

            LikeHistory likeHistory = LikeHistory.builder().board(board).value(LikeValues.GOOD).visitor("user"+(i%100)).build();

            likeHistoryRepository.save(likeHistory);

        });
    }
    @Test
    public void testList1() {

        Pageable pageable = PageRequest.of(0,12);

        Page<Object[]> result = boardRepository.getBoardList(pageable);

        result.getContent().forEach(arr -> {

            log.info(Arrays.toString((Object[])arr));

        });
    }

    @Test
    public void testList2() {

        Pageable pageable = PageRequest.of(0,200);

        Page<Object[]> result = boardRepository.getBoardList2(pageable);

        result.getContent().forEach(arr -> {

            log.info(Arrays.toString((Object[])arr));

        });
    }

    @Test
    public void testList3() {

        Pageable pageable = PageRequest.of(0,20, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.getBoardListAll(pageable);

        result.getContent().forEach(arr -> {

            log.info(Arrays.toString((Object[])arr));

        });
    }

    @Test
    public void testDynamic() {

        String type ="best";
        Pageable pageable = PageRequest.of(0,20, Sort.by("bno").descending());


        Page<Object[]> result = boardRepository.getListWithType(type, pageable);

        result.getContent().forEach(arr -> {

            log.info(Arrays.toString((Object[])arr));

        });
    }

}
