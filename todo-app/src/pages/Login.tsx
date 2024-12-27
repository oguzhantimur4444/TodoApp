import "../styles/authForm.css"

export default function Login() {
    return (
        <>
            <div className="form-container">
                <h1>Login</h1>
                <form action="/login" method="POST">
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text" id="username" name="username" required />
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" id="password" name="password" required />
                    </div>
                    <button type="submit" className="login-button">Login</button>
                </form>
            </div>
            <p>Hala bir hesapbın yok mu oluşturmak için <a href="/register">tıkla</a></p>
        </>
    )
}