import "../styles/authForm.css"

export default function Login() {
    return (
        <>
            <div className="form-container">
                <h1>Register</h1>
                <form action="/register" method="POST">
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text" id="username" name="username" required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" name="password" required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="hashPassword">Password Confirm</label>
                        <input type="password" id="hashPassword" name="hashPassword" required />
                    </div>
                    <button type="submit" className="login-button">Login</button>
                </form>
            </div>
        </>
    )
}