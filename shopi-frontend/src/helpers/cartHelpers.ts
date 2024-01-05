import { Product } from "../components/ProductOverview/ProductOverview";

export const addToCart = async (product: Product) => {
    const response = await fetch("http://localhost:8080/cart", {
        method: "POST",
        headers: {
            "Access-control-allow-origin": "*",
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
        body: JSON.stringify(product),
    });
};

export const getCart = async () => {
    const response = await fetch("http://localhost:8080/cart", {
        method: "GET",
        headers: {
            "Access-control-allow-origin": "*",
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
    });
    const data = await response.json();

    return data;
};

export const updateCart = async (product: Product, amount: number) => {
    const response = await fetch("http://localhost:8080/cart", {
        method: "PUT",
        headers: {
            "Access-control-allow-origin": "*",
            "Content-Type": "application/json",
            Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
        body: JSON.stringify({ product: product, amount: amount }),
    });

    return await response.json();
};
