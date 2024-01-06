import { Button, Card, CardActions, CardContent, IconButton, Typography } from "@mui/material";
import { Product } from "../ProductOverview/ProductOverview";
import style from "./SimpleProduct.module.css";
import { addToCart } from "../../helpers/cartHelpers";
import React from "react";
import AddShoppingCartIcon from "@mui/icons-material/AddShoppingCart";

const SimpleProduct = ({
    product,
    setSelectedProduct,
}: {
    product: Product;
    setSelectedProduct: (value: ((prevState: Product | null) => Product | null) | Product | null) => void;
}) => {
    return (
        <Card raised={true} sx={{ maxWidth: 300 }}>
            <CardContent>
                <Typography className={style.marginBottom} variant={"h5"}>
                    {product.name}
                </Typography>
                <img
                    className={`${style.productImage} ${style.marginBottom}`}
                    src={product.imageLink}
                    alt={product.name}
                />
                <div className={"center"}>
                    <Typography variant={"h6"}>{product.price}</Typography>
                </div>
            </CardContent>
            <CardActions className={"center"}>
                <Button onClick={() => setSelectedProduct(product)}>Details</Button>
                <IconButton onClick={() => addToCart(product)} color="primary" aria-label="add to shopping cart">
                    <AddShoppingCartIcon />
                </IconButton>
            </CardActions>
        </Card>
    );
};

export default SimpleProduct;
