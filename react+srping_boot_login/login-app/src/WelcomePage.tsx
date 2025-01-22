import React from 'react';
import { Link } from 'react-router-dom';

function WelcomePage() {
  return (
    <div style={{ textAlign: 'center', padding: '50px' }}>
      <h1>회원가입이 완료되었습니다!</h1>
      <p>환영합니다, 회원가입이 성공적으로 완료되었습니다.</p>
      <p>이제 서비스를 이용하실 수 있습니다.</p>
      
      <Link to="/">홈으로 가기</Link>  {/* Link to the homepage */}
    </div>
  );
}

export default WelcomePage;