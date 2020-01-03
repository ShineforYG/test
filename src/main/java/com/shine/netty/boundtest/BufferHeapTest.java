package com.shine.netty.boundtest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import java.nio.charset.Charset;

public class BufferHeapTest {
    final static Charset UTF_8 = Charset.forName("UTF-8");

    @Test
    public void testHeapBuffer() {
        ByteBuf buf = ByteBufAllocator.DEFAULT.heapBuffer(); // buffer默认为直接内存buffer
        buf.writeBytes("JVM中的内存".getBytes(UTF_8));
        if (buf.hasArray()) {
            byte[] array = buf.array();
            int offset = buf.arrayOffset() + buf.readerIndex();
            int length = buf.readableBytes();
            System.out.println(new String(array, offset, length, UTF_8));
        }
        buf.release();
    }

    @Test
    public void testDirectBuffer() {
        ByteBuf directBuf = ByteBufAllocator.DEFAULT.directBuffer();
        directBuf.writeBytes("系统内存".getBytes(UTF_8));
        if (!directBuf.hasArray()) {
            int length = directBuf.readableBytes();
            byte[] array = new byte[length]; // 将数据读取到堆内存中
            directBuf.getBytes(directBuf.readerIndex(), array);
            System.out.println(new String(array, UTF_8));
        }
        directBuf.release();
    }

    @Test
    public void CompositeBufferTest() {
        CompositeByteBuf bufs = ByteBufAllocator.DEFAULT.compositeBuffer();
        ByteBuf header = Unpooled.copiedBuffer("header".getBytes(UTF_8));
        ByteBuf body = Unpooled.copiedBuffer("body".getBytes(UTF_8));

        bufs.addComponents(header, body);
        sendMsg(bufs);
        header.retain();
        bufs.release();

        bufs = ByteBufAllocator.DEFAULT.compositeBuffer();
        body = Unpooled.copiedBuffer("new body".getBytes(UTF_8));
        bufs.addComponents(header, body);
        sendMsg(bufs);
        bufs.release();
    }

    private void sendMsg(CompositeByteBuf bufs) {
        for (ByteBuf b : bufs) {
            int length = b.readableBytes();
            byte[] array = new byte[length];
            b.getBytes(b.readerIndex(), array);
            System.out.println(new String(array, UTF_8));
        }
    }
}
