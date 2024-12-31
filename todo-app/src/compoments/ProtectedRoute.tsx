import { Navigate } from "react-router-dom";
import { isTokenExpired, logout } from "../Auth";

type ProtectedRouteProps = {
  children: React.ReactNode; // children için doğru tipi belirtin
};

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
  const user = localStorage.getItem("user");

  if (!user) {
    return <Navigate to="/login" replace />;
  }
  const token = JSON.parse(user);

  if (isTokenExpired(token)) {
    logout();
    alert('Oturumunuzun süresi doldu. Lütfen tekrar giriş yapın.');
    return <Navigate to="/login" replace />;
  }
  return <>{children}</>;
};

export default ProtectedRoute;
