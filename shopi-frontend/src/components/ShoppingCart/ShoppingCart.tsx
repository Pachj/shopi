import React, { useEffect, useState } from "react";
import { Box, CircularProgress, Modal, Table, TableBody, TableContainer, TextField, Typography } from "@mui/material";
import { getCart, updateCart } from "../../helpers/cartHelpers";
import { Product } from "../ProductOverview/ProductOverview";

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

type ShoppingCart = {
    items: ShoppingCartItem[];
};

type ShoppingCartItem = {
    product: Product;
    amount: number;
};

const ShoppingCart = ({ open, setOpen }: { open: boolean; setOpen: (b: boolean) => void }) => {
    const [shoppingCart, setShoppingCart] = useState<ShoppingCart | null>(null);
    const [shoppingCartIsLoaded, setShoppingCartIsLoaded] = useState(false);

    useEffect(() => {
        if (open && !shoppingCartIsLoaded) {
            getCart().then((cart) => {
                setShoppingCart(cart);
                setShoppingCartIsLoaded(true);
            });
        }
    }, [open]);

    const updateAmount = (product: Product, amount: number) => {
        setShoppingCart(null);
        setShoppingCartIsLoaded(false);
        updateCart(product, amount).then((cart) => {
            setShoppingCart(cart);
            setShoppingCartIsLoaded(true);
        });
    };

    return (
        <Modal
            open={open}
            onClose={() => {
                setOpen(false);
                setShoppingCart(null);
                setShoppingCartIsLoaded(false);
            }}
        >
            <Box sx={boxStyle}>
                <Typography variant={"h4"}>Warenkorb</Typography>
                {!shoppingCartIsLoaded && <CircularProgress />}
                {shoppingCartIsLoaded && shoppingCart && shoppingCart.items.length === 0 && (
                    <Typography variant={"h5"}>Keine Produkte im Warenkorb</Typography>
                )}
                {shoppingCartIsLoaded && shoppingCart !== null && (
                    <TableContainer>
                        <Table>
                            <TableBody>
                                {shoppingCart.items.map((item) => (
                                    <tr key={item.product.id}>
                                        <td>
                                            <TextField
                                                type={"number"}
                                                value={item.amount}
                                                onChange={(e) => updateAmount(item.product, parseInt(e.target.value))}
                                            />
                                        </td>
                                        <td>{item.product.name}</td>
                                        <td>{item.amount * item.product.price}</td>
                                    </tr>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                )}
            </Box>
        </Modal>
    );
};

export default ShoppingCart;
