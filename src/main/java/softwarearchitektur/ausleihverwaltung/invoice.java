package softwarearchitektur.ausleihverwaltung;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class invoice {
    PDDocument invc;
    int n;
    Double total = 0.0;
    Double price;
    String CustName;
    String CustPh;
    List<String> ProductName = new ArrayList<String>();
    List<Double> ProductPrice = new ArrayList<Double>();
    List<Integer> ProductQty = new ArrayList<Integer>();
    String InvoiceTitle = new String("Leihservice - Ausleihen statt Kaufen");
    String SubTitle = new String("Rechnung");

    //Using the constructor to create a PDF with a blank page
    invoice() {
        //Create Document
        invc = new PDDocument();
        //Create Blank Page
        PDPage newpage = new PDPage();
        //Add the blank page
        invc.addPage(newpage);
    }

    //getdata() method is used to get the customer information and the product details from the input stream
//    void getdata() {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the Customer Name: ");
//        CustName = sc.nextLine();
//        System.out.println("Enter the Customer Phone Number: ");
//        CustPh = sc.next();
//        System.out.println("Enter the Number of Products: ");
//        n = sc.nextInt();
//        System.out.println();
//        for(int i=0; i<n; i++) {
//            System.out.println("Enter the Product Name: ");
//            ProductName.add(sc.next());
//            System.out.println("Enter the Price of the Product: ");
//            ProductPrice.add(sc.nextInt());
//            System.out.println("Enter the Quantity of the Product: ");
//            ProductQty.add(sc.nextInt());
//            System.out.println();
//            //Calculating the total amount
//            total = total + (ProductPrice.get(i)*ProductQty.get(i));
//        }
//    }

    void setData(String customerName,String eMail,List<String> artikel,List<Integer> artikelnummer,List<Double> preise) {
        CustName = customerName;
        //Email adresse!!!- nicht Phone Number
        CustPh = eMail;
        ProductName = artikel;
        System.out.println(ProductName.size());
        ProductPrice = preise;
        ProductQty = artikelnummer;

        for (Double preis : ProductPrice) {
                total = total + preis;
        }

        n = artikel.size();
    }

    ByteArrayInputStream WriteInvoice(){
        //get the page
        PDPage mypage = invc.getPage(0);
        try {
            //Prepare Content Stream
            PDPageContentStream cs = new PDPageContentStream(invc, mypage);

            //TODO gibt irgendeinen error in Zeile 85 // geht allerdings trotzdem
            //Writing Single Line text
            //Writing the Invoice title
            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 20);
            cs.newLineAtOffset(140, 750);
            cs.showText(InvoiceTitle);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 18);
            cs.newLineAtOffset(270, 690);
            cs.showText(SubTitle);
            cs.endText();

            //Writing Multiple Lines
            //writing the customer details
            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(60, 610);
            cs.showText("Kunden Name: ");
            cs.newLine();
            cs.showText("E-Mail Adresse: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.setLeading(20f);
            cs.newLineAtOffset(170, 610);
            cs.showText(CustName);
            cs.newLine();
            cs.showText(CustPh);
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(80, 540);
            cs.showText("Artikelname");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(200, 540);
            cs.showText("Artikelpreis");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(310, 540);
            cs.showText("Artikelnummer");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(410, 540);
            cs.showText("Preis");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(80, 520);
            for(int i =0; i<n; i++) {
                cs.showText(ProductName.get(i));
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(200, 520);
            for(int i =0; i<n; i++) {
                cs.showText(ProductPrice.get(i).toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(310, 520);
            for(int i =0; i<n; i++) {
                cs.showText(ProductQty.get(i).toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 12);
            cs.setLeading(20f);
            cs.newLineAtOffset(410, 520);
            for(int i =0; i<n; i++) {
                price = ProductPrice.get(i);
                cs.showText(price.toString());
                cs.newLine();
            }
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            cs.newLineAtOffset(310, (500-(20*n)));
            cs.showText("Gesamtpreis: ");
            cs.endText();

            cs.beginText();
            cs.setFont(PDType1Font.TIMES_ROMAN, 14);
            //Calculating where total is to be written using number of products
            cs.newLineAtOffset(410, (500-(20*n)));
            cs.showText(total.toString());
            cs.endText();

            //Close the content stream
            cs.close();
            //Save the PDF
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            invc.save(out);
            invc.close();
            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

            //invc.save("src/main/resources/pdfSave/test.pdf");

            return in;

        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }


    public static void main(String args[]) {
        List<String> artikel2 = new ArrayList<>();
        artikel2.add("Buch1");
        artikel2.add("Handsäge9000");

        List<Double> preise2 = new ArrayList<Double>();
        preise2.add(7.99);
        preise2.add(50.69);

        List<Integer> nummer2 = new ArrayList<>();
        nummer2.add(20);
        nummer2.add(99);

        invoice i = new invoice();

        i.setData("Dennis","dennis.zimmer@gmx.net",artikel2,nummer2,preise2);
        i.WriteInvoice();
        System.out.println("Invoice Generated!");
    }
}
