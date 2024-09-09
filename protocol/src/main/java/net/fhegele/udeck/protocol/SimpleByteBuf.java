package net.fhegele.udeck.protocol;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.util.ByteProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SimpleByteBuf extends ByteBuf {

    private static final Gson GSON = new GsonBuilder().create();

    private final ByteBuf source;

    public SimpleByteBuf(ByteBuf source) {
        this.source = source;
    }

    @Override
    public int capacity() {
        return source.capacity();
    }

    @Override
    public ByteBuf capacity(int i) {
        return source.capacity(i);
    }

    @Override
    public int maxCapacity() {
        return source.maxCapacity();
    }

    @Override
    public ByteBufAllocator alloc() {
        return source.alloc();
    }

    /**
     * @deprecated
     */
    @Override
    @Deprecated(since = "0.1-ALPHA")
    public ByteOrder order() {
        return source.order();
    }

    /**
     * @param byteOrder
     * @deprecated
     */
    @Override
    @Deprecated(since = "0.1-ALPHA")
    public ByteBuf order(ByteOrder byteOrder) {
        return source.order(byteOrder);
    }

    @Override
    public ByteBuf unwrap() {
        return source;
    }

    @Override
    public boolean isDirect() {
        return source.isDirect();
    }

    @Override
    public boolean isReadOnly() {
        return source.isReadOnly();
    }

    @Override
    public ByteBuf asReadOnly() {
        return source.asReadOnly();
    }

    @Override
    public int readerIndex() {
        return source.readerIndex();
    }

    @Override
    public ByteBuf readerIndex(int i) {
        return source.readerIndex(i);
    }

    @Override
    public int writerIndex() {
        return source.writerIndex();
    }

    @Override
    public SimpleByteBuf writerIndex(int i) {
        source.writerIndex(i);
        return this;
    }

    @Override
    public SimpleByteBuf setIndex(int i, int i1) {
        source.setIndex(i, i1);
        return this;
    }

    @Override
    public int readableBytes() {
        return source.readableBytes();
    }

    @Override
    public int writableBytes() {
        return source.writableBytes();
    }

    @Override
    public int maxWritableBytes() {
        return source.maxWritableBytes();
    }

    @Override
    public boolean isReadable() {
        return source.isReadable();
    }

    @Override
    public boolean isReadable(int i) {
        return source.isReadable(i);
    }

    @Override
    public boolean isWritable() {
        return source.isWritable();
    }

    @Override
    public boolean isWritable(int i) {
        return source.isWritable(i);
    }

    @Override
    public SimpleByteBuf clear() {
        source.clear();
        return this;
    }

    @Override
    public SimpleByteBuf markReaderIndex() {
        source.markReaderIndex();
        return this;
    }

    @Override
    public SimpleByteBuf resetReaderIndex() {
        source.resetReaderIndex();
        return this;
    }

    @Override
    public SimpleByteBuf markWriterIndex() {
        source.markWriterIndex();
        return this;
    }

    @Override
    public SimpleByteBuf resetWriterIndex() {
        source.resetWriterIndex();
        return this;
    }

    @Override
    public SimpleByteBuf discardReadBytes() {
        source.discardReadBytes();
        return this;
    }

    @Override
    public SimpleByteBuf discardSomeReadBytes() {
        source.discardSomeReadBytes();
        return this;
    }

    @Override
    public SimpleByteBuf ensureWritable(int i) {
        source.ensureWritable(i);
        return this;
    }

    @Override
    public int ensureWritable(int i, boolean b) {
        return source.ensureWritable(i, b);
    }

    @Override
    public boolean getBoolean(int i) {
        return source.getBoolean(i);
    }

    @Override
    public byte getByte(int i) {
        return source.getByte(i);
    }

    @Override
    public short getUnsignedByte(int i) {
        return source.getUnsignedByte(i);
    }

    @Override
    public short getShort(int i) {
        return source.getShort(i);
    }

    @Override
    public short getShortLE(int i) {
        return source.getShortLE(i);
    }

    @Override
    public int getUnsignedShort(int i) {
        return source.getUnsignedShort(i);
    }

    @Override
    public int getUnsignedShortLE(int i) {
        return source.getUnsignedShortLE(i);
    }

    @Override
    public int getMedium(int i) {
        return source.getMedium(i);
    }

    @Override
    public int getMediumLE(int i) {
        return source.getMediumLE(i);
    }

    @Override
    public int getUnsignedMedium(int i) {
        return source.getUnsignedMedium(i);
    }

    @Override
    public int getUnsignedMediumLE(int i) {
        return source.getUnsignedMediumLE(i);
    }

    @Override
    public int getInt(int i) {
        return source.getInt(i);
    }

    @Override
    public int getIntLE(int i) {
        return source.getIntLE(i);
    }

    @Override
    public long getUnsignedInt(int i) {
        return source.getUnsignedInt(i);
    }

    @Override
    public long getUnsignedIntLE(int i) {
        return source.getUnsignedIntLE(i);
    }

    @Override
    public long getLong(int i) {
        return source.getLong(i);
    }

    @Override
    public long getLongLE(int i) {
        return source.getLongLE(i);
    }

    @Override
    public char getChar(int i) {
        return source.getChar(i);
    }

    @Override
    public float getFloat(int i) {
        return source.getFloat(i);
    }

    @Override
    public double getDouble(int i) {
        return source.getDouble(i);
    }

    @Override
    public SimpleByteBuf getBytes(int i, ByteBuf byteBuf) {
        source.getBytes(i, byteBuf);
        return this;
    }

    @Override
    public SimpleByteBuf getBytes(int i, ByteBuf byteBuf, int i1) {
        source.getBytes(i, byteBuf, i1);
        return this;
    }

    @Override
    public SimpleByteBuf getBytes(int i, ByteBuf byteBuf, int i1, int i2) {
        source.getBytes(i, byteBuf, i1, i2);
        return this;
    }

    @Override
    public SimpleByteBuf getBytes(int i, byte[] bytes) {
        source.getBytes(i, bytes);
        return this;
    }

    @Override
    public SimpleByteBuf getBytes(int i, byte[] bytes, int i1, int i2) {
        source.getBytes(i, bytes, i1, i2);
        return this;
    }

    @Override
    public SimpleByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        source.getBytes(i, byteBuffer);
        return this;
    }

    @Override
    public SimpleByteBuf getBytes(int i, OutputStream outputStream, int i1) throws IOException {
        source.getBytes(i, outputStream, i1);
        return this;
    }

    @Override
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i1) throws IOException {
        return source.getBytes(i, gatheringByteChannel, i1);
    }

    @Override
    public int getBytes(int i, FileChannel fileChannel, long l, int i1) throws IOException {
        return source.getBytes(i, fileChannel, l, i1);
    }

    @Override
    public CharSequence getCharSequence(int i, int i1, Charset charset) {
        return source.getCharSequence(i, i1, charset);
    }

    @Override
    public SimpleByteBuf setBoolean(int i, boolean b) {
        source.setBoolean(i, b);
        return this;
    }

    @Override
    public SimpleByteBuf setByte(int i, int i1) {
        source.setByte(i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setShort(int i, int i1) {
        source.setShort(i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setShortLE(int i, int i1) {
        source.setShortLE(i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setMedium(int i, int i1) {
        source.setMedium(i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setMediumLE(int i, int i1) {
        source.setMediumLE(i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setInt(int i, int i1) {
        source.setInt(i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setIntLE(int i, int i1) {
        source.setIntLE(i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setLong(int i, long l) {
        source.setLong(i, l);
        return this;
    }

    @Override
    public SimpleByteBuf setLongLE(int i, long l) {
        source.setLongLE(i, l);
        return this;
    }

    @Override
    public SimpleByteBuf setChar(int i, int i1) {
        source.setChar(i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setFloat(int i, float v) {
        source.setFloat(i, v);
        return this;
    }

    @Override
    public SimpleByteBuf setDouble(int i, double v) {
        source.setDouble(i, v);
        return this;
    }

    @Override
    public SimpleByteBuf setBytes(int i, ByteBuf byteBuf) {
        source.setBytes(i, byteBuf);
        return this;
    }

    @Override
    public SimpleByteBuf setBytes(int i, ByteBuf byteBuf, int i1) {
        source.setBytes(i, byteBuf, i1);
        return this;
    }

    @Override
    public SimpleByteBuf setBytes(int i, ByteBuf byteBuf, int i1, int i2) {
        source.setBytes(i, byteBuf, i1, i2);
        return this;
    }

    @Override
    public SimpleByteBuf setBytes(int i, byte[] bytes) {
        source.setBytes(i, bytes);
        return this;
    }

    @Override
    public SimpleByteBuf setBytes(int i, byte[] bytes, int i1, int i2) {
        source.setBytes(i, bytes, i1, i2);
        return this;
    }

    @Override
    public SimpleByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        source.setBytes(i, byteBuffer);
        return this;
    }

    @Override
    public int setBytes(int i, InputStream inputStream, int i1) throws IOException {
        return source.setBytes(i, inputStream, i1);
    }

    @Override
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i1) throws IOException {
        return source.setBytes(i, scatteringByteChannel, i1);
    }

    @Override
    public int setBytes(int i, FileChannel fileChannel, long l, int i1) throws IOException {
        return source.setBytes(i, fileChannel, l, i1);
    }

    @Override
    public ByteBuf setZero(int i, int i1) {
        source.setZero(i, i1);
        return this;
    }

    @Override
    public int setCharSequence(int i, CharSequence charSequence, Charset charset) {
        return source.setCharSequence(i, charSequence, charset);
    }

    @Override
    public boolean readBoolean() {
        return source.readBoolean();
    }

    @Override
    public byte readByte() {
        return source.readByte();
    }

    @Override
    public short readUnsignedByte() {
        return source.readUnsignedByte();
    }

    @Override
    public short readShort() {
        return source.readShort();
    }

    @Override
    public short readShortLE() {
        return source.readShortLE();
    }

    @Override
    public int readUnsignedShort() {
        return source.readUnsignedShort();
    }

    @Override
    public int readUnsignedShortLE() {
        return source.readShortLE();
    }

    @Override
    public int readMedium() {
        return source.readMedium();
    }

    @Override
    public int readMediumLE() {
        return source.readMediumLE();
    }

    @Override
    public int readUnsignedMedium() {
        return source.readUnsignedMedium();
    }

    @Override
    public int readUnsignedMediumLE() {
        return source.readUnsignedMediumLE();
    }

    @Override
    public int readInt() {
        return source.readInt();
    }

    @Override
    public int readIntLE() {
        return source.readIntLE();
    }

    @Override
    public long readUnsignedInt() {
        return source.readUnsignedInt();
    }

    @Override
    public long readUnsignedIntLE() {
        return source.readUnsignedIntLE();
    }

    @Override
    public long readLong() {
        return source.readLong();
    }

    @Override
    public long readLongLE() {
        return source.readLongLE();
    }

    @Override
    public char readChar() {
        return source.readChar();
    }

    @Override
    public float readFloat() {
        return source.readFloat();
    }

    @Override
    public double readDouble() {
        return source.readDouble();
    }

    @Override
    public ByteBuf readBytes(int i) {
        return source.readBytes(i);
    }

    @Override
    public ByteBuf readSlice(int i) {
        return source.readSlice(i);
    }

    @Override
    public ByteBuf readRetainedSlice(int i) {
        return source.readRetainedSlice(i);
    }

    @Override
    public SimpleByteBuf readBytes(ByteBuf byteBuf) {
        source.readBytes(byteBuf);
        return this;
    }

    @Override
    public SimpleByteBuf readBytes(ByteBuf byteBuf, int i) {
        source.readBytes(byteBuf, i);
        return this;
    }

    @Override
    public SimpleByteBuf readBytes(ByteBuf byteBuf, int i, int i1) {
        source.readBytes(byteBuf, i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf readBytes(byte[] bytes) {
        source.readBytes(bytes);
        return this;
    }

    @Override
    public SimpleByteBuf readBytes(byte[] bytes, int i, int i1) {
        source.readBytes(bytes, i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf readBytes(ByteBuffer byteBuffer) {
        source.readBytes(byteBuffer);
        return this;
    }

    @Override
    public SimpleByteBuf readBytes(OutputStream outputStream, int i) throws IOException {
        source.readBytes(outputStream, i);
        return this;
    }

    @Override
    public int readBytes(GatheringByteChannel gatheringByteChannel, int i) throws IOException {
        return source.readBytes(gatheringByteChannel, i);
    }

    @Override
    public CharSequence readCharSequence(int i, Charset charset) {
        return source.readCharSequence(i, charset);
    }

    @Override
    public int readBytes(FileChannel fileChannel, long l, int i) throws IOException {
        return source.readBytes(fileChannel, l, i);
    }

    @Override
    public SimpleByteBuf skipBytes(int i) {
        source.skipBytes(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeBoolean(boolean b) {
        source.writeBoolean(b);
        return this;
    }

    @Override
    public SimpleByteBuf writeByte(int i) {
        source.writeByte(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeShort(int i) {
        source.writeShort(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeShortLE(int i) {
        source.writeShortLE(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeMedium(int i) {
        source.writeMedium(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeMediumLE(int i) {
        source.writeMediumLE(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeInt(int i) {
        source.writeInt(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeIntLE(int i) {
        source.writeIntLE(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeLong(long l) {
        source.writeLong(l);
        return this;
    }

    @Override
    public SimpleByteBuf writeLongLE(long l) {
        source.writeLongLE(l);
        return this;
    }

    @Override
    public SimpleByteBuf writeChar(int i) {
        source.writeChar(i);
        return this;
    }

    @Override
    public SimpleByteBuf writeFloat(float v) {
        source.writeFloat(v);
        return this;
    }

    @Override
    public SimpleByteBuf writeDouble(double v) {
        source.writeDouble(v);
        return this;
    }

    public SimpleByteBuf writeUTF8(String s) {
        final byte[] bytes = s.getBytes(StandardCharsets.UTF_8);
        source.writeInt(bytes.length);
        source.writeBytes(bytes);

        return this;
    }

    public String readUTF8() {
        final int length = source.readInt();
        final byte[] bytes = new byte[length];
        source.readBytes(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    public SimpleByteBuf writeJsonObject(Object obj) {
        writeUTF8(GSON.toJson(obj));
        return this;
    }

    public <T> T readJsonObject(Class<T> clazz) {
        final String json = readUTF8();
        return GSON.fromJson(json, clazz);
    }

    @Override
    public SimpleByteBuf writeBytes(ByteBuf byteBuf) {
        source.writeBytes(byteBuf);
        return this;
    }

    @Override
    public SimpleByteBuf writeBytes(ByteBuf byteBuf, int i) {
        source.writeBytes(byteBuf, i);
        return this;
    }

    @Override
    public SimpleByteBuf writeBytes(ByteBuf byteBuf, int i, int i1) {
        source.writeBytes(byteBuf, i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf writeBytes(byte[] bytes) {
        source.writeBytes(bytes);
        return this;
    }

    @Override
    public SimpleByteBuf writeBytes(byte[] bytes, int i, int i1) {
        source.writeBytes(bytes, i, i1);
        return this;
    }

    @Override
    public SimpleByteBuf writeBytes(ByteBuffer byteBuffer) {
        source.writeBytes(byteBuffer);
        return this;
    }

    @Override
    public int writeBytes(InputStream inputStream, int i) throws IOException {
        return source.writeBytes(inputStream, i);
    }

    @Override
    public int writeBytes(ScatteringByteChannel scatteringByteChannel, int i) throws IOException {
        return source.writeBytes(scatteringByteChannel, i);
    }

    @Override
    public int writeBytes(FileChannel fileChannel, long l, int i) throws IOException {
        return source.writeBytes(fileChannel, l, i);
    }

    @Override
    public SimpleByteBuf writeZero(int i) {
        source.writeZero(i);
        return this;
    }

    @Override
    public int writeCharSequence(CharSequence charSequence, Charset charset) {
        return source.writeCharSequence(charSequence, charset);
    }

    @Override
    public int indexOf(int i, int i1, byte b) {
        return source.indexOf(i, i1, b);
    }

    @Override
    public int bytesBefore(byte b) {
        return source.bytesBefore(b);
    }

    @Override
    public int bytesBefore(int i, byte b) {
        return source.bytesBefore(i, b);
    }

    @Override
    public int bytesBefore(int i, int i1, byte b) {
        return source.bytesBefore(i, i1, b);
    }

    @Override
    public int forEachByte(ByteProcessor byteProcessor) {
        return source.forEachByte(byteProcessor);
    }

    @Override
    public int forEachByte(int i, int i1, ByteProcessor byteProcessor) {
        return source.forEachByte(i, i1, byteProcessor);
    }

    @Override
    public int forEachByteDesc(ByteProcessor byteProcessor) {
        return source.forEachByteDesc(byteProcessor);
    }

    @Override
    public int forEachByteDesc(int i, int i1, ByteProcessor byteProcessor) {
        return source.forEachByteDesc(i, i1, byteProcessor);
    }

    @Override
    public ByteBuf copy() {
        return source.copy();
    }

    @Override
    public ByteBuf copy(int i, int i1) {
        return source.copy();
    }

    @Override
    public ByteBuf slice() {
        return source.slice();
    }

    @Override
    public ByteBuf retainedSlice() {
        return source.retainedSlice();
    }

    @Override
    public ByteBuf slice(int i, int i1) {
        return source.slice(i, i1);
    }

    @Override
    public ByteBuf retainedSlice(int i, int i1) {
        return source.retainedSlice(i, i1);
    }

    @Override
    public ByteBuf duplicate() {
        return source.duplicate();
    }

    @Override
    public ByteBuf retainedDuplicate() {
        return source.retainedDuplicate();
    }

    @Override
    public int nioBufferCount() {
        return source.nioBufferCount();
    }

    @Override
    public ByteBuffer nioBuffer() {
        return source.nioBuffer();
    }

    @Override
    public ByteBuffer nioBuffer(int i, int i1) {
        return source.nioBuffer(i, i1);
    }

    @Override
    public ByteBuffer internalNioBuffer(int i, int i1) {
        return source.internalNioBuffer(i, i1);
    }

    @Override
    public ByteBuffer[] nioBuffers() {
        return source.nioBuffers();
    }

    @Override
    public ByteBuffer[] nioBuffers(int i, int i1) {
        return source.nioBuffers(i, i1);
    }

    @Override
    public boolean hasArray() {
        return source.hasArray();
    }

    @Override
    public byte[] array() {

        return source.array();
    }

    @Override
    public int arrayOffset() {
        return source.arrayOffset();
    }

    @Override
    public boolean hasMemoryAddress() {
        return source.hasMemoryAddress();
    }

    @Override
    public long memoryAddress() {
        return source.memoryAddress();
    }

    @Override
    public String toString(Charset charset) {
        return source.toString(charset);
    }

    @Override
    public String toString(int i, int i1, Charset charset) {
        return source.toString(i, i1, charset);
    }

    @Override
    public int hashCode() {
        return source.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return source.equals(o);
    }

    @Override
    public int compareTo(ByteBuf byteBuf) {
        return source.compareTo(byteBuf);
    }

    @Override
    public String toString() {
        return source.toString();
    }

    @Override
    public SimpleByteBuf retain(int i) {
        source.retain(i);
        return this;
    }

    @Override
    public int refCnt() {
        return source.refCnt();
    }

    @Override
    public SimpleByteBuf retain() {
        source.retain();
        return this;
    }
    @Override
    public SimpleByteBuf touch() {
        source.touch();
        return this;
    }

    @Override
    public SimpleByteBuf touch(Object o) {
        source.touch(o);
        return this;
    }

    @Override
    public boolean release() {
        return source.release();
    }

    @Override
    public boolean release(int i) {
        return source.release(i);
    }
}
