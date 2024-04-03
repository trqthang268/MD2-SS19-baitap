package business.entity;

import business.config.InputMethods;

import java.io.Serializable;
import java.util.List;

public class Categories implements Serializable {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
    public void inputData(boolean isAdd, List<Categories> categoriesList){
        if (isAdd){
            this.catalogId = inputCatalogId(categoriesList);
        }
        this.catalogName = inputCatalogName(categoriesList);
        this.descriptions = inputDescriptiopn();
        this.catalogStatus = inputCatalogStatus();
    }

    public int inputCatalogId(List<Categories> categoriesList) {
        if (categoriesList.isEmpty()){
            return 1;
        }else {
            int maxId = categoriesList.get(0).getCatalogId();
            for (int i = 1; i < categoriesList.size(); i++) {
                if (categoriesList.get(i).getCatalogId()>maxId){
                    maxId = categoriesList.get(i).getCatalogId();
                }
            }
            return maxId+1;
        }
    }

    public static String inputCatalogName(List<Categories> categoriesList){
        do {
            System.out.println("Nhập tên danh mục");
            String catalogName = InputMethods.getString();
            if (catalogName.length() <= 50){
                boolean isExist = true;
                for (int i = 0; i < categoriesList.size(); i++) {
                    if (categoriesList.get(i).getCatalogName().equals(catalogName)){
                        isExist = false;
                        break;
                    }
                }
                if (isExist){ // Tên không tồn tại
                    return catalogName;
                }else{  // Tên đã tồn tại
                    System.err.println("Tên danh mục đã tồn tại,, vui lòng nhập lại.");
                }
            }else{
                System.err.println("Tên danh mục tối đa 50 kí tự, vui lòng nhập lại.");
            }
        }while (true);
    }

    public String inputDescriptiopn(){
        System.out.println("Nhập mô tả danh mục");
        return InputMethods.getString();
    }

    public boolean inputCatalogStatus(){
        do {
            System.out.println("Nhập trạng thái danh mục");
            String status = InputMethods.getString();
            if (status.equals("true") || status.equals("false")){
                return Boolean.parseBoolean(status);
            }else{
                System.err.println("Trạng thái danh mục chỉ nhận hai giá trị true và false");
            }
        }while(true);
    }

    public void displayData() {
        System.out.printf("Mã DM: %d - Tên DM: %s - Mô tả: %s - Trạng thái: %s\n",
                this.catalogId, this.catalogName, this.descriptions, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }
}
