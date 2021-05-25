package kr.or.ddit.explorer;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

public class FileWrapper extends File{
//	new Integer(4)
	public FileWrapper(File file, ServletContext application) {
		super(file.getAbsolutePath());
		clz = file.isDirectory()?"lazy folder":"file";
		String fileAbPath = file.getAbsolutePath();
		String contextRealPath = application.getRealPath("/");
		String tmp = StringUtils.remove(fileAbPath, contextRealPath);
		fileURI = tmp.replace(File.separatorChar, '/');
		fileURI = fileURI.startsWith("/")?fileURI:"/"+fileURI;
	}

	private String clz;
	private String fileURI;
	public String getClz() {
		return clz;
	}
	public String getFileURI() {
		return fileURI;
	}
	public String getKey() {
		return fileURI;
	}
	public String getTitle() {
		return getName();
	}
	public boolean isFolder() {
		return isDirectory();
	}
	public boolean isLazy() {
		return isDirectory();
	}
}














