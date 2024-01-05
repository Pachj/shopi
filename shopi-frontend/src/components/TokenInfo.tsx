import { jwtDecode } from "jwt-decode";
import { Typography } from "@mui/material";

const TokenInfo = () => {
    const tokenString = localStorage.getItem("accessToken");

    if (tokenString) {
        const decodedToken = jwtDecode(tokenString);
        if (!decodedToken.exp || (decodedToken.exp && decodedToken.exp < Date.now() / 1000)) {
            return (
                <div>
                    <Typography variant={"h4"}>Bitte einloggen um alle Features nutzen zu können.</Typography>
                </div>
            );
        }
    }
    return null;
};

export default TokenInfo;
