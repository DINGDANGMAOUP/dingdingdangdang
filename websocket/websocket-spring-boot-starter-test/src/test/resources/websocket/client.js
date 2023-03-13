console.log("开始创建");

// const protobuf = require("protobufjs");


protobuf.load("WebsocketMessages.proto")
    .then(function(root) {
       // Establish a WebSocket connection
const ws = new WebSocket("ws://localhost:6666/websocket");

// When sending a message from the client to the server, encode the message using Protobuf

ws.send(root);

// When receiving a message from the server, decode the message using Protobuf
ws.onmessage = (event) => {
  console.log(event)
  const decodedMessage = respponse.decode(new Uint8Array(event.data));
  const message = respponse.toObject(decodedMessage);
  console.log(message);
};
    });

