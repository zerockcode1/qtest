package org.zerock.qtest.board.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class LikeHistory extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeNum;

    private LikeValues value;

    private String visitor;

    @ToString.Exclude
    @JoinColumn
    @ManyToOne
    private Board board;

}
