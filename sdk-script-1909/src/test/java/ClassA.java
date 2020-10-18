import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 徒有琴
 */
public class ClassA {
    private String name;

    public ClassA() {
        System.out.println("ClassA");
    }

    public void setName(String name) {
        System.out.println("A setName");
        this.name = name;
    }

    private static ClassB classB;

    public void setClassB(ClassB bb) {
        System.out.println("A setB");
        this.classB = bb;
    }

    public ClassB getClassB() {
        return classB;
    }
}
