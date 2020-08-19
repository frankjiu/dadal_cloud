/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年8月7日
 * @version: V1.0
 */

package com.controller.test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年8月7日
 */
public class Car {

    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);
        cars.forEach(Car::repair);
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);
    }

}
