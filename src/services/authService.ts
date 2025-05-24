import axios from 'axios';

const API_URL = 'http://localhost:8080/api/auth/'; // Replace with your backend API URL

const login = (username, password) => {
  return axios.post(API_URL + 'signin', {
    username,
    password
  });
};

const logout = () => {
  localStorage.removeItem('user');
};

const getCurrentUser = () => {
  const userStr = localStorage.getItem('user');
  if (userStr) {
    return JSON.parse(userStr);
  } else {
    return null;
  }
};

const authService = {
  login,
  logout,
  getCurrentUser,
};

export default authService;