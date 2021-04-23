package kr.or.ddit.mvc.filter.wrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

public class MultipartHttpServletRequest extends HttpServletRequestWrapper{
	private Map<String, List<MultipartFile>> fileMap;
	
	
	public MultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		fileMap = new LinkedHashMap<>();
		parseRequest(request);
	}


	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		Collection<Part> parts = request.getParts();
		for(Part tmp : parts) {
			if(tmp.getContentType()==null) continue;
			String partName = tmp.getName();
			List<MultipartFile> list = fileMap.get(partName);
			if(list==null) {
				list = new ArrayList<>();
				fileMap.put(partName, list);
			}
			list.add(new MultipartFile(tmp));
		}
	}

	public Map<String, List<MultipartFile>> getFileMap() {
		return fileMap;
	}
	
	public MultipartFile getFile(String name){
		List<MultipartFile> files = fileMap.get(name);
		MultipartFile file = null;
		if(files!=null && files.size() > 0)
			file = files.get(0);
		return file;
	}
	
	public List<MultipartFile> getFiles(String name){
		return fileMap.get(name);
	}
}










