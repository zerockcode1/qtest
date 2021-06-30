package org.zerock.qtest.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String replyText;

    @ToString.Exclude
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
