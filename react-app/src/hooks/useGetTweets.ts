import { useEffect, useState } from 'react';
import TweetData from '../types/TweetData';
import tweetData from '../tweet_data.json';
import { useLocation } from 'react-router-dom';

const useGetTweets = () => {
  const location = useLocation();
  const [tweets, setTweets] = useState<TweetData[]>([]);

  useEffect(() => {
    if (location.pathname === '/explore') {
      setTweets(tweetData);
    } else {
      setTweets([]);
    }
  }, [location]);

  return tweets;
};

export default useGetTweets;
