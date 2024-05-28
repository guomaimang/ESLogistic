package tech.hirsun.eslogistic.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackRecord {
    Long id;
    Long packId;
    String message;
    Date createTime;
}
