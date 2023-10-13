package com.gdsc_teama.servertoyproject.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table (name = "TBL_POST")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @NotNull
    private User user;

    @NotNull
    private String postTitle;

    @NotNull
    private String postContent;

    @CreationTimestamp
    private LocalDateTime postRegisterDate;

    @UpdateTimestamp
    private LocalDateTime postUpdateDate;


    @Builder
    public Post(User user, String postTitle, String postContent) {
        this.user = user;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }
}
