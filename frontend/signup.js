function handleSubmit(event) {
  event.preventDefault(); // Prevent default form submission

  // Get values from form inputs
  const username = document.getElementById("username").value.trim();
  const password = document.getElementById("password").value.trim();
  const confirmPassword = document
    .getElementById("confirm-password")
    .value.trim();
  const email = document.getElementById("email").value.trim();
  const fullName = document.getElementById("full-name").value.trim();
  const dateOfBirth = document.getElementById("date-of-birth").value.trim();
  const gender = document.getElementById("gender").value.trim();
  const contactNumber = document.getElementById("contact-number").value.trim();
  const address = document.getElementById("address").value.trim();
  const emergencyContact = document
    .getElementById("emergency-contact")
    .value.trim();
  const bloodType = document.getElementById("blood-type").value.trim();
  const doctorContactNumber = document
    .getElementById("doctor-contact-number")
    .value.trim();
  const medicalHistory =
    document.getElementById("medical-history").value.trim() || null;
  const currentMedication =
    document.getElementById("current-medication").value.trim() || null;
  const allergies = document.getElementById("allergies").value.trim() || null;

  // Frontend validation
  const errors = [];

  if (!username || !password || !email) {
    errors.push("Username, password, and email are required.");
  }
  if (!/^\S+@\S+\.\S+$/.test(email)) {
    errors.push("Invalid email format.");
  }
  if (password.length < 6) {
    errors.push("Password must be at least 6 characters long.");
  }
  if (password !== confirmPassword) {
    errors.push("Passwords do not match.");
  }
  if (!dateOfBirth || isNaN(Date.parse(dateOfBirth))) {
    errors.push("Invalid date of birth.");

  }   
  

  // If validation errors, display them and stop submission
  if (errors.length > 0) {
    alert(`Please fix the following errors:\n- ${errors.join("\n- ")}`);
    return;
  }

  // Create the user object with all necessary fields
  const user = {
    type: "patient",
    username,
    email,
    password,
    role: "PATIENT",
    fullName,
    dateOfBirth,
    gender,
    contactNumber,
    address,
    emergencyContact,
    bloodType,
    doctorContactNumber,
    medicalHistory,
    currentMedication,
    allergies,
  };

  const backendUrl = window.backendUrl || "http://localhost:8080";

  // Send data to the backend
  fetch(`${backendUrl}/api/users/registerPatient`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  })
    .then((response) => {
      if (!response.ok) {
        return response.json().then((data) => {
          throw new Error(data.message || "Failed to register.");
        });
      }
      return response.json();
    })
    .then((data) => {
      if (data.success) {
        alert("Registration successful!");
        window.location.href = "dashboard.html"; // Redirect on success
      } else {
        throw new Error(data.message || "Registration failed.");
      }
    })
    .catch((error) => {
      console.error("Error during registration:", error);
      alert(`Registration failed: ${error.message}`);
    });
}
