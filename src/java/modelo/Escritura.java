/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.FileNotFoundException;
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
            int cont=0;
            for(char a:palabra){
                raf.writeChar(a);
                cont++;
            }
            raf.writeChar('.');
        } catch (IOException ex) {
         ex.printStackTrace();
        }
    }
    
    public void escrituraSchema(String nombre){
        try {
         if(nombre.length()>=20){
             System.out.println("Nombre muy largo solo 19 caracteres maximo");
             return ;
         }
         // create a new RandomAccessFile with filename test
         RandomAccessFile raf = new RandomAccessFile("esquemas.txt", "rw");  
         if(raf.length()==0){
             raf.writeInt(1);
             raf.writeInt(108);
             raf.writeInt(1);
             raf.writeInt(108);
             raf.seek(108);
             raf.writeInt(1);
             destruirP(108+4, nombre, "esquemas.txt");
         }else{
             raf.seek(0);
             int cantidadDatos=raf.readInt();
             int posDatos=raf.readInt();
             raf.seek(posDatos+44*(cantidadDatos-1));
             int id=raf.readInt()+1;
             raf.seek(posDatos+44*cantidadDatos);
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

    public int recibirFK(String ns, String archivo) throws FileNotFoundException {
        try{
            RandomAccessFile raf= new RandomAccessFile(archivo, "rw");
            char[] palabra=ns.toCharArray();
            int cantidadDatos=raf.readInt();
            int posDatos=raf.readInt();
            raf.seek(posDatos);
            for(int i=0;i<cantidadDatos;i++){
                int id=raf.readInt();
                boolean t=true;
                for(int j=0;j<ns.length();j++){
                    char comparador=raf.readChar();
                    if(comparador=='.'){
                        t=false;
                        break;
                    }
                    if(palabra[j]!=comparador){
                        t=false;
                        break;
                    } 
                }
                if(t=true && raf.readChar()=='.'){
                    System.out.println("id; "+id);
                    return id;
                }
                posDatos+=44;
                raf.seek(posDatos);
            }
        } catch (IOException ex) {
         ex.printStackTrace();
        }
        return -1;
    }
    
     public void escrituraTables(int fk,String nombre){
         try {
         if(nombre.length()>=20 || fk==-1){
             System.out.println("Nombre muy largo solo 19 caracteres maximo o el nombre de la tabla no existe");
             return ;
         }
         // create a new RandomAccessFile with filename test
         RandomAccessFile raf = new RandomAccessFile("tablas.txt", "rw");
         if(raf.length()==0){
             raf.writeInt(1);
             raf.writeInt(208);
             raf.writeInt(1);
             raf.writeInt(208);
             raf.writeInt(fk);
             raf.seek(208);
             raf.writeInt(1);
             destruirP(208+4, nombre, "tablas.txt");
         }else{
             raf.seek(0);
             int cantidadDatos=raf.readInt();
             int posDatos=raf.readInt();
             raf.seek(posDatos+44*(cantidadDatos-1));
             int id=raf.readInt()+1;
             raf.seek(posDatos+44*cantidadDatos);
             raf.writeInt(id);
             int pos=(int) raf.getFilePointer();
             destruirP(pos, nombre, "tablas.txt");
             raf.seek(8+12*cantidadDatos);
             raf.writeInt(id);
             raf.writeInt(pos);
             raf.writeInt(fk);
             raf.seek(0);
             raf.writeInt(cantidadDatos+1);
            }
        } catch (IOException ex) {
         ex.printStackTrace();
        }
    }

    public void escrituraColumnas(int fk, String atr, String tipoAtr) {
        try {
         if(atr.length()>=20 || atr.length()>=20 || fk==-1){
             System.out.println("Nombre muy largo solo 19 caracteres maximo o el nombre de la tabla no existe");
             return ;
         }
         // create a new RandomAccessFile with filename test
         RandomAccessFile raf = new RandomAccessFile("columnas.txt", "rw");
         //File file = new File("columnas.txt");
            //System.out.println(file.getAbsolutePath());
         if(raf.length()==0){
             raf.writeInt(1);
             raf.writeInt(208);
             raf.writeInt(1);
             raf.writeInt(208);
             raf.writeInt(fk);
             raf.seek(208);
             raf.writeInt(1);
             destruirP(208+4, atr, "columnas.txt");
             destruirP(208+44, tipoAtr, "columnas.txt");
         }else{
             raf.seek(0);
             int cantidadDatos=raf.readInt();
             int posDatos=raf.readInt();
             raf.seek(posDatos+84*(cantidadDatos-1));
             int id=raf.readInt()+1;
             raf.seek(posDatos+84*cantidadDatos);
             raf.writeInt(id);
             int pos=(int) raf.getFilePointer();
             destruirP(pos, atr, "columnas.txt");
             destruirP(pos+40, tipoAtr, "columnas.txt");
             raf.seek(8+12*cantidadDatos);
             raf.writeInt(id);
             raf.writeInt(pos);
             raf.writeInt(fk);
             raf.seek(0);
             raf.writeInt(cantidadDatos+1);
            }
        } catch (IOException ex) {
         ex.printStackTrace();
        }
    }
    
    
}