import { Box, CircularProgress, Modal, Typography } from "@mui/material";
import style from "../SimpleProduct/SimpleProduct.module.css";
import React, { useEffect, useState } from "react";
import { Product } from "../../ProductOverview";
import ProductSpecifications from "./ProductSpecifications";

const boxStyle = {
    position: "absolute",
    top: "50%",
    left: "50%",
    transform: "translate(-50%, -50%)",
    width: 400,
    bgcolor: "background.paper",
    boxShadow: 24,
    p: 4,
};

export type Specification = {
    first: string;
    second: string;
};

type ProductWithDetails = {
    simpleProduct: Product;
    specification: Specification[];
};

const ProductDetails = ({ simpleProduct }: { simpleProduct: Product }) => {
    const [productWithDetails, setProductWithDetails] = useState<ProductWithDetails | null>(null);
    console.log("productDetails", productWithDetails);

    const fetchProductDetails = async () => {
        const response = await fetch(`http://localhost:8080/products/${simpleProduct.id}`);
        const data = await response.json();
        setProductWithDetails(data);
    };

    useEffect(() => {
        if (productWithDetails === null) {
            fetchProductDetails();
        }
    });

    return (
        <Modal open={simpleProduct !== null}>
            <Box sx={boxStyle}>
                {productWithDetails ? (
                    <>
                        <Typography variant={"h4"}>{productWithDetails.simpleProduct.name}</Typography>
                        <img
                            className={style.productImage}
                            src={productWithDetails.simpleProduct.imageLink}
                            alt={productWithDetails.simpleProduct.name}
                        />
                        <Typography variant={"body1"}>{productWithDetails.simpleProduct.description}</Typography>
                        <Typography variant={"h5"}>{productWithDetails.simpleProduct.price}</Typography>
                        <ProductSpecifications specification={productWithDetails.specification} />
                    </>
                ) : (
                    <CircularProgress />
                )}
            </Box>
        </Modal>
    );
};

export default ProductDetails;
