package com.example.login.domain.post.entity;

import com.example.login.domain.auth.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Builder
@AllArgsConstructor
@DynamicUpdate
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;

    private String content;

    private String weather;

    private String date;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User author;
    public void setAuthor(User author) {
        this.author = author;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
