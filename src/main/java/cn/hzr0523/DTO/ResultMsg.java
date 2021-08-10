package cn.hzr0523.DTO;

import lombok.Data;

/**
 * @author : hezr
 * @description :
 * @date : 2021/05/30
 **/
@Data
public class ResultMsg {

    private String code;

    private String msg;

    private Object data;
}
