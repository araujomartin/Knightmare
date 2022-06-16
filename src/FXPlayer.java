/*
 Ejemplo original

 https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html

 */

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


public enum FXPlayer {
   INTRO("intro.wav"),
   STAGE1("stage1.wav"),
   MUERTE("muriendo.wav"),
   FLECHA("flecha.wav"),
   FLECHA_FUEGO("flecha_fuego.wav"),
   BUMERAN("bumeran.wav"),
   FUEGO("fuego.wav"),
   INCOGNITO("incognito.wav"),
   INCOGNITO_ROTO("incognito_roto.wav"),
   BONUS("bonus.wav"),
   BOSS1("boss1.wav"),
   ESFERA("esfera.wav"),
   POWERUP("powerup.wav"),
   ESCUDO("escudo.wav"),
   POWEREND("end1.wav"),
   UP("1up.wav"),
   ESPADA("espada.wav"),
   TIEMPO("tiempo.wav");


   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   public static Volume volume = Volume.LOW;

   
   private Clip clip;


   FXPlayer(String wav) {
      try {

         URL url = this.getClass().getClassLoader().getResource("sonidos/"+wav);

         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

         clip = AudioSystem.getClip();

         clip.open(audioInputStream);

       
         
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }


   public void play(float numero) {
      FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        f.setValue(numero);
      if (volume != Volume.MUTE) {
         if (!clip.isRunning()){
         	  clip.setFramePosition(0);
         		clip.start();
         }else{
               clip.setFramePosition(0);
         		clip.start();
         }


      }
   }

   public void play2(float numero) {
      FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        f.setValue(numero);
      if (volume != Volume.MUTE) {
         if (!clip.isRunning()){
         	  clip.setFramePosition(0);
         		clip.start();
         }
      }
   }

   public void stop(){
      if (volume != Volume.MUTE) {
         if (clip.isRunning()){
              clip.setFramePosition(0);
               clip.stop();
         }


      }
   }
   public void loop(float numero){
      FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        f.setValue(numero);
       if (volume != Volume.MUTE) {
         if (!clip.isRunning()){
              clip.loop(Clip.LOOP_CONTINUOUSLY);
         }


      }
   }

   
 
   static void init() {
      values();
   }
}