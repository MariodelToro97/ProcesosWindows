package procesos.windows;

import javax.swing.JOptionPane;

/**
 * @author Mario Josue del Toro Morales
 */
public class ProcesosWindows {
    
    int RAM[];

    public static void main(String[] args) {
        int opcion;
        
        opcion = JOptionPane.showOptionDialog(null, "SELECCIONE OPCIÓN", "MENU PRINCIPAL", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"INICIAR PROCESO", "SALIR"}, null);
        
        if (opcion == 0) {
            JOptionPane.showMessageDialog(null, "Hola Mundo", "Titulo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            
        }
    }
    
}
