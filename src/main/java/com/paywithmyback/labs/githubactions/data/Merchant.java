package com.paywithmyback.labs.githubactions.data;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Merchant {

    @Id
    private Long id;

    private String name;

}
