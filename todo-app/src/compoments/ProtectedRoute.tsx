import { Navigate } from "react-router-dom";

type ProtectedRouteProps = {
  children: React.ReactNode; // children için doğru tipi belirtin
};

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
  const user = localStorage.getItem("user");

  return user ? <>{children}</> : <Navigate to="/login" />;
};

export default ProtectedRoute;
