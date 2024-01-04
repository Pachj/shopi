import { useEffect, useState } from "react";
import SimpleProduct from "./components/SimpleProduct/SimpleProduct";
import ProductDetails from "./components/ProductDetails/ProductDetails";

export type Product = {
    id: number;
    name: string;
    description: string;
    price: number;
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
        <>
            <div>
                <h1>Product Overview</h1>
                {products &&
                    products.map((product) => (
                        <SimpleProduct product={product} key={product.id} setSelectedProduct={setSelectedProduct} />
                    ))}
            </div>
            {selectedProduct && <ProductDetails simpleProduct={selectedProduct} />}
        </>
    );
};

export default ProductOverview;
