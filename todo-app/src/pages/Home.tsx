import { useNavigate } from "react-router-dom"
import { logout } from "../Auth"

export default function Home() {
  const navigate = useNavigate();


  const handleEvent = () => {
    logout()
    navigate('/login')
    
  }
  return (
    <div>
      <h1>Home</h1>
      <button onClick={handleEvent} type="submit">LogOut</button>
    </div>
  )
}
