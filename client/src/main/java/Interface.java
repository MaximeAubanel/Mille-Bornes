interface msgToSendListener {
	void sendMsg(String msg);
}

interface msgReceivedListener {
	void messageReceived(String msg);
	void changeState(String newState);
}