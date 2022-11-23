package com.example.login.domain.auth.entity;

import com.example.login.domain.post.entity.Post;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity @Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String userName;

    private String userPassword;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;
    public void addPost(Post post) {
        post.setAuthor(this);
        this.posts.add(post);
    }

}
