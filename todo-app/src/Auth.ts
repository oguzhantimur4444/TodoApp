import axios from 'axios';

const API_URL = "http://localhost:8080/api/auth/";

export const register = async (username: string, password:string) => {
  const response = await axios.post(`${API_URL}register`, { username:username, password:password });
  if (response.status.toString()=="200") {
    login(username,password)
  }
  return response.data
};

export const login = async (username:string, password:string) => {
  const response = await axios.post(`${API_URL}login`, { username:username, password:password });
  if (response.status.toString()=="200") {
    localStorage.setItem("user", JSON.stringify(response.data));
  }
  return response.data;
};
export const CheckUsername = async (username: string) => {
  const response = await axios.post(`${API_URL}checkUsername`, { username:username });
  return response.data
}
export const isTokenExpired = (token: string): boolean => {
  try {
    const payload = JSON.parse(atob(token.split('.')[1])); // Token'ın payload kısmını çöz
    const currentTime = Math.floor(Date.now() / 1000); // Şu anki zaman (saniye cinsinden)
    return payload.exp < currentTime; // Token süresi geçmiş mi kontrol et
  } catch (error) {
    console.error(`Invalid token ${token}`);
    return true; // Token geçersizse, süresi dolmuş olarak kabul edilir
  }
};
export const logout = () => {
  localStorage.removeItem("user");
};