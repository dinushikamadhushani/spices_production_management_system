package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MaterialCartTm {
    private String materialId;
    private String materialName;
    private int qty;
    private double unitPrice;
    private double tot;
    private Button btn;

}