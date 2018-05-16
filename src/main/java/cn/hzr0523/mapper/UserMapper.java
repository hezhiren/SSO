package cn.hzr0523.mapper;


import cn.hzr0523.entity.TbUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * hezhi
 * 2018/5/2 14:51
 */
@Mapper
public interface UserMapper extends BaseMapper<TbUser> {

    int saveUserInfo(TbUser user) ;

    TbUser getUserInfo(@Param("userName") String userName, @Param("password") String password) ;

}
