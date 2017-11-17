package procesos.windows;

import java.awt.HeadlessException;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * @author Mario Josue del Toro Morales
 */
public class ProcesosWindows {

    public static void main(String[] args) {
        int opcion, tamaño = 0, impresion = 0, h = 0, confirmacion = 0;
        byte procesos = 0, segmentos;
        float ocupado = -1;
        boolean salir, ram = false, process = false;

        String RAM[] = null;
        int[][] TMS = null;
        String[] tipo;
        Byte cuantos[] = null;
        String[][] TMP = null;
        byte[] proc = null;

        do {
            opcion = JOptionPane.showOptionDialog(null, "SELECCIONE OPCIÓN", "MENU PRINCIPAL", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"INGRESAR TAMAÑO RAM", "PORCENTAJE DE OCUPACIÓN RAM", "INGRESO DE PROCESOS", "IMPRESIÓN", "SALIR"}, null);

            if (opcion == 0) {

                if (ram) {
                    confirmacion = JOptionPane.showOptionDialog(null, "Usted ya ha ingresado un tamaño de RAM\nSi desea proseguir se perderá la memoria dada anteriormente", "ADVERTENCIA", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[]{"PROSEGUIR", "CANCELAR"}, null);

                    if (confirmacion == 0) {
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

                            } catch (HeadlessException | NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                                salir = true;
                            }
                        } while (salir);

                        RAM = new String[tamaño];
                        ram = true;
                        process = false;

                    } else {
                        JOptionPane.showMessageDialog(null, "Operación Cancelada con Éxito", "CANCELACIÓN", JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
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

                        } catch (HeadlessException | NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                            salir = true;
                        }
                    } while (salir);

                    RAM = new String[tamaño];
                    ram = true;

                    for (int i = 0; i < RAM.length; i++) {
                        RAM[i] = "---------";
                    }
                }

            } else {
                if (opcion == 1) {
                    if (!ram) {
                        JOptionPane.showMessageDialog(null, "Usted no ha ingresado ninguna memoria RAM\nPor lo tanto no puede usar este apartado\nLo invitamos ha ingresar los datos de la memoria RAM", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        if (process) {
                            confirmacion = JOptionPane.showOptionDialog(null, "Usted ya ha ingresado TODOS los datos de sus Procesos\nSi desea proseguir se perderá el progreso hasta este momento", "ADVERTENCIA", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[]{"PROSEGUIR", "CANCELAR"}, null);

                            if (confirmacion == 0) {
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

                                    } catch (HeadlessException | NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                                        salir = true;
                                    }
                                } while (salir);

                                //Método para llenar espacios de forma aleatoria de la RAM
                                RAM = OcupadosRAM(RAM, ocupado, tamaño);
                                process = false;
                            } else {
                                JOptionPane.showMessageDialog(null, "Operación Cancelada con Éxito", "CANCELACIÓN", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } else {

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

                                } catch (HeadlessException | NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                                    salir = true;
                                }
                            } while (salir);

                            //Método para llenar espacios de forma aleatoria de la RAM
                            RAM = OcupadosRAM(RAM, ocupado, tamaño);
                        }
                    }

                } else {
                    if (opcion == 2) {
                        if (!ram) {
                            JOptionPane.showMessageDialog(null, "Usted no ha ingresado ninguna memoria RAM\nPor lo tanto no puede usar este apartado\nLo invitamos ha ingresar los datos de la memoria RAM", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            if (process) {
                                confirmacion = JOptionPane.showOptionDialog(null, "Usted ya ha ingresado TODOS los datos de sus Procesos\nSi desea proseguir se perderá el progreso hasta este momento", "ADVERTENCIA", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, new Object[]{"PROSEGUIR", "CANCELAR"}, null);

                                if (confirmacion == 0) {
                                    //Ingreso por parte del usuario el número de procesos que va a ingresar a la RAM
                                    do {
                                        salir = false;
                                        try {

                                            do {
                                                procesos = Byte.parseByte(JOptionPane.showInputDialog(null, "¿Cuántos Procesos va a realizar?", "Numero de procesos", JOptionPane.QUESTION_MESSAGE));

                                                if (procesos < 1) {
                                                    JOptionPane.showMessageDialog(null, "Ingresó un número de procesos erróneo", "Procesos", JOptionPane.ERROR_MESSAGE);
                                                }
                                            } while (procesos < 1);

                                        } catch (HeadlessException | NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                                            salir = true;
                                        }
                                    } while (salir);

                                    proc = new byte[procesos];

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

                                        } catch (HeadlessException | NumberFormatException e) {
                                            JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                                            salir = true;
                                        }
                                    } while (salir);

                                    h = 0;

                                    for (int i = 0; i < proc.length; i++) {
                                        h += proc[i];
                                    }

                                    TMS = new int[h][4];

                                    tipo = new String[h];
                                    cuantos = new Byte[h];

                                    TMP = altaSegmentos(proc, h, tipo, cuantos, RAM, TMS, h);

                                    process = true;

                                } else {
                                    JOptionPane.showMessageDialog(null, "Operación Cancelada con Éxito", "CANCELACIÓN", JOptionPane.INFORMATION_MESSAGE);
                                }

                            } else {
                                //Ingreso por parte del usuario el número de procesos que va a ingresar a la RAM
                                do {
                                    salir = false;
                                    try {

                                        do {
                                            procesos = Byte.parseByte(JOptionPane.showInputDialog(null, "¿Cuántos Procesos va a realizar?", "Numero de procesos", JOptionPane.QUESTION_MESSAGE));

                                            if (procesos < 1) {
                                                JOptionPane.showMessageDialog(null, "Ingresó un número de procesos erróneo", "Procesos", JOptionPane.ERROR_MESSAGE);
                                            }
                                        } while (procesos < 1);

                                    } catch (HeadlessException | NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                                        salir = true;
                                    }
                                } while (salir);

                                proc = new byte[procesos];

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

                                    } catch (HeadlessException | NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                                        salir = true;
                                    }
                                } while (salir);

                                h = 0;

                                for (int i = 0; i < proc.length; i++) {
                                    h += proc[i];
                                }

                                TMS = new int[h][4];

                                tipo = new String[h];
                                cuantos = new Byte[h];

                                TMP = altaSegmentos(proc, h, tipo, cuantos, RAM, TMS, h);

                                process = true;
                            }
                        }

                    } else {
                        if (opcion == 3) {
                            if (!ram) {
                                JOptionPane.showMessageDialog(null, "Usted no ha ingresado ninguna memoria RAM\nPor lo tanto no puede usar este apartado\nLo invitamos ha ingresar los datos de la memoria RAM", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);

                            } else {
                                if (!process) {
                                    JOptionPane.showMessageDialog(null, "Usted no ha ingresado los datos de los procesos\nPor lo tanto no puede usar este apartado\nLo invitamos ha ingresar los datos de la memoria RAM", "ADVERTENCIA", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    //Menú para que el usuario elija que desplegado desea observar
                                    do {
                                        impresion = JOptionPane.showOptionDialog(null, "SELECCIONE OPCIÓN", "MENU IMPRESIÓN", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"IMPRESIÓN RAM", "IMPRESIÓN TMS", "IMPRESIÓN TMP", "SALIR"}, null);

                                        switch (impresion) {
                                            case 0:
                                                //Impresión de RAM
                                                desplegadoRAM(RAM, tamaño);
                                                break;

                                            case 1:
                                                //Impreión de TMS
                                                desplegadoTMS(TMS, h, proc, TMP, cuantos);
                                                break;

                                            case 2:
                                                //Impresión TMP
                                                desplegadoTMP(TMP, proc, cuantos);
                                                break;
                                        }
                                    } while (impresion != 3);
                                }
                            }
                        }
                    }
                }
            }
        } while (opcion != 4);
    }

    //Método para dar de alta los datos de cada segmento
    public static String[][] altaSegmentos(byte[] proc, int h, String[] tipo, Byte[] cuantos, String[] RAM, int[][] TMS, int ñ) {
        int k, o = 0;
        boolean bueno;
        String cadenota = "";
        byte numero = 0;

        for (int i = 0; i < proc.length; i++) {
            k = 0;
            cadenota += "\nEl Proceso " + (i + 1) + " tiene " + proc[i] + " Segmentos.";

            do {
                tipo[o] = (String) JOptionPane.showInputDialog(null, "¿Qué va a contener el Segmento " + (k + 1) + " del Proceso " + (i + 1), "ALTA DE SEGMENTOS", JOptionPane.QUESTION_MESSAGE, null, new Object[]{"PÁGINA", "VARIABLE"}, "SELECCIONE UNA OPCIÓN");

                if (tipo[o].equalsIgnoreCase("PÁGINA")) {

                    do {
                        bueno = false;
                        try {

                            do {
                                numero = Byte.parseByte(JOptionPane.showInputDialog(null, "Número de Páginas que contendrá el Segmento " + (k + 1) + " del Proceso " + (i + 1), "Total", JOptionPane.QUESTION_MESSAGE));

                                if (numero <= 0) {
                                    JOptionPane.showMessageDialog(null, "Ingresó un número de procesos erróneo", "Procesos", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (numero <= 0);

                        } catch (HeadlessException | NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                            bueno = true;
                        }
                    } while (bueno);

                } else {

                    do {
                        bueno = false;
                        try {
                            do {
                                numero = Byte.parseByte(JOptionPane.showInputDialog(null, "Número de Variables que contendrá el Segmento " + (k + 1) + " del Proceso " + (i + 1), "Total", JOptionPane.QUESTION_MESSAGE));

                                if (numero <= 0) {
                                    JOptionPane.showMessageDialog(null, "Ingresó un número de procesos erróneo", "Procesos", JOptionPane.ERROR_MESSAGE);
                                }
                            } while (numero <= 0);

                        } catch (HeadlessException | NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Ha ingresado algún valor que generó un conflicto \n" + e.getMessage() + "\n Favor de ingresar un valor correcto", "ERROR GENERAL", JOptionPane.ERROR_MESSAGE);
                            bueno = true;
                        }
                    } while (bueno);
                }

                cuantos[o] = numero;

                if (tipo[o].equalsIgnoreCase("PÁGINA")) {
                    cadenota += "\n --> El Segmento " + (k + 1) + " tiene " + cuantos[o] + " página(s) de 2 Kb c/u";
                } else {
                    cadenota += "\n --> El Segmento " + (k + 1) + " contiene " + cuantos[o] + " variable(s) de 1 Kb c/u";
                }

                k++;
                o++;

            } while (k < proc[i]);
            cadenota += "\n";
        }
        JOptionPane.showMessageDialog(null, cadenota, "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);
        String[][] TMP = creacionTMP(cuantos);
        llenadoRAM(tipo, proc, cuantos, RAM, TMS, ñ, TMP);

        return TMP;
    }

    //Método para llenar la RAM con los procesos seleccionados por los usuarios
    public static void llenadoRAM(String[] tipo, byte[] proc, Byte[] cuantos, String RAM[], int TMS[][], int h, String[][] TMP) {
        int s = 0, p, contador = 0, q, o, pagina;
        byte r;

        //try {
        for (int i = 0; i < proc.length; i++) {
            o = 0;
            p = 0;

            do {
                switch (tipo[s]) {
                    case "PÁGINA":
                        q = 0;
                        r = 0;
                        pagina = cuantos[s] * 2;

                        do {
                            for (int j = contador; j < RAM.length; j++) {
                                if (RAM[j].equalsIgnoreCase("---------")) {
                                    RAM[j] = "P" + (r + 1) + " S" + (o + 1) + " P" + (i + 1) + " ";
                                    contador = j;
                                    break;
                                }
                            }

                            if (q % 2 != 0) {
                                r++;
                            }

                            q++;
                        } while (q < pagina);

                        break;

                    case "VARIABLE":
                        q = 0;

                        do {
                            for (int j = contador; j < RAM.length; j++) {
                                if (RAM[j].equalsIgnoreCase("---------")) {
                                    RAM[j] = "V" + (q + 1) + " S" + (o + 1) + " P" + (i + 1) + " ";
                                    contador = j;
                                    break;
                                }
                            }
                            q++;
                        } while (q < cuantos[s]);
                        break;
                }
                o++;
                s++;
                p++;
            } while (p < proc[i]);

            //Meter Las Tablas a la RAM
            insercionTablas(proc[i], i, RAM, cuantos);
            llenadoTMS(TMS, h, tipo, cuantos);
        }

        /*} catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudieron ingresar todos los valores dentro de la memora RAM\n Existe un OverFlow\n" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }*/
        //llenadoTMP(cuantos, RAM, tipo, proc, i, TMP);        
    }

    //Método para crear la matriz TMP
    public static String[][] creacionTMP(Byte cuantos[]) {
        int sum = 0;

        for (int i = 0; i < cuantos.length; i++) {
            sum += cuantos[i];
        }

        String[][] TMP = new String[sum][4];

        for (int i = 0; i < TMP.length; i++) {
            for (int j = 0; j < 4; j++) {
                TMP[i][j] = "0";
            }
        }

        return TMP;
    }

    //Método para  incersión de las tablas a la RAM
    public static void insercionTablas(byte proc, int p, String[] RAM, Byte[] cuantos) {
        int k = 0;

        do {
            for (int j = 0; j < RAM.length; j++) {
                if (RAM[j].equalsIgnoreCase("---------")) {
                    RAM[j] = "E" + (k + 1) + " TMS P" + (p + 1);
                    break;
                }
            }
            k++;
        } while (k < proc);
    }

    //Método para creación y llenado de las tablas TMP
    public static void llenadoTMP(Byte[] cuantos, String[] RAM, String[] tipo, byte[] proc, int numero, String[][] TMP) {

    }

    //Método para llenar la Tabla de Mapa de Segmentos
    public static int[][] llenadoTMS(int[][] TMS, int h, String[] tipo, Byte[] cuantos) {

        for (int i = 0; i < h; i++) {
            TMS[i][0] = 1;
            TMS[i][1] = 0;

            if (tipo[i].equalsIgnoreCase("VARIABLE")) {
                TMS[i][2] = cuantos[i] / 2;
            } else {
                TMS[i][2] = cuantos[i];
            }

        }

        return TMS;
    }

    //Método para desplegar TMS
    public static void desplegadoTMS(int[][] TMS, int h, byte proc[], String TMP[][], Byte[] cuantos) {
        int k = 0, sum, proceso = 0, cuenta = proc[0], f = 0;

        sum = proc[0];

        System.out.println("\n\n------------IMPRESIÓN TMS------------");
        for (int i = 0; i < h; i++) {
            if (i == 0) {
                proceso = i + 1;
                System.out.println("\nTMS P" + proceso);
            } else {
                if (i == sum) {
                    proceso = k + 2;
                    System.out.println("\nTMS P" + proceso);

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

    //Método para Imprimir el TMP
    public static void desplegadoTMP(String[][] TMP, byte[] proc, Byte[] cuantos) {
        int fin = 0, segmento = 1, k = 0, sum = 0, proceso = 0, plus = 0;

        sum = cuantos[0];
        plus = proc[0];

        for (int i = 0; i < cuantos.length; i++) {
            fin += cuantos[i];
        }

        System.out.println("\n\n------------IMPRESIÓN TMP------------");
        for (int i = 0; i < fin; i++) {
            if (i == 0) {
                System.out.println("\nTMP S" + segmento + " P" + (proceso + 1));

            } else {
                if (i == sum) {
                    if (k == (plus - 1)) {
                        proceso++;
                        segmento = 0;
                        plus += proc[proceso];
                    }
                    segmento++;
                    System.out.println("\nTMP S" + segmento + " P" + (proceso + 1));
                    k++;
                    sum += cuantos[k];
                }
            }

            for (int j = 0; j < 4; j++) {
                System.out.print("  " + TMP[i][j]);
            }
            System.out.println();
        }
    }

    //Método para llenar espacios de RAM con espacios Ocupados
    public static String[] OcupadosRAM(String[] RAM, float ocupado, int tamaño) {
        int a = 0, i = 0, correcto, random;
        boolean encontrado = false;
        float decimal;

        correcto = (int) (tamaño * (ocupado / 100));
        decimal = (tamaño * (ocupado / 100));

        if (Math.abs(decimal - correcto) >= 0.5) {
            correcto++;
        }

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

        System.out.println("\n\n------------IMPRESIÓN RAM------------");
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
