package entity;

public class TypeQuestion {
    int id;
    TypeName name;

    public enum TypeName {
        ESSAY, MULTIPLE_CHOICE
    }

    public TypeQuestion() {
    }

    public TypeQuestion(int id, TypeName name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(TypeName name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public TypeName getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TypeQuestion{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}