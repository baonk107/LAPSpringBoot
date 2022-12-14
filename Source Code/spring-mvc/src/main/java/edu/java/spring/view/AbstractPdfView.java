package edu.java.spring.view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;


public abstract class AbstractPdfView extends AbstractView {
    public AbstractPdfView() {
        setContentType("application/pdf");
    }

    @Override
    protected boolean generatesDownloadContent() {
        return false;
    }

    protected Document newDocument() {
        return new Document(PageSize.A4);
    }

    protected PdfWriter newWriter(Document document, OutputStream os) throws DocumentException {
        return PdfWriter.getInstance(document, os);
    }


    protected void prepareWriter(Map<String, Object> model,
                                 PdfWriter writer,
                                 HttpServletRequest request) throws Exception {
        writer.setViewerPreferences(getViewerPreferences());
    }

    protected int getViewerPreferences() {
        return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {
        ByteArrayOutputStream byteOutput = createTemporaryOutputStream();

        Document document = newDocument();
        PdfWriter writer = newWriter(document, byteOutput);
        prepareWriter(map, writer, httpServletRequest);
        buildPdfMetadata(map, document, httpServletRequest);

        document.open();
        buildPdfDocument(map, document, writer, httpServletRequest, httpServletResponse);
        document.close();

        writeToResponse(httpServletResponse, byteOutput);
    }

    private void buildPdfMetadata(Map<String, Object> map,
                                  Document document,
                                  HttpServletRequest httpServletRequest) {
    }

    protected abstract void buildPdfDocument(Map<String, Object> map, Document document,
                                             PdfWriter writer,
                                             HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) throws DocumentException;
}
