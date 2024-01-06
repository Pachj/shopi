import { useEffect, useState } from "react";
import SimpleProduct from "../SimpleProduct/SimpleProduct";
import ProductDetails from "../ProductDetails/ProductDetails";
import style from "./ProductOverview.module.css";

export type Product = {
    id: number;
    name: string;
    description: string;
    price: number;
    stock: number;
    imageLink: string;
};

const ProductOverview = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const [selectedProduct, setSelectedProduct] = useState<Product | null>(null);

    const fetchProducts = async () => {
        const response = await fetch("http://localhost:8080/products");
        const data = await response.json();
        setProducts(data);
    };

    useEffect(() => {
        if (products.length === 0) {
            fetchProducts();
        }
    }, []);

    return (
        <div>
            <div className={style.container}>
                {products &&
                    products.map((product) => (
                        <SimpleProduct product={product} key={product.id} setSelectedProduct={setSelectedProduct} />
                    ))}
            </div>
            {selectedProduct && (
                <ProductDetails simpleProduct={selectedProduct} setSelectedProduct={setSelectedProduct} />
            )}
        </div>
    );
};

export default ProductOverview;
