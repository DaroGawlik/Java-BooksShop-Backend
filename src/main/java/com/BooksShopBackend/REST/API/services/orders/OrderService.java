package com.BooksShopBackend.REST.API.services.orders;


import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import com.BooksShopBackend.REST.API.models.dataBase.order.*;
import com.BooksShopBackend.REST.API.models.errors.OrderNotFoundError;
import com.BooksShopBackend.REST.API.models.errors.UserNotFoundError;
import com.BooksShopBackend.REST.API.models.orders.*;
import com.BooksShopBackend.REST.API.repositories.BookListRepository;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
//import com.BooksShopBackend.REST.API.repositories.orders.OrderBooksRepository;
import com.BooksShopBackend.REST.API.repositories.orders.OrderBooksRepository;
import com.BooksShopBackend.REST.API.repositories.orders.OrderDataRepository;
import com.BooksShopBackend.REST.API.repositories.orders.OrderDeliveryAddressRepository;
import com.BooksShopBackend.REST.API.repositories.orders.OrderRepository;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookListRepository bookListRepository;

    @Autowired
    private OrderBooksRepository orderBooksRepository;

    @Autowired
    private OrderDataRepository orderDataRepository;

    @Autowired
    private OrderDeliveryAddressRepository orderDeliveryAddressRepository;


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

            orderAdditional.setPaymentType((body.getPaymentType()));


            List<String> selectedGifts = body.getGifts();
            OrderGifts orderGifts = new OrderGifts();

            Map<String, String> giftColumnMapping = Map.of(
                    "Pack as a gift", "TRUE",
                    "Add postcard", "TRUE",
                    "Provide 2% discount to the next time", "TRUE",
                    "Branded pen or pencil", "TRUE"
            );

            for (String gift : selectedGifts) {
                if (giftColumnMapping.containsKey(gift)) {
                    switch (gift) {
                        case "Pack as a gift":
                            orderGifts.setGift1(giftColumnMapping.get(gift));
                            break;
                        case "Add postcard":
                            orderGifts.setGift2(giftColumnMapping.get(gift));
                            break;
                        case "Provide 2% discount to the next time":
                            orderGifts.setGift3(giftColumnMapping.get(gift));
                            break;
                        case "Branded pen or pencil":
                            orderGifts.setGift4(giftColumnMapping.get(gift));
                            break;
                        default:
                            break;
                    }
                }
            }
            List<OrderBooksDTO> selectedBooks = body.getBooks();
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            Order order = orderOptional.get();
            for (OrderBooksDTO bookInfo : selectedBooks) {
                String author = bookInfo.getAuthor();
                String title = bookInfo.getTitle();
                Integer amount = bookInfo.getAmount();

                Optional<BooksList> bookOptional = bookListRepository.findByAuthorAndTitle(author, title);

                if (bookOptional.isPresent()) {
                    BooksList foundBook = bookOptional.get();
                    OrderBooks orderBooks = new OrderBooks();
                    orderBooks.setOrder(order); // Przypisz obiekt Order do pola order
                    orderBooks.setBook(foundBook);
                    orderBooks.setAmount(amount);
                    orderBooksRepository.save(orderBooks); // Zapisz orderBooks w bazie danych
                } else {
                    return "Book with author " + author + " and title " + title + " not found";
                }
            }

            newOrder.setOrderData(orderData);
            orderData.setOrder(newOrder);

            newOrder.setOrderDeliveryAddress(orderDeliveryAddress);
            orderDeliveryAddress.setOrder(newOrder);

            newOrder.setOrderAdditional(orderAdditional);
            orderAdditional.setOrder(newOrder);

            newOrder.setOrderGifts(orderGifts);
            orderGifts.setOrder(newOrder);


            orderRepository.save(newOrder);

            return "Order accepted and registered under ID: " + orderId;
        }
        return "User with the provided userId does not exist";
    }

    @Transactional
    public List<OrderGetResponseDTO> OrderGet(Integer userId) throws ParseException {
        UserApplication user = getUserByUserId(userId);
        if (user == null) {
            throw new UserNotFoundError("User with the provided ID was not found.");
        }

        List<Order> userOrders = findByUserUserId(userId);
        if (userOrders.isEmpty()) {
            throw new OrderNotFoundError("No orders found for the user with the provided ID.");
        }

        List<OrderGetResponseDTO> responseDTOList = new ArrayList<>();

        for (Order order : userOrders) {
            OrderGetResponseDTO responseDTO = new OrderGetResponseDTO();

            OrderData orderData = orderDataRepository.findByOrderId(order.getOrderId());
            OrderDataDTO orderDataDTO = new OrderDataDTO();
            orderDataDTO.setName(orderData.getName());
            orderDataDTO.setSurname(orderData.getSurname());

            responseDTO.setOrderData(orderDataDTO);
            responseDTOList.add(responseDTO);
        }

        return responseDTOList;
    }
//    OrderDeliveryAddress orderDeliveryAddress = orderDeliveryAddressRepository.findByOrderId(order.getOrderId());
    private List<Order> findByUserUserId(Integer userId) {
        return orderRepository.findByUserUserId(userId);
    }

    private UserApplication getUserByUserId(Integer userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }
}
