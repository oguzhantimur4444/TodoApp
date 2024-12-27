import axios from 'axios';

const API_URL = "http://localhost:8080/api/auth/";

export const register = async (username: string, password:string) => {
  return await axios.post(`${API_URL}register`, { username:username, password:password });
};

export const login = async (username:string, password:string) => {
  const response = await axios.post(`${API_URL}login`, { username:username, password:password });
  console.log(response)
  if (response.status.toString()=="200") {
    localStorage.setItem("user", JSON.stringify(response.data));
  }
  return response.data;
};
export const logout = () => {
  localStorage.removeItem("user");
};