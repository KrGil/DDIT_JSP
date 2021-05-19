package kr.or.ddit.servlet03.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class XmlView extends AbstractView{

	@Override
	public void mergeModelAndView(Object target, HttpServletResponse resp)
			throws IOException {
		try(
			PrintWriter out = resp.getWriter();
		) {
			JAXBContext context = JAXBContext.newInstance(target.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.marshal(target, out);
		} catch (JAXBException e) {
			throw new IOException(e);
		}
	}

}















