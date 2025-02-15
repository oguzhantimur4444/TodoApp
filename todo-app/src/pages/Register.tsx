import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/authForm.css"
import { register } from "../Auth";

export default function Register() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [hashPassword, setHasPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if(password!=hashPassword){
            setError("Şifreler Eşleşmiyor")
            return
        }
        try {
            await register(username, password);
            navigate('/login');
        } catch (err: any) {
            setError('Login failed. Please check your credentials.');
        }
    };
    return (
        <>
            <div className="form-container">
                <h1>Register</h1>
                {error && <p style={{ color: 'red' }}>{error}</p>}
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text" id="username" name="username" value={username} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" name="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="hashPassword">Password Confirm</label>
                        <input type="password" id="hashPassword" name="hashPassword" value={hashPassword} onChange={(e) => setHasPassword(e.target.value)} required />
                    </div>
                    <button type="submit" className="login-button">Login</button>
                </form>
            </div>
        </>
    )
}