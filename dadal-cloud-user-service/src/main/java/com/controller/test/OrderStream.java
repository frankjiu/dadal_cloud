/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller.test   
 * @author: Frankjiu
 * @date: 2020年8月26日
 * @version: V1.0
 */

package com.controller.test;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年8月26日
 */
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderStream {

    public static void main(String[] args) {
        Map<String, Long> carrierMap = new HashMap<>();
        carrierMap.put("MF", 0L);
        carrierMap.put("CZ", 4L);
        carrierMap.put("MU", 4L);

        List<Map.Entry<String, Long>> carrierValue = sortValues(carrierMap);

        List<Map.Entry<String, Long>> collect = carrierValue.stream().filter(m -> "CZ".equals(m.getKey())).collect(toList());
        if (collect.size() > 0) {
            carrierValue.remove(collect.get(0));
            //carrierValue.add(new MapEntry<>("CZ", 0L));
            Map<String, Long> map = new HashMap<String, Long>();
            map.put("CZ", 0L);
            Entry<String, Long> entryMap = map.entrySet().iterator().next();
            carrierValue.add(entryMap);
        }

        //倒序
        Map<String, Integer> carrierTemp = getReverseValue(carrierValue);

        System.out.println(carrierTemp);
    }

    private static Map<String, Integer> getReverseValue(List<? extends Map.Entry> valueMap) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < valueMap.size(); i++) {
            map.put(valueMap.get(i).getKey().toString(), valueMap.size() - i);
        }
        return map;
    }

    private static <T extends Comparable<? super T>> List<Map.Entry<String, T>> sortValues(Map<String, T> map) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(toList());
    }

}
