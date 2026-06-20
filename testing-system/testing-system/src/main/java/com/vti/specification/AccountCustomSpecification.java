package com.vti.specification;

import com.vti.entity.Account;
import com.vti.enums.PositionName;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.domain.Specification;

public class AccountCustomSpecification implements Specification<Account> {

    @NotNull
    private String field;

    @NotNull
    private Object value;

    public AccountCustomSpecification(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public Predicate toPredicate (Root<Account> root,
                                  CriteriaQuery<?> query,
                                  CriteriaBuilder cb) {
        if (field.equalsIgnoreCase("username")) {
            return cb.like(root.get("username"), "%" + value + "%");
        }
        if (field.equalsIgnoreCase("email")) {
            return cb.like(root.get("email"), "%" + value + "%");
        }
        if (field.equalsIgnoreCase("fullName")) {
            return cb.like(root.get("fullName"), "%" + value + "%");
        }
        if (field.equalsIgnoreCase("password")) {
            return cb.like(root.get("password"), "%" + value + "%");
        }
        if (field.equalsIgnoreCase("department")) {
            return cb.like(root.get("department").get("name"), "%" + value + "%");
        }
        if (field.equalsIgnoreCase("position")) {
            try {
                PositionName positionName = PositionName.fromValue((String) value);
                return cb.equal(root.get("position").get("positionName"), positionName);
            } catch (IllegalArgumentException e) {
                return cb.disjunction(); // returns a false condition so invalid position returns nothing
            }
        }
        if (field.equalsIgnoreCase("createDate")) {
            return cb.equal(root.get("createDate"), value);
        }

        return null;
    }
}
