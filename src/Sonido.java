import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sonido{
    Clip clip;
    
    public Sonido(){
    }

    public void play(String nombreSonido)
    {        
      try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nombreSonido));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        f.setValue(-20.0f);
        clip.start();
       } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
         System.out.println("Error al reproducir el sonido.");
       }
    }
      
      public void AbrirFichero(String ruta) throws Exception {
      }
      
      public void Pausa() throws Exception {
      }
      
      public void Continuar() throws Exception {

      }
      
      public void Stop() throws Exception {
        this.clip.stop();
      }

      public void playEffect(String nombreSonido){
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nombreSonido));
          clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
          f.setValue(-7.0f);
          clip.start();
         } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
           System.out.println("Error al reproducir el sonido.");
         }
      }

      public void looping(String nombreSonido){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(nombreSonido));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            f.setValue(-20.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
           } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
             System.out.println("Error al reproducir el sonido.");
           }

      }
}
