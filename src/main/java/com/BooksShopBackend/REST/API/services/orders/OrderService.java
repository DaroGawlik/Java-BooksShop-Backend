package com.BooksShopBackend.REST.API.services.orders;


import com.BooksShopBackend.REST.API.models.dataBase.BooksList;
import com.BooksShopBackend.REST.API.models.dataBase.UserApplication;
import com.BooksShopBackend.REST.API.models.dataBase.order.*;
import com.BooksShopBackend.REST.API.models.errors.OrderNotFoundError;
import com.BooksShopBackend.REST.API.models.errors.UserNotFoundError;
import com.BooksShopBackend.REST.API.models.ordersDTO.*;
import com.BooksShopBackend.REST.API.repositories.BookListRepository;
import com.BooksShopBackend.REST.API.repositories.UserRepository;
import com.BooksShopBackend.REST.API.repositories.orders.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.BiConsumer;

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

    @Autowired
    private OrderAdditionalRepository orderAdditionalRepository;

    @Autowired
    private OrderGiftsRepository orderGiftsRepository;

    public String OrderPost(Integer userId, OrderPostDTO body) throws ParseException {
        UserApplication user = getUserByUserId(userId);
        if (user == null) {
            return "User with the provided userId does not exist";
        }

        Order newOrder = createOrderForUser(user);

        Integer orderId = newOrder.getOrderId();

        OrderData orderData = createOrderData(orderId, body.getOrderData());
        OrderDeliveryAddress orderDeliveryAddress = createOrderDeliveryAddress(orderId, body.getDeliveryAddress());
        OrderAdditional orderAdditional = createOrderAdditional(orderId, body);
        OrderGifts orderGifts = createOrderGifts(orderId, body.getGifts());

        String createBooksResult = createOrderBooks(orderId, body.getBooks());
        if (!createBooksResult.isEmpty()) {
            return createBooksResult;
        }

        setOrderDetails(newOrder, orderData, orderDeliveryAddress, orderAdditional, orderGifts);
        orderRepository.save(newOrder);

        return "Order accepted and registered under ID: " + orderId;
    }

    private UserApplication getUserByUserId(Integer userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

    private Order createOrderForUser(UserApplication user) {
        Order newOrder = new Order();
        newOrder.setUser(user);
        return orderRepository.save(newOrder);
    }

    private OrderData createOrderData(Integer orderId, OrderDataDTO orderDataDTO) {
        OrderData orderData = new OrderData();
        orderData.setOrderId(orderId);
        orderData.setName(orderDataDTO.getName());
        orderData.setSurname(orderDataDTO.getSurname());
        return orderData;
    }

    private OrderDeliveryAddress createOrderDeliveryAddress(Integer orderId, OrderDeliveryAddressDTO deliveryAddressDTO) {
        OrderDeliveryAddress orderDeliveryAddress = new OrderDeliveryAddress();
        orderDeliveryAddress.setOrderId(orderId);
        orderDeliveryAddress.setStreet(deliveryAddressDTO.getStreet());
        orderDeliveryAddress.setFlatNumber(deliveryAddressDTO.getFlatNumber());
        orderDeliveryAddress.setHouseNumber(deliveryAddressDTO.getHouseNumber());
        return orderDeliveryAddress;
    }

    private OrderAdditional createOrderAdditional(Integer orderId, OrderPostDTO body) throws ParseException {
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
        orderAdditional.setPaymentType(body.getPaymentType());

        return orderAdditional;
    }

    private static OrderGifts createOrderGifts(Integer orderId, List<String> selectedGifts) {
        OrderGifts orderGifts = new OrderGifts();
        orderGifts.setOrderId(orderId);

        Map<String, BiConsumer<OrderGifts, String>> giftColumnActions = Map.of(
                "Pack as a gift", OrderGifts::setGift1,
                "Add postcard", OrderGifts::setGift2,
                "Provide 2% discount to the next time", OrderGifts::setGift3,
                "Branded pen or pencil", OrderGifts::setGift4
        );

        selectedGifts.forEach(gift -> {
            BiConsumer<OrderGifts, String> action = giftColumnActions.get(gift);
            if (action != null) {
                action.accept(orderGifts, "TRUE");
            }
        });

        return orderGifts;
    }


    private String createOrderBooks(Integer orderId, List<OrderBooksDTO> booksDTOList) {
        for (OrderBooksDTO bookInfo : booksDTOList) {
            String author = bookInfo.getAuthor();
            String title = bookInfo.getTitle();
            Integer amount = bookInfo.getAmount();

            Optional<BooksList> bookOptional = bookListRepository.findByAuthorAndTitle(author, title);

            if (bookOptional.isPresent()) {
                saveOrderBook(orderId, bookOptional.get(), amount);
            } else {
                return "Book with author " + author + " and title " + title + " not found";
            }
        }
        return "";
    }

    private void saveOrderBook(Integer orderId, BooksList foundBook, Integer amount) {
        OrderBooks orderBooks = new OrderBooks();
        orderBooks.setOrder(orderRepository.getOne(orderId));
        orderBooks.setBook(foundBook);
        orderBooks.setAmount(amount);
        orderBooksRepository.save(orderBooks);
    }

    private void setOrderDetails(Order newOrder, OrderData orderData, OrderDeliveryAddress orderDeliveryAddress,
                                 OrderAdditional orderAdditional, OrderGifts orderGifts) {
        newOrder.setOrderData(orderData);
        newOrder.setOrderDeliveryAddress(orderDeliveryAddress);
        newOrder.setOrderAdditional(orderAdditional);
        newOrder.setOrderGifts(orderGifts);
    }

    @Transactional
    public List<OrderGetResponseDTO> OrderGet(Integer userId) throws ParseException {
        UserApplication user = getUserByUserId(userId);
        validateUserExists(user);

        List<Order> userOrders = findOrdersByUserId(userId);
        validateOrdersExist(userOrders);

        return mapOrdersToResponseDTOs(userOrders);
    }
    private List<Order> findOrdersByUserId(Integer userId) {
        return orderRepository.findByUserUserId(userId);
    }

    private void validateUserExists(UserApplication user) {
        if (user == null) {
            throw new UserNotFoundError("User with the provided ID was not found.");
        }
    }

    private void validateOrdersExist(List<Order> userOrders) {
        if (userOrders.isEmpty()) {
            throw new OrderNotFoundError("No orders found for the user with the provided ID.");
        }
    }

    private List<OrderGetResponseDTO> mapOrdersToResponseDTOs(List<Order> userOrders) {
        List<OrderGetResponseDTO> responseDTOList = new ArrayList<>();

        for (Order order : userOrders) {
            OrderGetResponseDTO responseDTO = createOrderResponseDTO(order);
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }

    private OrderGetResponseDTO createOrderResponseDTO(Order order) {
        OrderGetResponseDTO responseDTO = new OrderGetResponseDTO();

        responseDTO.setOrderId(order.getOrderId());

        OrderDataDTO orderDataDTO = createOrderDataDTO(order);
        responseDTO.setOrderData(orderDataDTO);

        OrderDeliveryAddressDTO deliveryAddressDTO = createOrderDeliveryAddressDTO(order);
        responseDTO.setDeliveryAddress(deliveryAddressDTO);

        List<String> orderGiftsDTOList = getGiftsForOrder(order);
        responseDTO.setGifts(orderGiftsDTOList);

        OrderAdditional orderAdditional = orderAdditionalRepository.findByOrderId(order.getOrderId());
        responseDTO.setDeliveryDate(String.valueOf(orderAdditional.getDeliveryDate()));
        responseDTO.setOrderDate(String.valueOf(orderAdditional.getOrderDate()));
        responseDTO.setAdditionalInformation(orderAdditional.getAdditionalInformation());
        responseDTO.setPaymentType(orderAdditional.getPaymentType());

        List<OrderBooksDTO> orderBooksDTO = createOrderBooksDTO(order);
        responseDTO.setBooks(orderBooksDTO);

        return responseDTO;
    }

    private List<OrderBooksDTO> createOrderBooksDTO(Order order) {
        List<OrderBooksDTO> orderBooksDTOList = new ArrayList<>();

        List<OrderBooks> orderBooksList = orderBooksRepository.findByOrder(order);

        for (OrderBooks orderBooks : orderBooksList) {
            OrderBooksDTO orderBooksDTO = new OrderBooksDTO();
            orderBooksDTO.setAuthor(orderBooks.getBook().getAuthor());
            orderBooksDTO.setTitle(orderBooks.getBook().getTitle());
            orderBooksDTO.setAmount(orderBooks.getAmount());
            orderBooksDTOList.add(orderBooksDTO);
        }
        return orderBooksDTOList;
    }

    private OrderDataDTO createOrderDataDTO(Order order) {
        OrderData orderData = orderDataRepository.findByOrderId(order.getOrderId());
        OrderDataDTO orderDataDTO = new OrderDataDTO();
        orderDataDTO.setName(orderData.getName());
        orderDataDTO.setSurname(orderData.getSurname());
        return orderDataDTO;
    }

    private OrderDeliveryAddressDTO createOrderDeliveryAddressDTO(Order order) {
        OrderDeliveryAddress orderDeliveryAddress = orderDeliveryAddressRepository.findByOrderId(order.getOrderId());
        OrderDeliveryAddressDTO deliveryAddressDTO = new OrderDeliveryAddressDTO();
        deliveryAddressDTO.setStreet(orderDeliveryAddress.getStreet());
        deliveryAddressDTO.setHouseNumber(orderDeliveryAddress.getHouseNumber());
        deliveryAddressDTO.setFlatNumber(orderDeliveryAddress.getFlatNumber());
        return deliveryAddressDTO;
    }

    public List<String> getGiftsForOrder(Order order) {
        OrderGifts orderGifts = orderGiftsRepository.findByOrderId(order.getOrderId());
        List<String> orderGiftsDTOList = new ArrayList<>();

        Map<String, String> giftColumnMapping = Map.of(
                "gift1", "Pack as a gift",
                "gift2", "Add postcard",
                "gift3", "Provide 2% discount to the next time",
                "gift4", "Branded pen or pencil"
        );

        for (Map.Entry<String, String> entry : giftColumnMapping.entrySet()) {
            String columnName = entry.getKey();
            String giftName = entry.getValue();
            try {
                Field field = OrderGifts.class.getDeclaredField(columnName);
                field.setAccessible(true);
                String giftValue = (String) field.get(orderGifts);

                if ("TRUE".equals(giftValue)) {
                    orderGiftsDTOList.add(giftName);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return orderGiftsDTOList;
    }

    @Transactional
    public String DeleteOrder(int orderId) throws ParseException {
        Order order = getOrderByOrderId(orderId);
        if (order == null) {
            throw new UserNotFoundError("Order with the provided ID was not found.");
        }
        List<OrderBooks> orderBooks = orderBooksRepository.findByOrder(order);
        orderBooksRepository.deleteAll(orderBooks);
        orderRepository.deleteById(orderId);

        return "The order and their details have been successfully deleted.";
    }

    private Order getOrderByOrderId(Integer userId) {
        return orderRepository.findOrderByOrderId(userId).orElse(null);
    }
}
