package com.onest.io;

import com.onest.misc.Utils;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Files {

    private static FileWriter fputs;
    private static RandomAccessFile fgets;
    private static byte[] sought;
    private static byte[] replacement;
    private static String fileName;
    private static int lineIndex;
    private static String[] lines;
    private static String dir;
    private static int numberOfLines;
    private static Lock fileLock = new ReentrantLock();
    public static final String LOG_DIR = System.getProperty("user.dir") + File.separator + "logs" + File.separator;
    public static final String CONF_DIR = System.getProperty("user.dir") + File.separator + "conf" + File.separator;
    public static final String PRINT_DIR = System.getProperty("user.dir") + File.separator + "print" + File.separator;
    public static final String USER_DIR = System.getProperty("user.dir") + File.separator;
    public static final String FILE_SEP = File.separator;

    public static synchronized String write2File(String what2write, String name) {
        setFileName(name);
        try {
            try {
                fileLock.lock();
                fputs = new FileWriter(fileName, true);
                fputs.write(what2write);
                fputs.close();
            } finally {
                fileLock.unlock();
            }
            return "";
        } catch (IOException err) {
            System.out.println(err.toString());
            return err.toString();
        }
    }

    public static synchronized void readFromFile(String name) {
        setFileName(name);
        lineIndex = 0;
        String[] tmpLines = new String[1];
        try {
            String tmpLine = "";
            LineNumberReader localFgets = new LineNumberReader(new FileReader(fileName));
            while ((tmpLine = localFgets.readLine()) != null) {
                tmpLines[lineIndex] = tmpLine;
                lineIndex += 1;
                if (tmpLines.length == lineIndex) {
                    tmpLines = (String[]) (String[]) Utils.resizeArray(tmpLines, tmpLines.length + 1);
                }
            }
            localFgets.close();
            lines = new String[lineIndex];
            lines = tmpLines;
            numberOfLines = lineIndex;
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    private static boolean matches(MappedByteBuffer bb, int pos) {
        for (int j = 0; j < sought.length; j++) {
            if (sought[j] != bb.get(pos + j)) {
                return false;
            }
        }
        return true;
    }

    private static void replace(MappedByteBuffer bb, int pos) {
        int length = sought.length > replacement.length ? sought.length : replacement.length;
        for (int j = 0; j < length; j++) {
            byte b = j < replacement.length ? replacement[j] : 32;
            if (pos + j == bb.limit() - 1) {
                b = 10;
            }
            bb.put(pos + j, b);
        }
    }

    private static void searchAndReplace(MappedByteBuffer bb, int sz) {
        int replacementsCount = 0;
        for (int pos = 0; pos <= sz - sought.length; pos++) {
            if (matches(bb, pos)) {
                replace(bb, pos);
                pos += sought.length - 1;
                replacementsCount++;
            }
        }
        /* 100 */ System.out.println("" + replacementsCount + " replacements done.");
    }

    private static boolean findAndReplace(String replaceWithThis, String what2search, String file4search) {
        /* 103 */ byte[] buff = new byte[100];
        /* 104 */ String tmpStr = "";

        /* 106 */ long lastPos = 0L;
        try {
            /* 109 */ fgets = new RandomAccessFile(file4search, "rw");
            /* 110 */ FileChannel fchannel = fgets.getChannel();

            /* 112 */ int sz = (int) fchannel.size();
            /* 113 */ while ((tmpStr = fgets.readLine()) != null) {
                /* 114 */ if (tmpStr.indexOf(what2search) != -1) {
                    /* 115 */ sought = tmpStr.getBytes();
                    /* 116 */ replacement = replaceWithThis.getBytes();
                    /* 117 */ sz += (sought.length < replacement.length ? replacement.length - sought.length : 0);

                    /* 119 */ MappedByteBuffer bb = fchannel.map(FileChannel.MapMode.READ_WRITE, 0L, sz);
                    /* 120 */ searchAndReplace(bb, sz);

                    /* 122 */ bb.force();

                    /* 124 */ fgets.close();
                    /* 125 */ return true;
                }
            }
            /* 128 */ fgets.close();
            /* 129 */ return false;
        } catch (IOException err) {
            /* 131 */ System.out.println(err.toString());
            /* 132 */        }
        return false;
    }

    private static void setFileName(String name) {
        /* 136 */ File f = new File(dir);
        /* 137 */ if (!f.exists()) {
            /* 138 */ f.mkdirs();
        }
        /* 140 */ fileName = name;
    }

    public static void createDir(String dir) {
        /* 143 */ File f = new File(dir);
        /* 144 */ if (!f.exists()) /* 145 */ {
            f.mkdirs();
        }
    }

    public static void setDir(String directorio) {
        /* 149 */ dir = directorio;
    }

    public static String[] getReadedLines() {
        /* 152 */ return lines;
    }

    public static int getNumberOfLinesReaded() {
        /* 155 */ return numberOfLines;
    }

    public static String getFileName() {
        /* 158 */ return fileName;
    }

    public static boolean deleteFile(String name) {
        /* 161 */ return new File(name).delete();
    }

    public static String getSystemSep() {
        /* 164 */ return File.separator;
    }

    public static boolean exist(String file) {
        /* 167 */ File f = new File(file);
        /* 168 */ return f.exists();
    }
}

/* Location:           C:\Users\OS7-PRO\Desktop\onest-common-utils-0.0.1.jar
 * Qualified Name:     com.onest.io.Files
 * JD-Core Version:    0.6.0
 */