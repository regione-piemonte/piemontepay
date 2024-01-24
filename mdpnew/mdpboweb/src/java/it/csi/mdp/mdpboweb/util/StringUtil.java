/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.mdp.mdpboweb.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class StringUtil {

	protected static final Logger log = Logger.getLogger(Constants.APPLICATION_CODE + ".StringUtil");

	public static String convertNull(String value, String replace) {
		if (value == null || value.trim().equals("")) {
			return replace;
		}
		return value;
	}

	/**
	 * 
	 * @param xml
	 * @param nomeFile
	 * @param contentType
	 * @throws Exception
	 * @throws IOException
	 */
	public static void estraiXml(String xml, String nomeFile, String contentType) throws Exception, IOException {
		log.info("[StringUtil::estraiXml] START");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-Disposition",String.format("%s; filename=%s", "attachment", Utility.encodeFilenameForDownload(nomeFile, ServletActionContext.getRequest().getHeader("user-agent"))));
		response.setContentType(contentType);
		ServletOutputStream outputStream = response.getOutputStream();
		PrintStream out = new PrintStream(outputStream);
		out.print(xml);
		out.flush();
		out.close();
		log.info("[StringUtil::estraiXml] STOP");
	}

	/**
	 * 
	 * @param xml
	 * @param nomeFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String estraiXml(byte[] xml, String nomeFile) throws FileNotFoundException, IOException {
		log.info("[StringUtil::estraiXml] START");
		byte[] bFile = xml;// new byte[(int) file.length()];
		
		if(bFile!=null){
			try {
				log.info("[StringUtil::estraiXml] bFile " + bFile.length);
				log.info("[StringUtil::estraiXml] bFile " + bFile.toString());
				log.info("[StringUtil::estraiXml] nomeFile " + "D:\\"+nomeFile);
				// convert array of bytes into file
//				FileOutputStream fileOuputStream = new FileOutputStream(new File("D:\\" + nomeFile));
//				fileOuputStream.write(bFile);
//				fileOuputStream.close();
				File f = new File("D:\\" + nomeFile);
				f.createNewFile();
//				FileOutputStream fs = new FileOutputStream(f);
//			    BufferedOutputStream bs = new BufferedOutputStream(fs);
//			    bs.write(bFile);
//			    bs.flush();
//			    bs.close();
			    //bs = null;
			} catch (Exception e) {
				return "KO " + e.getMessage();				
			}
		}else{
			log.info("[StringUtil::estraiXml] bFile = null");			
			return "KO bFile = null";
		}
		log.info("[StringUtil::estraiXml] STOP");
		return "OK";
	}

	public static void main(String[] args){
		try {
			System.out.println(estraiXml(null, "D:\\RT_RF44150580003TSTA00000007.xml"));
		} catch (FileNotFoundException e) {
			log.info("[StringUtil::estraiXml] STOP");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	
	public static String estraiXml(byte[] xml, String nomeFile, String contentType)  {
		log.info("[StringUtil::estraiXml] START");
		String ris ="";
		try{
			DownloadData data = new DownloadData(contentType, nomeFile, xml);
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			
			response.setHeader("Content-Disposition",
					String.format("%s; filename=%s",
					data.getContentDisposition(),
					Utility.encodeFilenameForDownload(data.getFilename(),
					request.getHeader("user-agent"))));
			
			response.setContentType(contentType);

			ServletOutputStream outputStream = response.getOutputStream();

//			PrintStream out = new PrintStream(outputStream);			
//			out.write(xml);
//			out.flush();
//			out.close();

			
			outputStream.write(xml);
			outputStream.flush();
			outputStream.close();
			ris = "OK";
		}catch(Exception e){
			e.getStackTrace();
			ris = "KO";
		}
		return ris;	
	}
}
