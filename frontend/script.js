let typingTimeout; // Variable to store the timeout ID for typing effect
let lastUserMessage = ""; // Store the last user message to regenerate
let currentMessageId = null; // Track the current message's ID for regenerating

// Function to simulate the typing effect
function typeText(element, text, delay = 25) {
  let index = 0;

  function type() {
    if (index < text.length) {
      element.innerHTML += text.charAt(index);
      index++;
      typingTimeout = setTimeout(type, delay); // Store the timeout to clear it when paused
    }
  }

  type();
}

// Function to send the message in the chatbot and get the response from the backend
async function sendMessage() {
  const userMessage = document.getElementById("user-message").value;
  if (userMessage.trim()) {
    const chatBox = document.getElementById("chat-box");

    // Add the user message to the chat box without clearing previous messages
    chatBox.innerHTML += `<div class="user-msg">${userMessage}</div>`;

    lastUserMessage = userMessage; // Store the current user message for regeneration

    try {
      // Make a POST request to the backend API
      const response = await fetch(`${backendUrl}/response`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: userMessage, // Send user message to backend
      });

      const aiResponse = await response.text(); // Get the response text from the backend

      // Create a container for the AI's message and append it to the chatBox
      const aiMessageContainer = document.createElement("div");
      aiMessageContainer.classList.add("ai-msg");
      chatBox.appendChild(aiMessageContainer);

      // Use the typing effect to display the AI's response
      typeText(aiMessageContainer, aiResponse);

      // Store the current message's ID for regeneration
      currentMessageId = aiMessageContainer;
    } catch (error) {
      chatBox.innerHTML += `<div class="ai-msg">Error: Unable to get response</div>`;
    }

    // Scroll to the bottom of the chat box
    chatBox.scrollTop = chatBox.scrollHeight;

    // Clear the input field
    document.getElementById("user-message").value = "";
  } else {
    alert("Please enter a message.");
  }
}

// Function to pause the typing effect
function pauseAnswer() {
  clearTimeout(typingTimeout); // Stop the typing effect
}

// Function to regenerate the AI's answer by re-sending the last user message to the server
async function regenerateAnswer() {
  if (lastUserMessage.trim()) {
    const chatBox = document.getElementById("chat-box");

    try {
      // Make a POST request to the backend API with the last user message
      const response = await fetch(`${backendUrl}/response`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json", // Make sure the content type is JSON
        },
        body: lastUserMessage, // Send last user message to backend
      });

      const aiResponse = await response.text(); // Get the response text from the backend

      // If the current message container exists, reuse it, otherwise create a new one
      if (currentMessageId) {
        currentMessageId.innerHTML = ""; // Clear previous AI response
      } else {
        // Create a new container for the AI's regenerated message if it doesn't exist
        currentMessageId = document.createElement("div");
        currentMessageId.classList.add("ai-msg");
        chatBox.appendChild(currentMessageId);
      }

      // Use the typing effect to display the AI's response
      typeText(currentMessageId, aiResponse);
    } catch (error) {
      chatBox.innerHTML += `<div class="ai-msg">Error: Unable to regenerate response</div>`;
    }

    // Scroll to the bottom of the chat box
    chatBox.scrollTop = chatBox.scrollHeight;
  } else {
    alert("No message to regenerate.");
  }
}

// Add event listener to handle pressing Enter to send message
document
  .getElementById("user-message")
  .addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
      event.preventDefault(); // Prevent the form from submitting (if it's in a form)
      sendMessage(); // Call sendMessage when Enter is pressed
    }
  });

// Event listener for the Send button
document.getElementById("send-button").addEventListener("click", sendMessage);

// Event listener for the Regenerate Answer button
document
  .getElementById("regenerate-button")
  .addEventListener("click", regenerateAnswer);
