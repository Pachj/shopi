import React, { useState } from "react";
import ProductOverview from "./components/ProductOverview/ProductOverview";
import { IconButton, Typography } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import ShoppingCart from "./components/ShoppingCart/ShoppingCart";
import LoginIcon from "@mui/icons-material/Login";
import Login from "./components/Login/Login";
import TokenInfo from "./components/TokenInfo";

const Home = () => {
    const [openShoppingCart, setOpenShoppingCart] = useState(false);
    const [showLoginMask, setShowLoginMask] = useState(false);

    return (
        <div>
            <Typography variant={"h2"}>Lampenshop</Typography>
            <div className={"flexEnd"}>
                <IconButton size={"large"} onClick={() => setOpenShoppingCart(!openShoppingCart)}>
                    <ShoppingCartIcon color={"primary"} fontSize={"inherit"} />
                </IconButton>
                <IconButton size={"large"} onClick={() => setShowLoginMask(true)}>
                    <LoginIcon color={"primary"} fontSize={"inherit"} />
                </IconButton>
            </div>
            <TokenInfo />
            <ProductOverview />
            <ShoppingCart open={openShoppingCart} setOpen={setOpenShoppingCart} />
            <Login open={showLoginMask} setOpen={setShowLoginMask} />
        </div>
    );
};

export default Home;
