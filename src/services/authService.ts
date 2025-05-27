import axios from 'axios';
import { LoginCredentials, User } from '../types/auth';

const API_URL = 'http://localhost:8081/api/auth/';

const login = (username: string, password: string) => {
  return axios.post(API_URL + 'signin', {
    username,
    password
  }).then(response => {
    if (response.data.token) {
      localStorage.setItem('token', response.data.token);
      localStorage.setItem('user', JSON.stringify(response.data));
    }
    return response.data;
  });
};

const logout = () => {
  localStorage.removeItem('user');
  localStorage.removeItem('token');
  // Clear any other auth-related items
  localStorage.clear();
  // Redirect to login page
  window.location.href = '/login';
};

const getCurrentUser = () => {
  const userStr = localStorage.getItem('user');
  if (userStr) {
    return JSON.parse(userStr);
  }
  return null;
};

const getToken = () => {
  return localStorage.getItem('token');
};

const isAuthenticated = () => {
  return !!getToken();
};

const authService = {
  login,
  logout,
  getCurrentUser,
  getToken,
  isAuthenticated
};

export default authService; 