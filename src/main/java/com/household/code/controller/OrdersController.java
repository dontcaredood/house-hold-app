//package com.household.code.controller;
//
//import com.household.code.entity.Orders;
//import com.household.code.model.*;
//import com.household.code.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/we-shoot/order")
//public class OrdersController {
//    @Autowired
//    OrderService orderService;
//
//    @Autowired
//    SmsController smsController;
//
//    @CrossOrigin(origins = {GlobalConstants.LOCAL_URL, GlobalConstants.WEB_URL}, methods = {RequestMethod.POST,RequestMethod.OPTIONS})
//    @PostMapping("/addOrder")
//    public ResponseEntity<Long> addOrder(@RequestBody Order order) {
//        try {
//            Long result = orderService.addOrders(order);
//
//            final String message = ApplicationUtil.addOrderMessageBuild(result);
//            smsController.sendSMS(new SMSRequest("+917094724707", "Santhosh", message, result));
//
//            return new ResponseEntity<>(result, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @CrossOrigin(origins = {GlobalConstants.LOCAL_URL, GlobalConstants.WEB_URL})
//    @GetMapping("/getOrders")
//    public ResponseEntity<OrdersResponse> getAllOrders() {
//        try {
//            List<Orders> ordersList = orderService.getAllOrders();
//            long elements = ordersList.size();
//            OrdersResponse ordersResponse = new OrdersResponse(elements, ordersList);
//            return new ResponseEntity<>(ordersResponse, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @CrossOrigin(origins = {GlobalConstants.LOCAL_URL, GlobalConstants.WEB_URL})
//    @GetMapping("/getOrder/{id}")
//    public ResponseEntity<Orders> getOrderById(@PathVariable Long id) {
//        try {
//            Optional<Orders> order = orderService.getOrderById(id);
//            if (order.isPresent()) {
//                return new ResponseEntity<>(order.get(), HttpStatus.OK);
//            } else {
//                throw new NoSuchElementException("No order found under the id: " + id);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @CrossOrigin(origins = {GlobalConstants.LOCAL_URL, GlobalConstants.WEB_URL}, methods = {RequestMethod.POST,RequestMethod.OPTIONS})
//    @PutMapping("/updateOrder")
//    public ResponseEntity<Orders> updateOrder(@RequestBody Order updatedOrder) {
//        try {
//            Orders order = orderService.updateOrder(updatedOrder);
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @CrossOrigin(origins = {GlobalConstants.LOCAL_URL, GlobalConstants.WEB_URL})
//    @GetMapping("/trackOrder/{id}")
//    public ResponseEntity<TrackOrderResponse> getTrackOrder(@PathVariable long id) {
//        try {
//            TrackOrderResponse trackOrderDetails = orderService.getTrackOrderDetails(id);
//            return new ResponseEntity<>(trackOrderDetails, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @CrossOrigin(origins = {GlobalConstants.LOCAL_URL, GlobalConstants.WEB_URL}, methods = {RequestMethod.POST,RequestMethod.OPTIONS})
//    @PostMapping("/trackOrder")
//    public ResponseEntity<TrackOrderResponse> insertTrackOrder(@RequestBody TrackOrderRequest trackOrderRequest) {
//        try {
//            TrackOrderResponse trackOrderDetails = orderService.insertTrackOrderDetails(trackOrderRequest);
//            return new ResponseEntity<>(trackOrderDetails, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}
