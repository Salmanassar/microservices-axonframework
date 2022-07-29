package com.appsdeveloper.estore.productservice.core.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productlookup")
public class ProductLookupEntity implements Serializable {
    private static final long serialVersionUID = 8280735093315772549L;

    @Id
    private String productId;

    @Column(unique = true)
    private String title;

}
