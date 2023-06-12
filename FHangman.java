package Hangman;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

public class FHangman extends JFrame implements Serializable{	

	private JPanel contentPane;
	private JPanel panelDatos;
	private String nombre = "";
	private boolean Server = true;
	private JLabel lblIntentos;
	private JLabel lbbPuntuacion;
	private boolean palabra = true;
	private JButton ServerButton;
	private boolean firstLetter = true;
	private static final int xLenght = 30;
	private JPanel buttonPanel;
	private JPanel lblPanel;
	private Hangman hang;
	private JButton AButton;
	private JPanel panelAhorcado;
	private JButton ClientServer;
	private JButton BButton;
	private JButton CButton;
	private JButton DButton;
	private JButton EButton;
	private JButton FButton;
	private JButton GButton;
	private JButton HButton;
	private JButton IButton;
	private JButton JButton;
	private JButton KButton;
	private JButton LButton;
	private JButton MButton;
	private JButton NButton;
	private JButton OButton;
	private JButton PButton;
	private JButton QButton;
	private JButton RButton;
	private JButton SButton;
	private JButton TButton;
	private JButton UButton;
	private JButton VButton;
	private JButton XButton;
	private JButton YButton;
	private JButton ZButton;
	private JButton WButton;
	private JButton ÑButton;
	private JButton btnStartGame;
	private Socket client;
	private ServerSocket server;
	private Socket socket;
	private final static String SERVER = "127.0.0.1";
	private final static int PORT = 5841;
	private PrintStream output;
	private BufferedReader input;
	private ObjectReader reader;
	private JPanel panelPalabrasFalladas;
	private JLabel lblPalabraAdivinada;
	private JLabel lblAciertos;
	private JLabel lblErrores;
	private JLabel lblPalabrasFalladas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FHangman frame = new FHangman();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FHangman() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblPanel = new JPanel();
		lblPanel.setBounds(227, 172, 193, 75);
		contentPane.add(lblPanel);
		
		lblPalabraAdivinada = new JLabel("");
		Font font1 = lblPalabraAdivinada.getFont();
		lblPalabraAdivinada.setFont(font1.deriveFont(font1.getSize() + 15f));
		lblPanel.add(lblPalabraAdivinada);
		
		
		btnStartGame = new JButton("Start Game");
		btnStartGame.setBounds(266, 192, 101, 27);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						hang = new Hangman(contentPane, lblPalabraAdivinada, lblPalabrasFalladas,lblIntentos,lblErrores, lblAciertos, lbbPuntuacion);
						btnStartGame.setVisible(false);
						createlbl(hang);
						showButtons();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnStartGame);
		btnStartGame.setVisible(false);
		
		buttonPanel = new JPanel();
		buttonPanel.setBounds(89, 300, 495, 117);
		contentPane.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		AButton = new JButton("A");
		AButton.setVisible(false);
		AButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				AButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(AButton);
		
		BButton = new JButton("B");
		BButton.setVisible(false);
		BButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				BButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(BButton);
		
		CButton = new JButton("C");
		CButton.setVisible(false);
		CButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				CButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(CButton);
		
		DButton = new JButton("D");
		DButton.setVisible(false);
		DButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				DButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(DButton);
		
		EButton = new JButton("E");
		EButton.setVisible(false);
		EButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				EButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(EButton);
		
		FButton = new JButton("F");
		FButton.setVisible(false);
		FButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				FButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(FButton);
		
		GButton = new JButton("G");
		GButton.setVisible(false);
		GButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				GButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(GButton);
		
		HButton = new JButton("H");
		HButton.setVisible(false);
		HButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				HButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(HButton);
		
		IButton = new JButton("I");
		IButton.setVisible(false);
		IButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				IButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(IButton);
		
		JButton = new JButton("J");
		JButton.setVisible(false);
		JButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				JButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(JButton);
		
		KButton = new JButton("K");
		KButton.setVisible(false);
		KButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				KButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(KButton);
		
		LButton = new JButton("L");
		LButton.setVisible(false);
		LButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				LButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(LButton);
		
		MButton = new JButton("M");
		MButton.setVisible(false);
		MButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				MButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(MButton);
		
		NButton = new JButton("N");
		NButton.setVisible(false);
		NButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				NButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(NButton);
		
		ÑButton = new JButton("Ñ");
		ÑButton.setVisible(false);
		ÑButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				ÑButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(ÑButton);
		
		OButton = new JButton("O");
		OButton.setVisible(false);
		OButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				OButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(OButton);
		
		PButton = new JButton("P");
		PButton.setVisible(false);
		PButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				PButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(PButton);
		
		QButton = new JButton("Q");
		QButton.setVisible(false);
		QButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				QButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(QButton);
		
		RButton = new JButton("R");
		RButton.setVisible(false);
		RButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				RButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(RButton);
		
		SButton = new JButton("S");
		SButton.setVisible(false);
		SButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				SButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(SButton);
		
		TButton = new JButton("T");
		TButton.setVisible(false);
		TButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				TButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(TButton);
		
		UButton = new JButton("U");
		UButton.setVisible(false);
		UButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				UButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(UButton);
		
		VButton = new JButton("V");
		VButton.setVisible(false);
		VButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				VButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(VButton);
		
		WButton = new JButton("W");
		WButton.setVisible(false);
		WButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				WButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(WButton);
		
		XButton = new JButton("X");
		XButton.setVisible(false);
		XButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				XButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(XButton);
		
		YButton = new JButton("Y");
		YButton.setVisible(false);
		YButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				YButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(YButton);
		
		ZButton = new JButton("Z");
		ZButton.setVisible(false);
		ZButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hang.actionPerformed(e);
				ZButton.setEnabled(false);
				try {
					output = new PrintStream(socket.getOutputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
	            output.println(e.getActionCommand());
			}
		});
		buttonPanel.add(ZButton);
		
		panelDatos = new JPanel();
		panelDatos.setBounds(530, 0, 131, 105);
		contentPane.add(panelDatos);
		
		lblIntentos = new JLabel("");
		lblIntentos.setFont(font1.deriveFont(font1.getSize() + 8f));
		panelDatos.add(lblIntentos);
		
		lblErrores = new JLabel("");
		lblErrores.setFont(font1.deriveFont(font1.getSize() + 8f));
		panelDatos.add(lblErrores);
		
		lblAciertos = new JLabel("");
		lblAciertos.setFont(font1.deriveFont(font1.getSize() + 8f));
		panelDatos.add(lblAciertos);
		
		panelPalabrasFalladas = new JPanel();
		panelPalabrasFalladas.setBounds(502, 172, 171, 75);
		contentPane.add(panelPalabrasFalladas);
		
		lblPalabrasFalladas = new JLabel("");
		lblPalabrasFalladas.setFont(font1.deriveFont(font1.getSize() + 15f));
		panelPalabrasFalladas.add(lblPalabrasFalladas);
		
		JPanel panelPuntuacion = new JPanel();
		panelPuntuacion.setBounds(227, 0, 180, 66);
		contentPane.add(panelPuntuacion);
		
		lbbPuntuacion = new JLabel("");
		lbbPuntuacion.setFont(font1.deriveFont(font1.getSize() + 10f));
		panelPuntuacion.add(lbbPuntuacion);
		
		panelAhorcado = new JPanel();
		panelAhorcado.setBounds(12, 12, 171, 238);
		contentPane.add(panelAhorcado);
		
		ServerButton = new JButton("Server");
		ServerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						hang = new Hangman(contentPane, lblPalabraAdivinada, lblPalabrasFalladas,lblIntentos,lblErrores, lblAciertos, lbbPuntuacion);
						createlbl(hang);
						server = new ServerSocket(PORT);
						client = server.accept();
						client.setSoLinger (true, 10);
						input = new BufferedReader(new InputStreamReader(client.getInputStream()));
						if(palabra) {
							output = new PrintStream(client.getOutputStream());
							output.println(hang.getPalabraSecreta());
							palabra = false;
						}
						else {
							output = new PrintStream(client.getOutputStream());
						}
						reader = new ObjectReader(input, socket, server, hang);
						reader.start();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}});
		ServerButton.setBounds(455, 12, 105, 27);
		contentPane.add(ServerButton);
		
		ClientServer = new JButton("Client");
		ClientServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						showButtons();
						socket = new Socket(SERVER, PORT);  
						//To read from the server      
			            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
			            String hangWord = input.readLine();
			            hang = new Hangman(panelPuntuacion, lblPalabraAdivinada, lblPalabrasFalladas, lblIntentos, lblErrores, lblAciertos, lbbPuntuacion, hangWord);
			            createlbl(hang);
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}   
			}
		});
		ClientServer.setBounds(455, 51, 105, 27);
		contentPane.add(ClientServer);
	}

	public void showButtons() {
		AButton.setVisible(true);
		BButton.setVisible(true);
		CButton.setVisible(true);
		DButton.setVisible(true);
		EButton.setVisible(true);
		FButton.setVisible(true);
		GButton.setVisible(true);
		HButton.setVisible(true);
		IButton.setVisible(true);
		JButton.setVisible(true);
		KButton.setVisible(true);
		LButton.setVisible(true);
		MButton.setVisible(true);
		NButton.setVisible(true);
		ÑButton.setVisible(true);
		OButton.setVisible(true);
		PButton.setVisible(true);
		QButton.setVisible(true);
		RButton.setVisible(true);
		SButton.setVisible(true);
		TButton.setVisible(true);
		UButton.setVisible(true);
		VButton.setVisible(true);
		WButton.setVisible(true);
		XButton.setVisible(true);
		YButton.setVisible(true);
		ZButton.setVisible(true);
	}
	
	public void createlbl(Hangman hang) {
	for(int i=0;i<hang.getPalabraSecreta().length();i++) {
		JLabel label = new JLabel(""); 
		System.out.println(label);
		lblPanel.add(label);
	}
	}
	
	@Override
	public String getName() {
		while (nombre.isEmpty()){
            nombre = JOptionPane.showInputDialog(null, "Ingrese su nombre:", "Nombre", JOptionPane.PLAIN_MESSAGE);
            if (nombre == "") {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return nombre;
	}
	

}

