package com.piritter.api.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.piritter.api.model.Tweet;
import com.piritter.api.model.User;

@DataJpaTest
public class TweetRepositoryTests {
    @Mock
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void testTweetCreation() {
        User user = new User("steve", "123456");
        entityManager.persistAndFlush(user);
        Tweet tweet = new Tweet("this is a tweet", user);
        entityManager.persistAndFlush(tweet);
        assertThat(tweet.getId()).isNotNull();
    }

    @Test
    public void testFindByUser() {
        User user = new User("steve", "123456");
        entityManager.persistAndFlush(user);
        Tweet tweet = new Tweet("this is a tweet", user);
        entityManager.persistAndFlush(tweet);
        List<Tweet> foundTweets = tweetRepository.findByUser(user);
        assertEquals(foundTweets.size(), 1);
    }
}
