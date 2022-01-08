import { initializeApp } from "firebase/app";
import {} from "firebase/auth";
import {} from "firebase/firestore";

// TODO: Replace the following with your app's Firebase project configuration
const firebaseConfig = {
  apiKey: "AIzaSyADzqIYc1truQ5eQI7qT45QoNpHD9HLVTs",
  authDomain: "sctea-9f2b4.firebaseapp.com",
  databaseURL: "https://sctea-9f2b4-default-rtdb.firebaseio.com",
  projectId: "sctea-9f2b4",
  storageBucket: "sctea-9f2b4.appspot.com",
  messagingSenderId: "648923577185",
  appId: "1:648923577185:web:918d35710996cb8d2f2320",
  measurementId: "G-2S9KN2JQH6",
};

const provider = new GoogleAuthProvider();
const app = initializeApp(firebaseConfig);

const auth = getAuth();

document.getElementById("Submit").onclick = signInWithPopup(auth, provider)
  .then((result) => {
    // This gives you a Google Access Token. You can use it to access the Google API.
    const credential = GoogleAuthProvider.credentialFromResult(result);
    const token = credential.accessToken;
    // The signed-in user info.
    const user = result.user;
    // ...
  })
  .catch((error) => {
    // Handle Errors here.
    const errorCode = error.code;
    const errorMessage = error.message;
    // The email of the user's account used.
    const email = error.email;
    // The AuthCredential type that was used.
    const credential = GoogleAuthProvider.credentialFromError(error);
    // ...
  });

/* some signout code

import { getAuth, signOut } from "firebase/auth";

const auth = getAuth();
signOut(auth).then(() => {
  // Sign-out successful.
}).catch((error) => {
  // An error happened.
});

*/
