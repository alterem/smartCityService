package com.zhcs.utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

//*****************************************************************************
/**
 * <p>Title:FileZip</p>
 * <p>Description:压缩工具类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class FileZip {

	//*************************************************************************
	/** 
	* 【处理】压缩
	* @param inputFileName 你要压缩的文件夹(整个完整路径)
	* @param zipFileName 压缩后的文件(整个完整路径)
	* @throws Exception  
	*/
	//*************************************************************************
	public static void zip(String inputFileName, String zipFileName) throws Exception {
		zip(zipFileName, new File(inputFileName));
	}

	private static void zip(String zipFileName, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				zipFileName));
		zip(out, inputFile, "");
		out.flush();
		out.close();
	}

	private static void zip(ZipOutputStream out, File f, String base) throws Exception {
		if (f.isDirectory()) {
			File[] fl = f.listFiles();
			out.putNextEntry(new ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (int i = 0; i < fl.length; i++) {
				zip(out, fl[i], base + fl[i].getName());
			}
		} else {
			out.putNextEntry(new ZipEntry(base));
			FileInputStream in = new FileInputStream(f);
			int b;
			//System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}
	
	 public static void main(String [] temp){       
		 try {           
			 zip("E:\\ftl","E:\\test.zip");//你要压缩的文件夹      和  压缩后的文件 
			 }catch (Exception ex) {       
				 ex.printStackTrace();    
			 }   
		}
}



		//=====================文件压缩=========================
		/*//把文件压缩成zip
		File zipFile = new File("E:/demo.zip");
		//定义输入文件流
		InputStream input = new FileInputStream(file);
		//定义压缩输出流	
		ZipOutputStream zipOut = null;
		//实例化压缩输出流,并制定压缩文件的输出路径  就是E盘下,名字叫 demo.zip
		zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
		zipOut.putNextEntry(new ZipEntry(file.getName()));
		//设置注释
		zipOut.setComment("www.demo.com");
		int temp = 0;
		while((temp = input.read()) != -1) {
			zipOut.write(temp);	
		}		
		input.close();
		zipOut.close();*/
//==============================================
