package com.piritter.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.piritter.api.model.Tweet;
import com.piritter.api.repository.TweetRepository;
import com.piritter.api.service.TweetService;
import com.piritter.api.service.UserService;

@SpringBootApplication
public class ApiApplication {

	@Lazy
	@Autowired
	private UserService userService;

	@Lazy
	@Autowired
	private TweetService tweetService;

	@Lazy
	@Autowired
	private TweetRepository tweetRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

	@Bean
	public ApplicationRunner initializer() {
		return (args) -> {
			String[] names = {"steve", "bobard", "peglegpete", "blackbeard", "captainrick"};

			for (String name : names) {
				userService.registerUser(name, "123456");
			}

			tweetService.saveNewTweet("I am a real person with genuine feelings", "steve");			
			tweetService.saveNewTweet("I am stevesie the magnificent", "steve");
			tweetService.saveNewTweet("What should I name my my new boat?", "steve");			
			tweetService.saveNewTweet("My ship has too many sails", "steve");
			tweetService.saveNewTweet("How do I become pirate?", "steve");
			tweetService.saveNewTweet("What's the best boat for hauling a tremendous amount of loot?", "steve");

			tweetService.saveNewTweet("I am running out of ideas for filler tweets", "bobard");			
			tweetService.saveNewTweet("Is this a filler tweet? Think about it", "bobard");
			tweetService.saveNewTweet("Most people think that Bob is short for Robert. Rediculous, I know", "bobard");
			tweetService.saveNewTweet("I am not very nautically inclined", "bobard");			
			tweetService.saveNewTweet("Hi steve", "bobard");
			tweetService.saveNewTweet("Many people that thought making twitter only for priates had too small of a market", "bobard");
	
			tweetService.saveNewTweet("In spite of what you may think I actually do not have a peg leg", "peglegpete");
			tweetService.saveNewTweet("Captain Rick is a better captain than blackbeard. just saying", "peglegpete");			
			tweetService.saveNewTweet("Blackbeard's stash is in his basement behind the couch.  He's not a very good hider", "peglegpete");
			tweetService.saveNewTweet("I only come here to argue and waste time", "peglegpete");
			tweetService.saveNewTweet("Why do I spend so much time here, no one is following me", "peglegpete");			
			tweetService.saveNewTweet("I am thinking about buying a schooner for my wife", "peglegpete");

			tweetService.saveNewTweet("I am the world's greatest swimmer", "blackbeard");
			tweetService.saveNewTweet("This beard of mine is more tremendous than a delicious apple", "blackbeard");			
			tweetService.saveNewTweet("I am an enjoyer of money and taking long walks by the beach", "blackbeard");
			tweetService.saveNewTweet("Confession: I have never stepped foot on a ship", "blackbeard");
			tweetService.saveNewTweet("My interests include hoarding gold and sailing", "blackbeard");			
			tweetService.saveNewTweet("No one will find my hidden stash. I guarantee it", "blackbeard");
			
			List<Tweet> tweets = tweetRepository.findAll();

			for (Tweet tweet : tweets) {
				for (String name : names) {
					if (Math.random() < 0.65) {
						try {
							tweetService.likeTweet(String.valueOf(tweet.getId()), name);
						} catch (Error e) {}
					}
				}
			}

			// for (Tweet tweet : tweets) {
			// 	for (String name : names) {
			// 		if (Math.random() < 0.65) {
			// 			try {
			// 				tweetService.retweet(String.valueOf(tweet.getId()), name);
			// 			} catch (Error e) {}
			// 		}
			// 	}
			// }

			// following
		};
	}
}
