package com.example.portfolio.item;

import com.example.portfolio.brand.Brand;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long price;
    private Long discountRate;

    @ManyToOne
    private Brand brand;

}
