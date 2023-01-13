import { Container, Divider, FormControl, Grid, IconButton, List, ListItem, ListItemText, Paper, TextField, Typography } from "@mui/material";
import { Box } from "@mui/system";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPaperPlane } from "@fortawesome/free-solid-svg-icons";
import { Fragment, useEffect, useRef, useState } from "react";
import { v4 as uuidv4 } from 'uuid';
import "./chat.css";
const ENDPOINT = 'http://localhost:8080/ws';

function Chat({ userObject }) {

  const [stompClient, setStompClient] = useState();

  const ENTER_KEY_CODE = 13;

  const scrollBottomRef = useRef(null);
  const [chatMessages, setChatMessages] = useState([]);
  const [user, setUser] = useState("");
  const [message, setMessage] = useState("");

  useEffect(() => {
      setUser(userObject);
      const webSocket = SockJS(ENDPOINT);
      const stompClient = Stomp.over(webSocket);
      stompClient.connect({}, () => {
        stompClient.subscribe('/topic/publicmessages',
          (data) => {
            onMessageReceived(data);
            if (scrollBottomRef.current) {
              scrollBottomRef.current
                .scrollIntoView({ behavior: 'smooth' });
            }
            console.log(`Stomp Message- ${data}`);
            console.log('i fire once');
          });
      });
      setStompClient(stompClient);
  }, [userObject]);

  const onMessageReceived = (data) => {
    const messageData = JSON.parse(data.body);
    setChatMessages(messagesReceived =>
      [...messagesReceived, messageData]);

  };
  console.log(message);

  const handleEnterKey = (event) => {
    if (event.keyCode === ENTER_KEY_CODE) {
    }
  }



  const sendMessage = () => {
    const payload = {
      'id': uuidv4(),
      'from': user.firstName,
      'to': message.to,
      'text': message
    };
    if (payload.to) {
      stompClient.send(`/user/${payload.to}/queue/inboxmessages`,
        {}, JSON.stringify(payload));
    } else {
      stompClient.send('/topic/publicmessages',
        {}, JSON.stringify(payload));
    }
    console.log(`Message sent ${payload.text}`);
    console.log(`Chat messages ${chatMessages}`);
  };



  const listChatMessages = chatMessages.map((chatMessage, index) =>
    <ListItem key={index}>
      <ListItemText primary={`${chatMessage.from}: ${chatMessage.text}`} />
    </ListItem>
  );

  return (
    <div className="chat-wrapper">
      <Fragment>
        <Container>
          <Paper elevation={5}>
            <Box p={3}>
              <Typography variant="h4" gutterBottom >
                General Chat Davines
              </Typography>
              <Divider />
              <Grid container spacing={4} alignItems="center">
                <Grid id="chat-window" xs={12} item>
                  <List id="chat-window-messages">
                    {listChatMessages}
                    <ListItem ref={scrollBottomRef}></ListItem>
                  </List>
                </Grid>
                <Grid xs={2} item>
                  <FormControl fullWidth>
                    <TextField
                      value={user.firstName}
                      label="Nickname"
                      variant="outlined"
                      disabled
                    />
                  </FormControl>
                </Grid>
                <Grid xs={9} item>
                  <FormControl fullWidth>
                    <TextField onChange={(e) => setMessage(e.target.value)}
                      onKeyDown={handleEnterKey}
                      value={message}
                      label="Type your message..."
                      variant="outlined" />
                  </FormControl>
                </Grid>
                <Grid xs={1} item>
                  <IconButton onClick={sendMessage}
                    aria-label="send"
                    color="primary">
                    <FontAwesomeIcon color="black" icon={faPaperPlane} />
                  </IconButton>
                </Grid>
              </Grid>
            </Box>
          </Paper>
        </Container>
      </Fragment>
    </div>
  );
}

export default Chat;