package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderDto {
    private String orderId;
    private String customerId;
    private String itemId;
    private String qty;
    private String date;
    private String payment;

}
