package org.zerock.qtest.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgno;

    private String fileName;

    private boolean main;

    @ToString.Exclude
    @JoinColumn
    @ManyToOne
    private Board board;
}
