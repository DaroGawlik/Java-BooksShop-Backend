package com.BooksShopBackend.REST.API.services.orders;


import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import com.BooksShopBackend.REST.API.models.dataBase.order.Order;
import com.BooksShopBackend.REST.API.models.dataBase.order.OrderAdditional;
import com.BooksShopBackend.REST.API.models.dataBase.order.OrderData;
import com.BooksShopBackend.REST.API.models.dataBase.order.OrderDeliveryAddress;
import com.BooksShopBackend.REST.API.models.orders.OrderDataDTO;
import com.BooksShopBackend.REST.API.models.orders.OrderDeliveryAddressDTO;
import com.BooksShopBackend.REST.API.models.orders.OrderPostDTO;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
import com.BooksShopBackend.REST.API.repositories.orders.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;


    public String OrderPost(Integer userId, OrderPostDTO body) throws ParseException {
        Optional<UserApplication> userByUserId = userRepository.findByUserId(userId);
        if (userByUserId.isPresent()) {
            UserApplication user = userByUserId.get();

            Order newOrder = new Order();
            newOrder.setUser(user);
            newOrder = orderRepository.save(newOrder);

            Integer orderId = newOrder.getOrderId();

            OrderDataDTO orderDataDTO = body.getOrderData();
            OrderData orderData = new OrderData();
            orderData.setOrderId(orderId);
            orderData.setName(orderDataDTO.getName());
            orderData.setSurname(orderDataDTO.getSurname());

            OrderDeliveryAddressDTO orderDeliveryAddressDTO = body.getDeliveryAddress();
            OrderDeliveryAddress orderDeliveryAddress = new OrderDeliveryAddress();
            orderDeliveryAddress.setOrderId(orderId);
            orderDeliveryAddress.setStreet(orderDeliveryAddressDTO.getStreet());
            orderDeliveryAddress.setFlatNumber(orderDeliveryAddressDTO.getFlatNumber());
            orderDeliveryAddress.setHouseNumber(orderDeliveryAddressDTO.getHouseNumber());


            OrderAdditional orderAdditional = new OrderAdditional();

            orderAdditional.setOrderId(orderId);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

            Instant deliveryInstant = Instant.from(formatter.parse(body.getDeliveryDate()));
            Instant orderInstant = Instant.from(formatter.parse(body.getOrderDate()));

            SimpleDateFormat customDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            String formattedDeliveryDate = customDateFormat.format(Date.from(deliveryInstant));
            String formattedOrderDate = customDateFormat.format(Date.from(orderInstant));

            Date deliveryDate = customDateFormat.parse(formattedDeliveryDate);
            Date orderDate = customDateFormat.parse(formattedOrderDate);

            orderAdditional.setDeliveryDate(deliveryDate);
            orderAdditional.setOrderDate(orderDate);


            orderAdditional.setAdditionalInformation(body.getAdditionalInformation());



            // Przypisanie obiektu OrderData do Order
            newOrder.setOrderData(orderData);
            orderData.setOrder(newOrder);

            // Przypisanie obiektu OrderDeliveryAddress do Order
            newOrder.setOrderDeliveryAddress(orderDeliveryAddress);
            orderDeliveryAddress.setOrder(newOrder);

            newOrder.setOrderAdditional(orderAdditional);
            orderAdditional.setOrder(newOrder);

            // Zapis obiektu Order, co spowoduje automatycznie zapis obiektów OrderData i OrderDeliveryAddress
            orderRepository.save(newOrder);

            return "Order accepted and registered under ID: " + orderId;
        }
        return "User with the provided userId does not exist";
    }

}
