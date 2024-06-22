package com.springboot.order.entity;

import com.springboot.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class OrderCoffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderCoffeeId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    // 보편적으로 add보다는 set을 사용
//    public void addCoffee(Coffee coffee) {
//        this.coffee = coffee;
//        if (!coffee.getOrderCoffee().contains(this)) {
//            coffee.addOrderCoffee(this);
//        }
//    }

    @ManyToOne
    @JoinColumn(name = "ORDERS_ID")
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
        if (!order.getOrderCoffees().contains(this)) {
         //   if(!this.order.getOrderCoffees().contains(this))
            order.getOrderCoffees().add(this);
        }
    }
    public OrderCoffee(int quantity) {
        this.quantity = quantity;
    }
}
