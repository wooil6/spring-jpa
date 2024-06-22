package com.springboot.order.mapper;

import com.springboot.coffee.dto.CoffeeResponseDto;
import com.springboot.coffee.entity.Coffee;
import com.springboot.order.dto.OrderResponseDto;
import com.springboot.order.entity.Order;
import com.springboot.order.entity.OrderCoffee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T14:40:58+0900",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.10 (Amazon.com Inc.)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderResponseDto orderToOrderResponseDto(Order order, List<Coffee> coffees) {
        if ( order == null && coffees == null ) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        if ( order != null ) {
            orderResponseDto.setOrderId( order.getOrderId() );
            orderResponseDto.setOrderStatus( order.getOrderStatus() );
            orderResponseDto.setOrderCoffees( orderCoffeeListToCoffeeResponseDtoList( order.getOrderCoffees() ) );
        }

        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponseDto> list = new ArrayList<OrderResponseDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( orderToOrderResponseDto1( order ) );
        }

        return list;
    }

    protected CoffeeResponseDto orderCoffeeToCoffeeResponseDto(OrderCoffee orderCoffee) {
        if ( orderCoffee == null ) {
            return null;
        }

        long coffeeId = 0L;
        String korName = null;
        String engName = null;
        int price = 0;

        CoffeeResponseDto coffeeResponseDto = new CoffeeResponseDto( coffeeId, korName, engName, price );

        return coffeeResponseDto;
    }

    protected List<CoffeeResponseDto> orderCoffeeListToCoffeeResponseDtoList(List<OrderCoffee> list) {
        if ( list == null ) {
            return null;
        }

        List<CoffeeResponseDto> list1 = new ArrayList<CoffeeResponseDto>( list.size() );
        for ( OrderCoffee orderCoffee : list ) {
            list1.add( orderCoffeeToCoffeeResponseDto( orderCoffee ) );
        }

        return list1;
    }

    protected OrderResponseDto orderToOrderResponseDto1(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        orderResponseDto.setOrderId( order.getOrderId() );
        orderResponseDto.setOrderStatus( order.getOrderStatus() );
        orderResponseDto.setOrderCoffees( orderCoffeeListToCoffeeResponseDtoList( order.getOrderCoffees() ) );

        return orderResponseDto;
    }
}
