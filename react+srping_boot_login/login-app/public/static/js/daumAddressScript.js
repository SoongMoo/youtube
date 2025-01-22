// /public/static/js/daumAddressScript.js
function execDaumPostcode() {
    new daum.Postcode({
       oncomplete: function(data) {
          // 도로명 주소 변수
          var roadAddr = data.roadAddress;
          var extraRoadAddr = ''; // 참고 항목 변수
 
          // 법정동명이 있을 경우 추가한다.
          if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
             extraRoadAddr += data.bname;
          }
 
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== '' && data.apartment === 'Y') {
             extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
          }
 
          // 참고항목이 있을 경우 괄호로 추가
          if (extraRoadAddr !== '') {
             extraRoadAddr = ' (' + extraRoadAddr + ')';
          }
 
          // 우편번호와 주소 정보를 해당 필드에 넣는다.
          //document.getElementById('sample4_postcode').value = data.zonecode;
          //document.getElementById("sample4_roadAddress").value = roadAddr + extraRoadAddr;

         const fullRoadAddr = roadAddr + extraRoadAddr;

         // React 상태 업데이트용 콜백 호출
         if (window.setAddress) {
            window.setAddress(data.zonecode, fullRoadAddr);
         }
       }
    }).open();
 }
