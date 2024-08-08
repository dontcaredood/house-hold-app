//package com.household.code.service;
//
//import com.household.code.entity.Orders;
//import com.household.code.repository.OrderRepository;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@Service
//@Log4j2
//public class OrderService {
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Autowired
//    AuditHistoryService auditHistoryService;
//
//    public Long addOrders(Order order) {
//        long orderId = orderRepository.save(mapOrderToEntity(order)).getOrderId();
//        auditHistoryService.addOrderHistory(orderId, order.toString());
//        return orderId;
//
//    }
//
//    public List<Orders> getAllOrders() {
//        return orderRepository.findAll();
//    }
//
//    public Optional<Orders> getOrderById(Long id) {
//        return orderRepository.findById(id);
//    }
//
//    public Optional<Orders> updateDeliveryStatus(Orders orders) {
//        Orders updatedOrder = orderRepository.save(orders);
//        //auditHistoryService.addOrderHistory(orders.getOrderId(), orders.toString());
//        return Optional.of(updatedOrder);
//    }
//
//    public Orders updateOrder(Order updatedOrder) {
//        return orderRepository.findById(updatedOrder.getOrderId())
//                .map(order -> {
//                    order = mapOrderToEntity(updatedOrder);
//                    order.setOrderId(updatedOrder.getOrderId());
//                    Orders save = orderRepository.save(order);
//                    auditHistoryService.updateOrderHistory(save);
//                    return save;
//                })
//                .orElseThrow(() -> new NoSuchElementException("Order not found with id " + updatedOrder.getOrderId()));
//    }
//
//    public TrackOrderResponse getTrackOrderDetails(long id) {
//        Optional<Orders> orderById = getOrderById(id);
//        if (orderById.isPresent()) {
//            return mapOrderToTrackOrderResponse(orderById.get());
//        }
//        return TrackOrderResponse.builder().build();
//    }
//    public TrackOrderResponse insertTrackOrderDetails(TrackOrderRequest trackOrderRequest) {
//        TrackOrderResponse trackOrderResponse = TrackOrderResponse.builder().build();
//        Optional<Orders> order = getOrderById(trackOrderRequest.getOrderId());
//        if(order.isPresent()){
//            Orders updateOrder = order.get();
//            Double total= updateOrder.getOrderAmount();
//            Double newAmountPaid = order.get().getAmountPaid() + trackOrderRequest.getAmount();
//            updateOrder.setAmountPaid(newAmountPaid);
//            updateOrder.setClosed(trackOrderRequest.getOrderStatus().equalsIgnoreCase("Completed"));
//            updateOrder.setDelivered(trackOrderRequest.getDeliveryStatus().equalsIgnoreCase("Completed"));
//            updateOrder.setRemainingBalance(total-newAmountPaid);
//            orderRepository.save(order.get());
//            auditHistoryService.updateTrackOrderHistory(trackOrderRequest);
//            trackOrderResponse = mapOrderToTrackOrderResponse(updateOrder);
//        }
//        return trackOrderResponse;
//    }
//    private TrackOrderResponse mapOrderToTrackOrderResponse(Orders order) {
//        return TrackOrderResponse.builder()
//                .orderId(order.getOrderId())
//                .orderAmount(order.getOrderAmount())
//                .amountPaid(order.getAmountPaid())
//                .orderStatus(order.isClosed() ? "Completed" : "InProgress")
//                .balanceAmount(order.getRemainingBalance())
//                .deliveryStatus(order.isDelivered() ? "Completed" : "InProgress")
//                .build();
//    }
//
//    private Orders mapOrderToEntity(Order order) {
//        //add validation
//
//        Double remainingAmount = order.getOrderAmount() - order.getAmountPaid();
//        if (remainingAmount < 0) {
//            throw new IllegalArgumentException("Invalid Amount paid value");
//        }
//        log.info("Order Amount : {}, Amount Paid in Advance: {}, Remaining Balance: {}", order.getOrderAmount(), order.getAmountPaid(), remainingAmount);
//        Orders orders = Orders.builder()
//                .orderDate(new Date())
//                .amountPaid(order.getAmountPaid())
//                .orderDescription(order.getOrderDescription())
//                .orderAmount(order.getOrderAmount())
//                .remainingBalance(remainingAmount)
//                .orderType(order.getOrderType())
//                .customerName(order.getCustomerName())
//                .customerEmail(order.getCustomerEmail())
//                .customerPhone(order.getCustomerPhone())
//                .eventLocation(order.getEventLocation())
//                .eventDate(order.getEventDate())
//                .isClosed(false)
//                .isDelivered(false)
//                .build();
//        return orders;
//    }
//
//
//}
