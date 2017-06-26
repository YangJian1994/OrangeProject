package com.example.orangeproject.bean;

import com.example.orangeproject.utils.MessageUtil;

/**
 * Created by 杨健 on 2017/6/26.
 * function: 发送的数据类
 */

public class Message {

    public static int top = 0XA534785A;
    public static int order = 0X56079747;
    public static int seq = 0XFFFFFFFE;
    public static int sum = 0X0000FFFE;

    public static byte[] sendStateBytes() {
        byte[] byte_0 = new byte[]{};
        byte[] byte_1 = MessageUtil.byteMerger(MessageUtil.intToBytes(top), MessageUtil.intToBytes(order));
        byte[] byte_2 = MessageUtil.byteMerger(MessageUtil.intToBytes(seq), MessageUtil.intToBytes(0));
        byte[] byte_3 = MessageUtil.byteMerger(byte_0, MessageUtil.intToBytes(sum));

        return MessageUtil.byteMerger(MessageUtil.byteMerger(byte_1, byte_2), byte_3);
    }
}
