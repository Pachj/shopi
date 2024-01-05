import { Box, Button, Modal, TextField, Typography } from "@mui/material";
import React, { useState } from "react";
import style from "./Login.module.css";

const boxStyle = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    boxShadow: 24,
    p: 4,
    display: "flex",
    flexWrap: "wrap",
    gap: "1rem",
};
const Login = ({ open, setOpen }: { open: boolean; setOpen: (b: boolean) => void }) => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const login = async () => {
        const response = await fetch("http://localhost:8080/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email: email, password: password }),
        });
        const data = await response.json();

        if (data.accessToken) {
            localStorage.setItem("accessToken", data.accessToken);
            setOpen(false);
        }
    };

    const register = async () => {
        const response = await fetch("http://localhost:8080/user", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email: email, password: password }),
        });
        const data = await response.json();
        await login();
    };

    return (
        <Modal open={open} onClose={() => setOpen(false)}>
            <Box sx={boxStyle}>
                <Typography variant={"h4"}>Login</Typography>
                <TextField
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className={style.fullRow}
                    required
                    type={"email"}
                    label={"E-Mail"}
                    variant={"outlined"}
                />
                <TextField
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className={style.fullRow}
                    required
                    type={"password"}
                    label={"Passwort"}
                    variant={"outlined"}
                />
                <Button variant={"text"} onClick={() => register()}>
                    Registrieren
                </Button>
                <Button variant={"contained"} onClick={() => login()}>
                    Login
                </Button>
            </Box>
        </Modal>
    );
};

export default Login;
