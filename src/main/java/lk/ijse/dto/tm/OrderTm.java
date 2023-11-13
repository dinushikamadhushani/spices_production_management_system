package lk.ijse.dto.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class OrderTm {
    private String orderID;
    private String customerId;
    private String productId;
    private String date;
    private String qty;
    private String payment;

}
