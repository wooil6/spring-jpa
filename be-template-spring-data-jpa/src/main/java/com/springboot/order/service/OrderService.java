package com.springboot.order.service;

import com.springboot.coffee.service.CoffeeService;
import com.springboot.exception.BusinessLogicException;
import com.springboot.exception.ExceptionCode;
import com.springboot.member.service.MemberService;
import com.springboot.order.entity.Order;
import com.springboot.order.entity.OrderCoffee;
import com.springboot.order.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private MemberService memberService;
    private CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository,
                        MemberService memberService,
                        CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order createOrder(Order order) {
//        // 회원이 있는지 확인
//       memberService.findVerifideMember(order.getMember().getMemberId());
//
//       // 커피가 있는지 확인
//
//        for (OrderCoffee orderCoffee : order.getOrderCoffees()) {
//            coffeeService.findVerifiedCoffee(orderCoffee.getCoffee().getCoffeeId());
//        }
        return orderRepository.save(order);
    }

    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    public Page<Order> findOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("orderId").descending());
        return orderRepository.findAll(pageable);

//        dfd .by("orderId").descending()));
    }

    public void cancelOrder(long orderId) {
        Order findOrder = findVerifiedOrder(orderId);
        if (findOrder.getOrderStatus().getStepNumber() == 1) {
            findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
            orderRepository.save(findOrder);
        }
        throw new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
    }

    public Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Order findOrder = optionalOrder.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }
}
