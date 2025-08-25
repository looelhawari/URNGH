document.addEventListener("DOMContentLoaded", function () {
  const sendMessageButton = document.getElementById("sendMessageButton");
  const messageInput = document.getElementById("messageInput");
  const chatMessages = document.getElementById("chatMessages");

  // Assuming backendUrl is defined dynamically somewhere in your application
  const backendUrl = window.backendUrl || "http://localhost:8080"; // Default to localhost if not set

  // Function to fetch user ID and doctor's contact number from localStorage
  function getUserDataFromLocalStorage() {
    const userData = JSON.parse(localStorage.getItem("user"));

    if (!userData || !userData.id || !userData.doctorContactNumber) {
      console.error(
        "User ID or Doctor Contact Number not found in localStorage"
      );
      return null;
    }

    return {
      senderId: userData.id,
      doctorContactNumber: userData.doctorContactNumber,
    };
  }

  const userData = getUserDataFromLocalStorage();
  if (!userData) {
    return; // Stop if user data is not found
  }

  const senderId = userData.senderId;
  const receiverId = userData.doctorContactNumber; // Use the doctor contact number as receiverId

  // Function to send message to the chat
  function sendMessage() {
    const messageText = messageInput.value.trim();
    console.log(messageText)
    if (messageText === "") return; // Don't send empty messages

    // Display the user's message
    displayMessage(messageText, "user");
    messageInput.value = ""; // Clear input field
    chatMessages.scrollTop = chatMessages.scrollHeight; // Scroll to the bottom

    // Send the message to the backend (using Fetch API)
    fetch(`${backendUrl}/api/chat/send`, {
      // Backend API endpoint for sending chat
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        senderId: senderId,
        receiverId: receiverId,
        message: messageText,
      }), // Sending the user message with sender and receiver IDs
    })
      .then((response) => response.json())
      .then((data) => {
        // Display bot's response (you can modify this logic to handle AI response)
        displayMessage(data.message, "bot");
      })
      .catch((error) => {
        console.error("Error:", error);
        displayMessage(
          "Sorry, something went wrong. Please try again later.",
          "bot"
        );
      });
  }

  // Function to display messages in the chat
  function displayMessage(message, sender) {
    const messageElement = document.createElement("div");
    messageElement.classList.add("message");
    messageElement.classList.add(sender); // Adds either 'user' or 'bot' class
    messageElement.innerText = message;
    chatMessages.appendChild(messageElement);
  }

  // Event listener for the send button
  sendMessageButton.addEventListener("click", sendMessage);

  // Optional: Allow pressing 'Enter' to send the message
  messageInput.addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
      sendMessage();
    }
  });

  // Load chat history for the user (this can be optional based on your use case)
  function loadChatHistory() {
    fetch(`${backendUrl}/api/chat/user/${senderId}`)
      .then((response) => response.json())
      .then((data) => {
        data.forEach((chat) => {
          displayMessage(
            chat.message,
            chat.sender.id === senderId ? "user" : "bot"
          );
        });
        chatMessages.scrollTop = chatMessages.scrollHeight; // Scroll to the bottom
      })
      .catch((error) => console.error("Error loading chat history:", error));
  }

  // Optionally, load the chat history when the page loads
  loadChatHistory();
});
