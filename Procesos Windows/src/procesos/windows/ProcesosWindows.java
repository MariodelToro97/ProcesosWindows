
package procesos.windows;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 * @author Mario Josue del Toro Morales
 */
public class ProcesosWindows {

    public static void main(String[] args) {
        int opcion, tamaño = 0;
        byte procesos = 0, segmentos;
        float ocupado = -1;
        boolean salir;

        opcion = JOptionPane.showOptionDialog(null, "SELECCIONE OPCIÓN", "MENU PRINCIPAL", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"INICIAR PROCESO", "SALIR"}, null);

        if (opcion == 0) {
            
            //Tamaño de memoria de la RAM
            do {
                salir = false;
                try {
                    do {
                        tamaño = Integer.parseInt(JOptionPane.showInputDialog(null, "Tamaño de la RAM:", "RAM", JOptionPane.INFORMATION_MESSAGE));

                        if (tamaño <= 0) {
                            JOptionPane.showMessageDialog(null, "Usted ingreso un tamaño negativo\n Favor de volver a ingresar un valor", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (tamaño <= 0);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR", JOptionPane.ERROR_MESSAGE);
                    salir = true;
                }
            } while (salir);
            
            //Porcentaje de utilización de la RAM
            do {
                salir = false;
                try {
                    do {
                        ocupado = Float.parseFloat(JOptionPane.showInputDialog(null, "Porcentaje de la RAM ocupada en porcentaje: (0-100)", "Ocupación RAM", JOptionPane.INFORMATION_MESSAGE));

                        if (ocupado < 0 || ocupado > 100) {
                            JOptionPane.showMessageDialog(null, "Usted ingreso un dato fuera del rango\n Favor de volver a ingresar un valor", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (ocupado < 0 || ocupado > 100);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    salir = true;
                }
            } while (salir);

            String RAM[] = new String[tamaño];
            
            //Método para llenar espacios de forma aleatoria de la RAM
            RAM = OcupadosRAM(RAM, ocupado, tamaño);

            //Ingreso por parte del usuario el número de procesos que va a ingresar a la RAM
            do {
                salir = false;
                try {
                    
                    do {
                        procesos = Byte.parseByte(JOptionPane.showInputDialog(null, "¿Cuántos Procesos va a realizar?", "Numero de procesos", JOptionPane.INFORMATION_MESSAGE));
                    
                        if (procesos < 1) {
                            JOptionPane.showMessageDialog(null, "IngresÓ un número de procesos erróneo", "Procesos", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (procesos < 1);
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    salir = true;
                }
            } while (salir);
            
            String nombre;
            
            //Número de Segemetos que va a contener cada uno de los Procesos
            do {
                salir = false;                
                try{
                    
                    for (int i = 0; i < procesos; i++) {
                        segmentos = Byte.parseByte(JOptionPane.showInputDialog(null, "Número de segmentos que tiene el proceo " + i+1, "Segementos proceso " + i + 1, JOptionPane.QUESTION_MESSAGE));
                        
                       nombre = "TMS_P" + i+1;
                        
                        byte[][] nombre = new byte[segmentos][4];
                    }
                    
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    salir = true;
                }
                
            } while (salir);
            

            desplegadoRAM(RAM, tamaño);
        }
    }

    //Método para llenar espacios de RAM con espacios Ocupados
    public static String[] OcupadosRAM(String[] RAM, float ocupado, int tamaño) {
        int a = 0, i = 0, correcto, random;
        boolean encontrado = false;

        correcto = (int) (tamaño * (ocupado / 100));

        if (correcto != 0) {
            int llenado[] = new int[correcto];

            Random aleatorio = new Random();

            for (int j = 0; j < correcto; j++) {
                llenado[j] = -1;
            }

            do {
                do {
                    encontrado = false;

                    random = aleatorio.nextInt(tamaño);

                    for (int j = 0; j < a; j++) {
                        if (llenado[j] == random) {
                            encontrado = true;
                            break;
                        }
                    }
                } while (encontrado);

                llenado[a] = random;
                a++;

                RAM[random] = "OCUPADO";
                i++;

            } while (i < correcto);
        }

        return RAM;
    }
    
    //Método para desplegar toda la RAM con sus respectivas asignaciones
    public static void desplegadoRAM(String[] RAM, int tamaño) {
        int i = 0;

        do {
            if (i % 2 == 0) {
                System.out.println(i + "     " + RAM[i]);
            } else {
                System.out.println("      " + RAM[i]);
            }
            i++;
        } while (i < tamaño);
    }
}
