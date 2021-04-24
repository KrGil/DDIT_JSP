package kr.or.ddit.mvc.filter.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class MultipartFile {
	private Part adaptee;
	private String originalFilename;
	private String uniqueSaveName;
	private boolean empty;

	public MultipartFile(Part adaptee) {
		super();
		this.adaptee = adaptee;
		String disposition = adaptee.getHeader("Content-Disposition");
		int index = disposition.indexOf("\"", disposition.indexOf("filename="));
		if(index!=-1) {
 			originalFilename = disposition.substring(index).replace("\"", "");
		}
		empty = StringUtils.isBlank(originalFilename);
		this.uniqueSaveName = UUID.randomUUID().toString();
	}
	
	public String getName() {
		return adaptee.getName();
	}
	
	public String getContentType() {
		return adaptee.getContentType();
	}
	
	public long getFileSize() {
		return adaptee.getSize();
	}
	
	public String getOriginalFilename() {
		return originalFilename;
	}
	
	public String getUniqueSaveName() {
		return uniqueSaveName;
	}
	
	public InputStream getInputStream() throws IOException {
		return adaptee.getInputStream();
	}
	
	public void saveTo(File saveFolder) throws IOException{
		File saveFile = new File(saveFolder, uniqueSaveName);
		adaptee.write(saveFile.getAbsolutePath());
	}
	
	public void transferTo(File dest) throws IOException{
		this.uniqueSaveName = dest.getName();
		adaptee.write(dest.getAbsolutePath());
	}
	
	public boolean isEmpty() {
		return empty;
	}
	
	public byte[] getBytes() throws IOException {
		try(
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			InputStream is = getInputStream();
		){
			IOUtils.copy(is, os);
			return os.toByteArray();
		}
	}
}










