package com.piritter.api.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tweet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date creationTime;

    // max length?
    @NotNull
    private String content;

    @ManyToOne
    private User user;

    // json ignore?
    @ElementCollection
    private Set<Long> likedByUserId = new HashSet<>();

    private int retweetCount = 0;
    
    public Tweet(String content, User user) {
        this.content = content;
        this.user = user;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Tweet) {
            Tweet otherTweet = (Tweet) object;
            return id == otherTweet.getId();
        }
        return false;
    }
}
