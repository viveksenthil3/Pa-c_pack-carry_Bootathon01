//package training;
//
//import java.io.*;
////import java.io.IOException;
//
//public class TxtIN {
//
//	public static void main(String[] args) {
//		try(FileInputStream f = new FileInputStream("src/training/data.txt");) {
//		
//
//		String str = "";
//		for(byte b : f.readAllBytes())
//			str+=(char)b;
//
//		String[] strs = str.split(",");
//		System.out.println(strs[0]);
//		System.out.println(strs[1]);
//		
//		}
//		catch(IOException e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
//
//}
