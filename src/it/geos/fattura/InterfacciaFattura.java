package it.geos.fattura;

import it.geos.fattura.calcoli.CalcoloIVA;
import it.geos.fattura.calcoli.CalcoloImponibile;
import it.geos.fattura.calcoli.CalcoloInarcassa;
import it.geos.fattura.calcoli.CalcoloRitenuta;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class InterfacciaFattura {
	
	private JFrame frmCalcoloFattura;	
	private JTextField txtRitenuta;	
	private JTextField txtInarcassa;	
	private JTextField txtIVA;
	private JTextField txtMensileLordo;
	private JTextField txtImporto;
	private JTextField txtRateLordo;
	private JTextField txtMensileNetto;	
	private JTextField txtRateNetto;	
	private JTextField txtReminderPensionistico;	
	private JTextField txtGiorni;	
	private JLabel lblRateNetto;	
	private JLabel lblMensilelordo;	
	private JLabel lblMensileNetto;	
	private JLabel lblPensionistico_1;	
	private JLabel lblIva;	
	private JLabel lblRitenuta;	
	private JLabel lblImporto;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacciaFattura window = new InterfacciaFattura();
					window.frmCalcoloFattura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** Create the application. */
	public InterfacciaFattura() {
		initialize();
	}

	/** Initialize the contents of the frame. */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {

		frmCalcoloFattura = new JFrame();
		frmCalcoloFattura.setTitle("Calcolo Fattura");
		frmCalcoloFattura.setBounds(100, 100, 244, 443);
		frmCalcoloFattura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalcoloFattura.getContentPane().setLayout(null);
		
		txtGiorni = new JTextField();
		txtGiorni.setToolTipText("Numero di giorni (INTERO)");
		txtGiorni.setColumns(10);
		txtGiorni.setBounds(129, 15, 89, 20);
		frmCalcoloFattura.getContentPane().add(txtGiorni);
		
		txtRateLordo = new JTextField();
		txtRateLordo.setToolTipText("Importo giornaliero (DOUBLE)");
		txtRateLordo.setBounds(98, 46, 120, 20);
		frmCalcoloFattura.getContentPane().add(txtRateLordo);
		txtRateLordo.setColumns(10);		
		
		final JComboBox cmbPensionistico = new JComboBox();
		cmbPensionistico.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (cmbPensionistico.getSelectedItem()=="Inarcassa"){
					txtReminderPensionistico.setText("Ritenuta ai fini pensionistici 4%");					
				}else if (cmbPensionistico.getSelectedItem()=="INPS"){
					txtReminderPensionistico.setText("Ritenuta ai fini pensionistici 10%");
				}				
			}
		});
		cmbPensionistico.setModel(new DefaultComboBoxModel(new String[] {"Inarcassa", "INPS"}));
		cmbPensionistico.setBounds(98, 77, 120, 20);
		frmCalcoloFattura.getContentPane().add(cmbPensionistico);
		
		txtRateNetto = new JTextField();
		txtRateNetto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRateNetto.setEditable(false);
		txtRateNetto.setColumns(10);
		txtRateNetto.setBounds(129, 147, 89, 20);
		frmCalcoloFattura.getContentPane().add(txtRateNetto);
		
		txtMensileLordo = new JTextField();
		txtMensileLordo.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMensileLordo.setEditable(false);
		txtMensileLordo.setBounds(129, 178, 89, 20);
		frmCalcoloFattura.getContentPane().add(txtMensileLordo);
		txtMensileLordo.setColumns(10);
		
		txtMensileNetto = new JTextField();
		txtMensileNetto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtMensileNetto.setEditable(false);
		txtMensileNetto.setColumns(10);
		txtMensileNetto.setBounds(129, 209, 89, 20);
		frmCalcoloFattura.getContentPane().add(txtMensileNetto);
		
		txtInarcassa = new JTextField();
		txtInarcassa.setHorizontalAlignment(SwingConstants.RIGHT);
		txtInarcassa.setEditable(false);
		txtInarcassa.setBounds(129, 240, 89, 20);
		frmCalcoloFattura.getContentPane().add(txtInarcassa);
		txtInarcassa.setColumns(10);
		
		txtIVA = new JTextField();
		txtIVA.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIVA.setEditable(false);
		txtIVA.setBounds(129, 271, 89, 20);
		frmCalcoloFattura.getContentPane().add(txtIVA);
		txtIVA.setColumns(10);
		
		txtRitenuta = new JTextField();
		txtRitenuta.setHorizontalAlignment(SwingConstants.RIGHT);
		txtRitenuta.setEditable(false);
		txtRitenuta.setBounds(129, 302, 89, 20);
		frmCalcoloFattura.getContentPane().add(txtRitenuta);
		txtRitenuta.setColumns(10);
		
		txtImporto = new JTextField();
		txtImporto.setHorizontalAlignment(SwingConstants.RIGHT);
		txtImporto.setEditable(false);
		txtImporto.setBounds(129, 333, 89, 20);
		frmCalcoloFattura.getContentPane().add(txtImporto);
		txtImporto.setColumns(10);
		
		JButton btnCalcola = new JButton("Calcola");
		btnCalcola.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
				
		int giorniIn=0;
		try {
			giorniIn = Integer.parseInt(txtGiorni.getText());
		} catch (NumberFormatException e1) {			
			JOptionPane.showMessageDialog(null, "Il numero di giorni non puo essere nullo");
		}		
		
		double lordoIn=0.0;
		try {
			lordoIn = Double.parseDouble(txtRateLordo.getText());
		} catch (NumberFormatException e) {			
			
			JOptionPane.showMessageDialog(null, "Il rate deve essere un numero decimale o intero");
		}							
				
		CalcoloImponibile.setGiorni(giorniIn);
		
		CalcoloImponibile.setRateLordo(lordoIn);					
		if (cmbPensionistico.getSelectedItem()=="Inarcassa"){
			CalcoloImponibile.setRateRiduzione(4);					
		}else if (cmbPensionistico.getSelectedItem()=="INPS"){
			CalcoloImponibile.setRateRiduzione(10);	
		}	
		
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ITALY);
		
		txtRateNetto.setText(format.format(CalcoloImponibile.setDailyRateNetto(lordoIn)));
		txtMensileLordo.setText(format.format(CalcoloImponibile.getMensileLordo()));
		txtMensileNetto.setText(format.format(CalcoloImponibile.getMensileNetto()));
		txtInarcassa.setText(format.format(CalcoloInarcassa.getInarcassa()));
		txtIVA.setText(format.format(CalcoloIVA.getIva()));
		txtRitenuta.setText(format.format(CalcoloRitenuta.getRitenuta()));				
		txtImporto.setText(format.format(CalcoloImponibile.getTotale()));				
		
		}
		});
		btnCalcola.setBounds(10, 371, 89, 23);
		frmCalcoloFattura.getContentPane().add(btnCalcola);
		
		JLabel lblRateGiorno = new JLabel("Rate Giorno");
		lblRateGiorno.setBounds(10, 49, 78, 14);
		frmCalcoloFattura.getContentPane().add(lblRateGiorno);
		
		JLabel lblPensionistico = new JLabel("Pensionistico");
		lblPensionistico.setBounds(10, 80, 78, 14);
		frmCalcoloFattura.getContentPane().add(lblPensionistico);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 103, 228, 2);
		frmCalcoloFattura.getContentPane().add(separator);
		
		txtReminderPensionistico = new JTextField();
		txtReminderPensionistico.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtReminderPensionistico.setBackground(new Color(255, 250, 250));
		txtReminderPensionistico.setEditable(false);
		txtReminderPensionistico.setBounds(10, 116, 208, 20);
		frmCalcoloFattura.getContentPane().add(txtReminderPensionistico);
		txtReminderPensionistico.setColumns(10);
		txtReminderPensionistico.setText("Ritenuta ai fini pensionistici 4%");
		
		JLabel lblGiorni = new JLabel("Giorni Lavorati");
		lblGiorni.setBounds(10, 18, 120, 14);
		frmCalcoloFattura.getContentPane().add(lblGiorni);
		
		JButton btnExit = new JButton("Exit");		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		btnExit.setBounds(119, 371, 89, 23);
		frmCalcoloFattura.getContentPane().add(btnExit);
		
		lblRateNetto = new JLabel("Rate Netto");
		lblRateNetto.setBounds(10, 150, 109, 14);
		frmCalcoloFattura.getContentPane().add(lblRateNetto);
		
		lblMensilelordo = new JLabel("Mensile Lordo");
		lblMensilelordo.setBounds(10, 181, 109, 14);
		frmCalcoloFattura.getContentPane().add(lblMensilelordo);
		
		lblMensileNetto = new JLabel("Mensile Netto");
		lblMensileNetto.setForeground(Color.RED);
		lblMensileNetto.setBounds(10, 212, 109, 14);
		frmCalcoloFattura.getContentPane().add(lblMensileNetto);
		
		lblPensionistico_1 = new JLabel("Pensionistico");
		lblPensionistico_1.setForeground(Color.RED);
		lblPensionistico_1.setBounds(10, 243, 78, 14);
		frmCalcoloFattura.getContentPane().add(lblPensionistico_1);
		
		lblIva = new JLabel("IVA (21%)");
		lblIva.setForeground(Color.RED);
		lblIva.setToolTipText("21%");
		lblIva.setBounds(10, 274, 78, 14);
		frmCalcoloFattura.getContentPane().add(lblIva);
		
		lblRitenuta = new JLabel("Ritenuta");
		lblRitenuta.setForeground(Color.RED);
		lblRitenuta.setBounds(10, 305, 78, 14);
		frmCalcoloFattura.getContentPane().add(lblRitenuta);
		
		lblImporto = new JLabel("Importo");
		lblImporto.setForeground(Color.RED);
		lblImporto.setBounds(10, 336, 78, 14);
		frmCalcoloFattura.getContentPane().add(lblImporto);
	}
}

