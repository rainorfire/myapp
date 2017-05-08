package com.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;
import java.util.Random;

public class Test {
	
	public static void main(String[] args) {
//		Selector selector = 
		File file = new File("C:\\Users\\lenovo\\Desktop\\2015-12-31.sql");
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			FileChannel fc = raf.getChannel();
			
			ByteBuffer bf = ByteBuffer.allocate(1240);
			ByteBuffer bf1 = ByteBuffer.allocate(1240);
			
			ByteBuffer[] array = {bf,bf1};
			
			long i = fc.read(array);
			while(i > -1){
				bf.flip();
				System.out.println("Read2： " + i);
				while(bf.hasRemaining()){
					System.out.print((char) bf.get());
				}
				
				bf.clear();
				
				bf1.flip();
				System.out.println("Read2： " + i);
				while(bf1.hasRemaining()){
					System.out.print((char) bf1.get());
				}
				bf1.clear();
				
//				array = {bf,bf1};
				i = fc.read(array);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
