package gr.homedeco.www.homedeco;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomProductsAdapter extends ArrayAdapter<Product> {

    private Context context2;

    public CustomProductsAdapter(Context context, List<Product> products) {
        super(context, R.layout.custom_products_row, products);
        this.context2 = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater myInflater = LayoutInflater.from(getContext());
            convertView = myInflater.inflate(R.layout.custom_products_row, parent, false);
        }

        ImageView imgv1;
        TextView tvProductsDesc1;

        Product product = getItem(position);

        imgv1 = (ImageView) convertView.findViewById(R.id.imgvProductsPhoto);
        tvProductsDesc1 = (TextView) convertView.findViewById(R.id.tvProductsDesc);

        String image_url = "http://83.212.107.169/" + product.getImage();
        Picasso.with(context2).load(image_url).into(imgv1);
        tvProductsDesc1.setText(product.getShortDescription());

        return  convertView;
    }
}
