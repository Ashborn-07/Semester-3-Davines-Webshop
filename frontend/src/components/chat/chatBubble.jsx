import React from "react";
import Draggable from 'react-draggable';
import "./chatBubble.css";

function chatBubble() {
  return (
    <Draggable defaultPosition={{x: 0, y: 0}}>
      <div className="chat-bubble">
      </div>
    </Draggable>
  )
}

export default chatBubble;