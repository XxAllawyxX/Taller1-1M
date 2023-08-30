import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InterfazCaluladora extends JFrame {
    private JTextField campoEntrada; //. Este componente permite a los usuarios ingresar texto, como números, palabras u otro tipo de datos de texto
    private JButton[] NumeroBotones;
    private JButton Suma, Resta, Multiplicacion, Division, Igual, Limpiar, Seno , Coseno , Tangente;
    private double numero1, numero2, resultado;
    private char operador;

    public InterfazCaluladora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(5, 4, 10, 10));

        campoEntrada = new JTextField();
        campoEntrada.setFont(new Font("Arial", Font.PLAIN, 24));
        campoEntrada.setHorizontalAlignment(JTextField.RIGHT);
        campoEntrada.setEditable(false);

        NumeroBotones = new JButton[10];
        for (int i = 0; i < 10; i++) {
            NumeroBotones[i] = new JButton(String.valueOf(i));
            NumeroBotones[i].setFont(new Font("Arial", Font.PLAIN, 24));
            NumeroBotones[i].addActionListener(new NumberButtonListener());
        }

        Suma = crearBotonOperador("+");
        Resta = crearBotonOperador("-");
        Multiplicacion = crearBotonOperador("*");
        Division = crearBotonOperador("/");
        Igual = crearBotonOperador("=");
        Limpiar = crearBotonOperador("C");
        Seno = crearBotonOperador("Sen");
        Coseno = crearBotonOperador("Cos");
        Tangente = crearBotonOperador("Tan");


        add(campoEntrada);

        for (int i = 1; i <= 9; i++) {
            add(NumeroBotones[i]);
        }

        add(Limpiar);
        add(NumeroBotones[0]);
        add(Igual);
        add(Suma);
        add(Resta);
        add(Multiplicacion);
        add(Division);
        add(Seno);
        add(Coseno);
        add(Tangente);

        pack();
        setLocationRelativeTo(null);
    }
    //Esta función se encarga de crear y configurar un botón de operador (como +, -, *, /) en la 
    //calculadora GUI, y lo devuelve listo para ser agregado a la interfaz gráfica. El oyente 
    //de acción que se agrega al botón se encargará de manejar las acciones cuando el usuario haga clic en ese botón específico.

    private JButton crearBotonOperador(String etiqueta) {
        JButton boton = new JButton(etiqueta);
        boton.setFont(new Font("Arial", Font.PLAIN, 24));
        boton.addActionListener(new ButtonListenerOperator());
        return boton;
    }

    //Esta clase actua como un oyente de eventos para los botones numericos y se encarga de 
    //actualizar el campo de entrada de la calculadora cuando el usuario hace clic en ellos.
    private class NumberButtonListener  implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton boton = (JButton) e.getSource();
            campoEntrada.setText(campoEntrada.getText() + boton.getText());
        }
    }

    private class ButtonListenerOperator implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            //Estas líneas de código se utilizan para determinar qué operador se seleccionó haciendo 
            //clic en el botón y asignarlo a la variable op como un carácter. Luego, ese operador 
            //se utiliza más adelante en el código para realizar la operación correspondiente cuando 
            //se hace clic en el botón de igual (=) para calcular el resultado.  
            JButton boton = (JButton) e.getSource();
            char op = boton.getText().charAt(0);

            if (op == '=') {
                numero2 = Double.parseDouble(campoEntrada.getText());
                switch (operador) {
                    case '+':
                        resultado = numero1 + numero2;
                        break;
                    case '-':
                        resultado = numero1 - numero2;
                        break;   
                    case '*':
                        resultado = numero1 * numero2;
                        break;
                    case '/':
                        if (numero2 != 0) {
                            resultado = numero1 / numero2;
                        } else {
                            resultado = 0; // Manejo de división por cero
                        }
                        break;
                }
                campoEntrada.setText(String.valueOf(resultado));
            } else if (op == 'C') {
                campoEntrada.setText("");
                numero1 = numero2 = resultado = 0;
                operador = '\0';
            } else {
                numero1 = Double.parseDouble(campoEntrada.getText());
                campoEntrada.setText("");
                operador = op;
            }
        }
    }

    //Se Crea una instancia de la calculadora ,configura su visibilidad y asegura que toda esta 
    //operación se realice en el hilo de eventos de Swing para garantizar un funcionamiento adecuado de la interfaz grafica.
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> {  //Ademas garantiza la consistencia y evita problemas de concurrencia.
            InterfazCaluladora calc = new InterfazCaluladora();
            calc.setVisible(true);
        });
}
}
