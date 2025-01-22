import axios from 'axios';

export const checkUserId = async (userId, setMessage, setColor) => {
  if (!userId.trim()) {
    setMessage('아이디를 입력해주세요.');
    setColor('red');
    return;
  }

  try {
    const response = await axios.post('http://localhost:8080/checkRest/userIdCheck', { userId });
    const result = response.data;
    
    if (result === 1) {
      console.log('사용중인 아이디입니다.' + response.data);
      setMessage('사용중인 아이디입니다.');
      setColor('red');
    } else {
      console.log('사용가능한 아이디입니다.' + response.data);
      setMessage('사용가능한 아이디입니다.');
      setColor('blue');
    }
  } catch (error) {
    console.error('아이디 중복 체크 실패:', error);
    setMessage('서버 오류. 다시 시도해주세요.');
    setColor('red');
  }
};

export const checkUserEmail = async (userEmail, setMessage, setColor) => {
  if (!userEmail.trim()) {
    setMessage('이메일을 입력해주세요.');
    setColor('red');
    return;
  }

  try {
    const response = await axios.post('http://localhost:8080/checkRest/userEmailCheck', { userEmail });
    const result = response.data;

    if (result === 1) {
      setMessage('사용중인 이메일입니다.');
      setColor('red');
    } else {
      setMessage('사용가능한 이메일입니다.');
      setColor('blue');
    }
  } catch (error) {
    console.error('이메일 중복 체크 실패:', error);
    setMessage('서버 오류. 다시 시도해주세요.');
    setColor('red');
  }
};