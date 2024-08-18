import axios from 'axios';

const api = axios.create({
    baseUrl: "http://localhost:8081/",
    timeout: 5000,
    headers: {"Content-Type": "application/json"}
})

export default api;