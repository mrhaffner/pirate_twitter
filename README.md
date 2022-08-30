# Piritter

A pirate themed Twitter clone where all tweets are translated into pirate speak.

### Functional Requirements

- A User can be created, modified and deleted
- A User can login or logout
- A User must be authenticated to use Pirate Twitter
- A User may view, create or delete their own Tweets
- All Tweets will be automatically translated into pirate speak
- A User may view, like, or share other Users' Tweets
- A User may follow or unfollow other Users
- A User may discover other Users by searching by pirate handle (username)
- Every User has a User Timeline viewable to any authenticated User, consisting of a reverse chronological listing of their Tweets and the Tweets they have shared
- Every User has a Home Timeline viewable only to them, consisting of a reverse chronological listing of all Tweets and shared Tweets from the Users they are following
- There is a Pirate Timeline, consisting of the last N Tweets made on Pirate Twitter

### Project Implementation

- A Spring Boot API
- Handles Authentication and Authorization
- Handles basic CRUD operations related to Users and Tweets
- Translates new Tweets to pirate speak
- Generates Timelines
- A React web app
- Serves as the user interface

### Acknowledgements

Thanks to bezkoder - I used this great [article](https://www.bezkoder.com/spring-boot-jwt-authentication/) to set up authentication and authorization.
