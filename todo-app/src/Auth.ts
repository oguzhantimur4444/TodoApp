import axios from 'axios';

const API_URL = "http://localhost:8080/api/";

export const register = async (username: string, password:string) => {
  const response = await axios.post(`${API_URL}auth/register`, { username:username, password:password });
  return response.data
};

export const login = async (username:string, password:string) => {
  localStorage.removeItem("user");
  const response = await axios.post(`${API_URL}auth/login`, { username:username, password:password });
  
  if (response.status.toString()=="200") {
    localStorage.setItem("user", JSON.stringify(response.data));
  }
  
  return response.data;
};

export const getAllTodos = async () => {
  const user = JSON.parse(localStorage.getItem("user") || "{}");
  const response = await axios.get(`${API_URL}todos/get-all`, { headers: { Authorization: `Bearer ${user.token}` } });
  return response.data;
}

export const addTodo = async (description:string) => {
  console.log(description);
  const user = JSON.parse(localStorage.getItem("user") || "{}");
  const response = await axios.post(`${API_URL}todos/add`, { description: description }, { headers: { Authorization: `Bearer ${user.token}` } });
  return response.status.toString()=="200";
}

export const isTokenExpired = (token: string): boolean => {
  try {
    const payload = JSON.parse(atob(token.split('.')[1]));
    const currentTime = Math.floor(Date.now() / 1000);
    
    return payload.exp < currentTime;
  } catch (error) {
    console.error(`Invalid token ${token}`);
    return true;
  }
};

export const logout = () => {
  localStorage.removeItem("user");
};