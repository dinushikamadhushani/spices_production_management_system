package lk.ijse.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemtDto {
    private String id;
    private String name;
    private double unitPrice;
    private int qtyOnHand;
}
