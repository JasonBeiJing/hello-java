package com.hello.java.jdk8.optional;

import java.io.Serializable;
import java.util.Optional;

public class TryOptional2 {

    //【Optional是一个final类，未实现任何接口，所以不能被序列化】
    public static void main(String[] args) {
        User user = new User("wangshujie", new Address("262112"));

        Optional<User> op = Optional.of(user);
        
        //orElse + orElseGet
        System.err.println(op.orElse(new User("wangshanshan", new Address("110119"))));
        System.err.println(Optional.ofNullable(null).orElse(new User("wangshanshan", new Address("110119"))));
        System.err.println(op.orElseGet(() -> new User("zhangsan", new Address("112114"))));
        System.err.println(Optional.ofNullable(null).orElseGet(() -> new User("lisi", new Address("115116"))));
        
        //filter
        System.out.println(op.filter(r -> r.getName().equals("wangshujie")).orElse(new User("zhangsan", new Address("000000"))));
        System.out.println(op.filter(r -> r.getName().equals("xxxxxxxxxx")).orElse(new User("lisi", new Address("000000"))));
        op.filter(r -> r.getName().equals("wangshujie")).ifPresent(System.out::println);
        
        //map:直接返回T
        System.err.println(op.map(User::getAddress).orElse(new Address("000000")));
        System.err.println(op.map(User::getAddress).map(Address::getPostcode).map(String::toUpperCase).orElse("000000"));
        System.err.println(Optional.of(new User("wangshujie", null)).map(User::getAddress).map(Address::getPostcode).map(String::toUpperCase).orElse("000000"));
        
        //flatmap:返回Optional<T>
        
        
    }

    static class User implements Serializable{
        private static final long serialVersionUID = 1L;
        private String name;
        private Address address;
        public User() {
            super();
        }
        public User(
            String name, Address address) {
            super();
            this.name = name;
            this.address = address;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Address getAddress() {
            return address;
        }
        public void setAddress(Address address) {
            this.address = address;
        }
        @Override
        public String toString() {
            return "User [name=" + name + ", address=" + address + "]";
        }
    }
    
    static class Address implements Serializable{
        private String postcode;
        public Address(
            String postcode) {
            super();
            this.postcode = postcode;
        }
        public String getPostcode() {
            return postcode;
        }
        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }
        @Override
        public String toString() {
            return "Address [postcode=" + postcode + "]";
        }
    }
}
