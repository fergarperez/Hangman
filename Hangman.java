package Hangman;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Hangman implements ActionListener, Serializable{
	
	private char letra;
	private String[] palabras = {
            "manzana", "naranja", "platano", "pera", "uva",
            "sandia", "melon", "limon", "kiwi", "piña",
            "fresa", "mango", "cereza", "mandarina", "durazno",
            "pomelo", "lima", "papaya", "coco", "frambuesa",
            "zarzamora", "arandano", "granada", "higo", "guayaba",
            "melocoton", "albaricoque", "ciruela", "morera", "membrillo",
            "manzanilla", "acelga", "ajo", "alcachofa", "apio",
            "berenjena", "calabaza", "calabacin", "cebolla", "coliflor",
            "zanahoria", "pepino", "pimiento", "patata", "judia",
            "lechuga", "rábano", "tomate", "remolacha", "espinaca",
            "pollo", "carne", "pescado", "cerdo", "ternera",
            "conejo", "cordero", "salmon", "atun", "gambas",
            "langosta", "camarones", "mejillones", "vieiras", "sepia",
            "leche", "agua", "café", "te", "jugo",
            "refresco", "vino", "cerveza", "sidra", "whisky",
            "vodka", "ron", "gin", "brandy", "chocolate",
            "helado", "pastel", "galleta", "chicle", "caramelo",
            "yogur", "queso", "mantequilla", "nuez", "almendra",
            "avellana", "cacahuete", "pistacho", "castaña", "chirimoya",
            "azul", "rojo", "verde", "amarillo", "naranja",
            "morado", "rosa", "blanco", "negro", "gris",
            "marrón", "turquesa", "violeta", "dorado", "plateado",
            "feliz", "triste", "enojado", "asustado", "emocionado",
            "aburrido", "sorprendido", "cansado", "orgulloso", "desanimado",
            "amor", "odio", "alegria", "dolor", "esperanza",
            "risa", "llanto", "sueño", "pasion", "miedo",
            "amistad", "familia", "vida", "muerte", "tiempo",
            "espacio", "mundo", "universo", "estrella", "luz",
            "luna", "sol", "cielo", "nube", "arcoiris",
            "montaña", "rio", "mar", "playa", "bosque",
            "prado", "desierto", "isla", "ciudad", "calle",
            "casa", "puerta", "ventana", "mesa", "silla",
            "sofa", "cama", "armario", "espejo", "televisor",
            "ordenador", "telefono", "libro", "revista", "periodico",
            "cuaderno", "boligrafo", "lapiz", "pincel", "lienzo",
            "cine", "teatro", "concierto", "musica", "danza",
            "arte", "pintura", "escultura", "poesia", "novela",
            "historia", "ciencia", "matematicas", "fisica", "quimica",
            "biologia", "geografia", "economia", "filosofia", "psicologia"
        };// Esto es un array de 200 palabras generado por chatGPT
	private int errors = 0;
	private FHangman hang = new FHangman();
	private Hangman hang2;
	private static final int NUMERO_INTENTOS = 6;
	private Connection connection;
	private String url="jdbc:mysql://localhost:4306/Hangman"; // test is the DB name
	private String user="root";
	private String password="alumnoalumno";
	private String query="insert into Puntuacion (nombre,puntuacion,aciertos,fallos) values (?,?,?,?)";
	private String query2="select nombre from Puntuacion where nombre = " + hang.getName();
	private String query3="UPDATE Puntuacion SET puntuacion = ?, aciertos = ?, fallos = ? WHERE nombre = ?";
	private int intentos;
	private int aciertos = 0;
	private ArrayList<Character> letrasIncorrectas;
	private String palabraSecreta;
	private StringBuilder palabraAdivinada;
	private ObjectOutputStream output;
	private JPanel text;
	private JPanel contentPane;
	private JLabel lblPalabraAdivinada;
	private JLabel lblPalabrasFalladas;
	private JLabel lblIntentos;
	private JLabel lblErrores;
	private JLabel lblAciertos;
	private JLabel lbbPuntuacion;
	private int puntuacion = 0;
	
	public Hangman() {
		palabraSecreta = getPalabra();
		palabraAdivinada = new StringBuilder("*".repeat(palabraSecreta.length()));
		intentos = NUMERO_INTENTOS;
		letrasIncorrectas = new ArrayList<Character>(palabraSecreta.length());
	}
	
	public Hangman(JPanel contentPane, JLabel lblPalabraAdivinada,JLabel lblPalabrasFalladas,JLabel lblIntentos,JLabel lblErrores, JLabel lblAciertos, JLabel lbbPuntuacion) {
		palabraSecreta = getPalabra();
		palabraAdivinada = new StringBuilder("*".repeat(palabraSecreta.length()));
		intentos = NUMERO_INTENTOS;
		letrasIncorrectas = new ArrayList<Character>(palabraSecreta.length());
		this.contentPane = contentPane;
		this.lblPalabraAdivinada = lblPalabraAdivinada;
		this.lblPalabrasFalladas = lblPalabrasFalladas;
		this.lblAciertos = lblAciertos;
		this.lblErrores = lblErrores;
		this.lblIntentos = lblIntentos;
		this.lbbPuntuacion = lbbPuntuacion;
		lblAciertos.setText("Aciertos: " + aciertos);
		lblErrores.setText("Errors: "+ errors);
		lblIntentos.setText("Try's: " + intentos);
		lblPalabraAdivinada.setText(palabraSecreta);
		lbbPuntuacion.setText("Puntuacion: " + puntuacion);
	}
	
	public Hangman(JPanel contentPane, JLabel lblPalabraAdivinada,JLabel lblPalabrasFalladas,JLabel lblIntentos,JLabel lblErrores, JLabel lblAciertos, JLabel lbbPuntuacion, String palabra) {
		palabraSecreta = palabra;
		palabraAdivinada = new StringBuilder("*".repeat(palabraSecreta.length()));
		intentos = NUMERO_INTENTOS;
		letrasIncorrectas = new ArrayList<Character>(palabraSecreta.length());
		this.contentPane = contentPane;
		this.lblPalabraAdivinada = lblPalabraAdivinada;
		this.lblPalabrasFalladas = lblPalabrasFalladas;
		this.lblAciertos = lblAciertos;
		this.lblErrores = lblErrores;
		this.lblIntentos = lblIntentos;
		this.lbbPuntuacion = lbbPuntuacion;
		lblAciertos.setText("Aciertos: " + aciertos);
		lblErrores.setText("Errors: "+ errors);
		lblIntentos.setText("Try's: " + intentos);
		lblPalabraAdivinada.setText(palabraAdivinada.toString());
		lbbPuntuacion.setText("Puntuacion: " + puntuacion);
	}
	
	
	public String getPalabra() {
		Random r = new Random();
		int i = r.nextInt(190);
		return palabras[i];
	}
	
	public String getPalabraSecreta() {
		return palabraSecreta;
	}
	
	public void pressLetra(String letra) {
		this.letra = letra.toLowerCase().charAt(0);
	}
	
	
	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public int getAciertos() {
		return aciertos;
	}

	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}

	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}

	public void setLetrasIncorrectas(ArrayList<Character> letrasIncorrectas) {
		this.letrasIncorrectas = letrasIncorrectas;
	}

	public char getLetra() {
		return letra;
	}
	
	public int getIntentos() {
		return intentos;
	}
	
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	public StringBuilder getPalabraAdivinada() {
		return palabraAdivinada;
	}
	
	public void setGame(Hangman hang2) {
		this.hang2 = hang2;
	}
	
	public void sendGameState(Object hang) throws IOException {
		output.writeObject(getGame());
	}
	
	public Hangman getGame() {
		return this;
	}

	public void insertDatabase() throws SQLException {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		    connection = DriverManager.getConnection(url, user, password);
		    System.out.println("connected");

		    PreparedStatement checkStatement = connection.prepareStatement("SELECT COUNT(*) FROM Puntuacion WHERE nombre = ?");
		    checkStatement.setString(1, hang.getName());
		    ResultSet resultSet = checkStatement.executeQuery();
		    resultSet.next();
		    int count = resultSet.getInt(1);

		    if (count == 0) {
		        // No existe un registro con el mismo nombre, procede con la inserción
		        PreparedStatement insertStatement = connection.prepareStatement(query);
		        insertStatement.setString(1, hang.getName());
		        insertStatement.setInt(2, puntuacion);
		        insertStatement.setInt(3, aciertos);
		        insertStatement.setInt(4, errors);
		        insertStatement.executeUpdate();
		    } else {
		        // El registro ya existe, realiza la actualización
		        PreparedStatement updateStatement = connection.prepareStatement(query3);
		        updateStatement.setInt(1, puntuacion);
		        updateStatement.setInt(2, aciertos);
		        updateStatement.setInt(3, errors);
		        updateStatement.setString(4, hang.getName());
		        updateStatement.executeUpdate();
		    }
		} catch (SQLException | ClassNotFoundException e) {
		    e.printStackTrace();
		}

	}
	
	public boolean checkLetra(char letra) {
		boolean check = false;
		for(int i=0;i<palabraSecreta.length();i++) {
			if(letra == palabraSecreta.charAt(i)) {
				palabraAdivinada.setCharAt(i, letra);
				lblPalabraAdivinada.setText(palabraAdivinada.toString());
				check = true;
			}
		}
		if(check) {
			++aciertos;
			lblAciertos.setText("Aciertos: " + aciertos);
			puntuacion+=20;
			setPuntuacion(puntuacion);
			lbbPuntuacion.setText("Puntuacion: " + puntuacion);
		}
		else {
			++errors;                                                                       
			setErrors(errors);
			lblErrores.setText("Errors: " + errors);
			--intentos;
			lblIntentos.setText("Try's: " + intentos);
			letrasIncorrectas.add(letra);
			puntuacion-=10;
			setPuntuacion(puntuacion);
			lbbPuntuacion.setText("Puntuacion: " + puntuacion);
			lblPalabrasFalladas.setText(lblPalabrasFalladas.getText() + " " + Character.toString(letra));
		}
		return check;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(intentos > 0 && palabraAdivinada.indexOf("*") != -1) {

            System.out.print("Ingresa una letra: ");
            pressLetra(e.getActionCommand());
            checkLetra(letra);
            System.out.println("Palabra adivinada: " + palabraAdivinada);
            System.out.println("Letras incorrectas: " + letrasIncorrectas);
            System.out.println("Intentos restantes: " + intentos);
            
		}
		else if(intentos == 0 || palabraAdivinada.indexOf("*") == -1) {
			System.out.println("Boton creado");
			System.out.println("Has perdido. La palabra era: " + palabraSecreta);
			try {
				insertDatabase();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JButton RestartGame = new JButton("Restart");
			RestartGame.setBounds(500, 250, 101, 27);
			RestartGame.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						FHangman frame = new FHangman();
						frame.setVisible(true);
					}
				}
			);
			contentPane.add(RestartGame);
		}
	}
	
	public void actionPerformed(String palabra) {
		if(intentos > 0 && palabraAdivinada.indexOf("*") != -1) {

            System.out.print("Ingresa una letra: ");
            pressLetra(palabra);
            checkLetra(letra);
            System.out.println("Palabra adivinada: " + palabraAdivinada);
            System.out.println("Letras incorrectas: " + letrasIncorrectas);
            System.out.println("Intentos restantes: " + intentos);
            
		}
		else if(intentos == 0 || palabraAdivinada.indexOf("*") == -1) {
			System.out.println("Boton creado");
			System.out.println("Has perdido. La palabra era: " + palabraSecreta);
			try {
				insertDatabase();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JButton RestartGame = new JButton("Restart");
			RestartGame.setBounds(500, 250, 101, 27);
			RestartGame.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
						FHangman frame = new FHangman();
						frame.setVisible(true);
					}
				}
			);
			contentPane.add(RestartGame);
		}
	}
	
}


