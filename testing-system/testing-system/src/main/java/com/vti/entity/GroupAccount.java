package com.vti.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "groupaccount")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAccount {

    @EmbeddedId
    private GroupAccountId id;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "groupid")
    private Group group;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "accountid")
    private Account account;

    @Column(name = "joindate")
    private LocalDate joinDate;
}
