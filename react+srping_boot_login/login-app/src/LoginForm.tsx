import React, { useState, useEffect  } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

const LoginForm = () => {
    const [userId, setUserId] = useState("");
    const [userPw, setUserPw] = useState("");
    const [isLoggedIn, setIsLoggedIn] = useState(false); // 로그인 상태 관리
    const [errorMessage, setErrorMessage] = useState("");

    // 로그인 상태 확인
    useEffect(() => {
        const token = sessionStorage.getItem("authToken");
        
        if (token) {
            setIsLoggedIn(true);
            const decoded  = jwtDecode(token);
           //setUserId(decoded.userId); // userId 클레임 가져오기
           const authData = JSON.parse(decoded.auth);
           console.log(authData.userId);
           setUserId(authData.userId);
        }
    }, []);


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("http://localhost:8080/api/login", {
                userId,
                userPw,
            });
            const token = response.data.token;
            sessionStorage.setItem("authToken", token);
            setIsLoggedIn(true); // 로그인 상태 변경
            setErrorMessage(""); // 에러 메시지 초기화
        } catch (error) {
            setErrorMessage("로그인 실패! 사용자 이름 또는 비밀번호를 확인하세요.");
        }
    };
    const handleLogout = async () => {
        try {
            const response = await axios.post("http://localhost:8080/api/logout");
            sessionStorage.removeItem("authToken");
            //console.log(response.data); // 서버 응답 확인 (선택 사항)
            setIsLoggedIn(false); // 로그아웃 처리
            setUserId("");
            setUserPw("");
        } catch (error) {
            console.error("로그아웃 중 오류 발생:", error);
        }
    };
    return (
        <div>
            {isLoggedIn ? (
                <><h2>환영합니다, {userId}!</h2>
                <button onClick={handleLogout}>로그아웃</button></>
            ) : (
                <form onSubmit={handleSubmit}>
                    <h2>로그인</h2>
                    <div>
                        <label>사용자 이름:</label>
                        <input
                            type="text"
                            value={userId}
                            onChange={(e) => setUserId(e.target.value)}
                        />
                    </div>
                    <div>
                        <label>비밀번호:</label>
                        <input
                            type="password"
                            value={userPw}
                            onChange={(e) => setUserPw(e.target.value)}
                        />
                    </div>
                    <button type="submit">로그인</button>
                    <p>
                        아직 계정이 없으신가요? <Link to="/regulationsPage">회원가입</Link>
                    </p>
                </form>
            )}
        </div>
    );
};

export default LoginForm;