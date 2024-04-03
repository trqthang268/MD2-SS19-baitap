package business.implement;

import business.config.IOFile;
import business.config.InputMethods;
import business.design.ICategoriesDesign;
import business.entity.Categories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static business.implement.ProductImplement.productList;

public class CategoriesImplement implements ICategoriesDesign {

    public static final String PATH_CATEGORY = "D:\\module02\\Session19-baitaptonghop\\src\\business\\storage\\categoriesData.txt";
    public static  File categoriesFile = new File(PATH_CATEGORY);
    public static List<Categories> categoriesList = new ArrayList<>();

    @Override
    public void displayAll() {
        if (!categoriesList.isEmpty()) {
            System.out.println("============== Danh sach danh mục ===============");
            categoriesList.forEach(Categories::displayData);
            System.out.println("=================================================");
        }else{
            System.err.println("Không có danh mục nào trong danh sách");
        }
    }

    @Override
    public void addNewElement() {
        System.out.println("Nhập số danh mục cần nhập thông tin:");
        int numberOfCategories = InputMethods.getInteger();
        for (int i = 0; i < numberOfCategories; i++) {
            System.out.println("Nhập thông tin cho danh mục thứ " + (i + 1));
            Categories categories = new Categories();
            categories.inputData(true, categoriesList);
            categoriesList.add(categories);
        }
        IOFile.writeObjectToFile(categoriesList,categoriesFile);
    }

    @Override
    public void updateElement() {
        System.out.println("Nhập vào mã danh mục cần cập nhật :");
        int updateId = InputMethods.getInteger();
        Categories updateCategories = findById(updateId);
        if (updateCategories == null) {
            throw new RuntimeException("Danh mục không tồn tại");
        }
        System.out.println("Thông tin cũ của danh mục : ");
        updateCategories.displayData();
        System.out.println("Nhập thông tin mới ");
        int choice;
        do {
            System.out.println("Lựa chọn thông tin muốn cập nhật");
            System.out.println("1. Tên danh mục");
            System.out.println("2. Mô tả danh mục");
            System.out.println("3. Trạng thái danh mục");
            System.out.println("4. Tất cả thông tin");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn :");
            choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên danh mục mới :");
                    updateCategories.setCatalogName(Categories.inputCatalogName(categoriesList));
                    break;
                case 2:
                    System.out.println("Nhập mô tả danh mục mới :");
                    updateCategories.setDescriptions(InputMethods.getString());
                    break;
                case 3:
                    updateCategories.setCatalogStatus(InputMethods.getBoolean());
                    break;
                case 4:
                    System.out.println("Nhập thông tin mới :");
                    updateCategories.inputData(false, categoriesList);
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-5");
            }
        } while (choice != 5);
        System.out.println("Cập nhật thông tin thành công");
        IOFile.writeObjectToFile(categoriesList,categoriesFile);
    }

    @Override
    public void deleteElement() {
        System.out.println("Nhập mã danh mục muốn xóa");
        int deleteId = InputMethods.getInteger();
        Categories delete = findById(deleteId);
        if (delete == null) {
            throw new RuntimeException("Danh mục không tồn tại");
        }
        if (productList.stream().anyMatch(product -> product.getCatalogId() == deleteId)) {
            System.err.println("Danh mục có sản phẩm, không thể xóa");
            return;
        }
        categoriesList.remove(delete);
        IOFile.writeObjectToFile(categoriesList,categoriesFile);
        System.out.println("Xóa danh mục thành công");
    }

    @Override
    public Categories findById(Integer id) {
        for (Categories categories : categoriesList) {
            if (categories.getCatalogId() == id) {
                return categories;
            }
        }
        return null;
    }

    @Override
    public void updateCategoriesStatus() {
        System.out.println("Nhập vào mã danh mục muốn cập nhật trạng thái");
        int catalogId = InputMethods.getInteger();
        Categories updateStatus = findById(catalogId);
        if (updateStatus == null) {
            System.err.println("Không tìm thấy mã danh mục");
            return;
        }
        updateStatus.setCatalogStatus(!updateStatus.isCatalogStatus());
        IOFile.writeObjectToFile(categoriesList,categoriesFile);
        System.out.println("Cập nhật trạng thái thành " + (updateStatus.isCatalogStatus() ? "Hoạt động" : "Không hoạt động"));
    }
}
