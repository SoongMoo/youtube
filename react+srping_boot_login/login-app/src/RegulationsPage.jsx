import { useRef } from "react";

const RegulationsPage = () => {
  // useRef를 사용하여 checkbox DOM에 직접 접근
  const checkboxRef = useRef(null);

  const handleLinkClick = (e) => {
    // checkboxRef.current.checked로 체크박스 상태 확인
    if (!checkboxRef.current.checked) {
      e.preventDefault(); // 체크 안 되어 있으면 이동 막기
      alert("규약에 동의하셔야 회원가입 페이지로 이동할 수 있습니다.");
    }
  };

  return (
    <div>
      <h1>회원가입 전 규약 동의</h1>
      <p>[필수] 글내용</p>
      <textarea rows="5" cols="80" readOnly>
        규약 내용이 여기에 표시됩니다.
      </textarea>
      <br />
      <label>
        <input
          type="checkbox"
          name="agree"
          ref={checkboxRef} // ref를 checkbox에 연결
        />
        규약에 동의합니다.
      </label>
      <br />
      <a href="/signup" onClick={handleLinkClick}>
        회원가입 이동
      </a>
    </div>
  );
};

export default RegulationsPage;