import axios from 'axios';

const baseUri = 'http://127.0.0.1:8080/api/tweet';

// tweet type? tweet dto?
// return type is tweet response?
export async function createTweet(tweet: any, token: string) {
  try {
    const response = await axios.post(`${baseUri}`, tweet, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return true; // unless it fails
  } catch (error) {
    return false;
  }
}

// tweet type? tweet dto?
// return type is tweet response?
export async function deleteTweet(tweetId: number, token: string) {
  try {
    const response = await axios.delete(`${baseUri}/${tweetId}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return true; // unless it fails // user a toast?
  } catch (error) {
    return false;
  }
}

// tweet type? tweet dto?
// return type is tweet response?
export async function likeTweet(tweetId: number, token: string) {
  try {
    const response = await axios.put(
      `${baseUri}/${tweetId}/like`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );
    return true; // unless it fails
  } catch (error) {
    return false;
  }
}

// tweet type? tweet dto?
// return type is tweet response?
export async function unlikeTweet(tweetId: number, token: string) {
  try {
    const response = await axios.put(
      `${baseUri}/${tweetId}/unlike`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );
    return true; // unless it fails
  } catch (error) {
    return false;
  }
}

// tweet type? tweet dto?
// return type is tweet response?
export async function reweet(tweetId: number, token: string) {
  try {
    const response = await axios.put(
      `${baseUri}/${tweetId}/retweet`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );
    return true; // unless it fails
  } catch (error) {
    return false;
  }
}
