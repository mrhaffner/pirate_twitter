import axios from 'axios';

const baseUri = 'http://127.0.0.1:8080/api/timeline';

// needs return type
export async function getMyTimeline(token: string) {
  try {
    const response = await axios.get(baseUri, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    return response.data;
  } catch (error) {
    return [];
  }
}

// needs return type
export async function getPirateTimeline(token: string) {
  try {
    let response;
    if (token) {
      response = await axios.get(`${baseUri}/all`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    } else {
      response = await axios.get(`${baseUri}/all`);
    }
    return response.data;
  } catch (error) {
    return [];
  }
}

// needs return type
export async function getTimeline(username: string, token: string) {
  try {
    let response;
    if (token) {
      response = await axios.get(`${baseUri}/user/${username}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    } else {
      response = await axios.get(`${baseUri}/user/${username}`);
    }
    return response.data;
  } catch (error) {
    return [];
  }
}
