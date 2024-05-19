package tech.hirsun.eslogistic.pojo.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DBPackRecord {
    Long id;
    Long packId;
    String message;
    Date createTime;
}
