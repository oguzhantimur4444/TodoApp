import { Navigate } from "react-router-dom";
import { isTokenExpired, logout } from "../Auth";

type ProtectedRouteProps = {
  children: React.ReactNode;
};

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
  const user = localStorage.getItem("user");

  if (!user) {
    return <Navigate to="/login" replace />;
  }
  const token = JSON.parse(user);
  console.log(token);
  
  if (isTokenExpired(token.token)) {
    logout();
    alert('Oturumunuzun süresi doldu. Lütfen tekrar giriş yapın.');
    return <Navigate to="/login" replace />;
  }
  return <>{children}</>;
};

export default ProtectedRoute;
