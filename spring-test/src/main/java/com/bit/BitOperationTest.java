package com.bit;

import java.util.concurrent.atomic.AtomicInteger;
 
public class BitOperationTest {
	
	private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
    
    private static final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

	public static void main(String[] args) {
//		int x = 11;
//		int y = 13;
//		System.out.println(Integer.toBinaryString(-1));
//		System.out.println("ctl="+Integer.toBinaryString(ctl.get()));
//		System.out.println("run="+Integer.toBinaryString(RUNNING));
//		System.out.println("1 << COUNT_BITS="+Integer.toBinaryString(1 << COUNT_BITS));
//		System.out.println("CAPACITY="+Integer.toBinaryString(CAPACITY));
//		System.out.println("RUNNING="+Integer.toBinaryString(RUNNING));
//		System.out.println("SHUTDOWN="+Integer.toBinaryString(SHUTDOWN));
//		System.out.println("STOP="+Integer.toBinaryString(STOP));
//		System.out.println("TIDYING="+Integer.toBinaryString(TIDYING));
//		System.out.println("TERMINATED="+Integer.toBinaryString(TERMINATED));
		
//		int ssize = 1;
//		int sshift = 0;
//        while (ssize < 16) {
//        	++sshift;
//            ssize <<= 1;
//        }
//        Integer segmentMask = ssize - 1;
//        System.out.println(sshift+"===");
//        System.out.println(Integer.toBinaryString(segmentMask));
//        
//        ConcurrentHashMap<String, Integer> testMap = new ConcurrentHashMap<String, Integer>();
//        for(int i = 0; i < 15 ; i++){
//        	testMap.put("key_"+i, i);
//        }
//        testMap.put("key_"+14, 14);
		
		Integer testVal = 10;
		System.out.println(Integer.toBinaryString(testVal));
		
		Integer leftOffset = testVal << 2;
		System.out.println("左移：二进制="+Integer.toBinaryString(leftOffset)+",十进制="+leftOffset);
		
		Integer rightOffset = -10 >>> 2;
		System.out.println("右移：二进制="+Integer.toBinaryString(rightOffset)+",十进制="+rightOffset+","+Integer.toBinaryString(-10));
		
		System.out.println("0xff="+Integer.toBinaryString(0xff));
		System.out.println("0xff00="+Integer.toBinaryString(0xff00));
		System.out.println("0xff0000="+Integer.toBinaryString(0xff0000));
		System.out.println("0xff000000="+Integer.toBinaryString(0xff000000));
	}
	
	public static int ToInt32(byte[] buffer) {
		int int32 = 0;
		int32 = buffer[0] & 0xff;
		int32 |= ((int) buffer[1] << 8) & 0xff00;
		int32 |= ((int) buffer[2] << 16) & 0xff0000;
		int32 |= ((int) buffer[3] << 24) & 0xff000000;
		return int32;
	}

}
