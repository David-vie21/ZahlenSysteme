/*
 * Dieses Programm wandelt Zahlen in verschiedene Systeme um (2-36) und kann damit rechnen!
   Die Methoden zur umwandlung wurden selber entwickelt und es wurden keine externen Librarys zur umwandlung verwendet.
  
   (Zum Potenzieren wurde eine Library verwendet => java.lang.Math) Änderung=> wurde durch eine selbstgeschriebene ersetzt
   Das ganze dient dem Zweck der Übung.
   @David Ankenbrand   
*/

package application;


import java.util.ArrayList;
import java.util.List;

public class ZahlenSysteme_umwandler {

	static List<Double> eD = new ArrayList<Double>(); // Zwischenspeicher für Nachkommastellen-zerlegung
	boolean isMinus = false;
	static boolean deBug = true; // Sys.out.Println Schreiben oder nicht schreiben

	public ZahlenSysteme_umwandler() {
		// TODO Auto-generated constructor stub
	}

	// Main Metode zum Testen
	public static void main(String[] args) throws EigeneCheckedException {
		// aufDec("2a2b", 13); // vonDec(155,16); //
		// sysOutP(Konverter("a",16, 10)); // aTest("2a");
		// String test = vonDecNachkomma(10, 16);
		// sysOutP(test);
		// Konverter("10,12", 10, 10);
		// aufDecNachkomma("5", 10);
		// infoBox("YOUR INFORMATION HERE", "TITLE BAR MESSAGE");
		// sysOutP(isAlpha2("l"));
		// sysOutP(isMinus("10"));

		// rechner2("-11", 10, "-12", 10, 10, "+");
		// System.out.println(rechnerKomma("11",10,"3",10,2,"/"));
		// System.out.println(round(10.123456789 , 5));
		double d =pot(5,-2);
		
		sysOutP(String.valueOf(d));
	}

	public static void sysOutP(String s) // Erstzen von sysOutP
	{
		if (deBug) {
			System.out.println(s);
		}
	}

	// Test rechner

	public static String rechnerKomma(String e1, int z1, String e2, int z2, int zA, String zeichen)
			throws EigeneCheckedException {
		// Minues Merken und erstmals rausnehmen
		boolean e1m = false;
		boolean e2m = false;
		// minus erkennen, merken und löschen
		if (isMinus(e1)) {
			e1 = e1.replace("-", "");
			e1m = true;
		}

		if (isMinus(e2)) {
			e2 = e2.replace("-", "");
			e2m = true;
		}

		// e1 zerlegen:

		boolean komma = false;
		String vorKomma1 = "";
		String nachKomma1 = "";

		for (char c : e1.toCharArray()) {

			if (c == ',') {
				if (komma) { // zu viele kommas
					throw new EigeneCheckedException("Zu viele Komma!");
				}
				komma = true;
			}

			if (!komma) { // wenn noch kein Komma vorgekommen ist dann wird es in VorKomma sortier
				if (c != ',') {
					vorKomma1 = vorKomma1 + c;
				}
			} else {// wenn schon ein Komma vorgekommen ist dann wird es in Nachkomma sortier
				if (c != ',') {
					nachKomma1 = nachKomma1 + c;
				}
			}

		}
		// e2 zerlegen
		boolean komma2 = false;
		String vorKomma2 = "";
		String nachKomma2 = "";
		for (char c : e2.toCharArray()) {

			if (c == ',') {
				if (komma2) { // zu viele kommas
					throw new EigeneCheckedException("zu viele komma");
				}
				komma2 = true;
			}

			if (!komma2) { // wenn noch kein Komma vorgekommen ist dann wird es in VorKomma sortier
				if (c != ',') {
					vorKomma2 = vorKomma2 + c;
				}
			} else {// wenn schon ein Komma vorgekommen ist dann wird es in Nachkomma sortier
				if (c != ',') {
					nachKomma2 = nachKomma2 + c;
				}
			}

		}
		// Rechner mit Komma
		sysOutP("Rechner: ");

		int zwischenS1 = aufDec(vorKomma1, z1);
		int zwischenS2 = aufDec(vorKomma2, z2);

		double zwischenS3;
		double zwischenS4;
		// prüfen ob komma vorhanden
		if (nachKomma1 == "") {
			zwischenS3 = 0;
		} else {
			zwischenS3 = aufDecNachkomma(nachKomma1, z1);
		}

		if (nachKomma2 == "") {
			zwischenS4 = 0;
		} else {
			zwischenS4 = aufDecNachkomma(nachKomma2, z2);
		}

		// Wenn Minus-zahl dabei => jetzt auf minus bringen
		if (e1m == true) {
			zwischenS1 *= -1;
			zwischenS3 *= -1;
		}
		if (e2m == true) {
			zwischenS2 *= -1;
			zwischenS4 *= -1;

		}

		double ergebnisDec;

		// welche Rechnung?
		switch (zeichen) {
		case "+":
			ergebnisDec = zwischenS1 + (zwischenS3 / 10) + zwischenS2 + (zwischenS4 / 10);
			break;

		case "-":
			ergebnisDec = (zwischenS1 + (zwischenS3 / 10)) - (zwischenS2 + (zwischenS4 / 10));
			break;

		case "*":
			ergebnisDec = (zwischenS1 + (zwischenS3 / 10)) * (zwischenS2 + (zwischenS4 / 10));
			break;

		case "/":
			ergebnisDec = (zwischenS1 + (zwischenS3 / 10)) / (zwischenS2 + (zwischenS4 / 10));
			break;

		default:
			throw new EigeneCheckedException("Falscher Operator");
		}
		// ZERLGEN

		String ergebinsmitkomma = Double.toString(ergebnisDec);

		// Minus rausfischen!!
		boolean minusNachkomma = false; // merkt ob minus
		if (isMinus(ergebinsmitkomma)) {
			minusNachkomma = true;
			ergebinsmitkomma = ergebinsmitkomma.replace("-", "");
		}

		boolean komma3 = false;
		String vorKomma3 = "";
		String nachKomma3 = "";
		for (char c : ergebinsmitkomma.toCharArray()) {

			if (c == '.') {
				if (komma3) { // zu viele kommas
					throw new EigeneCheckedException("zu viele komma!!!");
				}
				komma3 = true;
			}

			if (!komma3) { // wenn noch kein Komma vorgekommen ist dann wird es in VorKomma sortier
				if (c != '.') {
					vorKomma3 = vorKomma3 + c;
				}
			} else {// wenn schon ein Komma vorgekommen ist dann wird es in Nachkomma sortier
				if (c != '.') {
					nachKomma3 = nachKomma3 + c;
				}
			}

		}

		// Zerlegen ENDE
		int vorKommaInt = Integer.parseInt(vorKomma3);
		String ergebisVorKomma = vonDec(vorKommaInt, zA);

		// periodische zahlen runden
		// double d = pot(10,);
		if (nachKomma3.length() > 6) {
			nachKomma3 = nachKomma3.substring(0, 6);
		}

		double ergebnisNachkommaDouble = Double.parseDouble(nachKomma3);
		// ergebnisNachkommaDouble = Math.rint(ergebnisNachkommaDouble * d);

		String ergebnisNachkomma = vonDecNachkomma(ergebnisNachkommaDouble, zA);

		String ergebnisEnde = ergebisVorKomma + "," + ergebnisNachkomma;
		// wenn ein minus dabei ist ==> wird ein minus wieder einfügen
		if (minusNachkomma) {
			ergebnisEnde = "-" + ergebnisEnde;
		}

		sysOutP("\n \n");
		sysOutP("Ergebnis Aus rechnung: " + ergebnisEnde);

		return ergebnisEnde;
	}

	public static String rechner2(String e1, int z1, String e2, int z2, int zA, String zeichen)
			throws EigeneCheckedException { // in Laptop version übernehmen
		// test lauf: rechner("A04bc",16,"11000",2,10);
		sysOutP("Rechner: ");

		// minus merk variblen
		boolean e1m = false;
		boolean e2m = false;
		// minus erkennen, merken und löschen
		if (isMinus(e1)) {
			e1 = e1.replace("-", "");
			e1m = true;
		}

		if (isMinus(e2)) {
			e2 = e2.replace("-", "");
			e2m = true;
		}
		// -------

		// auf dezimal bringen
		int zwischenS1 = aufDec(e1, z1);
		int zwischenS2 = aufDec(e2, z2);

		// Wenn Minus zahl dabei jetzt auf minus bringen
		if (e1m == true) {
			zwischenS1 *= -1;
		}
		if (e2m == true) {
			zwischenS2 *= -1;
		}

		// welche Rechnung?
		int ergebnisDec;
		// double eDouble;

		switch (zeichen) {
		case "+":
			ergebnisDec = zwischenS1 + zwischenS2;

			break;

		case "-":
			ergebnisDec = zwischenS1 - zwischenS2;
			break;

		case "*":
			ergebnisDec = zwischenS1 * zwischenS2;
			break;

		case "/":
			ergebnisDec = zwischenS1 / zwischenS2;
			break;

		default:
			throw new EigeneCheckedException("Falscher Operator");
		}
		// falls minus Zahl ==> umwandeln auf positive zahl und Minus merken
		sysOutP("Ergebnis DEC MINUS :" + ergebnisDec);
		boolean minusMerkerErgebnis = false;
		if (ergebnisDec < 0) {
			ergebnisDec *= -1;
			minusMerkerErgebnis = true;
		}

		String ergebisZA = vonDec(ergebnisDec, zA);
		// fals minus auf Minus brigen
		if (minusMerkerErgebnis) {
			ergebisZA = "-" + ergebisZA;
		}
		sysOutP("\n \n");
		sysOutP("Ergebnis Aus rechnung: " + ergebisZA);
		return ergebisZA;

	}

	public String rechner(String e1, int z1, String e2, int z2, int zA, String zeichen) throws EigeneCheckedException { // in
																														// Laptop
																														// version
																														// übernehmen
		// test lauf: rechner("A04bc",16,"11000",2,10);
		sysOutP("Rechner: ");

		int zwischenS1 = aufDec(e1, z1);
		int zwischenS2 = aufDec(e2, z2);

		int ergebnisDec;

		// welche Rechnung?
		switch (zeichen) {
		case "+":
			ergebnisDec = zwischenS1 + zwischenS2;
			break;

		case "-":
			ergebnisDec = zwischenS1 - zwischenS2;
			break;

		case "*":
			ergebnisDec = zwischenS1 * zwischenS2;
			break;

		case "/":
			ergebnisDec = zwischenS1 / zwischenS2;
			break;

		default:
			throw new EigeneCheckedException("Falscher Operator");
		}

		String ergebisZA = vonDec(ergebnisDec, zA);

		sysOutP("\n \n");
		sysOutP("Ergebnis Aus rechnung: " + ergebisZA);
		return ergebisZA;

	}

	public void kommaZerleger(String eingabe) throws EigeneCheckedException {
		boolean komma = false;
		String vorKomma = "";
		String nachKomma = "";
		for (char c : eingabe.toCharArray()) {

			if (c == ',') {
				if (komma) { // zu viele kommas
					throw new EigeneCheckedException("zu viele komma");
				}
				komma = true;
			}

			if (!komma) { // wenn noch kein Komma vorgekommen ist dann wird es in VorKomma sortier
				if (c != ',') {
					vorKomma = vorKomma + c;
				}
			} else {// wenn schon ein Komma vorgekommen ist dann wird es in Nachkomma sortier
				if (c != ',') {
					nachKomma = nachKomma + c;
				}
			}

		}

	}

	public static String Konverter(String startWert, int startZahlenSystem, int endZahlensystem)
			throws EigeneCheckedException {

		String r;
		String rV;
		String rN;

		// kommaZerleger();
		boolean komma = false;
		String vorKomma = "";
		String nachKomma = "";
		for (char c : startWert.toCharArray()) {

			if (c == ',') {
				if (komma) { // zu viele kommas
					throw new EigeneCheckedException("zu viele komma");
				}
				komma = true;
			}

			if (!komma) { // wenn noch kein Komma vorgekommen ist dann wird es in VorKomma sortier
				if (c != ',') {
					vorKomma = vorKomma + c;
				}
			} else {// wenn schon ein Komma vorgekommen ist dann wird es in Nachkomma sortier
				if (c != ',') {
					nachKomma = nachKomma + c;
				}
			}

		}

		if (!komma) {
			r = vonDec(aufDec(startWert, startZahlenSystem), endZahlensystem);
		} else {
			rV = vonDec(aufDec(vorKomma, startZahlenSystem), endZahlensystem);
			// double vDnK = aufDecNachkomma(nachKomma, startZahlenSystem);
			// //vonDecNachkomma()
			rN = vonDecNachkomma(aufDecNachkomma(nachKomma, startZahlenSystem), endZahlensystem);
			r = rV + "," + rN;
		}

		sysOutP(r);
		return r;

	}

	public static String[] eingabeUmwandler(String eingabeD) // Um Buchstaben in Zahlen umzuwandeln
	{

		String eingabe = eingabeD.toUpperCase();
		sysOutP("TO UPPER CASE: " + eingabe);
		String[] split2 = s_Split(eingabe);

		for (int i = 0; i < split2.length; i++) {
			switch (split2[i]) // Zahlen über 9 in Buchstaben umwandeln / unter 9 in List übergeben
			{
			case "A":
				split2[i] = "10";
				break;

			case "B":
				split2[i] = "11";
				break;

			case "C":
				split2[i] = "12";
				break;

			case "D":
				split2[i] = "13";
				break;

			case "E":
				split2[i] = "14";
				break;

			case "F":
				split2[i] = "15";
				break;

			case "G":
				split2[i] = "16";
				break;

			case "H":
				split2[i] = "17";
				break;

			case "I":
				split2[i] = "18";
				break;

			case "J":
				split2[i] = "19";
				break;

			case "K":
				split2[i] = "20";
				break;

			case "L":
				split2[i] = "21";
				break;

			case "M":
				split2[i] = "22";
				break;

			case "N":
				split2[i] = "23";
				break;

			case "O":
				split2[i] = "24";
				break;

			case "P":
				split2[i] = "25";
				break;

			case "Q":
				split2[i] = "26";
				break;

			case "R":
				split2[i] = "27";
				break;

			case "S":
				split2[i] = "28";
				break;

			case "T":
				split2[i] = "29";
				break;

			case "U":
				split2[i] = "30";
				break;

			case "V":
				split2[i] = "31";
				break;

			case "W":
				split2[i] = "32";
				break;

			case "X":
				split2[i] = "33";
				break;

			case "Y":
				split2[i] = "34";
				break;

			case "Z":
				split2[i] = "35";
				break;
			}
		}
		return split2;

	}

	// Funktion wird nicht mehr verwendet
	public static String[] s_Split(String number) // Stings zerlegen
	{
		String[] split = number.split("");
		// Ausgabe
		// for(int i=0;i<split.length;i++)
		// sysOutP(split[i]);
		return split;
	}

	public static int aufDec(String eingabe, int ak_zs) throws EigeneCheckedException { // beliebiges Zahlensystem auf
																						// Decimal bringen
		eingabe.toUpperCase();
		// Plausi check
		if (eingabe.length() > 8) // stackoverflow vermeiden
		{
			throw new EigeneCheckedException("Eingabe zu groß \n Stack Overflow Gefahr");
		}
		if (eingabe.isEmpty()) {
			throw new EigeneCheckedException("Eigabe ist leer");
		}

		if (ak_zs < 2 || ak_zs > 36) {
			System.err.println("Die angegebene Basis liegt nicht im Bereich zwischen 2-36");
			throw new EigeneCheckedException(
					"Die angegebene Basis liegt nicht im Bereich zwischen 2-36 \n Falsche Basis");
		}
		// Eingabe prüfen
		if (ak_zs <= 10) // prüfe ob Buchstaben gebraucht werden wenn nicht wird geblockt
		{
			sysOutP("Basis kleiner 11!");
			sysOutP(eingabe);
			if (isAlpha2(eingabe)) // überarbeiten!!!!!
			{
				System.err.println("Die angegebene Basis liegt nicht im Bereich zwischen 11-36");
				throw new EigeneCheckedException(
						"Keine Buchstaben erlaubt \n Die angegebene Basis liegt nicht im Bereich zwischen 11-36");
			}

		}

		else {
			for (char c : eingabe.toCharArray()) {
				String cAufString = Character.toString(c);
				if (isAlpha2(cAufString)) {

					eingabe.toUpperCase();
					// Prüfen auf buchstaben mit charackter stadt swich case
					if (c < 'Z' - (25 - (ak_zs - 10)) && (c <= 'Z' && c >= 'A')) // welche Zahlen darf man verwenden
					{
						sysOutP("zwischen A-Z");
						sysOutP(c + ": ist erlaubt");
					} else if (c < 'z' - (25 - (ak_zs - 10)) && (c <= 'z' && c >= 'a')) {
						sysOutP("zwischen a-z");
						sysOutP(c + ": ist erlaubt");
					} else {
						sysOutP("NICHT ERLAUBT!!!");
						sysOutP(c + ": ist NICHT erlaubt");
						char cU = Character.toUpperCase(c);
						throw new EigeneCheckedException("Dieser Buchstabe: " + cU
								+ " ist bei dem aktuelen Zahlensystem (" + ak_zs + ") nicht erlaubt");
					}

				} else {
					// throw new EigeneCheckedException();
				}
			}

		}
		if (!isAlpha2(eingabe)) {
			String zwischenC;
			int cvonEingabe;
			for (char c : eingabe.toCharArray()) {
				zwischenC = Character.toString(c);
				cvonEingabe = Integer.parseInt(zwischenC);
				sysOutP("ZahlenSystem in Int " + cvonEingabe);
				if (cvonEingabe >= ak_zs) {
					if (cvonEingabe == ',' || cvonEingabe == '-') {

					} else {
						sysOutP("ZahlenSystem in CHAR " + ak_zs);
						throw new EigeneCheckedException(
								"Diese Zahl: " + c + " ist in diesem Zahlensysten: " + ak_zs + " nicht erlaubt!"
										+ " \n " + "Du solltest dir das Thema Zahlensysteme nochmal genauer anschauen");
					}
				}
			}

		}

		// Plausi check ENDE

		sysOutP("");
		sysOutP("Auf Decimal Bringen");
		// ak_zs ==> aktuelles Zahlensystem
		// eingbae ==> eingabe

		// int ak_zs = 16; //aktuelles Zahlensystem
		// String eingabe = "18"; //zu konvertirende Zahl

		// s_Split funktion direkt eingebaut
		// String[] split = eingabe.split(""); //muss ersetze werden ==> / wurde ersetz
		String[] split = eingabeUmwandler(eingabe);
		// String[] split = s_Split(eingabe);

		// Ausgabe String Arry zerlegt Zahl
		for (int i = 0; i < split.length; i++)
			sysOutP("Die einzellen Teile: " + split[i]);

		// Adition Einzellner Teile
		int zwischenSpeicher = 0; // zwischenSpeicher für additon einzellner Stellen
		int stringTOint; // Zerlegte Teile des String Arrays in einen Int speichern
		int pM = 0; // potenz mutiplikator
		int mult; // mulitiplikartor

		for (int i = (split.length - 1); i >= 0; i--) {
			mult = (int) pot(ak_zs, pM); // potenz der Aktuellen Stelle wird berechnet
			stringTOint = Integer.parseInt(split[i]);
			zwischenSpeicher = zwischenSpeicher + (stringTOint * mult); //
			sysOutP("Zwischen Ergebnis: " + zwischenSpeicher);
			pM++;
		}
		// Ausgabe Ergebnis in Decimal
		sysOutP("Ergebnis: " + zwischenSpeicher);
		return zwischenSpeicher;
	}

	public static double aufDecNachkomma(String eingabe, int ak_zs) throws EigeneCheckedException { // beliebiges
																									// Zahlensystem
																									// auf
		// Decimal bringen
		eingabe.toUpperCase();
// Plausi check
		if (eingabe.length() > 8) // stackoverflow vermeiden
		{
			throw new EigeneCheckedException("Eingabe zu groß");
		}
		if (eingabe.isEmpty()) {
			throw new EigeneCheckedException("Eigabe ist leer");
		}

		if (ak_zs < 2 || ak_zs > 36) {
			System.err.println("Die angegebene Basis liegt nicht im Bereich zwischen 2-36");
			throw new EigeneCheckedException(
					"Falsche Basis \n Die angegebene Basis liegt nicht im Bereich zwischen 2-36");
		}
// Eingabe prüfen
		if (ak_zs <= 10) // prüfe ob Buchstaben gebraucht werden wenn nicht wird geblockt
		{
			sysOutP("Basis kleiner 11!");
			sysOutP(eingabe);
			if (isAlpha2(eingabe)) // überarbeiten!!!!!
			{
				System.err.println("Die angegebene Basis liegt nicht im Bereich zwischen 11-36");
				throw new EigeneCheckedException(
						"Keine Buchstaben erlaubt \n Die angegebene Basis liegt nicht im Bereich zwischen 11-36");
			}

		}

		else {
			for (char c : eingabe.toCharArray()) {
				String cAufString = Character.toString(c);
				if (isAlpha2(cAufString)) {

					eingabe.toUpperCase();
// Prüfen auf buchstaben mit charackter stadt swich case
					if (c < 'Z' - (25 - (ak_zs - 10)) && (c <= 'Z' && c >= 'A')) // welche Zahlen darf man verwenden
					{
						sysOutP("zwischen A-Z");
						sysOutP(c + ": ist erlaubt");
					} else if (c < 'z' - (25 - (ak_zs - 10)) && (c <= 'z' && c >= 'a')) {
						sysOutP("zwischen a-z");
						sysOutP(c + ": ist erlaubt");
					} else {
						sysOutP("NICHT ERLAUBT!!!");
						sysOutP(c + ": ist NICHT erlaubt");
						throw new EigeneCheckedException("Dieser Buchstabe: " + c
								+ " ist bei dem aktuelen Zahlensystem(" + ak_zs + ") nicht erlaubt");
					}

				}
			}

		}

// Plausi check ENDE

		sysOutP("");
		sysOutP("Auf Decimal Bringen NACH komma");
// ak_zs ==> aktuelles Zahlensystem
// eingbae ==> eingabe

// int ak_zs = 16; //aktuelles Zahlensystem
// String eingabe = "18"; //zu konvertirende Zahl

// s_Split funktion direkt eingebaut
// String[] split = eingabe.split(""); //muss ersetze werden ==> / wurde ersetz
		String[] split = eingabeUmwandler(eingabe);
// String[] split = s_Split(eingabe);

// Ausgabe String Arry zerlegt Zahl
		for (int i = 0; i < split.length; i++) {
			sysOutP("Die einzellen Teile von Postiton: (" + i + ")" + split[i]);
		}

// Adition Einzellner Teile
		double zwischenSpeicher = 0; // zwischenSpeicher für additon einzellner Stellen
		double stringToDouble; // Zerlegte Teile des String Arrays in einen Int speichern
		double pM = -1; // potenz mutiplikator
		double mult; // mulitiplikartor

		for (int i = 0; i < split.length; i++) {
			sysOutP("Zwischen Ergebnis : " + i + " " + zwischenSpeicher);
			mult = (double) pot(ak_zs, pM); // potenz der Aktuellen Stelle wird berechnet
			stringToDouble = Integer.parseInt(split[i]);
			zwischenSpeicher = zwischenSpeicher + (stringToDouble * mult); //
			sysOutP("Zwischen Ergebnis: " + zwischenSpeicher);
			pM--;
		}
// Ausgabe Ergebnis in Decimal
		sysOutP("Ergebnis NACH komma: " + zwischenSpeicher * 10);
		return zwischenSpeicher * 10;
	}

	public static String vonDecNachkomma(double eingabe, int zahlensytemAusgabe) throws EigeneCheckedException { // von
		sysOutP("vonDecNachkomma!!!!!!!:   " + eingabe); // Decimal
		// in
		// jedes andere
		// System
		// Plausi Check
		if (zahlensytemAusgabe < 2 || zahlensytemAusgabe > 36) // Basis wird geprüft
		{
			System.err.println("Die angegebene Basis liegt nicht im Bereich zwischen 2-36");
			throw new EigeneCheckedException(
					"Falsche Basis  \n Die angegebene Basis liegt nicht im Bereich zwischen 2-36");
		}

		if (eingabe < 0) // Eingabe wird geprüft
		{
			System.err.println("Die angegebene Zahl liegt nicht im positiven Bereich oder ");
			throw new EigeneCheckedException(
					"Falsche Eingabe \n  Die angegebene Zahl liegt nicht im positiven Bereich oder");
		}
		if (eingabe > Double.MAX_VALUE) {
			System.err.println("Stack Overflow----Zahl zu groß");
			throw new EigeneCheckedException("Falsche Eingabe \n Stack Overflow----Zahl zu groß");
		}

		// Plausi check ENDE

		sysOutP("");
		sysOutP("Von Decimal Umwandel NACH komma");
		double eI = eingabe; // eingabe int INT / return wert der funkton aufDec
		double e2;
		e2 = eI;
		int zEi;
		int zsAusgabe = zahlensytemAusgabe; // Das gewünschte Zahlensystem bei er Ausgabe
		String ausgabeE = "";
		// Zwischen Variblen für Divison

		ArrayList<String> e = new ArrayList<>();
		// double ergebnisZ;
		double rest = 0;
		// nachhkomma bereich
		int eingabeI = (int) eingabe;
		String eingabeString = String.valueOf(eingabeI);
		double nachkomma;
		int divisor = eingabeString.length();
		nachkomma = eingabe / (pot(10, divisor));
		// Divison bis Null oder gewollte genauichkeit
		for (int i = 0; i < 7; i++) // bis 0 rechnen oder 15 nachkomma Stellen
		{
			if (i > 0) {
				nachkomma = rest;
			}
			e2 = zsAusgabe * nachkomma;
			zEi = (int) e2;
			sysOutP("Ausgabe vor SwichCASE !!!!!!!!! " + zEi);
			eD.add((double) zEi);

			rest = e2 - zEi;

			switch (zEi) // Zahlen über 9 in Buchstaben umwandeln / unter 9 in List übergeben
			{
			case 10:
				e.add(i, "A");
				break;

			case 11:
				e.add(i, "B");
				break;

			case 12:
				e.add(i, "C");
				break;

			case 13:
				e.add(i, "D");
				break;

			case 14:
				e.add(i, "E");
				break;

			case 15:
				e.add(i, "F");
				break;

			case 16:
				e.add(i, "G");
				break;

			case 17:
				e.add(i, "H");
				break;

			case 18:
				e.add(i, "I");
				break;

			case 19:
				e.add(i, "J");
				break;

			case 20:
				e.add(i, "K");
				break;

			case 21:
				e.add(i, "L");
				break;

			case 22:
				e.add(i, "M");
				break;

			case 23:
				e.add(i, "N");
				break;

			case 24:
				e.add(i, "O");
				break;

			case 25:
				e.add(i, "P");
				break;

			case 26:
				e.add(i, "Q");
				break;

			case 27:
				e.add(i, "R");
				break;

			case 28:
				e.add(i, "S");
				break;

			case 29:
				e.add(i, "T");
				break;

			case 30:
				e.add(i, "U");
				break;

			case 31:
				e.add(i, "V");
				break;

			case 32:
				e.add(i, "W");
				break;

			case 33:
				e.add(i, "X");
				break;

			case 34:
				e.add(i, "Y");
				break;

			case 35:
				e.add(i, "Z");
				break;

			default:
				String ze = String.valueOf(zEi);
				e.add(i, ze);

			}
			sysOutP("Ergebnis Nach swich CASE !!!!!!!!!!!!!!!" + zEi);
			if (rest == 0) // ende der Rechnung wenn Ergebnis 0 ist
			{
				break;
			}

		}
		// Ausgabe ArrayList
		for (int i = 0; i < e.size(); i++) {
			sysOutP("ArrayList: NACHkomma=>>  " + e.get(i));

		}

		// Englültige zusammengestze Ausgabe
		// Zusammensetzen der Einzellen Werte
		for (int i = 0; i < (e.size()); i++) {
			ausgabeE = ausgabeE + e.get(i);
			sysOutP("e.size: " + e.size() + " " + i + " Zwischgen Ausgabe:  NACkomma==> " + ausgabeE);
		}
		sysOutP(" \n  ");
		sysOutP("Endglültige Ausgabe: NACkomma==>  " + ausgabeE);
		sysOutP("return wert: " + ausgabeE);

		// Ausgabe in Double zum rechnen WARSCHEINLICH UNÖTIG
		double ergebnisTest = 0;
		double pow = -1;
		for (int i = 0; i < eD.size() - 1; i++) {

			ergebnisTest = ergebnisTest + (eD.get(i) * pot(zahlensytemAusgabe, pow));
			String sysOutDoubletoString = String.valueOf(ergebnisTest);
			sysOutP(sysOutDoubletoString);
			pow--;
		}
		sysOutP("Ergebins IN DECIMAL / NACHkomma==>  " + ergebnisTest);

		return ausgabeE;

	}

	public static String vonDec(int eingabe, int ZahlensytemAusgabe) throws EigeneCheckedException { // von Decimal in
																										// jedes andere
																										// System

// Plausi Check
		if (ZahlensytemAusgabe < 2 || ZahlensytemAusgabe > 36) // Basis wird geprüft
		{
			System.err.println("Die angegebene Basis liegt nicht im Bereich zwischen 2-36");
			throw new EigeneCheckedException(
					"Falsche Basis \n  Die angegebene Basis liegt nicht im Bereich zwischen 2-36");
		}

		if (eingabe < 0) // Eingabe wird geprüft
		{
			System.err.println("Die angegebene Zahl liegt nicht im positiven Bereich oder ");
			throw new EigeneCheckedException(
					"Falsche Eingabe \n Die angegebene Zahl liegt nicht im positiven Bereich oder");
		}
		if (eingabe > Integer.MAX_VALUE) {
			System.err.println("Stack Overflow----Zahl zu groß");
			throw new EigeneCheckedException("Falsche Eingabe \n Stack Overflow----Zahl zu groß");
		}

// Plausi check ENDE

		sysOutP("");
		sysOutP("Von Decimal Umwandel");
		int eI = eingabe; // eingabe int INT / return wert der funkton aufDec
		int zsAusgabe = ZahlensytemAusgabe; // Das gewünschte Zahlensystem bei er Ausgabe

// Zwischen Variblen für Divison
		ArrayList<String> e = new ArrayList<>();
		// int ergebnisZ;
		int rest;

// Divison bis Null
		for (int i = 0; i >= 0; i++) // gewollte Endlosschleife
		{
			rest = eI % zsAusgabe;
			eI = eI / zsAusgabe;

			switch (rest) // Zahlen über 9 in Buchstaben umwandeln / unter 9 in List übergeben
			{
			case 10:
				e.add(i, "A");
				break;

			case 11:
				e.add(i, "B");
				break;

			case 12:
				e.add(i, "C");
				break;

			case 13:
				e.add(i, "D");
				break;

			case 14:
				e.add(i, "E");
				break;

			case 15:
				e.add(i, "F");
				break;

			case 16:
				e.add(i, "G");
				break;

			case 17:
				e.add(i, "H");
				break;

			case 18:
				e.add(i, "I");
				break;

			case 19:
				e.add(i, "J");
				break;

			case 20:
				e.add(i, "K");
				break;

			case 21:
				e.add(i, "L");
				break;

			case 22:
				e.add(i, "M");
				break;

			case 23:
				e.add(i, "N");
				break;

			case 24:
				e.add(i, "O");
				break;

			case 25:
				e.add(i, "P");
				break;

			case 26:
				e.add(i, "Q");
				break;

			case 27:
				e.add(i, "R");
				break;

			case 28:
				e.add(i, "S");
				break;

			case 29:
				e.add(i, "T");
				break;

			case 30:
				e.add(i, "U");
				break;

			case 31:
				e.add(i, "V");
				break;

			case 32:
				e.add(i, "W");
				break;

			case 33:
				e.add(i, "X");
				break;

			case 34:
				e.add(i, "Y");
				break;

			case 35:
				e.add(i, "Z");
				break;

			default:
				String ze = String.valueOf(rest);
				e.add(i, ze);

			}
			if (eI == 0) // ende der Rechnung wenn Ergebnis 0 ist
			{
				break;
			} else if (i == 1000000) {
				break;
			}
		}
		// Ausgabe ArrayList
		for (int i = 0; i < e.size(); i++) {
			sysOutP("ArrayList: " + e.get(i));
		}

		String ausgabeE = "";// Englültige zusammengestze Ausgabe
// Zusammensetzen der Einzellen Werte
		for (int i = (e.size() - 1); i >= 0; i--) {
			ausgabeE = ausgabeE + e.get(i);
			sysOutP("Zwischgen Ausgabe: " + ausgabeE);
		}
		sysOutP(" \n \n ");
		sysOutP("Endglültige Ausgabe: " + ausgabeE);
		sysOutP("return wert: " + ausgabeE);
		return ausgabeE;
	}

	public static boolean isAlpha2(String text) throws EigeneCheckedException {// überarbeiet von Java-Forum
		boolean is = false;
		for (char c : text.toCharArray()) {

			// a - z
			if (c >= 'a' && c <= 'z') {
				is = true;
			}

			// A - Z
			if (c >= 'A' && c <= 'Z') {
				is = true;
			}
			if (c > 'z' && c <= 'ÿ') {
				throw new EigeneCheckedException(c + "Ist ein Falscher Buchstabe");
			}

		}
		return is;
	}

	public static boolean isAF(String text) {
		boolean is = false;
		for (char c : text.toCharArray()) {

			// a - z
			if (c >= 'a' && c <= 'f') {
				is = true;
			}

			// A - Z
			if (c >= 'A' && c <= 'F') {
				is = true;
			}

		}
		return is;
	}

	public static boolean isMinus(String s) // Prüft auf minus
	{
		for (char c : s.toCharArray()) {
			if (c == '-') {
				return true;
			}

		}
		return false;
	}

	public static double round(double value, int stellen) {
		int i = (int) (value * pot(10, stellen));
		return i / pot(10, stellen);

	}

	public static double pot(double basis, double potenzwert) { //potenzieren 
		double e = basis;

		if (potenzwert == 0) {
			return 1.0;
		}
		if (basis == 0) {
			return 0;
		}

		if (potenzwert > 0) {
			for (int i = 0; i < potenzwert-1; i++) {
				e = e * basis;
			}
			return e;
		}
		if(potenzwert<0)
		{
			double p2 = potenzwert * -1;
			e= 1/(pot(basis,p2));
			return e;
		}
		return -1;
		
	}

}
