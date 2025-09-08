const API_BASE = "http://localhost:8032/api/auth";
let jwtToken = null;

// Handle registration
document.getElementById("registerForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const res = await fetch(`${API_BASE}/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });

    if (res.ok) {
        alert("Registration successful!");
    } else {
        alert("Registration failed!");
    }
});

// Handle login
document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    const username = document.getElementById("loginUsername").value;
    const password = document.getElementById("loginPassword").value;

    const res = await fetch(`${API_BASE}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    });

    if (res.ok) {
        const data = await res.json();
        jwtToken = data.token;
        alert("Login successful!");
    } else {
        alert("Login failed!");
    }
});

// Handle appointment booking
document.getElementById("appointmentForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    if (!jwtToken) {
        alert("Please login first!");
        return;
    }

    const patientName = document.getElementById("patientName").value;
    const doctorName = document.getElementById("doctorName").value;
    const appointmentTime = document.getElementById("appointmentTime").value;

    const res = await fetch("http://localhost:8032/api/appointments", {
        method: "POST",
        headers: { 
            "Content-Type": "application/json",
            "Authorization": "Bearer " + jwtToken
        },
        body: JSON.stringify({ patientName, doctorName, appointmentTime })
    });

    if (res.ok) {
        alert("Appointment booked!");
    } else {
        alert("Failed to book appointment!");
    }
});
