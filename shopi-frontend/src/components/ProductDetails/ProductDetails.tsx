import { Box, CircularProgress, IconButton, Modal, Paper, Typography } from "@mui/material";
import style from "../SimpleProduct/SimpleProduct.module.css";
import React, { useEffect, useState } from "react";
import { Product } from "../ProductOverview/ProductOverview";
import ProductSpecifications from "./ProductSpecifications";
import { boxStyle } from "../../helpers/muiStyles";
import CloseIcon from "@mui/icons-material/Close";

export type Specification = {
    first: string;
    second: string;
};

type ProductWithDetails = {
    simpleProduct: Product;
    specification: Specification[];
};

const ProductDetails = ({
    simpleProduct,
    setSelectedProduct,
}: {
    simpleProduct: Product;
    setSelectedProduct: (b: null) => void;
}) => {
    const [productWithDetails, setProductWithDetails] = useState<ProductWithDetails | null>(null);

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
        <Modal open={simpleProduct !== null} onClose={() => setSelectedProduct(null)}>
            <Box sx={boxStyle}>
                <div className={"flexEnd"} style={{ width: "100%" }}>
                    <IconButton onClick={() => setSelectedProduct(null)}>
                        <CloseIcon />
                    </IconButton>
                </div>
                {productWithDetails?.simpleProduct ? (
                    <>
                        <Typography variant={"h4"}>{productWithDetails.simpleProduct.name}</Typography>
                        <img
                            className={style.productImage}
                            src={productWithDetails.simpleProduct.imageLink}
                            alt={productWithDetails.simpleProduct.name}
                        />
                        <Typography variant={"body1"}>{productWithDetails.simpleProduct.description}</Typography>
                        <div className={"flexSpaceBetween"}>
                            <Paper sx={{ padding: "1rem" }}>
                                <Typography variant={"h5"}>{productWithDetails.simpleProduct.price}</Typography>
                            </Paper>
                            <Paper sx={{ padding: "1rem" }}>
                                <Typography variant={"h5"}>{productWithDetails.simpleProduct.stock} Stück</Typography>
                            </Paper>
                        </div>
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
