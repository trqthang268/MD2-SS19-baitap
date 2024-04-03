package business.design;

public interface IGenericDesign <T,E>{
    void displayAll();
    void addNewElement();
    void updateElement();
    void deleteElement();
    T findById(E id);
}
