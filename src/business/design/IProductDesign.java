package business.design;

import business.entity.Product;

public interface IProductDesign extends IGenericDesign<Product,String>{
void sortByPrice();
void searchProductByName();
void searchProductInRange();
}
