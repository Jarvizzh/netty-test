package com.jarvis.pb;

import com.google.protobuf.InvalidProtocolBufferException;
import com.jarvis.netty.protobuf_6.PbInfo;

/**
 * @author jarvis 2019/3/30
 */
public class PbTest {

    public static void main(String[] args) {
//        DataInfo.Student student = DataInfo.Student
//                .newBuilder()
//                .setName("jarvis")
//                .setAddress("广州")
//                .setAge(22)
//                .build();
//
//        byte[] stud2ByteArr = student.toByteArray();
//        try {
//            DataInfo.Student stud = DataInfo.Student.parseFrom(stud2ByteArr);
//            System.out.println(stud);
//            System.out.println(stud.getAddress());
//        } catch (InvalidProtocolBufferException e) {
//            e.printStackTrace();
//        }

        PbInfo.Person student = PbInfo.Person
                .newBuilder()
                .setName("jarvis")
                .setAge(22)
                .addArr("0")
                .addArr("1")
                .build();

        byte[] stud2ByteArr = student.toByteArray();
        try {
            PbInfo.Person stud = PbInfo.Person.parseFrom(stud2ByteArr);
            System.out.println(stud);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }
}
