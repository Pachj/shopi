import { Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";
import React from "react";
import { Specification } from "./ProductDetails";

const ProductSpecifications = ({ specification }: { specification: Specification[] }) => {
    return (
        <TableContainer>
            <Table>
                <TableBody>
                    {specification.map((spec) => (
                        <TableRow key={spec.first}>
                            <TableCell>{spec.first}</TableCell>
                            <TableCell>{spec.second}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};
export default ProductSpecifications;
