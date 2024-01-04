import { Button, Card, CardActions, CardContent } from "@mui/material";
import { Product } from "../ProductOverview/ProductOverview";
import style from "./SimpleProduct.module.css";

const SimpleProduct = ({
    product,
    setSelectedProduct,
}: {
    product: Product;
    setSelectedProduct: (value: ((prevState: Product | null) => Product | null) | Product | null) => void;
}) => {
    return (
        <Card raised={true} sx={{ maxWidth: 300 }}>
            <CardContent>
                <h2>{product.name}</h2>
                <img className={style.productImage} src={product.imageLink} alt={product.name} />
                <p>{product.price}</p>
            </CardContent>
            <CardActions sx={{ display: "flex", justifyContent: "center" }}>
                <Button onClick={() => setSelectedProduct(product)}>Details</Button>
                <Button>Warenkorb</Button>
            </CardActions>
        </Card>
    );
};

export default SimpleProduct;
