package cn.hzr0523.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO,(Data Transfer Object) 数据传输对象，主要用于表现层，封装业务需要的属性
 * hezhi
 * 2018/5/3 15:14
 */
@Getter
@Setter
public class UserDTO {
    private String email;

    private String userName;

    private String password;
}
