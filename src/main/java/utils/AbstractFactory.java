package utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AbstractFactory <T> {
    private static Logger logger = LogManager.getLogger(AbstractFactory.class);
    private void init (Properties props){
        for (String propertyName : props.stringPropertyNames()){
            try {
                System.out.println(propertyName+" ");
                Class c = Class.forName(props.getProperty(propertyName));
                classMap.put(propertyName, c);
            } catch (ClassNotFoundException e) {
                logger.warn("unregistered class for property {}", propertyName);
            }
        }
    }

    public AbstractFactory(String config) throws IOException {
        Properties properties = new Properties();
        properties.load(AbstractFactory.class.getResourceAsStream(config));
        init(properties);
    }
    @Nullable
    public T get(String key){
        try {
            return classMap.get(key).getConstructor().newInstance();
        } catch (Exception e) {
            logger.warn("trying get unregistered class for property {}", key);
        }
        return null;
    }

    private Map<String, Class<? extends T>> classMap = new HashMap<>();

}
