/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olmectron.material.hardware;
import com.olmectron.material.components.MaterialSelector;
import com.olmectron.material.components.MaterialToast;
import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableSet;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;

/**
 *
 * @author Édgar
 */
public class MaterialPrinter {
    private static ObservableSet<Printer> printers=Printer.getAllPrinters();
    public MaterialPrinter(){
        selector=new  MaterialSelector();
        selector.setStyle(selector.getStyle()+" -fx-min-width: 300px;");
        printers.forEach(new Consumer<Printer>() {
            
                @Override
                public void accept(Printer t) {
                    selector.addItem(t.getName());
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        selector.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            onSelectionChange(selector.getItems().get((Integer)newValue).toString());
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        });
            
            
        }
    
        public void onSelectionChange(String newValue){
            
        }
    public static Printer getDefaultPrinter(){
        return Printer.getDefaultPrinter();
    }
    public static String getDefaultPrinterName(){
        return Printer.getDefaultPrinter().getName();
    }
    private MaterialSelector<String> selector;
    public MaterialSelector getPrinterSelector(){
        
        
        
        return selector;
    }
    public void selectPrinter(String printerName){
        selector.getSelectionModel().select(printerName);
    }
    
    private static Printer p=null;
    private static void retrievePrinter(Printer t){
        p=t;
    }
    public static Printer getSelectedPrinter(){
        return p;
    }
    public static Printer getPrinter(String printerName){
        
        printers.forEach(new Consumer<Printer>() {
            
                @Override
                public void accept(Printer t) {
                    if(printerName.equalsIgnoreCase(t.getName())){
                        retrievePrinter(t);
                        
                    }
                    
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        System.out.println(p.getName());
        return p;
        
    }
    public static void printNode(Node node, Printer printer){
        PrinterJob job = PrinterJob.createPrinterJob();
        
 if (job != null) {
     job.setPrinter(printer);
     
    boolean success = job.printPage(node);
    if (success) {
        job.endJob();
    }
 }
 else{
     new MaterialToast("Error al imprimir").unhide();
 }
    }
    
    /* private Code128Bean bean;    
    private void setJob(){
        barcodeJob= PrinterJob.createPrinterJob()
    barcodeJob.setPrintable(new HelloBarcodePrinter());
    }
    private void setPrinter(String printerr){
         PrintService[] printServices;
        PrintService printService;
        PageFormat pageFormat;

        String printerName = printerr;

        PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
        printServiceAttributeSet.add(new PrinterName(printerName, null));       
        printServices = PrintServiceLookup.lookupPrintServices(null, printServiceAttributeSet); 




        try{
            printService = printServices[0];
            barcodeJob.setPrintService(printService);   // Try setting the printer you want
        }catch (ArrayIndexOutOfBoundsException e){
                System.err.println("Error: No printer named '"+printerName+"', using default printer.");
                pageFormat = barcodeJob.defaultPage();  // Set the default printer instead.
        }catch (PrinterException exception) {
            System.err.println("Printing error: " + exception);
        }
    }
    private void initBean(){
        

            
        
            
         bean= new Code128Bean();

              

             final int dpi = 150;

         

             //Configure the barcode generator

             bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi)*4); //makes the narrow bar 
             bean.setHeight(40);
             bean.setFontSize(8);                                                              //width exactly one pixel

             //bean.setWideFactor(3);

             bean.doQuietZone(false);
        }
    private static PrinterJob barcodeJob;
        public void print() throws PrinterException {
    
  }
        private final int LABEL_HORIZONTAL_DISTANCE=200;
        private final int LABEL_VERTICAL_DISTANCE=75;
        private Java2DCanvasProvider canvas;

    public JTextField getCodeTextField() {
        return this.codigoTextField;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private class HelloBarcodePrinter
              implements Printable {
      private Paper myPaper=new Paper();
           
      @Override
        public int print(Graphics g, PageFormat pf, int page)
             throws PrinterException {
          
          
          System.out.println(page+"");
          if(page>5){
              
              System.out.println("Impresión Finalizada");
              return NO_SUCH_PAGE;
          }
          int k=0;
          
          for(int j=0;j<10;j++){
                
              Graphics[] gArray =new Graphics[3];
                gArray[0]=g.create();
                gArray[1]=g.create();
                gArray[2]=g.create();
          
              for(int i=0;i<3;i++){
              
                  String[] arregloString=new String[]{"","","","",""};
                  if(lista!=null){
                      arregloString=lista.get(k);
                  }
                myPaper.setImageableArea((i*LABEL_HORIZONTAL_DISTANCE)+20,(LABEL_VERTICAL_DISTANCE*(j)-(j*2.5))+54, 800,800);
                  pf.setPaper(myPaper);
                  Graphics2D g2d = (Graphics2D)gArray[i];
                 g2d.translate(pf.getImageableX(), pf.getImageableY());

                 if (page > 0) {
                      return NO_SUCH_PAGE;
                 }
                 gArray[i].setFont(gArray[i].getFont().deriveFont(10f));
                 gArray[i].setFont(gArray[i].getFont().deriveFont(Font.BOLD));
                 gArray[i].drawString(arregloString[1],90,8);
                 gArray[i].setFont(gArray[i].getFont().deriveFont(Font.PLAIN));
                 gArray[i].drawString(arregloString[2],90,19);
                 gArray[i].drawString(arregloString[4],90,41);
                 gArray[i].setFont(gArray[i].getFont().deriveFont(7.5f));
                 gArray[i].drawString(arregloString[3],90,30);
                 gArray[i].setFont(gArray[i].getFont().deriveFont(9f));
                 if(!arregloString[1].equals("")){
                     ZAPA="Zapatería Regina";
                 }
                 gArray[i].drawString(ZAPA,85,50);
                 
                 ZAPA="";
                 //if (bargen == null || msg == null) {
                  //return;
              //}

              if (canvas == null) {
                  canvas = new Java2DCanvasProvider(g2d, 0);
              } else {
                  canvas.setGraphics2D(g2d);
              }

              g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                      RenderingHints.VALUE_ANTIALIAS_ON);
              g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, 
                      RenderingHints.VALUE_FRACTIONALMETRICS_ON);

              try {
                  AffineTransform baktrans = g2d.getTransform();
                  try {
                      //set up for painting
                      //transformAsNecessary(g2d);
                      g2d.setColor(Color.black);

                      //now paint the barcode

                      bean.generateBarcode(canvas, arregloString[0]);

                      //   fireSuccessNotification();
                  } finally {
                      g2d.setTransform(baktrans);

                  }
              } catch (Exception e) {
                  //gArray[i].setColor(Color.red);
                  //gArray[i].drawLine(0, 0, getWidth(), getHeight());
                  //gArray[i].drawLine(0, getHeight(), getWidth(), 0);
                 // fireErrorNotification(e);
              }
              k++;
      }}
          
           return PAGE_EXISTS;
          
         }
    };
    public static void printBarcode(){
        try {
            barcodeJob.print();
        } catch (PrinterException ex) {
            
        }
    }*/
}
