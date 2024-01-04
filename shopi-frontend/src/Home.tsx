import React, { useState } from "react";
import ProductOverview from "./components/ProductOverview/ProductOverview";
import { IconButton, Typography } from "@mui/material";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import ShoppingCart from "./components/ShoppingCart/ShoppingCart";

const Home = () => {
    const [openShoppingCart, setOpenShoppingCart] = useState(false);

    return (
        <div>
            <Typography variant={"h2"}>Lampenshop</Typography>
            <div className={"flexEnd"}>
                <IconButton size={"large"} onClick={() => setOpenShoppingCart(!openShoppingCart)}>
                    <ShoppingCartIcon color={"primary"} fontSize={"inherit"} />
                </IconButton>
            </div>
            <ProductOverview />
            <ShoppingCart open={openShoppingCart} setOpen={setOpenShoppingCart} />
        </div>
    );
};

export default Home;
