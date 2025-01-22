import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginForm from "./LoginForm";
import SignupForm from "./SignupForm";
import RegulationsPage from "./RegulationsPage";
import WelcomePage from './WelcomePage';

function App() {
  const [count, setCount] = useState(0)

  return (
    <Router>
        <Routes>
            <Route path="/" element={<LoginForm />} />
            <Route path="/signup" element={<SignupForm />} />
            <Route path="/regulationsPage" element={<RegulationsPage />} />
            <Route path="/welcome" element={<WelcomePage />} />  {/* Welcome page route */}
        </Routes>
    </Router>
  );
}

export default App
