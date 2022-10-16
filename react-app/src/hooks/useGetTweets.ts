import { useEffect, useState } from 'react';
import TweetData from '../types/TweetData';
import { useLocation, useParams } from 'react-router-dom';
import {
  getMyTimeline,
  getPirateTimeline,
  getTimeline,
} from '../service/timelineService';
import useUserContext from './useUserContext';

const useGetTweets = () => {
  const location = useLocation();
  const [tweets, setTweets] = useState<TweetData[]>([]);
  const { token } = useUserContext();
  const { username } = useParams();

  useEffect(() => {
    (async () => {
      if (location.pathname === '/') {
        const tweetData = await getMyTimeline(token);
        setTweets(tweetData);
      } else if (location.pathname === '/explore') {
        const tweetData = await getPirateTimeline(token);
        setTweets(tweetData);
      } else {
        if (username) {
          const tweetData = await getTimeline(username, token);
          setTweets(tweetData);
        }
      }
    })();
  }, [location, username]);

  return tweets;
};

export default useGetTweets;
