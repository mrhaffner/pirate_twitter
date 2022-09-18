import axios from 'axios';

const baseUri = 'http://127.0.0.1:8080/api';

// needs return type
export async function signup(username: string, password: string) {
  try {
    const response = await axios.post(`${baseUri}/auth/signup`, {
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
    const response = await axios.post(`${baseUri}/auth/login`, {
      username,
      password,
    });
    return response.data;
  } catch (error) {
    return [];
  }
}
