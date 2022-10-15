import axios from 'axios';

const baseUri = 'http://127.0.0.1:8080/api/user';

// needs return type
export async function follow(username: string, token: string) {
  try {
    const response = await axios.put(
      `${baseUri}/${username}/follow`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );
    // return a boolean based on response code
    return true;
  } catch (error) {
    return false;
  }
}

// needs return type
export async function unfollow(username: string, token: string) {
  try {
    const response = await axios.put(
      `${baseUri}/${username}/unfollow`,
      {},
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },
    );
    // return a boolean based on response code
    return true;
  } catch (error) {
    return false;
  }
}

// needs return type
export async function searchUsernames(partialUsername: string) {
  try {
    const response = await axios.get(
      `${baseUri}/find-similar/${partialUsername}`,
    );
    return response.data;
  } catch (error) {
    return [];
  }
}

// needs return type
export async function getUserFromToken(token: string) {
  try {
    const response = await axios.get(`${baseUri}/token/`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  } catch (error) {
    return null;
  }
}
