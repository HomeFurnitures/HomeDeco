package gr.homedeco.www.homedeco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONparser {

    public JSONparser() {
    }

    public List<Product> toProduct(String result) throws JSONException {

        List<Product> products = new ArrayList<>();
        Product returnedProduct;
        JSONArray jArray = new JSONArray(result);

        for (int i=0; i<jArray.length(); i++) {
            JSONObject jObject = jArray.getJSONObject(i);

            if (jObject.length() != 0) {
                int productID = jObject.getInt("ProductID");
                String SKU = jObject.getString("SKU");
                String name = jObject.getString("Name");
                float price = (float) jObject.getDouble("Price");
                float discountPrice = (float) jObject.getDouble("DiscountPrice");
                int weight = jObject.getInt("Weight");
                String desc = jObject.getString("Description");
                String shortDesc = jObject.getString("ShortDescription");
                String image = jObject.getString("Image");
                int categoryID = jObject.getInt("CategoryID");

                returnedProduct = new Product();
                returnedProduct.setProductID(productID);
                returnedProduct.setSKU(SKU);
                returnedProduct.setName(name);
                returnedProduct.setPrice(price);
                returnedProduct.setDiscountPrice(discountPrice);
                returnedProduct.setWeight(weight);
                returnedProduct.setDescription(desc);
                returnedProduct.setShortDescription(shortDesc);
                returnedProduct.setImage(image);
                returnedProduct.setCategoryID(categoryID);

                products.add(returnedProduct);
            }
        }

        return products;
    }
}
