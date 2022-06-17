
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;


public class SistemaJuego extends JFrame implements ActionListener{

    Fondo fondo=new Fondo();
    JPanel configuracion;
    Juego j1,j2,j3,j4;
    CardLayout cl= new CardLayout();
    
    JPanel botonera;
    JPanel contenedor;
    JPanel titulo=new JPanel();
    JButton iniciar, config, volver;
    JPanelBackgroundSemiOpaco juegos;
   
    public SistemaJuego(){

        super("UNLPAM Games");
        fondo.setLayout(new BorderLayout());
        botonera();
        contenedor= new JPanel();
        contenedor.setLayout(cl);
        juegos= new JPanelBackgroundSemiOpaco();
        
        this.setContentPane(fondo);
        this.getContentPane().add(botonera,BorderLayout.SOUTH);
        this.getContentPane().add(contenedor,BorderLayout.CENTER);
        this.getContentPane().add(titulo,BorderLayout.NORTH);
        
        
        titulo.add(new JLabel("UNLPAM GAMES"));
        contenedor.add("lista",juegos);
        

        j1=new Juego("knightmare");
        j1.addActionListener(this);
        j1.setActionCommand(j1.get_titulo());
        juegos.add(j1);
        contenedor.add(j1.get_titulo()+"_info",j1.descripcion());
        contenedor.add("cfg",j1.config_panel());
        
        

        j2=new Juego("mk3");
        j2.addActionListener(this);
        j2.setActionCommand(j2.get_titulo());
        juegos.add(j2);

        j3=new Juego("bomberman");
        j3.addActionListener(this);
        j3.setActionCommand(j3.get_titulo());
        juegos.add(j3);

        j4=new Juego("adventure");
        j4.addActionListener(this);
        j4.setActionCommand(j4.get_titulo());
        juegos.add(j4);

        
        
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(650, 600);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }


 public static void main(String args[]){
     new SistemaJuego();

 }

    private void botonera(){

        botonera= new JPanel();
        iniciar= new JButton("Jugar");
        config= new JButton("Configuracion");
        volver= new JButton("Volver");

        botonera.add(iniciar);
        botonera.add(config);
        botonera.add(volver);

        iniciar.addActionListener(this);
        volver.addActionListener(this);
        config.addActionListener(this);

        botonera.setVisible(false);

    }
 

    public void actionPerformed(ActionEvent e){

        if(e.getActionCommand()==j1.getActionCommand()){
            System.out.println("juego 1");
            botonera.setVisible(true);
            cl.show(contenedor, j1.get_titulo()+"_info");              
        }
        else if (e.getActionCommand()==j2.getActionCommand()){
            System.out.println("juego 2");
        }
        if(e.getActionCommand()==iniciar.getActionCommand()){
            Knightmare game = new Knightmare();

						Thread t = new Thread() {
						    public void run() {
						        game.run(1.0 / 60.0);
                                System.exit(0);
						    }
						};

						t.start();       
            
        }
        if(e.getActionCommand()==volver.getActionCommand()){
            cl.show(contenedor, "lista");
            botonera.setVisible(false);
        }
        if(e.getActionCommand()==config.getActionCommand()){
            cl.show(contenedor,"cfg");
            
        }
    
    }
 
 
    private class Juego extends JButton {

        private GameConfigV2 configuracion;
        private String titulo;
        private JPanel descripcion=new JPanel();
        
        public Juego(String titulo){
            this.titulo=titulo;
            this.setMargin(new Insets(0, 0, 0, 0));
            this.setIcon(new ImageIcon(getClass().getResource("imagenes/"+titulo+".jpg")));
              }

        public Container config_panel(){

            configuracion=new GameConfigV2();
            configuracion.setVisible(false);

            return configuracion.getContentPane();  
        }
        public String get_titulo(){
            return titulo;
        }
        public JPanel descripcion(){

            descripcion.setLayout(new BorderLayout());
            JLabel imagen= new JLabel();
            imagen.setIcon(new ImageIcon(getClass().getResource("imagenes/"+titulo+".gif")));
            descripcion.add(imagen,BorderLayout.CENTER);
            
            return descripcion;
        }
  
        }
    

    private class Fondo extends JPanel{

        private Image imagen;
        

        public void paint(Graphics g){

            imagen= new ImageIcon(getClass().getResource("imagenes/fondo.gif")).getImage();
            g.drawImage(imagen,0,0,getWidth(),getHeight(),this);
            setOpaque(false);
            super.paint(g);
        
              }
    }


    class JPanelBackgroundSemiOpaco extends JPanel{
	 
        public void paintComponent(Graphics g) {
         
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.80f));
            g2d.setColor(Color.black);
            g2d.fillRoundRect(0,0, this.getWidth(), this.getHeight(), 10, 10); 
         
        }
    }


    



    
}


