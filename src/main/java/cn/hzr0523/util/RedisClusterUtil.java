package cn.hzr0523.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisClusterUtil  {
    private static final Logger logger = org.apache.log4j.Logger.getLogger(RedisClusterUtil.class);


    @Autowired
    private static StringRedisTemplate redisTemplate;

    public  void setRedisTemplate(StringRedisTemplate redisTemplate) {

        this.redisTemplate = redisTemplate;
//        setRedisTemplate2(redisTemplate);

    }
    public static void setRedisTemplate2(StringRedisTemplate redisTemplate) {

        redisTemplate = redisTemplate;
    }

    /**
     * 发布消息
     * @param channel 消息信道
     * @param message 消息内容
     */
    public static void sendMessage(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }

    public static void testSendMq()
    {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
                "applicationContext.xml"
        });

    }

    public static void main(String[] args) {
        String message1 = "1530158633";

        for(int i=0;i<10;i++)
        {
            String message=message1+i;
            int lastNo = new Integer(message.substring(message.length()-1,message.length()));
            System.out.println(message+"    "+lastNo%3);
        }

    }
    /**
     *  以map形式存储所有字段
     * @param key
     * @param objMap
     */
    public static boolean setMapAllField( String key,Map<String ,String> objMap) {
        try {
            if(!StringUtils.isBlank(key)){
                redisTemplate.opsForHash().putAll(key, objMap);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     *  存储和修改map中的某个字段
     * @param key
     * @param fieldName
     * @param fieldValue
     */
    public static boolean setMapField( String key,String fieldName,String fieldValue) {
        try {
            if(!StringUtils.isBlank(key)){
                redisTemplate.opsForHash().put(key, fieldName, fieldValue);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 根据key获取整个map
     * @param key
     * @return
     */
    public static Map<Object, Object> getMapAllField(String key) {
        Map<Object, Object> objMap = new HashMap<>();
        try {
            if(!StringUtils.isBlank(key)){
                objMap = redisTemplate.opsForHash().entries(key);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return objMap;
    }

    /**
     * 根据key和字段名获取map中的某一个字段
     * @param key
     * @param fieldName
     * @return
     */
    public static String getMapField( String key,String fieldName) {
        String result = "";
        try {
            if(!StringUtils.isBlank(key)){
                result = redisTemplate.opsForHash().get(key, fieldName) + "";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return result;
    }
    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public static boolean expire(String key,long time){
        try {
            if(time>0){
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public static long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public static boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public static void del(String ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * HashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public static Object hget(String key,String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public static Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public static boolean hmset(String key, Map<String,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public static boolean hmset(String key, Map<String,Object> map, long time){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }
    /**
     * HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @return true成功 false失败
     */
    public static boolean hmsetString(String key, Map<String,String> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
            return false;
        }
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     */
    public static boolean hset(String key,String item,Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
           logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public static boolean hset(String key,String item,Object value,long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if(time>0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public static void hdel(String key, Object... item){
        redisTemplate.opsForHash().delete(key,item);
    }

    /**
     * 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public static boolean hHasKey(String key, String item){
        return redisTemplate.opsForHash().hasKey(key, item);
    }




    // 批量插入数据到Redis，使用Pipeline
    public static void batchSetUsePipeline(final List<Map<String, String>> list, String prefix) throws IOException {
        logger.info("======开始批量插入数据到Redis======");
        logger.info("======开始批量插入数据到Redis3333======"+redisTemplate.toString());
        try {
            redisTemplate.executePipelined(new RedisCallback<Object>() {

                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisConnection conn = (StringRedisConnection) connection;
                    logger.info("redis open pipeline start  ");
                    conn.openPipeline();
                    logger.info("redis open pipeline end ");
                    int i = 0;
                    for (Map<String, String> map : list) {
                        i++;
//                    System.out.println(map.get("KEYID")+"start"+i+" ===== "+map.toString());
                        logger.info(map.get("KEYID") + "start" + i + " ===== " + map.toString());
                        conn.hMSet((prefix + map.get("KEYID")), map);
                        logger.info(map.get("KEYID") + "end" + i + "  ===== " + map.toString());

//                    System.out.println(map.get("KEYID")+"end"+i+"  ===== "+map.toString());
                    }
                    return null;
                }
            });
        }catch (Exception e)
        {
            logger.error("批量插入数据到Redis报错",e);
        }
        logger.info("======批量插入数据到Redis结束======");

    }
    // 批量从Redis中删除数据，使用Pipeline
    public static void batchDelUsePipeline(List<Map<String, String>> list, String prefix) throws IOException {
        List a = redisTemplate.executePipelined(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection conn=(StringRedisConnection)connection;
                connection.openPipeline();
                String[] str=new String[list.size()];
                for (int i=0;i<list.size();i++) {
                    Map<String,String> mapc=list.get(i);
                    str[i]=prefix + mapc.get("KEYID");
                }
                conn.del(str);
                return null;
            }
        });
    }

    // 批量从Redis中获取数据，使用Pipeline
    public static List<Map<String, String>> batchGetUsePipeline(String prefix) throws IOException {
        long start = System.currentTimeMillis();
        logger.info("======3 开始批量获取数据======");
        List<String> keyList = getAllRedisKey(prefix);
        /**
        List<String> keyList = new ArrayList<>();
        keyList.add("t_101234567890");
        keyList.add("t_101234567898");
        keyList.add("t_101234567897");
        keyList.add("t_101234567899");**/
        logger.info("======3 开始根据获取的Key值,批量获取redis中的value======");
        List<Map<String, String>> listMap = new ArrayList<>();
        List<Object> result = redisTemplate.executePipelined(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection conn=(StringRedisConnection)connection;
                connection.openPipeline();
                for (String key : keyList) {
                    //conn.get(key);
                    conn.hGetAll(key);
                }
//                connection.closePipeline();
                return null;
            }
        });
        long end1 = System.currentTimeMillis();
        logger.info("======3 批量获取redis中的value结束======耗时"+(end1-start)/1000+"秒");
        for(int i=0;i<result.size();i++){
            if(result.get(i)!=null){
           Map<String, String> response=(Map<String, String>)result.get(i);
            listMap.add(response);}
        }
//        for (Response<Map<String, String>> response : listResponse) {
//            listMap.add(response.get());
//        }
        return listMap;
    }


    // 批量从Redis中获取数据，使用Pipeline
    public static List<Map<String, String>> batchGetUsePipelineByKeys(List<String> keyList,String prefix) throws IOException {
        long start = System.currentTimeMillis();
        logger.info("======5 开始批量获取redis中的value======");
        List<Map<String, String>> listMap = new ArrayList<>();
        List<Object> result = redisTemplate.executePipelined(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                StringRedisConnection conn=(StringRedisConnection)connection;
                connection.openPipeline();
                for (String key : keyList) {
                    //conn.get(key);
                    conn.hGetAll(prefix+key);
                }
//                connection.closePipeline();
                return null;
            }
        });
        long end1 = System.currentTimeMillis();
        logger.info("======5 批量获取redis中的value结束======耗时"+(end1-start)/1000+"秒");
        for(int i=0;i<result.size();i++){
            if(result.get(i)!=null){
                if(!((Map<String, String>)result.get(i)).isEmpty()){
                Map<String, String> response=(Map<String, String>)result.get(i);
                listMap.add(response);}}
        }
        return listMap;
    }


    private static List<String> getAllRedisKey(String prefix) {
        String uuid = null;
        logger.info("======3  开始批量遍历redis中的key======"+uuid);
        List<String> list = new ArrayList<>();
        //根据前缀获取匹配的KEY,用于接下来批量获取value
        try {
        RedisConnection redisConnection = redisTemplate.getConnectionFactory().getConnection();
        logger.info("======3  获取到redis连接======"+uuid);

        ScanOptions allKey = ScanOptions.scanOptions().match(prefix + "*").count(10000).build();
        logger.info("======3  创建Scan======"+uuid);

        Cursor<byte[]> c = redisConnection.scan(allKey);
        logger.info("======3  创建Cursor======"+uuid);

        while (c.hasNext()) {
           list.add(new String(c.next()));
        }
        logger.info("======3  获取到" + list.size() + "条数据======"+uuid);

            c.close();
            redisConnection.close();
        } catch (Exception e) {
            logger.error("======3  ERROR 关闭scan出错======"+uuid,e);
        }
        logger.info("======3  批量遍历redis中的key结束======"+uuid);
        return  list;


    }
}