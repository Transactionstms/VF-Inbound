package com.onest.security;

public class BASE64 {

    private static char[] map1 = new char[64];
    private static byte[] map2;

    public static String encodeString(String s) {
        return new String(encode(s.getBytes()));
    }

    public static char[] encode(byte[] in) {
        return encode(in, in.length);
    }

    public static char[] encode(byte[] in, int iLen) {
        int oDataLen = (iLen * 4 + 2) / 3;
        int oLen = (iLen + 2) / 3 * 4;
        char[] out = new char[oLen];
        int ip = 0;
        int op = 0;
        while (ip < iLen) {
            int i0 = in[(ip++)] & 0xFF;
            int i1 = ip < iLen ? in[(ip++)] & 0xFF : 0;
            int i2 = ip < iLen ? in[(ip++)] & 0xFF : 0;
            int o0 = i0 >>> 2;
            int o1 = (i0 & 0x3) << 4 | i1 >>> 4;
            int o2 = (i1 & 0xF) << 2 | i2 >>> 6;
            int o3 = i2 & 0x3F;
            out[(op++)] = map1[o0];
            out[(op++)] = map1[o1];
            out[op] = (op < oDataLen ? map1[o2] : '=');
            op++;
            out[op] = (op < oDataLen ? map1[o3] : '=');
            op++;
        }
        return out;
    }

    public static String decodeString(String s) {
        /* 108 */ return new String(decode(s));
    }

    public static byte[] decode(String s) {
        /* 118 */ return decode(s.toCharArray());
    }

    public static byte[] decode(char[] in) {
        /* 128 */ int iLen = in.length;
        /* 129 */ if (iLen % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        /* 130 */ while ((iLen > 0) && (in[(iLen - 1)] == '=')) {
            iLen--;
        }
        /* 131 */ int oLen = iLen * 3 / 4;
        /* 132 */ byte[] out = new byte[oLen];
        /* 133 */ int ip = 0;
        /* 134 */ int op = 0;
        /* 135 */ while (ip < iLen) {
            /* 136 */ int i0 = in[(ip++)];
            /* 137 */ int i1 = in[(ip++)];
            /* 138 */ int i2 = ip < iLen ? in[(ip++)] : 65;
            /* 139 */ int i3 = ip < iLen ? in[(ip++)] : 65;
            /* 140 */ if ((i0 > 127) || (i1 > 127) || (i2 > 127) || (i3 > 127)) /* 141 */ {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            /* 142 */ int b0 = map2[i0];
            /* 143 */ int b1 = map2[i1];
            /* 144 */ int b2 = map2[i2];
            /* 145 */ int b3 = map2[i3];
            /* 146 */ if ((b0 < 0) || (b1 < 0) || (b2 < 0) || (b3 < 0)) /* 147 */ {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            /* 148 */ int o0 = b0 << 2 | b1 >>> 4;
            /* 149 */ int o1 = (b1 & 0xF) << 4 | b2 >>> 2;
            /* 150 */ int o2 = (b2 & 0x3) << 6 | b3;
            /* 151 */ out[(op++)] = (byte) o0;
            /* 152 */ if (op < oLen) {
                out[(op++)] = (byte) o1;
            }
            /* 153 */ if (op < oLen) {
                out[(op++)] = (byte) o2;
            }
        }
        /* 155 */ return out;
    }

    static {
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c = (char) (c + '\001')) {
            map1[(i++)] = c;
        }
        for (char c = 'a'; c <= 'z'; c = (char) (c + '\001')) {
            map1[(i++)] = c;
        }
        for (char c = '0'; c <= '9'; c = (char) (c + '\001')) {
            map1[(i++)] = c;
        }
        map1[(i++)] = '+';
        map1[(i++)] = '/';

        map2 = new byte[''];

        for (int ii = 0; ii < map2.length; ii++) {
            map2[ii] = -1;
        }
        for (int ij = 0; ij < 64; ij++) {
            map2[map1[ij]] = (byte) ij;
        }
    }
}