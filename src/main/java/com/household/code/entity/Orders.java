package com.household.code.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Transactional
public class Orders {
//add isClose and isDelivered
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @SequenceGenerator(name = "orders_sequence", sequenceName = "orders_sequence", initialValue= 100, allocationSize = 1)
    private long orderId;
    @Column(name = "order_amount")
    private Double orderAmount;
    @Column(name = "event_date")
    private Date eventDate;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "event_location")
    private String eventLocation;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_phone")
    private Long customerPhone;
    @Column(name = "customer_email")
    private String customerEmail;
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Column(name = "remaining_balance")
    private Double remainingBalance;
    @Column(name = "order_type")
    private String orderType;
	@Column(name = "description")
	private String orderDescription;
    @Column(name = "isClosed")
    private boolean isClosed;
    @Column(name = "isDelivered")
    private boolean isDelivered;

    @Override
    public String toString() {
        return "Orders{" +
                "orderAmount=" + orderAmount +
                ", eventDate=" + eventDate +
                ", eventLocation='" + eventLocation + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhone=" + customerPhone +
                ", customerEmail='" + customerEmail + '\'' +
                ", amountPaid=" + amountPaid +
                ", remainingBalance=" + remainingBalance +
                ", orderType='" + orderType + '\'' +
                ", orderDescription='" + orderDescription + '\'' +
                '}';
    }
//    @OneToMany(mappedBy="orders")
//    private Set<OrderHistory> ordersHistory;
}
