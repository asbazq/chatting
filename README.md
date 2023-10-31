# chatting
 채팅방 입장 시
```
// 메시지 발송
{
  "type":"ENTER",
  "roomId":"9e648d2d-5e2e-42b3-82fc-b8bef8111cbe",
  "sender":"happydaddy",
  "message":""
}
// 메시지 수신 
{
   "type":"ENTER",
   "roomId":"9e648d2d-5e2e-42b3-82fc-b8bef8111cbe",
   "sender":"happydaddy",
   "message":"happydaddy님이 입장했습니다."
}
```
채팅 입력 시
```
// 메시지 발송
{
  "type":"TALK",
  "roomId":"9e648d2d-5e2e-42b3-82fc-b8bef8111cbe",
  "sender":"happydaddy",
  "message":"안녕하세요"
}
// 메시지 수신
{
   "type":"TALK",
   "roomId":"9e648d2d-5e2e-42b3-82fc-b8bef8111cbe",
   "sender":"happydaddy",
   "message":"안녕하세요"
}
```
