package gr.homedeco.www.homedeco;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

    private List<Product> dataList;
    private Context context2;

    public ProductsAdapter(List<Product> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        context2 = parent.getContext();
        View view = layoutInflater.inflate(R.layout.custom_products_row, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.tvProductsDesc.setText(dataList.get(position).getShortDescription());
        String image_url = "http://83.212.107.169/" + dataList.get(position).getImage();
        Picasso.with(context2).load(image_url).into(holder.imgProductsPhoto);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView tvProductsDesc;
        ImageView imgProductsPhoto;

        ProductViewHolder(View itemView) {
            super(itemView);
            tvProductsDesc = (TextView) itemView.findViewById(R.id.tvProductsDesc);
            imgProductsPhoto = (ImageView) itemView.findViewById(R.id.imgvProductsPhoto);
        }
    }
}
