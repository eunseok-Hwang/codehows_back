package com.back.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "commnet")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String comment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberId")
    private Member memberId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


}
