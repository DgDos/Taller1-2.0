/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Sary
 */
public class Escritura {
    
    public static void destruirP(int pos, String txt, String archivo){
        try{
            RandomAccessFile raf= new RandomAccessFile(archivo, "rw");
            char[] palabra=txt.toCharArray();
            raf.seek(pos);
            for(char a:palabra)
                raf.writeChar(a);
        } catch (IOException ex) {
         ex.printStackTrace();
      }
    }
    
    public void escrituraSchema(String nombre){
        try {
         if(nombre.length()>20){
             return ;
         }
         // create a new RandomAccessFile with filename test
         RandomAccessFile raf = new RandomAccessFile("esquemas.txt", "rw");
         
         File file = new File("esquemas.txt");
            System.out.println(file.getAbsolutePath());
            
         if(raf.length()==0){
             raf.writeInt(1);
             raf.writeInt(108);
             raf.writeInt(1);
             raf.writeInt(108);
             raf.seek(108);
             raf.writeInt(1);
             destruirP(108+4, nombre, "esquemas.txt");
             System.out.println("holaaaa");
         }else{
             int cantidadDatos=raf.readInt();
             raf.seek(raf.readInt()+44*cantidadDatos-44);
             int id=raf.readInt()+1;
             raf.skipBytes(40);
             raf.writeInt(id);
             int pos=(int) raf.getFilePointer();
             destruirP(pos, nombre, "esquemas.txt");
             raf.seek(8+8*cantidadDatos);
             raf.writeInt(id);
             raf.writeInt(pos);
             raf.seek(0);
             raf.writeInt(cantidadDatos+1);
             
             
         }
      } catch (IOException ex) {
         ex.printStackTrace();
      }
    }  
}