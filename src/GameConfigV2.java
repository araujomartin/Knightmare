import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;
import javax.swing.*;


public class GameConfigV2 extends JFrame implements ActionListener {

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();
    String music_list[] = { "Original", "Final_Countdown" };
    String personajes[] = { "Popolon", "PopolonBoca"};
    JTabbedPane pestañas = new JTabbedPane();
    JDialog dialogo = new JDialog();
    JTextField efectos, musica, pausa, left, right, up, down, disparo, enter;

    JButton guardar = new JButton("Guardar");
    JButton pordefecto = new JButton("Por defecto");

    JRadioButton BtnFullScreen = new JRadioButton("Pantalla completa");
    JRadioButton BtnWindowed = new JRadioButton("Ventana", true);
    JRadioButton BtnMusicON = new JRadioButton("Activado", true);
    JRadioButton BtnMusicOFF = new JRadioButton("Desactivado");

    JComboBox<String> pistas = new JComboBox<String>(music_list);
    JComboBox<String> personaje = new JComboBox<String>(personajes);

    JLabel msj = new JLabel();
    GamePropieties cfg;

    public GameConfigV2() {

        super("Configuracion juego");
        teclas();
        dialogo();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));

        pestañas.add("Config. General", configuracion_general());
        pestañas.add("Controles", controles());

        this.getContentPane().add(pestañas);
        this.getContentPane().add(botonera(), BorderLayout.SOUTH);

        this.setSize(640, 480);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    public static void main(String args[]) {
        GameConfigV2 juego = new GameConfigV2();
    }

    private JPanel configuracion_general() {

        JPanel config_gral = new JPanel(gbl);
        ButtonGroup resolucion = new ButtonGroup();
        ButtonGroup musica = new ButtonGroup();

        JLabel titulo = new JLabel("Configuracion general del juego");

        musica.add(BtnMusicON);
        musica.add(BtnMusicOFF);
        resolucion.add(BtnFullScreen);
        resolucion.add(BtnWindowed);

        titulo.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 20));
        // Posicion en mi JPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Cuantas columnas quiero que ocupe mi obj
        gbc.gridheight = 1; // Cuantas filas quiero que ocupe mi obj
        // gbc.weightx=1.0; // 1.0 Para estirar la columna hasta el final del JPanel
        gbc.anchor = GridBagConstraints.WEST; // Posicion que ocupa dentro de la columna/fila
        gbc.fill = GridBagConstraints.HORIZONTAL; // Completa la celda en horizontal
        config_gral.add(titulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        config_gral.add(new JLabel("Sonido"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        config_gral.add(BtnMusicON, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        config_gral.add(BtnMusicOFF, gbc);

        // Etiqueta resolucion con sus opciones

        gbc.gridx = 0;
        gbc.gridy = 2;
        config_gral.add(new JLabel("Resolucion"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        config_gral.add(BtnFullScreen, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        config_gral.add(BtnWindowed, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        config_gral.add(new JLabel("Personaje"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        config_gral.add(personaje, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        config_gral.add(new JLabel("Tema"), gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        config_gral.add(pistas, gbc);

        return config_gral;
    }

    private JPanel controles() {

        JPanel controles = new JPanel(gbl);
        JLabel titulo = new JLabel("Definición de teclas");

        titulo.setFont(new Font("Times-Roman", Font.BOLD + Font.ITALIC, 18));

        gbc.gridx = 0;
        gbc.gridy = 0;

        controles.add(titulo, gbc);

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridy = 1;
        controles.add(new JLabel("Activar/Desactivar efectos de sonidos: "), gbc);

        gbc.gridy = 2;
        controles.add(new JLabel("Activar/desactivar música de fondo: "), gbc);

        gbc.gridy = 3;
        controles.add(new JLabel("Pausar/Reanudar juego: "), gbc);

        gbc.gridy = 4;
        controles.add(new JLabel("Disparar: "), gbc);

        gbc.gridy = 5;
        controles.add(new JLabel("Iniciar juego: "), gbc);

        gbc.gridy = 6;
        controles.add(new JLabel("Izquierda: "), gbc);

        gbc.gridy = 7;
        controles.add(new JLabel("Derecha: "), gbc);

        gbc.gridy = 8;
        controles.add(new JLabel("Arriba: "), gbc);

        gbc.gridy = 9;
        controles.add(new JLabel("Abajo: "), gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;

        gbc.gridx = 2;
        gbc.gridy = 1;
        controles.add(efectos, gbc);

        gbc.gridy = 2;
        controles.add(musica, gbc);

        gbc.gridy = 3;
        controles.add(pausa, gbc);

        gbc.gridy = 4;
        controles.add(disparo, gbc);

        gbc.gridy = 5;
        controles.add(enter, gbc);

        gbc.gridy = 6;
        controles.add(left, gbc);

        gbc.gridy = 7;
        controles.add(right, gbc);

        gbc.gridy = 8;
        controles.add(up, gbc);

        gbc.gridy = 9;
        controles.add(down, gbc);

        return controles;
    }

    private JPanel botonera() {

        JPanel botonera = new JPanel();

        guardar.addActionListener(this);
        pordefecto.addActionListener(this);
        botonera.add(guardar);
        botonera.add(pordefecto);

        return botonera;

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == guardar.getActionCommand()) {
            msj.setText("Configuracion guardada");
            dialogo.setVisible(true);
            dialogo.setSize(200, 150);
            dialogo.setLocationRelativeTo(null);
            JTextField[] campo = { efectos, musica, pausa, left, right, up, down, disparo, enter };
            for(JTextField campos: campo){
             cfg.guardar(campos.getName(),campos.getText());
            }
            if(BtnFullScreen.isSelected()){
                cfg.guardar("fullScreen","true");
            }
            else{
                cfg.guardar("fullScreen","false");
            }
            
            if(BtnMusicON.isSelected()){
                cfg.guardar("sonidoGeneral","true");
            }
            else{
                cfg.guardar("sonidoGeneral","false");
            }

            cfg.guardar("personaje", personaje.getSelectedItem().toString().toLowerCase());
            cfg.guardar("pista", pistas.getSelectedItem().toString().toLowerCase());
            

        } else if (e.getActionCommand() == pordefecto.getActionCommand()) {
            pordefecto();
        }

    }

    private void dialogo() {

        msj.setFont(new Font("Times-Roman", Font.BOLD, 15));
        dialogo.setLayout(new GridLayout());
        dialogo.setTitle("Configuracion");
        dialogo.add(msj);
        dialogo.setResizable(false);
        dialogo.setLocationRelativeTo(null);

    }

    private void pordefecto() {

        msj.setText("Configuracion restaurada por defecto");
        dialogo.setVisible(true);
        dialogo.setSize(300, 150);
        dialogo.setLocationRelativeTo(null);

        BtnWindowed.setSelected(true);
        BtnMusicON.setSelected(true);
        pistas.setSelectedIndex(0);
        personaje.setSelectedIndex(0);

        efectos.setText(cfg.defaultProps.getProperty("Efectos"));
        musica.setText(cfg.defaultProps.getProperty("Musica"));
        pausa.setText(cfg.defaultProps.getProperty("Pausa"));
        left.setText(cfg.defaultProps.getProperty("Left"));
        right.setText(cfg.defaultProps.getProperty("Right"));
        up.setText(cfg.defaultProps.getProperty("Up"));
        down.setText(cfg.defaultProps.getProperty("Down"));
        disparo.setText(cfg.defaultProps.getProperty("Disparo"));
        enter.setText(cfg.defaultProps.getProperty("Enter"));



    }

    private void teclas() {

        cfg= new GamePropieties();
    
        efectos = new JTextField(cfg.defaultProps.getProperty("efectos"));
        efectos.setName("efectos");
        musica = new JTextField(cfg.defaultProps.getProperty("musica"));
        musica.setName("musica");
        pausa = new JTextField(cfg.defaultProps.getProperty("pausa"));
        pausa.setName("pausa");
        left = new JTextField(cfg.defaultProps.getProperty("izquierda"));
        left.setName("izquierda");
        right = new JTextField(cfg.defaultProps.getProperty("derecha"));
        right.setName("derecha");
        up = new JTextField(cfg.defaultProps.getProperty("arriba"));
        up.setName("arriba");
        down = new JTextField(cfg.defaultProps.getProperty("abajo"));
        down.setName("abajo");
        disparo = new JTextField(cfg.defaultProps.getProperty("disparo"));
        disparo.setName("disparo");
        enter = new JTextField(cfg.defaultProps.getProperty("enter"));
        enter.setName("enter");

        
        JTextField[] campo = { efectos, musica, pausa, left, right, up, down, disparo, enter };

        for (JTextField campos : campo) {
            campos.setColumns(5);
            campos.setEditable(false);
            campos.setHorizontalAlignment(JTextField.CENTER);
            campos.setBackground(Color.lightGray);
            campos.setForeground(Color.white);
            campos.addKeyListener(new KeyAdapter() {

                public void keyPressed(KeyEvent e) {
                    campos.setText(KeyEvent.getKeyText(e.getKeyCode()).toUpperCase());
                }
            });
        }
    }

    public class GamePropieties{
        String jgamePropsFile="jgame.properties";
	    Properties gameProps=new Properties();
        Properties defaultProps=new Properties();

        public GamePropieties(){
            try{
            
                FileInputStream in = new FileInputStream("default.properties");
				defaultProps.load(in);
				in.close();

                gameProps=defaultProps;

				FileOutputStream output = new FileOutputStream(jgamePropsFile); 

				gameProps.store(output,null);
				output.close();

            }
            catch(Exception e){

            }
        }
        public void guardar(String key, String value){
            System.out.println(key + " - "+value);
            try{
                gameProps.setProperty(key,value);
                FileOutputStream out=new FileOutputStream("jgame.properties");
                gameProps.store(out,null);
                out.close();
            }catch(Exception e){
    
            }
        }

    }
}



