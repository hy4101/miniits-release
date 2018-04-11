package com.miniits.base.utils;

import java.util.Random;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/4/6
 * @Time: 20:51
 * <p>
 * Description:
 * WWW.MINIITS.COM
 */
public class CommonUtil {

    private static final char[] A_z = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R'
            , 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm'
            , 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static String randomStr() {
        Random rd = new Random();
        String str = "";
        for (int i = 0; i <= 20; i++) {
            str += A_z[rd.nextInt(52)];
        }
        return str;
    }

}
