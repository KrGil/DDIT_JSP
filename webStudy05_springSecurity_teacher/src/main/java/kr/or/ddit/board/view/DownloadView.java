package kr.or.ddit.board.view;

import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.AttatchVO;

public class DownloadView extends AbstractView {
	private static final Logger logger =
			LoggerFactory.getLogger(DownloadView.class);

	@Value("#{appInfo.attatchPath}")
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		logger.info("{} 초기화, {} 주입됨."
				, getClass().getSimpleName()
				, saveFolder.getAbsolutePath());
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model
			, HttpServletRequest req,
			HttpServletResponse resp) throws Exception {
		
		AttatchVO attatch = (AttatchVO) model.get("attatch");
		String agent = req.getHeader("User-Agent");
		
		File saveFile = new File(saveFolder, attatch.getAtt_savename());
		
		String filename = attatch.getAtt_filename();
		if(StringUtils.containsIgnoreCase(agent, "trident")) {
			filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", " ");
		}else {
			byte[] bytes = filename.getBytes();
			filename = new String(bytes, "ISO-8859-1");
		}
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+filename+"\"");
		resp.setHeader("Content-Length", attatch.getAtt_size()+"");
		resp.setContentType("application/octet-stream");
		try(
			OutputStream os = resp.getOutputStream();	
		){
			FileUtils.copyFile(saveFile, os);
		}

	}

}
