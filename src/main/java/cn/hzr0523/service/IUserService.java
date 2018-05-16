package cn.hzr0523.service;

import cn.hzr0523.DTO.UserDTO;
import cn.hzr0523.VO.ResultObject;

import java.io.UnsupportedEncodingException;

/**
 * hezhi
 * 2018/5/2 14:00
 */
public interface IUserService {
    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    ResultObject userRegister(UserDTO userDTO) ;

    /**
     * 用户登录
     * @param userDTO
     * @return
     */
    ResultObject userLogin(UserDTO userDTO);
}
