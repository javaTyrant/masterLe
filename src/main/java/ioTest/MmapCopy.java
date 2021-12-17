package ioTest;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lufengxiang
 * @since 2021/12/1
 **/
public class MmapCopy {
    public static void main(String[] args) {
        RandomAccessFile f;
        try {
            //new一个随机访问的文件
            f = new RandomAccessFile("D:\\code/hello.txt", "rw");
            //要复制到的文件
            RandomAccessFile world = new RandomAccessFile("D:\\code/world.txt", "rw");
            //获取f的channel.
            FileChannel fc = f.getChannel();
            //映射.
            MappedByteBuffer buf = fc.map(FileChannel.MapMode.READ_WRITE, 0, 20);
            //获取目标的channel
            FileChannel worldChannel = world.getChannel();
            //映射
            MappedByteBuffer worldBuf = worldChannel.map(FileChannel.MapMode.READ_WRITE, 0, 20);
            //把buf放入目标
            worldBuf.put(buf);
            //全部close掉.
            fc.close();
            f.close();
            world.close();
            worldChannel.close();
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}
