import React from "react";
import { Box, Modal, Table, TableBody, TableContainer, Typography } from "@mui/material";

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

const ShoppingCart = ({ open, setOpen }: { open: boolean; setOpen: (b: boolean) => void }) => {
    return (
        <Modal open={open} onClose={() => setOpen(false)}>
            <Box sx={boxStyle}>
                <Typography variant={"h4"}>Warenkorb</Typography>
                <TableContainer>
                    <Table>
                        <TableBody></TableBody>
                    </Table>
                </TableContainer>
            </Box>
        </Modal>
    );
};

export default ShoppingCart;
