import { jwtDecode } from "jwt-decode";

export const isLoggedIn = () => {
    const tokenString = localStorage.getItem("accessToken");

    if (tokenString) {
        const decodedToken = jwtDecode(tokenString);
        if (!decodedToken.exp || (decodedToken.exp && decodedToken.exp < Date.now() / 1000)) {
            return false;
        }
    } else {
        return false;
    }
    return true;
};
