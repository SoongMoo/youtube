import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { checkUserId, checkUserEmail } from '../public/static/js/check';


const SignupForm = () => {
  const navigate = useNavigate();  // Initialize useNavigate for navigation

  const handleCancel = () => {
    navigate('/');  // Redirects to the home page
  };

  const [formData, setFormData] = useState({
    memberId: '',
    memberPw: '',
    memberPwCon: '',
    memberName: '',
    memberAddr: '',
    memberAddrDetail: '',
    memberPost: '',
    memberPhone1: '',
    memberPhone2: '',
    gender: 'M',
    memberBirth: '',
    memberEmail: '',
  });

  const [idCheckMessage, setIdCheckMessage] = useState('');
  const [idCheckColor, setIdCheckColor] = useState('');
  const [emailCheckMessage, setEmailCheckMessage] = useState('');
  const [emailCheckColor, setEmailCheckColor] = useState('');

  

  // Daum 주소 검색 결과를 처리할 콜백을 정의
  useEffect(() => {
    window.setAddress = (zonecode, roadAddress) => {
      setFormData((prevData) => ({
        ...prevData,
        memberPost: zonecode,
        memberAddr: roadAddress,
      }));
    };
  }, []);
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 비밀번호 확인
    if (formData.memberPw !== formData.memberPwCon) {
      alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
      return;
    }

    // 아이디와 이메일 확인
    if (idCheckMessage !== '사용가능한 아이디입니다.' || emailCheckMessage !== '사용가능한 이메일입니다.') {
      alert("아이디 또는 이메일이 유효하지 않습니다.");
      return;
    }
    
    try {
      const response = await axios.post('http://localhost:8080/register/userWrite', formData);
      console.log('회원가입 성공:', response.data);
      if(response.data == "200") navigate('/welcome');
      else alert("회원가입 실패 / 관리자에게 물어보세요.")
    } catch (error) {
      console.error('회원가입 실패:', error);
    }
  };

  return (
    <div>
      <h2>회원가입</h2>
      <form onSubmit={handleSubmit}>
      <label>
          회원아이디:
          <input
            type="text"
            name="memberId"
            value={formData.memberId}
            onChange={handleChange}
            onKeyUp={() => checkUserId(formData.memberId, setIdCheckMessage, setIdCheckColor)}
            required
          />
        </label>
        <span style={{ color: idCheckColor }}>{idCheckMessage}</span><br />
  
        <label>
          회원비밀번호:
          <input
            type="password"
            name="memberPw"
            value={formData.memberPw}
            onChange={handleChange}
            required
          />
        </label><br />
  
        <label>
          회원비밀번호확인:
          <input
            type="password"
            name="memberPwCon"
            value={formData.memberPwCon}
            onChange={handleChange}
            required
          />
        </label><br />
  
        <label>
          회원명:
          <input
            type="text"
            name="memberName"
            value={formData.memberName}
            onChange={handleChange}
            required
          />
        </label><br />
  
        <label>
          회원주소:
          <input
            type="text"
            name="memberAddr"
            value={formData.memberAddr}
            required
            id="sample4_roadAddress"
            onChange={handleChange}
            readOnly
          />
        </label>
        <button type="button" onClick={execDaumPostcode}>
          우편번호검색
        </button><br />
  
        <label>
          상세주소:
          <input
            type="text"
            name="memberAddrDetail"
            value={formData.memberAddrDetail}
            onChange={handleChange}
          />
        </label><br />
  
        <label>
          우편번호:
          <input
            type="text"
            name="memberPost"
            value={formData.memberPost}
            required
            id="sample4_postcode"
            onChange={handleChange}
            readOnly
          />
        </label><br />
  
        <label>
          회원연락처1:
          <input
            type="tel"
            name="memberPhone1"
            value={formData.memberPhone1}
            onChange={handleChange}
            required
          />
        </label><br />
  
        <label>
          회원연락처2:
          <input
            type="tel"
            name="memberPhone2"
            value={formData.memberPhone2}
            onChange={handleChange}
            required
          />
        </label><br />
  
        <label>
          회원성별:
          <input
            type="radio"
            name="gender"
            value="M"
            checked={formData.gender === 'M'}
            onChange={handleChange}
          />
          남자
          <input
            type="radio"
            name="gender"
            value="F"
            checked={formData.gender === 'F'}
            onChange={handleChange}
          />
          여자
        </label><br />
  
        <label>
          회원생년월일:
          <input
            type="date"
            name="memberBirth"
            value={formData.memberBirth}
            onChange={handleChange}
            required
          />
        </label><br />
  
        <label>
          회원이메일:
          <input
            type="email"
            name="memberEmail"
            value={formData.memberEmail}
            onChange={handleChange}
            onKeyUp={() => checkUserEmail(formData.memberEmail, setEmailCheckMessage, setEmailCheckColor)}
            required
          /> 
        </label>
        <span style={{ color: emailCheckColor }}>{emailCheckMessage}</span><br />
  
        <button type="submit">회원가입완료</button>
        <button type="button" onClick={handleCancel}>회원가입취소</button>  {/* Cancel button */}
      </form>
    </div>
  );
};

export default SignupForm;