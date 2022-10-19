package edu.java.spring.controller;

import com.itextpdf.text.pdf.qrcode.Mode;
import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.JavaClazz;
import edu.java.spring.model.Student;
import edu.java.spring.utils.XSLTUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("clazz")
public class ClazzController {
    @Autowired
    private StudentDAO studentDAO;

    //Convert Object to XML
    @RequestMapping(value = "xml", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody JavaClazz viewInXml() {
        return new JavaClazz(studentDAO.list());
    }

    //Convert Object to JSON
    @RequestMapping(value = "json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody JavaClazz viewInJson() {
        return new JavaClazz(studentDAO.list());
    }

    //XSLT View Controller
    @RequestMapping(value = "xslt", method = RequestMethod.GET)
    public ModelAndView viewXSLT() throws JAXBException, ParserConfigurationException, IOException, SAXException {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("ClazzView");
        model.getModelMap().put("data", XSLTUtils.clazzToDomSource(clazz));
        return model;
    }

    //View Excel
    @RequestMapping(value = "excel")
    public ModelAndView viewExcel() {
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("excelView");
        model.getModelMap().put("clazzObj", clazz);
        return model;
    }

    //View Pdf
    @RequestMapping(value = "pdf", produces = "application/pdf")
    public ModelAndView viewPdf(){
        JavaClazz clazz = new JavaClazz(studentDAO.list());
        ModelAndView model = new ModelAndView("pdfView");
        model.getModelMap().put("clazzObj", clazz);
        return model;
    }

    //View Report
    @RequestMapping(value = "report", produces = "application/pdf")
    public ModelAndView viewReport(){
        JRDataSource dataSource = new JRBeanCollectionDataSource(studentDAO.list());
        ModelAndView mv = new ModelAndView("pdfReport");
        mv.addObject("dataSource", dataSource);
        return mv;
    }
}
