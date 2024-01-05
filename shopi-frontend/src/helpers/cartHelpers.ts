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

    const data = await response.json();
    console.log(data);
};
