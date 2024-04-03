package business.implement;

import business.config.IOFile;
import business.config.InputMethods;
import business.design.IProductDesign;
import business.entity.Product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static business.implement.CategoriesImplement.categoriesList;

public class ProductImplement implements IProductDesign {
    public static List<Product> productList = new ArrayList<>();
    public static final String PATH_PRODUCT = "D:\\module02\\Session19-baitaptonghop\\src\\business\\storage\\productData.txt";
    public static File productFile = new File(PATH_PRODUCT);

    @Override
    public void displayAll() {
        System.out.println("=========== Danh sách sản phẩm ==========");
        productList.forEach(Product::displayData);
        System.out.println("=========================================");
    }

    @Override
    public void addNewElement() {
        System.out.println("Nhập số sản phẩm cần nhập thông tin");
        int numberOfProduct = InputMethods.getInteger();
        for (int i = 0; i < numberOfProduct; i++) {
            System.out.println("Nhập thông tin danh mục thứ "+(i+1));
            Product product = new Product();
            product.inputData(productList,categoriesList);
            productList.add(product);
        }
        IOFile.writeObjectToFile(productList,productFile);
    }

    @Override
    public void updateElement() {
        System.out.println("Nhập mã sản phẩm muốn sửa đổi");
        String updateId = InputMethods.getString();
        Product updateProduct = findById(updateId);
        if (updateProduct == null){
            System.err.println("Sản phẩm không tồn tại");
            return;
        }
        System.out.println("Thông tin cũ :");
        updateProduct.displayData();
        System.out.println("Nhập thông tin mới");
        int choice;
        do {
            System.out.println("Lựa chọn thông tin muốn cập nhật");
            System.out.println("1. Mã sản phẩm");
            System.out.println("2. Tên sản phẩm");
            System.out.println("3. Giá sản phẩm");
            System.out.println("4. Mô tả sản phẩm");
            System.out.println("5. Ngày nhập sản phẩm");
            System.out.println("6. Mã danh mục");
            System.out.println("7. Trạng thái danh mục");
            System.out.println("8. Cập nhật toàn bộ");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn của bạn :");
            choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    System.out.println("Nhập mã sản phẩm mới :");
                    updateProduct.setProductId(Product.inputProductId(productList));
                    break;
                case 2:
                    System.out.println("Nhập tên sản phẩm mới :");
                    updateProduct.setProductName(Product.inputProductName(productList));
                    break;
                case 3:
                    System.out.println("Nhập giá sản phẩm mới :");
                    updateProduct.setPrice(Product.inputProductPrice());
                    break;
                case 4:
                    System.out.println("Nhập mô tả sản phẩm mới :");
                    updateProduct.setDescription(InputMethods.getString());
                    break;
                case 5:
                    System.out.println("Nhập ngày nhập mới :");
                    updateProduct.setCreated(InputMethods.getDate());
                    break;
                case 6:
                    System.out.println("Nhập mã danh mục của sản phẩm mới");
                    updateProduct.setCatalogId(Product.inputCatalogId(categoriesList));
                    break;
                case 7:
                    System.out.println("Nhập trạng thái sản phẩm :");
                    updateProduct.setProductStatus(Product.inputStatus());
                    break;
                case 8:
                    updateProduct.inputData(productList,categoriesList);
                    break;
                case 9:
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (choice != 9);
        System.out.println("Cập nhật thông tin thành công");
        IOFile.writeObjectToFile(productList,productFile);
    }

    @Override
    public void deleteElement() {
        System.out.println("Nhập mã danh mục muốn xóa");
        String deleteId = InputMethods.getString();
        Product delete = findById(deleteId);
        if (delete == null){
            System.err.println("Không tìm thấy mã danh mục");
            return;
        }
        productList.remove(delete);
        System.out.println("Xóa danh mục thành công");
        IOFile.writeObjectToFile(productList,productFile);
    }

    @Override
    public Product findById(String id) {
        for (Product product : productList) {
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }

    @Override
    public void sortByPrice() {
        System.out.println("Lựa chọn thứ tự sắp xếp");
        System.out.println("1. Sắp xếp giá tăng dần");
        System.out.println("2. Sắp xếp giá giảm dần");
        int choice = InputMethods.getInteger();
        switch (choice){
            case 1:
                productList.sort((o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
                displayAll();
                break;
            case 2:
                productList.sort((o1, o2) -> (int) (o2.getPrice() - o1.getPrice()));
                displayAll();
                break;
            default:
        }
        IOFile.writeObjectToFile(productList,productFile);
    }

    @Override
    public void searchProductByName() {
        System.out.println("Nhập tên sản phẩm cần tìm :");
        String inputName = InputMethods.getString();
        if (productList.stream().anyMatch(product -> product.getProductName().equalsIgnoreCase(inputName))){
            productList.stream().filter(product -> product.getProductName().equalsIgnoreCase(inputName)).forEach(Product::displayData);
        }else{
            System.err.println("Không có sản phẩm nào cùng tên");
        }
    }

    @Override
    public void searchProductInRange() {
        System.out.println("Nhập giá tiền tối thiểu");
        float fromPrice = InputMethods.getFloat();
        System.out.println("Nhập giá tiền tối đa");
        float toPrice = InputMethods.getFloat();
        System.out.printf("Sản phẩm có giá trị trong khoảng %f - %f\n",fromPrice,fromPrice);
        boolean isExist = true;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getPrice() >= fromPrice && productList.get(i).getPrice()<=toPrice){
                productList.get(i).displayData();
                isExist = false;
            }
        }
        if (isExist){
            System.out.println("Không có sản phẩm nào trong khoảng giá đó");
        }
    }
}
