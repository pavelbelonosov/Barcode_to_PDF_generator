

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.io.FileNotFoundException;
import java.util.Map;

public class PDFcreator {

    private final static String dest = "./result.pdf";

    public static void createPdf(Map<String, byte[]> barcodesList) throws FileNotFoundException {

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        Table table = new Table(UnitValue.createPercentArray(3)).setAutoLayout();
        barcodesList.entrySet().forEach(entry -> {
            ImageData data = ImageDataFactory.create(entry.getValue());
            Image img = new Image(data);
            Paragraph paragraph = new Paragraph();
            paragraph.setTextAlignment(TextAlignment.CENTER);
            paragraph.add(entry.getKey());
            paragraph.add(img);
            table.addCell(paragraph);
        });
        document.add(table);
        document.close();

    }
}
