package com.back.entity;

import com.back.constant.BoardType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity {
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardtype;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @JoinColumn(name = "memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @Column(nullable = true)
    private String img;
}
