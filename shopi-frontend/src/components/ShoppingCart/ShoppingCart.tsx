import React, { useEffect, useState } from "react";
import {
    Box,
    CircularProgress,
    IconButton,
    Modal,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableRow,
    TextField,
    Typography,
} from "@mui/material";
import { getCart, updateCart } from "../../helpers/cartHelpers";
import { Product } from "../ProductOverview/ProductOverview";
import CloseIcon from "@mui/icons-material/Close";

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
                <div className={"flexEnd"} style={{ width: "100%" }}>
                    <IconButton onClick={() => setOpen(false)}>
                        <CloseIcon />
                    </IconButton>
                </div>
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
                                    <TableRow key={item.product.id}>
                                        <TableCell>
                                            <TextField
                                                type={"number"}
                                                value={item.amount}
                                                onChange={(e) => updateAmount(item.product, parseInt(e.target.value))}
                                            />
                                        </TableCell>
                                        <TableCell>{item.product.name}</TableCell>
                                        <TableCell>{item.amount * item.product.price}</TableCell>
                                    </TableRow>
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
