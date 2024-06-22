package com.springboot.coffee.entity;

import com.springboot.order.entity.Order;
import com.springboot.order.entity.OrderCoffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Coffee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long coffeeId;

    @Column(length = 100, nullable = false)
    private String korName;

    @Column(length = 100, nullable = false)
    private String  engName;

    @Column(nullable = false)
    private int price;

    @Column(length = 3, nullable = false, unique = true)
    private String coffeeCode;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_FOR_SALE;

    @Column(nullable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(nullable = false, name = "LAST_MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "coffee")
    private List<OrderCoffee> orderCoffee = new ArrayList<>();

//    public void addOrderCoffee(OrderCoffee orderCoffee) {
//        orderCoffee.addCoffee(orderCoffee.getCoffee());
//        if (orderCoffee.getCoffee() != this) {
//            orderCoffee.addCoffee(this);
//        }
//    }
    public enum CoffeeStatus {
        COFFEE_FOR_SALE("판매중"),
        COFFEE_SOLD_OUT("판매 중지");

        @Getter
        private String status;

        CoffeeStatus(String status) {
            this.status = status;
        }
    }
}
