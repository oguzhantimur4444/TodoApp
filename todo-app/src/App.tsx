import {BrowserRouter,Routes,Route} from "react-router-dom"
import Home from "./Home";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Home />} />
        <Route path="/register" element={<Home />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
