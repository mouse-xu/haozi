package cn.xm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 1、将加载JDBC驱动级获取连接所需参数放在专门的配置文件中
 * 方便数据库类型变动，只需改变配置文件参数，无需改变java程序
 * 2、此类使用了单例模式，单例模式的实现步骤：
 *      1)、把构造方法私有化
 *      2)、程序提供给别人唯一对象(就是通过调用本类的静态方法getInstance()
 *          时，判断是否已经存在对象，没有则调用自身私有化的构造方法，new一个
 *          新的对象，有则不new)
 * 3、单例模式又分2种实现方式：
 *      1)、恶汉方式：
 *              属性声明时直接new一个对象：private static ConfigManager configManager;
 *              下方方法中不new，直接返回一个configManager
 *      2)、懒汉方式：此类使用的就是此方式(但此方式线程不安全，下方有转为线程安全方法)
 */
public class ConfigManager {
    private static ConfigManager configManager;
    //properties.load(InputStream);读取属性文件
    private static Properties properties;

    private ConfigManager(){
        String configFile="database.properties";
        properties=new Properties();
        //getClassLoader()是获得ConfigManager.class类的类加载器，再通过加载器把资源文件读成文件流
        InputStream in=ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(in);
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //提供给别人一个唯一的ConfigManager对象，但是是线程不安全的，可以主动加上
    //synchronized在static之后，转为线程安全的
    public static ConfigManager getInstance(){
        if(configManager==null){
            configManager=new ConfigManager();
        }
        return configManager;
    }

    public String getString(String key){
        return properties.getProperty(key);
    }
}