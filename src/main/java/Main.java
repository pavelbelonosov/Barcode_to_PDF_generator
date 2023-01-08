
import java.util.HashMap;
import java.util.Map;

/*
На вход в качестве аргументов приложение принимает путь к файлу, содержащему текст для баркодов,
а также тип кода - qr или ean. Первой строкой можно указывать эндпоинт, который будет зашит в QR-код.
*/
public class Main {

    public static void main(String[] args) {
        System.out.println("Start");
        FileReader fr = new FileReader(args[0]);
        Map<String, byte[]> map = new HashMap<>();

        try {
            System.out.println("Reading file...");
            fr.read();
            System.out.println("Generating barcodes...");
            String endpoint = (!fr.getEndpointName().equals("") ? fr.getEndpointName() + "?id=" : "");

            fr.getContent().forEach(line -> {
                try {
                    if (args[1].equalsIgnoreCase("qr")) {
                        map.put(line, BarcodeGenerator.generateQRCodeImage(endpoint + line));
                    } else if (args[1].equalsIgnoreCase("ean")) {
                        map.put(line, BarcodeGenerator.generateEAN13BarcodeImage(line));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });

            System.out.println("Saving PDF...");
            PDFcreator.createPdf(map);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Done successfully...");
    }
}
