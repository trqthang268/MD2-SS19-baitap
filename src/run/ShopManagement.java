package run;

import business.config.InputMethods;
import business.implement.CategoriesImplement;
import business.implement.ProductImplement;

public class ShopManagement {
    public static void main(String[] args) {
        ShopManagement shopManagement = new ShopManagement();
        do {
            System.out.println("************SHOP MENU************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    shopManagement.displayCategoriesMenu();
                    break;
                case 2:
                    shopManagement.displayProductMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public void displayProductMenu() {
        ProductImplement productImplement = new ProductImplement();
        boolean isExit = true;
        do {
            System.out.println("***************PRODUCT MENU*****************");
            System.out.println("1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn phím)\n" +
                    "8. Quay lại\n");
            System.out.println("Nhập lựa chọn của bạn");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    productImplement.addNewElement();
                    break;
                case 2:
                    productImplement.displayAll();
                    break;
                case 3:
                    productImplement.sortByPrice();
                    break;
                case 4:
                    productImplement.updateElement();
                    break;
                case 5:
                    productImplement.deleteElement();
                    break;
                case 6:
                    productImplement.searchProductByName();
                    break;
                case 7:
                    productImplement.searchProductInRange();
                    break;
                case 8:
                    isExit = false;
                    break;
                default:
                    System.out.println("Vui lòng nhập lựa chọn từ 1-8");
            }

        } while (isExit);
    }

    public void displayCategoriesMenu() {
        CategoriesImplement categoriesImplement = new CategoriesImplement();
        boolean isExit = true;
        do {
            System.out.println("*************CATEGORIES MENU************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin các danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Quay lại");
            System.out.print("Lựa chọn của bạn:");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    categoriesImplement.addNewElement();
                    break;
                case 2:
                    categoriesImplement.displayAll();
                    break;
                case 3:
                    categoriesImplement.updateElement();
                    break;
                case 4:
                    categoriesImplement.deleteElement();
                    break;
                case 5:
                    categoriesImplement.updateCategoriesStatus();
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }
}
