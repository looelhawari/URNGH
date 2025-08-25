// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyBjr8iibcNLTSowQCyfY8UL7LGyHO4GyvA",
  authDomain: "urngh-fbb57.firebaseapp.com",
  projectId: "urngh-fbb57",
  storageBucket: "urngh-fbb57.firebasestorage.app",
  messagingSenderId: "45683376184",
  appId: "1:45683376184:web:2272bebcf464947ad2e6cd",
  measurementId: "G-WWL0B957XM"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);