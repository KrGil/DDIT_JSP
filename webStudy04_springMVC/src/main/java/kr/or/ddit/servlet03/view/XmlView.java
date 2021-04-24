package kr.or.ddit.servlet03.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;


public class XmlView extends AbstractView{

	@Override
	public void mergeModelAndView(Object target, HttpServletResponse resp) throws IOException { // 호출자에게 넘길때
		try(
				PrintWriter out = resp.getWriter();
		){
			JAXBContext context = JAXBContext.newInstance(target.getClass());
			Marshaller marshaller =  context.createMarshaller();
			marshaller.marshal(target, out);
			
		} catch (JAXBException e) {
			throw new IOException(e); //
		}
	}

}
