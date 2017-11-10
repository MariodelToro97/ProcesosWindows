package procesos.windows;

import java.util.ArrayList;
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
                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
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
                            JOptionPane.showMessageDialog(null, "Ingresó un número de procesos erróneo", "Procesos", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (procesos < 1);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                    salir = true;
                }
            } while (salir);

            byte[] proc = new byte[procesos];

            //Número de Segemetos que va a contener cada uno de los Procesos
            do {
                salir = false;
                try {
                    int i = 0;

                    do {
                        do {
                            segmentos = Byte.parseByte(JOptionPane.showInputDialog(null, "Número de segmentos que tiene el proceso " + (i + 1), "Segementos proceso " + (i + 1), JOptionPane.QUESTION_MESSAGE));

                            if (segmentos <= 0) {
                                JOptionPane.showMessageDialog(null, "Ingresó un número de segmentos erróneo", "Error Segmentos", JOptionPane.ERROR_MESSAGE);
                            }
                        } while (segmentos <= 0);

                        proc[i] = segmentos;

                        i++;
                    } while (i < procesos);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                    salir = true;
                }
            } while (salir);

            int h = 0;

            for (int i = 0; i < proc.length; i++) {
                h += proc[i];
            }

            int[][] TMS = new int[h][4];

            String[] tipo = new String[h];
            Byte cuantos[] = new Byte[h];

            altaSegmentos(proc, h, tipo, cuantos, RAM);

            TMS = llenadoTMS(TMS, h);
            desplegadoRAM(RAM, tamaño);
            desplegadoTMS(TMS, h, proc);
        }
    }

    //Método para dar de alta los datos de cada segmento
    public static void altaSegmentos(byte[] proc, int h, String[] tipo, Byte[] cuantos, String[] RAM) {
        int k, o = 0;
        boolean bueno;
        String cadenota = "";
        byte numero = 0;

        for (int i = 0; i < proc.length; i++) {
            k = 0;
            cadenota += "\nEl Proceso " + (i + 1) + " tiene " + proc[i] + " Segmentos.";

            do {
                tipo[o] = (String) JOptionPane.showInputDialog(null, "¿Qué va a contener el Segmento " + (k + 1) + " del Proceso " + (i + 1), "ALTA DE SEGMENTOS", JOptionPane.QUESTION_MESSAGE, null, new Object[]{"PÁGINA", "VARIABLE"}, "SELECCIONE UNA OPCIÓN");

                do {
                    bueno = false;

                    try {
                        if (tipo[o].equalsIgnoreCase("PÁGINA")) {
                            do {
                                numero = Byte.parseByte(JOptionPane.showInputDialog(null, "Número de Páginas que contendrá el Segmento " + (k + 1) + " del Proceso " + (i + 1), "Total", JOptionPane.QUESTION_MESSAGE));

                                if (numero <= 0) {
                                    JOptionPane.showMessageDialog(null, "Ingresó un número de procesos erróneo", "Procesos", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (numero <= 0);
                        } else {
                            do {
                                numero = Byte.parseByte(JOptionPane.showInputDialog(null, "Número de Variables que contendrá el Segmento " + (k + 1) + " del Proceso " + (i + 1), "Total", JOptionPane.QUESTION_MESSAGE));

                                if (numero <= 0) {
                                    JOptionPane.showMessageDialog(null, "Ingresó un número de procesos erróneo", "Procesos", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (numero <= 0);
                        }

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                        bueno = true;
                    }
                } while (bueno);

                cuantos[i] = numero;

                if (tipo[o].equalsIgnoreCase("PÁGINA")) {
                    cadenota += "\n --> El Segmento " + (k + 1) + " tiene " + cuantos[i] + " páginas";
                } else {
                    cadenota += "\n --> El Segmento " + (k + 1) + " contiene " + cuantos[i] + " variables de 1 Kb c/u";
                }

                k++;
                o++;

            } while (k < proc[i]);
            cadenota += "\n";
        }

        JOptionPane.showMessageDialog(null, cadenota, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        llenadoRAM(tipo, proc, cuantos, RAM);
    }

    //Método para llenar la RAM con los procesos seleccionados por los usuarios
    public static void llenadoRAM(String[] tipo, byte[] proc, Byte[] cuantos, String RAM[]) {
        int t = 0, p = 0, contador = 0, q = 0;

        for (int i = 0; i < proc.length; i++) {
            do {
                switch (tipo[t]) {
                    case "PÁGINA":

                        break;

                    case "VARIABLE":
                        q = 0;
                        do {
                            for (int j = contador; j < RAM.length; j++) {
                                if (RAM[j].equalsIgnoreCase("---------")) {
                                    RAM[j] = "V" + (q + 1) + " S" + (t + 1) + " P" + (p + 1) + " ";
                                }
                                contador = j;
                            }
                            q++;
                        } while (q < (cuantos[t] - 1));

                        break;
                }

                t++;
                p++;
            } while (p < proc[i]);

        }
    }

    //Método para llenar la Tabla de Mapa de Segmentos
    public static int[][] llenadoTMS(int[][] TMS, int h) {

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < 4; j++) {
                TMS[i][j] = (i + j);
            }
        }

        return TMS;
    }

    //Método para desplegar TMS
    public static void desplegadoTMS(int[][] TMS, int h, byte proc[]) {
        int k = 0, sum;

        sum = proc[0];

        for (int i = 0; i < h; i++) {
            if (i == 0) {
                System.out.println("\nTMS P" + (i + 1));
            } else {
                if (i == sum) {
                    System.out.println("\nTMS P" + (k + 2));

                    k++;
                    sum += proc[k];
                }
            }
            for (int j = 0; j < 4; j++) {
                System.out.print("  " + TMS[i][j]);
            }
            System.out.println();
        }
    }

    //Método para llenar espacios de RAM con espacios Ocupados
    public static String[] OcupadosRAM(String[] RAM, float ocupado, int tamaño) {
        int a = 0, i = 0, correcto, random;
        boolean encontrado = false;

        correcto = (int) (tamaño * (ocupado / 100));

        for (int j = 0; j < RAM.length; j++) {
            RAM[j] = "---------";
        }

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

                RAM[random] = "-OCUPADO-";
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
                System.out.println(RAM[i] + "     " + i);
            } else {
                System.out.println(RAM[i]);
            }
            i++;
        } while (i < tamaño);
    }
}
