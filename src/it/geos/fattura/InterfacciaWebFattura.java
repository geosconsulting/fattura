package it.geos.fattura;

import it.geos.fattura.calcoli.CalcoloIVA;
import it.geos.fattura.calcoli.CalcoloImponibile;
import it.geos.fattura.calcoli.CalcoloInarcassa;
import it.geos.fattura.calcoli.CalcoloRitenuta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class InterfacciaWebFattura extends JApplet {
	public InterfacciaWebFattura() {
	}
			
	private static final long serialVersionUID = 1L;	
	private JTextField txtDays;	
	private JTextField txtRate;
	private JTextField txtImporto;	
	private JTextField txtRateNetto;
	private JTextField txtMensileLordo;	
	private JTextField txtMensileNetto;	
	private JTextField txtIVA;	
	private JTextField txtInarcassa;	
	private JTextField txtRitenuta;

	public void init() {
		this.setSize(240,450);
		getContentPane().setLayout(null);
		
		txtDays = new JTextField();
		txtDays.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDays.setBounds(122, 22, 108, 20);
		getContentPane().add(txtDays);
		txtDays.setColumns(10);
		
		txtRate = new JTextField();
		txtRate.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRate.setBounds(122, 64, 108, 20);
		getContentPane().add(txtRate);
		txtRate.setColumns(10);
		
		txtImporto = new JTextField();
		txtImporto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImporto.setEditable(false);
		txtImporto.setBounds(122, 358, 108, 20);
		getContentPane().add(txtImporto);
		txtImporto.setColumns(10);
		
		JButton btnCalcolo = new JButton("Calcolo");
		btnCalcolo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int giorniIn=0;
				try {
					giorniIn = Integer.parseInt(txtDays.getText());
				} catch (NumberFormatException e1) {			
					JOptionPane.showMessageDialog(null, "Il numero di giorni non puo essere nullo");
				}		
				
				double lordoIn=0.0;
				try {
					lordoIn = Double.parseDouble(txtRate.getText());
				} catch (NumberFormatException e) {			
					
					JOptionPane.showMessageDialog(null, "Il rate deve essere un numero decimale o intero");
				}							
				
				
				NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ITALY);	
				
				CalcoloImponibile.setRateRiduzione(4);
				CalcoloImponibile.setGiorni(giorniIn);				
				CalcoloImponibile.setRateLordo(lordoIn);
				txtRateNetto.setText(format.format((CalcoloImponibile.setDailyRateNetto(lordoIn))));
								
				txtMensileLordo.setText(format.format(CalcoloImponibile.getMensileLordo()));
				txtMensileNetto.setText(format.format(CalcoloImponibile.getMensileNetto()));
				txtInarcassa.setText(format.format(CalcoloInarcassa.getInarcassa()));
				txtIVA.setText(format.format(CalcoloIVA.getIva()));
				txtRitenuta.setText(format.format(CalcoloRitenuta.getRitenuta()));	
				txtImporto.setText(format.format(CalcoloImponibile.getTotale()));		
			}
		});
		btnCalcolo.setBounds(122, 403, 108, 23);
		getContentPane().add(btnCalcolo);
		
		txtRateNetto = new JTextField();
		txtRateNetto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRateNetto.setEditable(false);
		txtRateNetto.setColumns(10);
		txtRateNetto.setBounds(122, 106, 108, 20);
		getContentPane().add(txtRateNetto);
		
		txtMensileLordo = new JTextField();
		txtMensileLordo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMensileLordo.setEditable(false);
		txtMensileLordo.setColumns(10);
		txtMensileLordo.setBounds(122, 148, 108, 20);
		getContentPane().add(txtMensileLordo);
		
		txtMensileNetto = new JTextField();
		txtMensileNetto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMensileNetto.setEditable(false);
		txtMensileNetto.setColumns(10);
		txtMensileNetto.setBounds(122, 190, 108, 20);
		getContentPane().add(txtMensileNetto);
		
		txtIVA = new JTextField();
		txtIVA.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIVA.setEditable(false);
		txtIVA.setColumns(10);
		txtIVA.setBounds(122, 232, 108, 20);
		getContentPane().add(txtIVA);
		
		txtInarcassa = new JTextField();
		txtInarcassa.setHorizontalAlignment(SwingConstants.RIGHT);
		txtInarcassa.setEditable(false);
		txtInarcassa.setColumns(10);
		txtInarcassa.setBounds(122, 274, 108, 20);
		getContentPane().add(txtInarcassa);
		
		txtRitenuta = new JTextField();
		txtRitenuta.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRitenuta.setEditable(false);
		txtRitenuta.setColumns(10);
		txtRitenuta.setBounds(122, 316, 108, 20);
		getContentPane().add(txtRitenuta);
		
		JLabel lblGiorni = new JLabel("Giorni");
		lblGiorni.setBounds(21, 22, 46, 14);
		getContentPane().add(lblGiorni);
		
		JLabel lblRateLordo = new JLabel("Rate Lordo");
		lblRateLordo.setBounds(21, 68, 82, 14);
		getContentPane().add(lblRateLordo);
		
		JLabel lblMensileLordo = new JLabel("Mensile Lordo");
		lblMensileLordo.setBounds(21, 151, 82, 14);
		getContentPane().add(lblMensileLordo);
		
		JLabel lblMensileNetto = new JLabel("Mensile Netto");
		lblMensileNetto.setBounds(21, 193, 82, 14);
		getContentPane().add(lblMensileNetto);
		
		JLabel lblIva = new JLabel("IVA");
		lblIva.setBounds(21, 235, 82, 14);
		getContentPane().add(lblIva);
		
		JLabel lblInarcassa = new JLabel("Inarcassa");
		lblInarcassa.setBounds(21, 277, 82, 14);
		getContentPane().add(lblInarcassa);
		
		JLabel lblRitenuta = new JLabel("Ritenuta");
		lblRitenuta.setBounds(21, 319, 82, 14);
		getContentPane().add(lblRitenuta);
		
		JLabel lblImporto = new JLabel("Importo");
		lblImporto.setBounds(21, 361, 82, 14);
		getContentPane().add(lblImporto);
		
		JLabel label_2 = new JLabel("Rate Netto");
		label_2.setBounds(21, 109, 82, 14);
		getContentPane().add(label_2);
		


	}
}
