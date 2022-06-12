package com.naveen.crudapp.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.naveen.crudapp.repository.CustomerRepository;
import com.naveen.crudapp.service.CustomerServices;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class CacheExample {
    private static CustomerRepository repository;
    private static CustomerServices customerServices;

    private static LoadingCache<String, Map<String, String>> empCache;

    {
        empCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(
                        new CacheLoader<String, Map<String, String>>() {

                            @Override
                            public Map<String, String> load(final String str) throws Exception {
                                return customerData(str).get(str);
                            }
                        }
                );
    }

    public static LoadingCache<String, Map<String, String>> getLoadingCache() {
        return empCache;
    }

    private static Map<String, Map<String, String>> customerData(String name) {

        /*midDataMap.put("MidData 1", Arrays.asList("lastData 1", "lastData 2", "lastData 3"));
        midDataMap.put("MidData 2", Arrays.asList("lastData 1", "lastData 2", "lastData 3"));
        midDataMap.put("MidData 3", Arrays.asList("lastData 1", "lastData 2", "lastData 3"));
        finalMapResultSet.put("Final Data 1", midDataMap);
        finalMapResultSet.put("Final Data 2", midDataMap);
        finalMapResultSet.put("Final Data 3", midDataMap);*/
//        customerList = repository.findByFirstname("naveen");

        return customerServices.getEmpDetails(name);
    }

    public Map<String, String> get(String key) throws Exception {
        try {
            return empCache.get(key);
        } catch (Exception e) {
            throw new Exception("Invalid GCI Code - " + key);
        }
    }
}
