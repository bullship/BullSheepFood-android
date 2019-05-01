package com.bullsheep.bullsheepfood_android.ui.add_food;

import com.bullsheep.bullsheepfood_android.data.ApiFactory;
import com.bullsheep.bullsheepfood_android.data.ProductService;
import com.bullsheep.bullsheepfood_android.model.Product;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProductViewModel extends ViewModel {
    private static final String TAG = "ProductViewModel";
    private ProductService productService;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Boolean> isProcessed = new MutableLiveData<>();


    public ProductViewModel() {
        productService = ApiFactory.getProductService();
    }

    public void saveProduct(Product product) {
        isProcessed.setValue(false);

        compositeDisposable.add(
                productService.create(product)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(productResponse -> isProcessed.setValue(true))
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
