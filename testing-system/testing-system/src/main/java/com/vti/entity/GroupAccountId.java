package com.vti.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GroupAccountId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "account_id")
    private Integer accountId;
}
