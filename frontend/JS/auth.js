// Local storage-based mock authentication
document.addEventListener("DOMContentLoaded", () => {
  const signupForm = document.getElementById("signupForm");
  const loginForm = document.getElementById("loginForm");

  // Signup form logic
  if (signupForm) {
    signupForm.addEventListener("submit", e => {
      e.preventDefault();
      const username = document.getElementById("signupUsername").value.trim();
      const password = document.getElementById("signupPassword").value;
      const confirm = document.getElementById("signupConfirm").value;
      const msg = document.getElementById("signupMsg");

      if (password !== confirm) {
        msg.textContent = "Passwords do not match.";
        msg.style.color = "#f87171";
        return;
      }

      if (localStorage.getItem("user:" + username)) {
        msg.textContent = "Username already exists.";
        msg.style.color = "#f87171";
        return;
      }

      localStorage.setItem("user:" + username, JSON.stringify({ username, password }));
      msg.textContent = "Account created! Redirecting to login...";
      msg.style.color = "#4ade80";

      setTimeout(() => {
        window.location.href = "../Pages/login.html";
      }, 1500);
    });
  }

  // Login form logic
  if (loginForm) {
    loginForm.addEventListener("submit", e => {
      e.preventDefault();
      const username = document.getElementById("loginUsername").value.trim();
      const password = document.getElementById("loginPassword").value;
      const msg = document.getElementById("loginMsg");

      const userData = localStorage.getItem("user:" + username);
      if (!userData) {
        msg.textContent = "User not found.";
        msg.style.color = "#f87171";
        return;
      }

      const { password: storedPass } = JSON.parse(userData);
      if (password === storedPass) {
        msg.textContent = "Login successful! Redirecting...";
        msg.style.color = "#4ade80";
        localStorage.setItem("loggedInUser", username);

        setTimeout(() => {
          window.location.href = "../Pages/home.html"; // redirect to main app
        }, 1500);
      } else {
        msg.textContent = "Incorrect password.";
        msg.style.color = "#f87171";
      }
    });
  }
});
