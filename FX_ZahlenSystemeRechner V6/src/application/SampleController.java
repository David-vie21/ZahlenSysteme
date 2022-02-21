package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleController {
		public Main main;
		private ZahlenSysteme_umwandler zs;
		
		//Umwandler
	@FXML private Label label;
	@FXML private TextField txEingabe;
	@FXML private TextField txEZ;
	@FXML private Label txAusgabe;
	@FXML private TextField txAusgabeZS;
		  
			 //Rechner Varibalen
	@FXML private Label ergebnisR;
	@FXML private TextField e1;
	@FXML private TextField e2;
	@FXML private TextField z1;
	@FXML private TextField z2;
	@FXML private TextField aZ;
	@FXML private TextField zeichen;
		  private boolean test = true; //setzt wenn zahlen Systeme leer sind automatisch decimal system
			 
	    
	    public void setMain(Main main) {
	    	this.main = main;
	    	
	    }
	    
	    @FXML
	    public void berechnen() throws EigeneCheckedException {
	    	
	    		System.out.println("berechner");
		    	String eingabe = txEingabe.getText();
		    	//auf minus prüfen
		    	boolean minus = false;
		    	String eingabeOhneMinus  = "";
		    	for (char c : eingabe.toCharArray()) {
					if (c == '-') {
						minus = true;
					}
					else {
						eingabeOhneMinus = eingabeOhneMinus +c;
					
					}
				}
		    	
		    	
		    	System.out.println(eingabe);
		    	String eingabeZS = txEZ.getText();
		    	String ausgabeZS = txAusgabeZS.getText();
			if(eingabeOhneMinus == null || eingabeZS == null || ausgabeZS == null || eingabe == "" || eingabeZS == "" || ausgabeZS == "" )
			{
				txAusgabe.setText("Eingabe Fehlerhaft");
				throw new EigeneCheckedException("Eingabe leer");				
			}
	    	
	    	
	    	int iEingabeZS = Integer.parseInt(eingabeZS);
	    	int iAusgabeZS = Integer.parseInt(ausgabeZS);
	    	
	    	String ausgabeE = zs.Konverter(eingabeOhneMinus, iEingabeZS, iAusgabeZS);
	    	if(minus)
	    	{
	    		System.out.println("Ergebnis: "+"-"+ausgabeE);
		    	txAusgabe.setText("Ergebnis: "+"-"+ausgabeE);
	    	}
	    	else {
	    		System.out.println("Ergebnis: "+ausgabeE);
		    	txAusgabe.setText("Ergebnis: "+ausgabeE);
	    	}
	    	    
	    }
	    
	    @FXML
	    public void rechner() throws EigeneCheckedException{  //Rechner NICHT Konverter
	    //	String eR = ZahlenSysteme_umwandler.rechner2("10",10,"10",10,10,"+");
	    	//System.out.println("Zwischen Ergebnisse: "+eR);
	    	ergebnisR.setText(" ----");
	    	
	    	//eingabe variablen
	    	String e1R = e1.getText();
	    	
	    	//Falls zahlenSystem 1 leer ist ==> Dezimal verwenden oder Fehlermedlung
	    	int z1R;
	    	if(z1.getText() == "" && test == true)
	    	{
	    		z1R = 10;	    		
	    	}
	    	else 
	    		if(z1.getText() == "")
	    		{
	    			throw new EigeneCheckedException("Kein Zahlensystem eingeben");
	    		}
	    	{	z1R = Integer.parseInt(z1.getText());	}
	    	
	    	
	    	String e2R = e2.getText();
	    	
	    	//Falls zahlenSystem 2 leer ist ==> Dezimal verwenden
	    	int z2R;
	    	if(z2.getText() == "" && test == true)
	    	{
	    		z2R = 10;
	    		
	    	}
	    	else 
	    	{	
	    		if(z2.getText() == "")
	    		{
	    			throw new EigeneCheckedException("Kein Zahlensystem eingeben");
	    		}
	    		z2R = Integer.parseInt(z2.getText());	
	    		}
	    	
	    	//standert wert für ausgabesysten
	    	int aZR;
	    	if(aZ.getText()  == "" && test == true)
	    	{
	    		aZR = 10; //standert Wert
	    		
	    	}
	    	else
	    	{
	    		if(aZ.getText() == "")
	    		{
	    			throw new EigeneCheckedException("Kein Zahlensystem eingeben");
	    		}
	    		aZR = Integer.parseInt(aZ.getText());
	    	}
	    	
	    	//nicht durch 0 dividiren
	    	
	    	
	    	String z = zeichen.getText();
	    	System.out.println("Rechner: " + e1R + " : "+z1R+" : " + e2R + " : "+z2R + " : " + z+ " : " + aZR);
	    	System.out.println(e2R + z);
	    	
	    	if(z == "/" && e2R == "0")
	    	{
	    		System.out.println("0 division");
	    		throw new EigeneCheckedException("Man darf nicht durch 0 dividieren");
	    	}
	    	
	    	//Minus variablen
	    	//boolean e1M = isMinus(e1R);
	    	//boolean e2M = isMinus(e2R);
	    	
	    	
	    	
	    	
	    	//if(isKomma(e1R) || isKomma(e2R))
	    	//if(true) //test für Division wo eine Kommer Zahl rauskommt
	    	//{
	    	String ErgebnisKomma = ZahlenSysteme_umwandler.rechnerKomma(e1R,z1R,e2R,z2R,aZR,z);
	    	ergebnisR.setText(" Ergebnis: "+" "+ErgebnisKomma);
	    	
	    //}
//	    	else { //unnütz da der Kommer rechner alles kann
//	    		String Ergebnis = ZahlenSysteme_umwandler.rechner2(e1R,z1R,e2R,z2R,aZR,z);
//	    		ergebnisR.setText(" Ergebnis: "+" "+Ergebnis);
//	    	}
	    	
	    	
	    
	    	//ergebnisR.setText(zs.rechner(e1R,z1R,e2R,z2R,aZR,z));
	    	//ergebnisR.setText(ZahlenSysteme_umwandler.rechnerKomma(e1R,z1R,e2R,z2R,aZR,z));
	    } 
	    
	    public static boolean isKomma(String text) {
			boolean is = false;
			for (char c : text.toCharArray()) {
				if (c == ',') {
					is = true;
				}				

			}
			return is;
		}
	    
	    public boolean isMinus(String s) //Prüft auf minus
	    {
	    	for (char c : s.toCharArray())
	    	{
				if(c == '-')
				{
					return true;
				}
				
			}
	    	return false;	    	
	    }
	
}
