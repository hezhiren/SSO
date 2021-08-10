package cn.hzr0523.config;

import java.util.concurrent.TimeUnit;

/**
 * @author : hezr
 * @description :
 * @date : 2021/05/14
 **/
public enum CacheType {
    //BaseInfo 缓存24小时
    ACCESS_TOKEN(2,TimeUnit.HOURS),
    USER(2,TimeUnit.HOURS);

    private int expires;

    private TimeUnit unit;

    CacheType(int expires,TimeUnit unit) {
        this.expires = expires;
        this.unit = unit;
    }

    public int getExpires() {
        return expires;
    }

    public TimeUnit getUnit(){
        return unit;
    }
}
