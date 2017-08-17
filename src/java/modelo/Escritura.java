package modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Sara Chamseddine, Brenda Mendez y Oscar Romero
 * @since   16/08/2017
 */
public class Escritura {
    
    /** Destruye la palabra en caracteres.
     * 
     * @param pos posición en la que está la palabra.
     * @param txt palabra.
     * @param archivo dirección del archivo.
     */
    
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
    
    /** Escribe el archivo de los esquemas.
     * 
     * @param nombre nombre del esquema.
     */
    
    public void escrituraSchema(String nombre){
        try {
            // Si el nombre del esquema es mayor a 20 no retorna nada.
         if(nombre.length()>=20){
             System.out.println("Nombre muy largo solo 19 caracteres maximo");
             return ;
         }
         // crea el archivo de esquemas.
         RandomAccessFile raf = new RandomAccessFile("esquemas.txt", "rw");  
         // si el archivo está vacío
          File file = new File("esquemas.txt");
            System.out.println(file.getAbsolutePath());
         if(raf.length()==0){
             raf.writeInt(1); // Cantidad de elementos.
             raf.writeInt(108); // Byte en donde comienza la tercera parte del archivo (datos).
             raf.writeInt(1); // Clave.
             raf.writeInt(108); // Posición.
             raf.seek(108); // Busca el byte 108.
             raf.writeInt(1); // Escribe el dato.
             destruirP(108+4, nombre, "esquemas.txt"); // Invoca al método y guarda el nombre del esquema.
         }else{
             //No vacío y no sobrepasa longitud.
             raf.seek(0); // Byte cero = cantidad de elementos que hay en el documento.
             int cantidadDatos=raf.readInt(); 
             int posDatos=raf.readInt(); 
             raf.seek(posDatos+44*(cantidadDatos-1)); // Se posiciona en la clave anterior.
             int id=raf.readInt()+1; // Lee id anterior.
             raf.seek(posDatos+44*cantidadDatos); // Se posiciona en la clave actual.
             raf.writeInt(id);
             int pos=(int) raf.getFilePointer(); // Obtiene la posición.
             destruirP(pos, nombre, "esquemas.txt"); // añade nombre del esquema.
             raf.seek(8+8*cantidadDatos); // Agrega la clave y su posición en la sección de claves del archivo.
//             int posLlave=(int) raf.getFilePointer();
             raf.writeInt(id); 
             raf.writeInt(pos);
             //armarArbol(posLlave, "esquemas.txt");
             raf.seek(0);
             raf.writeInt(cantidadDatos+1); // Aumenta cantidad de datos.    
            }
        } catch (IOException ex) {
         ex.printStackTrace();
        }
    }  

    /** Busca en el archivo el nombre de un esquema o tabla.
     * 
     * @param ns nombre del esquema o tabla.
     * @param archivo dirección del archivo.
     * @return -1 si no lo encuentra, sino devuelve el id.
     * @throws FileNotFoundException 
     */
    
    public int recibirFK(String ns, String archivo) throws FileNotFoundException {
        try{
            RandomAccessFile raf= new RandomAccessFile(archivo, "rw");
            char[] palabra=ns.toCharArray();
            int cantidadDatos=raf.readInt();
            int posDatos=raf.readInt();
            raf.seek(posDatos);
            // Recorrer todos los datos.
            for(int i=0;i<cantidadDatos;i++){
                int id=raf.readInt();
                boolean t=true;
                // Recorrer todos los caracteres de la palabra.
                for(int j=0;j<ns.length();j++){
                    char comparador=raf.readChar(); // Lee los caracteres de la palabra.
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
    
    /**Escribe el archivo de las tablas.
     * 
     * @param fk foreign key. 
     * @param nombre nombre del esquema.
     */
    
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
    /**Escribe el archivo de las columnas.
     * 
     * @param fk foreign key. 
     * @param atr nombre del esquema.
     * @param tipoAtr
     */
    public void escrituraColumnas(int fk, String atr, String tipoAtr) {
        try {
         if(atr.length()>=20 || atr.length()>=20 || fk==-1){
             System.out.println("Nombre muy largo solo 19 caracteres maximo o el nombre de la tabla no existe");
             return ;
         }
         // create a new RandomAccessFile with filename test
         RandomAccessFile raf = new RandomAccessFile("columnas.txt", "rw");
        
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
    /**Imprime los datos dado un id de esquema
     * 
     * @param idEsquema= id del esquema del cual se imprimiran los datos
     * @return un String con los valores de las tablas y columnas correspodintes a ese id
     */
    public String imprimirDatos(int idEsquema){
        try{
            String palabra="";
            RandomAccessFile tab = new RandomAccessFile("tablas.txt", "rw");
            RandomAccessFile col = new RandomAccessFile("columnas.txt", "rw");
            tab.seek(0);
            int cantidadT = tab.readInt();
            tab.seek(8);
            for(int i=0;i<cantidadT;i++){
                int idTabla=tab.readInt();
                int posDatoTabla=tab.readInt();
                int idComparadorT=tab.readInt();
                int posBusquedaT=(int) tab.getFilePointer();
                if(idComparadorT==idEsquema){
                    palabra+="<h4>Tabla #"+idTabla+":";
                    tab.seek(posDatoTabla);
                    while(true){
                        char a=tab.readChar();
                        if(a!='.'){
                            palabra+=a;
                        }else{
                            break;
                        }
                    }
                    palabra+="</h4>";
                    col.seek(0);
                    int cantidadC=col.readInt();
                    col.seek(8);
                    for(int j=0;j<cantidadC;j++){
                        int idColumna=col.readInt();
                        int posColumna=col.readInt();
                        int idComparadorC=col.readInt();
                        int posBusqueda=(int) col.getFilePointer();
                        if(idComparadorC==idTabla){
                            palabra+="<h6>"+idColumna+" ";
                            col.seek(posColumna);
                            while(true){
                                char a=col.readChar();
                                if(a!='.'){
                                    palabra+=a;
                                }else{
                                    break;
                                }
                            }
                            palabra+=" ";
                            col.seek(posColumna+40);
                            while(true){
                                char a=col.readChar();
                                if(a!='.'){
                                    palabra+=a;
                                }else{
                                    break;
                                }
                            }
                            palabra+="</h6>";
                        }
                        col.seek(posBusqueda);
                    }
                }
                tab.seek(posBusquedaT);
            }
            return palabra;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
        
    }

    
}