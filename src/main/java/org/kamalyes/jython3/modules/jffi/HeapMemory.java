
package org.kamalyes.jython3.modules.jffi;

import com.kenai.jffi.Platform;
import java.nio.ByteOrder;
import java.util.Arrays;

public final class HeapMemory implements Memory {
  protected static final ArrayIO IO = getArrayIO();
  protected static final int LONG_SIZE = Platform.getPlatform().longSize() / 8;
  protected static final int ADDRESS_SIZE = Platform.getPlatform().addressSize() / 8;

  protected final byte[] buffer;
  protected final int offset, length;

  public HeapMemory(byte[] buffer, int offset, int length) {
    this.buffer = buffer;
    this.offset = offset;
    this.length = length;
  }

  public HeapMemory(int size) {
    this(new byte[size], 0, size);
  }

  private final void checkBounds(long off, long len) {
    Util.checkBounds(arrayLength(), off, len);
  }

  public final byte[] array() {
    return buffer;
  }

  public final int arrayOffset() {
    return offset;
  }

  public final int arrayLength() {
    return length;
  }

  private static final ArrayIO getArrayIO() {
    if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
      return Platform.getPlatform().addressSize() == 64
          ? newBE64ArrayIO()
          : newBE32ArrayIO();
    } else {
      return Platform.getPlatform().addressSize() == 64
          ? newLE64ArrayIO()
          : newLE32ArrayIO();
    }
  }

  private static final ArrayIO newBE64ArrayIO() {
    return new BE64ArrayIO();
  }

  private static final ArrayIO newBE32ArrayIO() {
    return new BE32ArrayIO();
  }

  private static final ArrayIO newLE64ArrayIO() {
    return new LE64ArrayIO();
  }

  private static final ArrayIO newLE32ArrayIO() {
    return new LE32ArrayIO();
  }

  protected final int index(long off) {
    return this.offset + (int) off;
  }

  public final boolean isNull() {
    return false;
  }

  public final boolean isDirect() {
    return false;
  }

  public HeapMemory slice(long offset) {
    checkBounds(offset, 1);
    return offset == 0 ? this : new HeapMemory(array(), arrayOffset() + (int) offset, arrayLength() - (int) offset);
  }

  public final DirectMemory getMemory(long offset) {
    checkBounds(offset, ADDRESS_SIZE);
    final long ptr = getAddress(offset);
    return ptr != 0 ? new NativeMemory(ptr) : null;
  }

  public final void putAddress(long offset, Memory value) {
    checkBounds(offset, ADDRESS_SIZE);
    putAddress(offset, ((DirectMemory) value).getAddress());
  }

  public final byte getByte(long offset) {
    checkBounds(offset, 1);
    return (byte) (buffer[index(offset)] & 0xff);
  }

  public final short getShort(long offset) {
    checkBounds(offset, 2);
    return IO.getInt16(buffer, index(offset));
  }

  public final int getInt(long offset) {
    checkBounds(offset, 4);
    return IO.getInt32(buffer, index(offset));
  }

  public final long getLong(long offset) {
    checkBounds(offset, 8);
    return IO.getInt64(buffer, index(offset));
  }

  public final long getNativeLong(long offset) {
    return LONG_SIZE == 4 ? getInt(offset) : getLong(offset);
  }

  public final float getFloat(long offset) {
    checkBounds(offset, 4);
    return IO.getFloat32(buffer, index(offset));
  }

  public final double getDouble(long offset) {
    checkBounds(offset, 8);
    return IO.getFloat64(buffer, index(offset));
  }

  public final long getAddress(long offset) {
    checkBounds(offset, ADDRESS_SIZE);
    return IO.getAddress(buffer, index(offset));
  }

  public final byte[] getZeroTerminatedByteArray(long offset) {
    checkBounds(offset, 1);
    int len = indexOf(offset, (byte) 0);
    byte[] bytes = new byte[len != -1 ? len : length - (int) offset];
    System.arraycopy(buffer, index(offset), bytes, 0, bytes.length);
    return bytes;
  }

  public final void putByte(long offset, byte value) {
    checkBounds(offset, 1);
    buffer[index(offset)] = value;
  }

  public final void putShort(long offset, short value) {
    checkBounds(offset, 2);
    IO.putInt16(buffer, index(offset), value);
  }

  public final void putInt(long offset, int value) {
    checkBounds(offset, 4);
    IO.putInt32(buffer, index(offset), value);
  }

  public final void putLong(long offset, long value) {
    checkBounds(offset, 8);
    IO.putInt64(buffer, index(offset), value);
  }

  public final void putNativeLong(long offset, long value) {
    if (LONG_SIZE == 4) {
      putInt(offset, (int) value);
    } else {
      putLong(offset, value);
    }
  }

  public final void putFloat(long offset, float value) {
    checkBounds(offset, 4);
    IO.putFloat32(buffer, index(offset), value);
  }

  public final void putDouble(long offset, double value) {
    checkBounds(offset, 8);
    IO.putFloat64(buffer, index(offset), value);
  }

  public final void putAddress(long offset, long value) {
    checkBounds(offset, ADDRESS_SIZE);
    IO.putAddress(buffer, index(offset), value);
  }

  public void putZeroTerminatedByteArray(long offset, byte[] bytes, int off, int len) {
    // Ensure room for terminating zero byte
    checkBounds(offset, len + 1);
    System.arraycopy(bytes, off, buffer, index(offset), len);
    buffer[len] = (byte) 0;
  }

  public final void get(long offset, byte[] dst, int off, int len) {
    checkBounds(offset, len);
    System.arraycopy(buffer, index(offset), dst, off, len);
  }

  public final void put(long offset, byte[] src, int off, int len) {
    checkBounds(offset, len);
    System.arraycopy(src, off, buffer, index(offset), len);
  }

  public final void get(long offset, short[] dst, int off, int len) {
    checkBounds(offset, len << 1);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      dst[off + i] = IO.getInt16(buffer, begin + (i << 1));
    }
  }

  public final void put(long offset, short[] src, int off, int len) {
    checkBounds(offset, len << 1);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      IO.putInt16(buffer, begin + (i << 1), src[off + i]);
    }
  }

  public final void get(long offset, int[] dst, int off, int len) {
    checkBounds(offset, len << 2);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      dst[off + i] = IO.getInt32(buffer, begin + (i << 2));
    }
  }

  public final void put(long offset, int[] src, int off, int len) {
    checkBounds(offset, len << 2);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      IO.putInt32(buffer, begin + (i << 2), src[off + i]);
    }
  }

  public final void get(long offset, long[] dst, int off, int len) {
    checkBounds(offset, len << 3);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      dst[off + i] = IO.getInt64(buffer, begin + (i << 3));
    }
  }

  public final void put(long offset, long[] src, int off, int len) {
    checkBounds(offset, len << 3);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      IO.putInt64(buffer, begin + (i << 3), src[off + i]);
    }
  }

  public final void get(long offset, float[] dst, int off, int len) {
    checkBounds(offset, len << 2);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      dst[off + i] = IO.getFloat32(buffer, begin + (i << 2));
    }
  }

  public final void put(long offset, float[] src, int off, int len) {
    checkBounds(offset, len << 2);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      IO.putFloat32(buffer, begin + (i << 2), src[off + i]);
    }
  }

  public final void get(long offset, double[] dst, int off, int len) {
    checkBounds(offset, len << 3);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      dst[off + i] = IO.getFloat64(buffer, begin + (i << 3));
    }
  }

  public final void put(long offset, double[] src, int off, int len) {
    checkBounds(offset, len << 3);
    int begin = index(offset);
    for (int i = 0; i < len; ++i) {
      IO.putFloat64(buffer, begin + (i << 3), src[off + i]);
    }
  }

  public final int indexOf(long offset, byte value) {
    int off = index(offset);
    for (int i = 0; i < length; ++i) {
      if (buffer[off + i] == value) {
        return i;
      }
    }
    return -1;
  }

  public final int indexOf(long offset, byte value, int maxlen) {
    int off = index(offset);
    for (int i = 0; i < Math.min(length, maxlen); ++i) {
      if (buffer[off + i] == value) {
        return i;
      }
    }
    return -1;
  }

  public final void setMemory(long offset, long size, byte value) {
    checkBounds(offset, size);
    Arrays.fill(buffer, index(offset), (int) size, value);
  }

  public final void clear() {
    Arrays.fill(buffer, offset, length, (byte) 0);
  }

  protected static abstract class ArrayIO {

    public abstract short getInt16(byte[] buffer, int offset);

    public abstract int getInt32(byte[] buffer, int offset);

    public abstract long getInt64(byte[] buffer, int offset);

    public abstract long getAddress(byte[] buffer, int offset);

    public abstract void putInt16(byte[] buffer, int offset, int value);

    public abstract void putInt32(byte[] buffer, int offset, int value);

    public abstract void putInt64(byte[] buffer, int offset, long value);

    public abstract void putAddress(byte[] buffer, int offset, long value);

    public final float getFloat32(byte[] buffer, int offset) {
      return Float.intBitsToFloat(getInt32(buffer, offset));
    }

    public final void putFloat32(byte[] buffer, int offset, float value) {
      putInt32(buffer, offset, Float.floatToRawIntBits(value));
    }

    public final double getFloat64(byte[] buffer, int offset) {
      return Double.longBitsToDouble(getInt64(buffer, offset));
    }

    public final void putFloat64(byte[] buffer, int offset, double value) {
      putInt64(buffer, offset, Double.doubleToRawLongBits(value));
    }
  }

  private static abstract class LittleEndianArrayIO extends ArrayIO {
    public final short getInt16(byte[] array, int offset) {
      return (short) ((array[offset] & 0xff) | ((array[offset + 1] & 0xff) << 8));
    }

    public final int getInt32(byte[] array, int offset) {
      return ((array[offset + 0] & 0xff) << 0)
          | ((array[offset + 1] & 0xff) << 8)
          | ((array[offset + 2] & 0xff) << 16)
          | ((array[offset + 3] & 0xff) << 24);
    }

    public final long getInt64(byte[] array, int offset) {
      return (((long) array[offset + 0] & 0xff) << 0)
          | (((long) array[offset + 1] & 0xff) << 8)
          | (((long) array[offset + 2] & 0xff) << 16)
          | (((long) array[offset + 3] & 0xff) << 24)
          | (((long) array[offset + 4] & 0xff) << 32)
          | (((long) array[offset + 5] & 0xff) << 40)
          | (((long) array[offset + 6] & 0xff) << 48)
          | (((long) array[offset + 7] & 0xff) << 56);
    }

    public final void putInt16(byte[] buffer, int offset, int value) {
      buffer[offset + 0] = (byte) (value >> 0);
      buffer[offset + 1] = (byte) (value >> 8);
    }

    public final void putInt32(byte[] buffer, int offset, int value) {
      buffer[offset + 0] = (byte) (value >> 0);
      buffer[offset + 1] = (byte) (value >> 8);
      buffer[offset + 2] = (byte) (value >> 16);
      buffer[offset + 3] = (byte) (value >> 24);
    }

    public final void putInt64(byte[] buffer, int offset, long value) {
      buffer[offset + 0] = (byte) (value >> 0);
      buffer[offset + 1] = (byte) (value >> 8);
      buffer[offset + 2] = (byte) (value >> 16);
      buffer[offset + 3] = (byte) (value >> 24);
      buffer[offset + 4] = (byte) (value >> 32);
      buffer[offset + 5] = (byte) (value >> 40);
      buffer[offset + 6] = (byte) (value >> 48);
      buffer[offset + 7] = (byte) (value >> 56);
    }
  }

  private static abstract class BigEndianArrayIO extends ArrayIO {
    public short getInt16(byte[] array, int offset) {
      return (short) (((array[offset + 0] & 0xff) << 8)
          | (array[offset + 1] & 0xff));
    }

    public int getInt32(byte[] array, int offset) {
      return ((array[offset + 0] & 0xff) << 24)
          | ((array[offset + 1] & 0xff) << 16)
          | ((array[offset + 2] & 0xff) << 8)
          | ((array[offset + 3] & 0xff) << 0);
    }

    public long getInt64(byte[] array, int offset) {
      return (((long) array[offset + 0] & 0xff) << 56)
          | (((long) array[offset + 1] & 0xff) << 48)
          | (((long) array[offset + 2] & 0xff) << 40)
          | (((long) array[offset + 3] & 0xff) << 32)
          | (((long) array[offset + 4] & 0xff) << 24)
          | (((long) array[offset + 5] & 0xff) << 16)
          | (((long) array[offset + 6] & 0xff) << 8)
          | (((long) array[offset + 7] & 0xff) << 0);
    }

    public final void putInt16(byte[] buffer, int offset, int value) {
      buffer[offset + 0] = (byte) (value >> 8);
      buffer[offset + 1] = (byte) (value >> 0);
    }

    public final void putInt32(byte[] buffer, int offset, int value) {
      buffer[offset + 0] = (byte) (value >> 24);
      buffer[offset + 1] = (byte) (value >> 16);
      buffer[offset + 2] = (byte) (value >> 8);
      buffer[offset + 3] = (byte) (value >> 0);
    }

    public final void putInt64(byte[] buffer, int offset, long value) {
      buffer[offset + 0] = (byte) (value >> 56);
      buffer[offset + 1] = (byte) (value >> 48);
      buffer[offset + 2] = (byte) (value >> 40);
      buffer[offset + 3] = (byte) (value >> 32);
      buffer[offset + 4] = (byte) (value >> 24);
      buffer[offset + 5] = (byte) (value >> 16);
      buffer[offset + 6] = (byte) (value >> 8);
      buffer[offset + 7] = (byte) (value >> 0);
    }
  }

  private static final class LE32ArrayIO extends LittleEndianArrayIO {
    public final long getAddress(byte[] buffer, int offset) {
      return ((long) getInt32(buffer, offset)) & 0xffffffffL;
    }

    public final void putAddress(byte[] buffer, int offset, long value) {
      putInt32(buffer, offset, (int) value);
    }
  }

  private static final class LE64ArrayIO extends LittleEndianArrayIO {
    public final long getAddress(byte[] buffer, int offset) {
      return getInt64(buffer, offset);
    }

    public final void putAddress(byte[] buffer, int offset, long value) {
      putInt64(buffer, offset, value);
    }
  }

  private static final class BE32ArrayIO extends BigEndianArrayIO {
    public final long getAddress(byte[] buffer, int offset) {
      return ((long) getInt32(buffer, offset)) & 0xffffffffL;
    }

    public final void putAddress(byte[] buffer, int offset, long value) {
      putInt32(buffer, offset, (int) value);
    }
  }

  private static final class BE64ArrayIO extends BigEndianArrayIO {
    public final long getAddress(byte[] buffer, int offset) {
      return getInt64(buffer, offset);
    }

    public final void putAddress(byte[] buffer, int offset, long value) {
      putInt64(buffer, offset, value);
    }
  }
}
