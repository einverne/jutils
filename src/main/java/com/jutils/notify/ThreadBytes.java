package com.jutils.notify;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.TimeUnit;

/**
 * 通过字节流通信
 */
public class ThreadBytes {

	public static void main(String[] args) {
		try {
			WriteData writeData = new WriteData();
			ReadData readData = new ReadData();

			PipedInputStream pipedInputStream = new PipedInputStream();
			PipedOutputStream pipedOutputStream = new PipedOutputStream();
			pipedOutputStream.connect(pipedInputStream);

			ThreadRead threadRead = new ThreadRead(readData, pipedInputStream);
			ThreadWrite threadWrite = new ThreadWrite(writeData, pipedOutputStream);

			threadRead.start();
			TimeUnit.MILLISECONDS.sleep(2000);
			threadWrite.start();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static class WriteData {

		public void write(PipedOutputStream out) {
			try {
				System.out.println("write: ");
				for (int i = 0; i < 100; i++) {
					java.lang.String outData = "" + i;
					out.write(outData.getBytes());
					System.out.print(outData);
				}
				System.out.println();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static class ReadData {

		public void read(PipedInputStream input) {
			try {
				System.out.println("read: ");
				byte[] bytes = new byte[20];
				int length = input.read(bytes);
				while (length != -1) {
					String string = new String(bytes, 0, length);
					System.out.print(string);
					length = input.read(bytes);
				}
				System.out.println();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static class ThreadRead extends Thread {

		private ReadData read;
		private PipedInputStream input;

		public ThreadRead(ReadData read, PipedInputStream input) {
			this.read = read;
			this.input = input;
		}

		@Override
		public void run() {
			super.run();
			read.read(input);
		}
	}

	public static class ThreadWrite extends Thread {

		private WriteData write;
		private PipedOutputStream out;

		public ThreadWrite(WriteData write, PipedOutputStream out) {
			this.write = write;
			this.out = out;
		}

		@Override
		public void run() {
			super.run();
			write.write(out);
		}
	}
}
