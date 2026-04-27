package entity;

import java.time.LocalDate;
import java.util.Arrays;

public class Group {
    int id;
    String name;
    Account creator;
    LocalDate createDate;
    public Account[] accounts;

    public Group() {
    }

    public Group(int i, String javaFresher, Account acc1, LocalDate now) {
        this.id = i;
        this.name = javaFresher;
        this.creator = acc1;
        this.createDate = now;
    }

    public Group(int id, String name, Account creator, LocalDate createDate, Account[] accounts) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.createDate = createDate;
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getCreator() {
        return creator;
    }

    public void setCreator(Account creator) {
        this.creator = creator;
    }

    public Account[] getAccounts() {
        return accounts;
    }

    public void setAccounts(Account[] accounts) {
        this.accounts = accounts;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creator=" + creator +
                ", createDate=" + createDate +
                ", accounts=" + Arrays.toString(accounts) +
                '}';
    }
}