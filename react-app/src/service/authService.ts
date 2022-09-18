import axios from 'axios';

const baseUri = 'http://127.0.0.1:8080/api/auth';

// needs return type
export async function signup(username: string, password: string) {
  try {
    const response = await axios.post(`${baseUri}/signup`, {
      username,
      password,
    });
    return response.data;
  } catch (error) {
    return [];
  }
}

// needs return type
export async function login(username: string, password: string) {
  try {
    const response = await axios.post(`${baseUri}/login`, {
      username,
      password,
    });
    return response.data;
  } catch (error) {
    return [];
  }
}
