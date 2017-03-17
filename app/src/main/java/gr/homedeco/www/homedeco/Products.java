package gr.homedeco.www.homedeco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Products extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        getProducts();
    }

    private void getProducts() {

//        ServerRequests serverRequest = new ServerRequests(this);
//        serverRequest.fetchProductDataInBackground(0, new GetProductCallback() {
//            @Override
//            public void done(List<Product> returnedList) {
//                populateProductsList(returnedList);
//            }
//        });

        // Mockup - TODO: Delete this
        List<Product> returnedList = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductID(1);
        product1.setSKU("123ABC");
        product1.setName("BRIMNES");
        product1.setPrice(119);
        product1.setDiscountPrice(10);
        product1.setWeight(1);
        product1.setDescription("Αυτό το έπιπλο πρέπει να στερεώνεται στον τοίχο, με την βοήθεια του εξαρτήματος στερέωσης στον τοίχο που περιλαμβάνεται στην συσκευασία. Τα διαφορετικά υλικά τοίχου, απαιτούν και διαφορετικούς τύπους μηχανισμών στερέωσης. Χρησιμοποιείτε μηχανισμούς, οι οποίοι να είναι οι κατάλληλοι για τους τοίχους του σπιτιού σας, πωλούνται χωριστά. Μπορεί να συνδυαστεί και με άλλα έπιπλα της σειράς των προϊόντων BRIMNES. ");
        product1.setShortDescription("Οι πόρτες σάς επιτρέπουν να κρύψετε τα πράγματά σας και να τα προστατέψετε από τη σκόνη");
        product1.setImage("http://www.ikea.gr/images/250x250/00300664/brimnes_ntoylapi_me_portes_gyali_mayro_0.jpg?v=1");
        product1.setStock(35);
        product1.setCategoryID(1);

        Product product2 = new Product();
        product2.setProductID(2);
        product2.setSKU("123ABC");
        product2.setName("ALEX");
        product2.setPrice(79.99);
        product2.setDiscountPrice(10);
        product2.setWeight(1);
        product2.setDescription("Σκουπίστε με ένα πανί, ελαφρά βρεγμένο με ένα ήπιο καθαριστικό. Στεγνώστε με ένα καθαρό πανί. ");
        product2.setShortDescription("Τα φρένα για τα συρτάρια εμποδίζουν τα συρτάρια να βγουν πολύ έξω");
        product2.setImage("http://www.ikea.gr/images/250x250/10192824/alex_syrtariera_0.jpg?v=0");
        product2.setStock(20);
        product2.setCategoryID(1);

        Product product3 = new Product();
        product3.setProductID(2);
        product3.setSKU("123ABC");
        product3.setName("BILLY/OXBERG");
        product3.setPrice(149.99);
        product3.setDiscountPrice(10);
        product3.setWeight(1);
        product3.setDescription("Ρυθμιζόμενα ράφια. Προσαρμόστε το χώρο μεταξύ των ραφιών ανάλογα με τις ανάγκες σας.\n" +
                "Η επιφάνεια είναι κατασκευασμένη με όψη φυσικού ξύλου.\n" +
                "Οι ρυθμιζόμενοι μεντεσέδες σάς επιτρέπουν να προσαρμόσετε την πόρτα στη σωστή θέση.\n" +
                "Οι γυάλινες πόρτες προφυλάσσουν τα αγαπημένα σας αντικείμενα από τη σκόνη και επιτρέπουν να είναι ορατά. ");
        product3.setShortDescription("Ρυθμιζόμενα ράφια. Προσαρμόστε το χώρο μεταξύ των ραφιών ανάλογα με τις ανάγκες σας.");
        product3.setImage("http://www.ikea.gr/images/250x250/79020482/billy_oxberg_bibliothiki_skoyro_kafe_0.jpg?v=5");
        product3.setStock(20);
        product3.setCategoryID(1);

        returnedList.add(product1);
        returnedList.add(product2);
        returnedList.add(product3);

        populateProductsList(returnedList);
    }

    private void populateProductsList(List<Product> returnedList) {

        recyclerView = (RecyclerView) findViewById(R.id.rvProducts);
        adapter = new ProductsAdapter(returnedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Products.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
