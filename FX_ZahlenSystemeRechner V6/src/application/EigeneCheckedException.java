package application;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class EigeneCheckedException extends Exception {

	public EigeneCheckedException() {
		super("Ich bin deine Exception");
	}

	public EigeneCheckedException(String fehlermeldung) {
		super(fehlermeldung);
		infoBox(fehlermeldung, "Falsche Angaben");
	}
	
	public EigeneCheckedException(String fehlermeldung, String titel) {
		super(fehlermeldung);
		infoBox(fehlermeldung, titel);
	}
		
	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.ERROR_MESSAGE);
	}

	
	}

